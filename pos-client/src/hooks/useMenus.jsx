import { useState, useEffect } from "react";
import { menusApi } from "../services/api";
export const useMenus = () => {
  const [menus, setMenus] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchMenus = async () => {
      try {
        const response = await menusApi() // Backend endpoint
        setMenus(response);
      } catch (err) {
        console.error("Failed to fetch menus", err);
      } finally {
        setLoading(false);
      }
    };

    fetchMenus();
  }, []);

  return { menus, loading };
};
