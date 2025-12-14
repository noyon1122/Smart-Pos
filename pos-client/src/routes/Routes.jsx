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
import TestMenu from "../pages/menus/TestMenu";
import UpdateUser from "../pages/users/UpdateUser";
const router=createBrowserRouter([

    {
        path:'/',
        element:<App></App>,
        children:[
           
            {
                element:<PrivateRoutes />,
                children:[
                    { path: "menu/create", element:<CreateMenu /> },
                    { path: "test/url", element:<TestMenu /> },
                    { path: "menu/list", element: <ListMenu />},
                    { path: "menu/update/:id", element: <UpdateMenu /> },
                    { path: "menu/show/:id", element: <ShowMenu /> },
                    { path: "user/create", element: <CreateUser />},
                    { path: "user/update/:id", element: <UpdateUser />},
                    { path: "user/list", element: <ListUser />},
                    { path: "role/create", element: <CreateRole />},
                    { path: "role/list", element: <ListRole />},
                    { path: "requestmap/create", element: <CreatePermission />},
                    { path: "requestmap/list", element: <ListPermission />},
                ] 
           }
           
        ]
    },

     {
            path:'/login',
                element:<Login></Login>
            },
    
    { path: "/unauthorized", element: <Unauthorized /> },
])

export default router;