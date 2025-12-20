import React, { useEffect, useState } from 'react'
import { createContext } from 'react'
import { getMyMenu } from '../services/api';

export const AuthContext=createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  const login = async (token) => {
    localStorage.setItem('token', token);
  };

    const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setUser(null);
  };


  const myAuth = async () => {
    try {
    const me = await getMyMenu();
    setUser(me);
    localStorage.setItem("user", JSON.stringify(me));
  } catch (e) {
    logout(); // ⬅️ THIS IS CRITICAL
  } finally {
    setLoading(false);
  }
  };

 /* ---------------- INIT AUTH ---------------- */
  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      setUser(null);
      setLoading(false);
      return;
    }

    myAuth();
  }, []);


  return (
    <AuthContext.Provider
      value={{ user, loading, login, logout, myAuth }}
    >
      {children}
    </AuthContext.Provider>
  );
};



export default AuthProvider;