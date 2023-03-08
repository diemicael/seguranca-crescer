import "./index.css";

export const Button = ({ children, ...props }) => {
  return (
    <button {...props} className={`button ${props.className}`}>
      {children}
    </button>
  );
};
