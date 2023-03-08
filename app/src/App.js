import { RouterProvider } from "react-router-dom";
import { GlobalUserProvider } from "./contexts/user.context";
import { router } from "./routes";
import { ToastContainer } from "react-toastify";
import { injectStyle } from "react-toastify/dist/inject-style";
import "./App.css";

function App() {
  return (
    <>
      {injectStyle()}
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />

      <GlobalUserProvider>
        <div className="App">
          <RouterProvider router={router} />
        </div>
      </GlobalUserProvider>
    </>
  );
}

export default App;
