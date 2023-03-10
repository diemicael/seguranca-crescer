import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "../../../hooks";
import { Button, Input } from "../../components";

export const ChangePasswordScreen = () => {
  const [senha, setSenha] = useState("");
  const [loading, setLoading] = useState(false);
  const { changePassword } = useAuth();
  const navigate = useNavigate();
  const { token } = useParams();

  function handleChange({ target }) {
    setSenha(target.value);
  }

  async function handleClick() {
    try {
      setLoading(true);
      await changePassword({ password: senha }, token);
      setLoading(false);
      navigate("/");
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <section className="forgot-password-screen__container">
      <div className="forgot-password-screen__wrapper">
        <h2>Informe uma nova Senha</h2>
        <Input
          type="password"
          placeholder="Password"
          id="nome"
          name="nome"
          onChange={handleChange}
          value={senha}
          required
        />
        <Button onClick={handleClick}>Enviar</Button>
      </div>
    </section>
  );
};
