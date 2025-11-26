import { useLocation } from "react-router-dom";

const UrlMapping = () => {
  const location = useLocation();

  return (
    <div>
      <h2>Page for: {location.pathname}</h2>
      {/* Load content depending on pathname */}
    </div>
  );
};

export default UrlMapping;
