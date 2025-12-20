import axios from "axios";

const api=axios.create({
    baseURL:'http://localhost:8080/api',
})

api.interceptors.request.use((config)=>{
    const token=localStorage.getItem("token");
    console.log("JWT token: ",token);
    if (token && !config.url.includes("/auth/login")) {
    config.headers.Authorization = `Bearer ${token}`;
  }
    return config;
})

export const register=async(userData) => {
  const response=await api.post('/auth/register',userData)
  return response.data;

}

export const loginApi = async (loginData) => {
  const response = await api.post('/auth/login', loginData);
  const token = response.data.accessToken;

  if (!token || token.split('.').length !== 3) {
    throw new Error("Invalid token format from backend.");
  }

  // store token immediately
  localStorage.setItem("token", token);

  return { token}; // return user too
};

export const logout=async()=>{
  localStorage.removeItem('token');
  localStorage.removeItem('user')
}

// menu api
export const menusApi=async()=>{
  const response=await api.get('/menus')
  return response.data;
}

export const createMenu = async (menuData) => {
  const response = await api.post("/menu/create", menuData);
  return response.data;
};

export const getMyMenu=async()=>{
  const response=await api.get("/auth/me");
  console.log(response.data)
  return response.data;
}
export const getMenuById = async (id) => {
  const response = await api.get(`/menu/update/${id}`);
  return response.data;
};

export const updateMenu =async(id,updateData)=>{
  const response=await api.post(`/menu/update/${id}`,updateData);
  return response.data;
}

export const hiMenusApi =async ()=>{
  const response=await api.get("/hiMenus");
  return response.data;
}

//role api
export const rolesApi=async()=>{
  const response= await api.get("/roles");
  return response.data;
}

export const createRole =async (roleData) =>{
  const response =await api.post("/role/create",roleData);
  return response.data;
}

//user api
export const usersApi= async ()=>{
  const response=await api.get("/users");
  return response.data;
}
export const createUser=async(userData) =>{
  const response =await api.post("/user/create",userData)
  return response.data;
}
export const getUserById =async(id)=>{
  const response=await api.get(`user/${id}`);
  return response.data;
}

export const updateUser =async(updateUser,id)=>{
  const response=await api.post(`/user/update/${id}`,updateUser);
  return response.data;
}

// Request map api
export const requestmapsApi=async ()=>{
  const response =await api.get("/requestmaps");
  return response.data;
}
export const getRequestmapById=async(id)=>{
  const response=await api.get(`/requestmap/${id}`)
  return response.data;
}
export const createRequestmap=async(permissionData)=>{
  const response =await api.post("/requestmap/create",permissionData)
  return response.data;
}
export const updateRequestmap=async(permissionData,id)=>{
  const response =await api.post(`/requestmap/update/${id}`,permissionData);
  return response.data;
}

//plaza api
export const plazasApi=async()=>{
  const response =await api.get("/plazas")
  return response.data;
}
 