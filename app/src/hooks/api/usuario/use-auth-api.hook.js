import { axiosInstance } from "../_base/axios-instance";

export const useAuth = () => {
  async function login({ username, password }) {
    const response = await axiosInstance.post(
      "/login",
      {},
      {
        auth: {
          username,
          password,
        },
      }
    );

    return response.data;
  }

  async function registrar(body) {
    const response = await axiosInstance.post("/usuarios", body);

    return response.data;
  }

  async function editarPerfil(body) {
    const response = await axiosInstance.put("/usuarios", body);

    return response.data;
  }

  async function logout() {
    const response = await axiosInstance.post("/logout");

    return response.data;
  }

  async function getMe() {
    const response = await axiosInstance.get("/usuarios/me");

    return response.data;
  }

  async function forgotPassword(email) {
    const response = await axiosInstance.post(
      "/usuarios/forgot-password/publico",
      email
    );

    return response.data;
  }

  async function changePassword(senha, token) {
    const response = await axiosInstance.post(
      `/usuarios/change-password/${token}/publico`,
      senha
    );

    return response.data;
  }

  return {
    login,
    registrar,
    logout,
    getMe,
    editarPerfil,
    forgotPassword,
    changePassword,
  };
};
