import axios from "axios";
import { API_URL } from "../../../core/constants";

export const axiosInstance = axios.create({
  baseURL: API_URL,
  timeout: 5000,
  withCredentials: true,
});
