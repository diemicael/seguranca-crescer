import { useState } from "react";
import { Button, Input } from "../../components";
import { useAuth } from "../../../hooks";
import { toast } from "react-toastify";
import "./index.css";

export const ForgotPasswordScreen = () => {
  const [email, setEmail] = useState("");
  const [loading, setLoading] = useState(false);
  const { forgotPassword } = useAuth();

  function handleChange({ target }) {
    setEmail(target.value);
  }

  async function handleClick() {
    try {
      setLoading(true);
      await forgotPassword({ email: email });
      setLoading(false);
      toast.success("Um email de redefinição de senha foi enviado para você");
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <section className="forgot-password-screen__container">
      <div className="forgot-password-screen__wrapper">
        <h2>Recuperação de Senha</h2>
        <Input
          type="email"
          placeholder="Email"
          id="nome"
          name="nome"
          onChange={handleChange}
          value={email}
          required
        />
        <Button onClick={handleClick}>Enviar</Button>
      </div>
    </section>
  );
};
