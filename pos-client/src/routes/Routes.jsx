import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import Login from "../pages/auth/Login";
import CreateMenu from "../pages/menus/CreateMenu";
import PrivateRoutes from "./PrivateRoutes";
import Unauthorized from "../pages/Unauthorized";
import ListMenu from "../pages/menus/ListMenu";
import CreateUser from "../pages/users/CreateUser";
import CreateRole from "../pages/role/CreateRole";
import CreatePermission from "../pages/requestmap/CreatePermission";
import ListRole from "../pages/role/ListRole";
import ListUser from "../pages/users/ListUser";
import ListPermission from "../pages/requestmap/ListPermission";
import UpdateMenu from "../pages/menus/UpdateMenu";
import ShowMenu from "../pages/menus/ShowMenu";
import UpdateUser from "../pages/users/UpdateUser";
import ShowUser from "../pages/users/ShowUser";
import UpdateRole from "../pages/role/UpdateRole";
import UpdatePermission from "../pages/requestmap/UpdatePermission";
import ShowPermission from "../pages/requestmap/ShowPermission";
const router = createBrowserRouter([

    {
        path: '/',
        element: <App></App>,
        children: [

            {
                element: <PrivateRoutes />,
                children: [
                    { path: "menu/create", element: <CreateMenu /> },
                    { path: "menu/list", element: <ListMenu /> },
                    { path: "menu/update/:id", element: <UpdateMenu /> },
                    { path: "menu/show/:id", element: <ShowMenu /> },
                    { path: "user/create", element: <CreateUser /> },
                    { path: "user/update/:id", element: <UpdateUser /> },
                    { path: "user/show/:id", element: <ShowUser /> },
                    { path: "user/list", element: <ListUser /> },
                    { path: "role/create", element: <CreateRole /> },
                    { path: "role/update/:id", element: <UpdateRole /> },
                    { path: "role/list", element: <ListRole /> },
                    { path: "requestmap/create", element: <CreatePermission /> },
                    { path: "requestmap/list", element: <ListPermission /> },
                    { path: "requestmap/update/:id", element: <UpdatePermission /> },
                    { path: "requestmap/show/:id", element: <ShowPermission /> },
                   
                ]
            }

        ]
    },

    {
        path: '/login',
        element: <Login></Login>
    },

    { path: "/unauthorized", element: <Unauthorized /> },
])

export default router;