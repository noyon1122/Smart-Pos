import React, { useEffect, useState } from 'react'
import { createContext } from 'react'

export const AuthContext=createContext(null);

export const AuthProvider =({children})=>{
    const [user,setUser]=useState(null);

    useEffect(()=>{
        const token=localStorage.getItem('token');
        const storedUser=localStorage.getItem('user');

        if(token && token.split('.').length==3 && storedUser){
            try {
                setUser(JSON.parse(storedUser));
            } catch (e) {
                console.error("Failed to parse stored user",e);
            }
        } else{
            console.error("No valid token or user found");
        }

    },[]);
    const login=(token,user)=>{
        localStorage.setItem('token',token);
        localStorage.setItem('user',JSON.stringify(user));
        setUser(user);
    };

    const logout=()=>{
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        setUser(null);
    }

    const authInfo={
        user,setUser,login,logout
    }
    return <AuthContext.Provider value={authInfo}>{children}</AuthContext.Provider>
}

export default AuthProvider;