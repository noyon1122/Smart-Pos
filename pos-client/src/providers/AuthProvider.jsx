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

  const myAuth = async () => {
    try {
      const me = await getMyMenu();
      setUser(me);
      localStorage.setItem("user", JSON.stringify(me));
    } catch (e) {
      setUser(null);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      myAuth();
    } else {
      setLoading(false);
    }
  }, []);

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setUser(null);
  };

  return (
    <AuthContext.Provider
      value={{ user, loading, login, logout, myAuth }}
    >
      {children}
    </AuthContext.Provider>
  );
};



export default AuthProvider;