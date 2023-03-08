import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useGlobalUser } from "../../../contexts/user.context";
import { useAuth } from "../../../hooks/";
import { Button, Input } from "../../components";
import "./index.css";

const INITIAL_DATA = { username: "", password: "" };
const INITIAL_CADASTRO = {
  nome: "",
  telefone: "",
  email: "",
  senha: "",
  foto: "",
};

export const LoginScreen = () => {
  const [userInfo, setUserInfo] = useState(INITIAL_DATA);
  const [cadastro, setCadastro] = useState(INITIAL_CADASTRO);
  const [user, setUser] = useGlobalUser();
  const [classe, setClasse] = useState(false);
  const { login, registrar } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (user) navigate("/");
  }, [navigate, user]);

  function handleChangeLogin({ target }) {
    const { name, value } = target;

    setUserInfo((oldUserInfo) => ({ ...oldUserInfo, [name]: value }));
  }

  function handleChangeCadastro({ target }) {
    const { name, value } = target;

    setCadastro((oldCadastro) => ({ ...oldCadastro, [name]: value }));
  }

  async function handleLogin(event) {
    event.preventDefault();

    try {
      const userGlobal = await login({
        username: userInfo.username,
        password: userInfo.password,
      });
      setUser(userGlobal);
    } catch (error) {}
  }

  async function handleCadastro(event) {
    event.preventDefault();

    try {
      await registrar(cadastro);
      setClasse(!classe);
      toast.success("Cadastro criado com sucesso.");
    } catch (error) {}
  }

  function handleClasse() {
    setClasse(!classe);
  }

  return (
    <section className="login-screen__container">
      <div className={`container ${classe && "right-panel-active"}`}>
        <div className="form-container sign-up-container">
          <form onSubmit={handleCadastro} className="form">
            <h1>Crie um Perfil</h1>

            <Input
              type="text"
              placeholder="Nome"
              id="nome"
              name="nome"
              onChange={handleChangeCadastro}
              value={cadastro.nome}
            />
            <Input
              type="text"
              placeholder="Telefone"
              id="telefone"
              name="telefone"
              onChange={handleChangeCadastro}
              value={cadastro.telefone}
            />
            <Input
              type="email"
              placeholder="Email"
              id="email"
              name="email"
              onChange={handleChangeCadastro}
              value={cadastro.email}
            />
            <Input
              type="password"
              placeholder="Senha"
              id="senha"
              name="senha"
              onChange={handleChangeCadastro}
              value={cadastro.senha}
            />
            <Input
              type="text"
              placeholder="Foto de Perfil"
              id="foto"
              name="foto"
              onChange={handleChangeCadastro}
              value={cadastro.foto}
            />
            <Button>Concluir</Button>
          </form>
        </div>
        <div className="form-container sign-in-container">
          <form onSubmit={handleLogin} className="form">
            <h1>Login</h1>
            <Input
              type="text"
              placeholder="Login"
              id="username"
              name="username"
              onChange={handleChangeLogin}
              value={userInfo.username}
            />
            <Input
              type="password"
              placeholder="Senha"
              id="password"
              name="password"
              onChange={handleChangeLogin}
              value={userInfo.password}
            />
            <Button>Entrar</Button>
          </form>
        </div>
        <div className="overlay-container">
          <div className="overlay">
            <div></div>
            <div className="overlay-panel overlay-left">
              <h1>Bem Vindo de Volta!</h1>
              <p>
                Para se manter conectado conosco, faça o login com suas
                informações pessoais
              </p>
              <Button className="ghost" onClick={handleClasse}>
                Login
              </Button>
            </div>
            <div className="overlay-panel overlay-right">
              <h1>Olá, Chef!</h1>
              <p>
                Aqui você pode compartilhar sua receitas com amigos, favoritar
                as que mais gostar!
              </p>
              <Button className="ghost" onClick={handleClasse}>
                Cadastre-se
              </Button>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
