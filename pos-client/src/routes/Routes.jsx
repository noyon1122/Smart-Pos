import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import Home from "../components/home/Home";
import Login from "../pages/auth/Login";
import Registration from "../pages/auth/Registration";

import CreateMenu from "../pages/menus/CreateMenu";
import PrivateRoutes from "./PrivateRoutes";
import Unauthorized from "../pages/Unauthorized";

import ListMenu from "../pages/menus/ListMenu";


const router=createBrowserRouter([

    {
        path:'/',
        element:<App></App>,
        children:[
           
            {
                element:<PrivateRoutes />,
                children:[
                    { path: "menu/create", element:<CreateMenu /> },
                    { path: "menu/list", element: <ListMenu />}
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