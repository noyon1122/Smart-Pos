import axios from "axios";

const api=axios.create({
    baseURL:'http://localhost:8080/api',
})

api.interceptors.request.use((config)=>{
    const token=localStorage.getItem("token");
    console.loglog("JWT token: ",token);
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

  const token = response.data.accessToken;
  if (!token || token.split('.').length !== 3) {
    throw new Error("Invalid token format from backend.");
  }

  return { token: token, user: response.data.user };
};

export const logout=async()=>{
  localStorage.removeItem('token');
  localStorage.removeItem('user')
}
 