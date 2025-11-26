import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import Home from "../components/home/Home";
import Login from "../pages/auth/Login";
import Registration from "../pages/auth/Registration";

import CreateMenu from "../pages/menus/CreateMenu";


const router=createBrowserRouter([

    {
        path:'/',
        element:<App></App>,
        children:[
            {
                path:'/',
                element:<Home></Home>
            },
            {
                path: "/menu/create",
                element: <CreateMenu />,
            }
           
        ]
    },
     {
        path:'/login',
        element:<Login></Login>
    },
    {
        path:'/register',
        element:<Registration></Registration>

    }
])

export default router;