import axios from "axios";

const api=axios.create({
    baseURL:'http://localhost:8080/api',
})

api.interceptors.request.use((config)=>{
    const token=localStorage.getItem("token");
    console.log("JWT token: ",token);
    if(token){
        config.headers.Authorization=`Bearer ${token}`;
    }
    return config;
})

export const register=async(userData) => {
  const response=await api.post('/auth/register',userData)
  return response.data;

}

export const loginApi = async (loginData) => {
  const response = await api.post('/auth/login', loginData);
   console.log("Login response:", response.data);
  const token = response.data.accessToken;
  if (!token || token.split('.').length !== 3) {
    throw new Error("Invalid token format from backend.");
  }

  return { token: token, user: response.data.userDto };
};

export const logout=async()=>{
  localStorage.removeItem('token');
  localStorage.removeItem('user')
}

export const menusApi=async()=>{
  const response=await api.get('/menus')
  return response.data;
}

export const createMenu = async (menuData) => {
  const response = await api.post("/menu/create", menuData);
  return response.data;
};

export const createRole =async (roleData) =>{
  const response =await api.post("/role/create",roleData);
  return response.data;
}

export const rolesApi=async()=>{
  const response= await api.get("/roles");
  return response.data;
}

export const createUser=async(userData) =>{
  const response =await api.post("/user/create",userData)
  return response.data;
}

export const getMyMenu=async()=>{
  const response=await api.get("/auth/me");
  console.log(response.data)
  return response.data;
}

export const plazasApi=async()=>{
  const response =await api.get("/plazas")
  return response.data;
}

export const createPermission=async(permissionData)=>{

  const response =await api.post("/requestmap/create",permissionData)
  return response.data;
}
 