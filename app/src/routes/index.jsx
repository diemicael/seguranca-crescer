import { createBrowserRouter } from "react-router-dom";
import { PrivateRoute } from "./private-router.component";
import {
  LoginScreen,
  HomeScreen,
  ForgotPasswordScreen,
  ChangePasswordScreen,
} from "../ui/screens";

export const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginScreen />,
  },
  {
    path: "/",
    element: <PrivateRoute Screen={HomeScreen} />,
  },
  {
    path: "/forgot-password",
    element: <ForgotPasswordScreen />,
  },
  {
    path: "/change-password/:token",
    element: <ChangePasswordScreen />,
  },
]);
