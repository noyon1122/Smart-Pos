import { useContext } from "react";
import { Navigate, Outlet, useLocation } from "react-router-dom";
import { AuthContext } from "../providers/AuthProvider";
import PageLoader from "../utils/PageLoader";

const PrivateRoutes = () => {
  const { user, loading } = useContext(AuthContext);
  const location = useLocation();

  // wait until auth check finishes
  if (loading) return <PageLoader />;

  // not logged in
 if (!user) {
  return (
    <Navigate
      to="/login"
      replace
      state={{ from: location.pathname }}
    />
  );
}

  const allowedUrls = user.allowedUrls || [];
  const path = location.pathname.replace(/\/$/, "");

  const hasAccess = allowedUrls.some(url => {
    if (!url || url === "#") return false;

    if (url.endsWith("/**")) {
      return path.startsWith(url.replace("/**", ""));
    }

    return path === url;
  });

  if (!hasAccess) {
    console.log("BLOCKED:", path);
    return <Navigate to="/unauthorized" replace />;
  }

  return <Outlet />;
};

export default PrivateRoutes;