import { toast } from "react-toastify";
import { useGlobalUser } from "../../../contexts/user.context";
import { useAuth } from "../../../hooks";
import { Button, Input } from "../../components";
import "./index.css";

export const HomeScreen = () => {
  const [user, setUser] = useGlobalUser();
  const { logout, editarPerfil } = useAuth();

  function handleChange({ target }) {
    const { name, value } = target;

    setUser((oldUser) => ({ ...oldUser, [name]: value }));
  }

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const userGlobal = await editarPerfil({
        nome: user.nome,
        telefone: user.telefone,
        foto: user.foto,
      });
      setUser(userGlobal);
      toast.success("Perfil foi atualizado com sucesso.");
    } catch (error) {}
  }

  async function handleClickLogout() {
    await logout();
    setUser(null);
  }

  return (
    <section className="home-screen__container">
      <div className="home-screen__wrapper">
        <Button onClick={handleClickLogout}>Sair</Button>
        <div className="home-screen__card">
          <img src={user.foto} alt="" />
          <span>Nome: {user.nome}</span>
          <span>Telefone: {user.telefone}</span>
          <span>Email: {user.email}</span>
        </div>

        <form onSubmit={handleSubmit}>
          <h1>Edite seu Perfil</h1>
          <Input
            type="text"
            placeholder="Nome"
            id="nome"
            name="nome"
            onChange={handleChange}
            value={user.nome}
          />
          <Input
            type="text"
            placeholder="Telefone"
            id="telefone"
            name="telefone"
            onChange={handleChange}
            value={user.telefone}
          />
          <Input
            type="text"
            placeholder="Foto de Perfil"
            id="foto"
            name="foto"
            onChange={handleChange}
            value={user.foto}
          />
          <Button>Concluir</Button>
        </form>
      </div>
    </section>
  );
};
