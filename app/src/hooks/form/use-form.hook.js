import { useState } from "react";

export function useForm({
  initialData,
  onSubmit,
  validators = {},
  messages = {},
}) {
  const [data, setData] = useState(initialData);

  function handleChange(event) {
    const { name, value } = event.target;

    setData((oldData) => ({
      ...oldData,
      [name]: {
        value,
        hasError: oldData[name].hasError,
        errorMessage: oldData[name].errorMessage,
      },
    }));
  }

  function validateError() {
    const dataWithErrors = Object.entries(data).map(([name, field]) => {
      const validator = validators[name];
      const hasError = validator ? !validator(field.value) : false;

      const message = messages[name] || "Campo obrigatório";

      const newField = {
        value: field.value,
        hasError: !field.value,
        errorMessage: hasError || !field.value ? message : "",
      };

      return [name, newField];
    });

    const hasErrors = dataWithErrors.some(([, field]) => field.hasError);

    if (hasErrors) {
      const newPersonalData = Object.fromEntries(dataWithErrors);

      setData(newPersonalData);
    }

    return hasErrors;
  }

  function handleSubmit(event) {
    event.preventDefault();

    const hasErrors = validateError();

    if (!hasErrors) {
      onSubmit(data);
    }
  }

  return {
    handleChange,
    handleSubmit,
    data,
  };
}
