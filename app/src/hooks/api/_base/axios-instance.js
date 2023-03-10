import axios from "axios";
import { API_URL } from "../../../core/constants";

export const axiosInstance = axios.create({
  baseURL: API_URL,
  timeout: 2000,
  withCredentials: true,
});
