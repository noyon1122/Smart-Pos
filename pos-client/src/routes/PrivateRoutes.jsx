import { Navigate, Outlet, useLocation } from "react-router-dom";

const PrivateRoutes = () => {
  const token = localStorage.getItem("token");
  const allowedUrls = JSON.parse(localStorage.getItem("allowedUrls")) || [];

  const location = useLocation();
  let path = location.pathname;

  // remove trailing slash
  if (path !== "/" && path.endsWith("/")) {
    path = path.slice(0, -1);
  }

  if (!token) return <Navigate to="/login" replace />;

  const hasAccess = allowedUrls.some(url => path.startsWith(url));

  if (!hasAccess) {
    console.log("BLOCKED PATH:", path);
    console.log("ALLOWED:", allowedUrls);
    return <Navigate to="/unauthorized" replace />;
  }

  return <Outlet />;
};

export default PrivateRoutes;
