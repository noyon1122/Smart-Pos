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


const router=createBrowserRouter([

    {
        path:'/',
        element:<App></App>,
        children:[
           
            {
                element:<PrivateRoutes />,
                children:[
                    { path: "menu/create", element:<CreateMenu /> },
                    { path: "menu/list", element: <ListMenu />},
                    { path: "user/create", element: <CreateUser />},
                    { path: "user/list", element: <ListMenu />},
                    { path: "role/create", element: <CreateRole />},
                    { path: "requestmap/create", element: <CreatePermission />},
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