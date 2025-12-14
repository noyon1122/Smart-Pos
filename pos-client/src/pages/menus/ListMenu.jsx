import React, { useEffect, useState } from "react";
import {  menusApi } from "../../services/api";
import { useNavigate } from "react-router-dom";

const ListMenu = () => {
  const [menus, setMenus] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchMenus();
  }, []);

  const fetchMenus = async () => {
    try {
      const res = await menusApi();
      console.log("menus : ",res)
      setMenus(res); // If API returns {data: []} then use res.data
    } catch (error) {
      console.error("Error fetching menus:", error);
    }
  };

  return (
    <div className="shadow-md mx-10 mb-10 mt-6 p-4">
      <h2 className="text-xl font-semibold mb-4">Menu List</h2>

      <div className="overflow-auto border rounded">
        <table className="w-full text-left border-collapse">
          <thead>
            <tr className="bg-linear-to-t bg-sky-900 to-sky-400  text-white text-sm">
              <th className="border p-2">Title</th>
              <th className="border p-2">Parent Menu</th>
              <th className="border p-2">Url Path</th>
              <th className="border p-2">Description</th>
              <th className="border p-2">Menu Type</th>
              <th className="border p-2 text-center">Action</th>
            </tr>
          </thead>

          <tbody>
            {menus.length === 0 && (
              <tr>
                <td colSpan="6" className="text-center p-4 text-gray-500">
                  No menus found.
                </td>
              </tr>
            )}

            {menus.map((menu) => (
              <tr key={menu.id} className="text-sm border hover:bg-gray-50">
                <td className="border p-2">{menu.title}</td>
                <td className="border p-2">{menu.parentMenu?.title || ''}</td>
                <td className="border p-2">{menu.urlPath}</td>
                <td className="border p-2">{menu.description}</td>
                <td className="border p-2">{menu.menuType}</td>

                {/* Action Column */}
                <td className="border p-2 text-center">
                  <div className="flex items-center justify-center gap-3">

                    {/* Edit button */}
                    <button
                      className="p-1 hover:opacity-80"
                      onClick={() => navigate(`/menu/show/${menu.id}`)}
                    >
                      <img
                        src="/icons/show.png"
                        alt="Edit"
                        className="w-5 h-5"
                      />
                    </button>

                    {/* Edit button */}
                    <button
                      className="p-1 hover:opacity-80"
                      onClick={() => navigate(`/menu/update/${menu.id}`)}
                    >
                      <img
                        src="/icons/edit.png"
                        alt="View"
                        className="w-5 h-5"
                      />
                    </button>

                  </div>
                </td>

              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListMenu;
