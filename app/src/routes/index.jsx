import { createBrowserRouter } from "react-router-dom";
import { PrivateRoute } from "./private-router.component";
import { LoginScreen } from "../ui/screens";
import { HomeScreen } from "../ui/screens/home/home.screen";

export const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginScreen />,
  },
  {
    path: "/",
    element: <PrivateRoute Screen={HomeScreen} />,
  },
]);
