import { Navigate, Outlet, useLocation } from "react-router-dom";

const PrivateRoutes = () => {
  const token = localStorage.getItem("token");
  const allowedUrls = JSON.parse(localStorage.getItem("allowedUrls")) || [];
  const location = useLocation();

  let path = location.pathname.replace(/\/$/, ""); // trim trailing slash

  if (!token) return <Navigate to="/login" replace />;

  const hasAccess = allowedUrls.some(url => {
    if (!url || url === "#") return false;

    // handle /** wildcard
    if (url.endsWith("/**")) {
      const base = url.replace("/**", "");
      return path.startsWith(base);
    }

    // exact match
    return path === url;
  });

  if (!hasAccess) {
    console.log("BLOCKED:", path);
    console.log("ALLOWED:", allowedUrls);
    return <Navigate to="/unauthorized" replace />;
  }

  return <Outlet />;
};

export default PrivateRoutes;
