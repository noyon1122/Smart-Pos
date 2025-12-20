import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getMenuById } from "../../services/api";
import { format } from "date-fns";

const ShowMenu = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [menu, setMenu] = useState(null);

  useEffect(() => {
    const fetchMenu = async () => {
      try {
        const res = await getMenuById(id);
        setMenu(res);
      } catch (error) {
        console.error("Failed to load menu:", error);
      }
    };

    fetchMenu();
  }, [id]);

  if (!menu) return <div className="text-center mt-10">Loading...</div>;

  const Row = ({ label, value }) => (
    <div className="grid grid-cols-3 gap-4 py-1 text-sm">
      <div className="text-right font-medium text-gray-600">{label}</div>
      <div className="col-span-2 text-left">{value ?? "-"}</div>
    </div>
  );

  return (
    <div className="mx-40 my-8 bg-white shadow-md rounded p-6">
      <div className="max-w-3xl mx-auto">

        <Row label="Title" value={menu.title} />
        <Row label="Description" value={menu.description} />
        <Row label="Url Path" value={menu.urlPath} />
        <Row label="Menu Type" value={menu.menuType} />
        <Row
          label="Parent Menu"
          value={
            menu.parentMenu ? (
              <span className="text-blue-600 font-medium">
                {menu.parentMenu.title}
              </span>
            ) : (
              "-"
            )
          }
        />
        <Row label="Is Active" value={menu.isActive ? "True" : "False"} />
        <Row label="Sort Order" value={menu.sortOrder} />
        <Row label="Created" value={format(new Date(menu.created), 'dd-MM-yyyy HH:mm')} />  
        <Row label="Created By" value={menu.createdBy} />

        {/* ✅ CONDITIONAL FIELDS */}
        {menu.modified && (
          <Row label="Modified" value={format(new Date(menu.modified), 'dd-MM-yyyy HH:mm')} />
        )}

        {menu.modifiedBy && (
          <Row label="Modified By" value={menu.modifiedBy} />
        )}

        {/* Action Buttons */}
        <div className="flex gap-4 mt-6 ml-32">
          <button
            onClick={() => navigate(`/menu/update/${menu.id}`)}
            className="px-6 py-1 rounded text-white bg-orange-500 hover:bg-orange-600 shadow"
          >
            Edit
          </button>

          <button
            className="px-6 py-1 rounded text-white bg-red-600 hover:bg-red-700 shadow"
          >
            Delete
          </button>
        </div>

      </div>
    </div>
  );
};

export default ShowMenu;
