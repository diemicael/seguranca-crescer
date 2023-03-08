import { useGlobalUser } from "../contexts/user.context";
import { Navigate } from "react-router-dom";

export const PrivateRoute = ({ Screen }) => {
  const [user] = useGlobalUser();

  if (user) {
    return <Screen />;
  }

  return <Navigate to="/login" />;
};
