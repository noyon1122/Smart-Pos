import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import { createMenu, menusApi } from "../../services/api";

const CreateMenu = () => {
  const { register, handleSubmit, reset } = useForm();
  const [parentMenus, setParentMenus] = useState([]);

  // Fetch existing menus from backend
  useEffect(() => {
    const fetchParentMenus = async () => {
      try {
        const response = await menusApi() // replace with your API
        setParentMenus(response);
      } catch (error) {
        console.error("Error fetching menus:", error);
      }
    };

    fetchParentMenus();
  }, []);

  // Recursive function to render hierarchical dropdown options
  const renderMenuOptions = (menus, prefix = "") => {
    return menus.map((menu) => (
      <React.Fragment key={menu.id}>
        <option value={menu.id}>
          {prefix + menu.title}
        </option>
        {menu.children && menu.children.length > 0
          ? renderMenuOptions(menu.children, prefix + menu.title + " > ")
          : null}
      </React.Fragment>
    ));
  };

  const onSubmit = async (data) => {
    try {
      
      await createMenu(data); // replace with your API
      alert("Menu created successfully!");
      reset(); // clear form
    } catch (error) {
      console.error("Error creating menu:", error);
      alert("Failed to create menu.");
    }
  };

  return (
    <div className="shadow-md mx-40 mb-6">

   
    <div className="max-w-lg mx-auto mt-10 p-4">
      
  <form onSubmit={handleSubmit(onSubmit)} className="space-y-4 ">

  {/* Title */}
  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Title</label>
    <input
      type="text"
      {...register("title", { required: true })}
      className="flex-1 border  px-3 py-[3px] rounded"
    />
  </div>

  {/* Description */}
  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Description</label>
    <input
      type="text"
      {...register("description")}
      className="flex-1 border  px-3 py-[3px] rounded"
    />
  </div>

  {/* URL */}
  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">URL</label>
    <input
      type="text"
      {...register("url", { required: true })}
      className="flex-1 border  px-3 py-[3px] rounded"
    />
  </div>

  {/* Menu Class */}
  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Menu Class</label>
    <input
      type="text"
      {...register("menuClass")}
      className="flex-1 border  px-3 py-[3px] rounded"
    />
  </div>

  {/* Menu Type */}
  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Menu Type</label>
    <input
      type="text"
      {...register("menuType")}
      defaultValue="MAIN_MENU"
      readOnly
      className="flex-1 border  px-3 py-[3px] rounded bg-gray-100"
    />
  </div>

  {/* Parent Menu */}
  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Parent Menu</label>
    <select
      {...register("parentMenu")}
      className="flex-1 border   px-[3px] py-[3px] rounded"
    >
      <option value="">Select parent menu</option>
      {renderMenuOptions(parentMenus)}
    </select>
  </div>

  {/* Checkboxes */}
  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Is External</label>
    <input type="checkbox" {...register("isExternal")} />
  </div>

  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Open New Tab</label>
    <input type="checkbox" {...register("isOpenNewTab")} />
  </div>

  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Is Active</label>
    <input type="checkbox" {...register("isActive")} />
  </div>

  {/* Sort Order */}
  <div className="flex items-center gap-3">
    <label className="w-32 text-right text-sm">Sort Order</label>
    <input
      type="number"
      {...register("sortOrder")}
      className="flex-1 border  px-3 py-[3px] rounded"
    />
  </div>

 <div className="text-start">
    <button
    className="w-24 py-1.5 rounded text-white font-medium text-sm 
               bg-gradient-to-b from-sky-400 to-sky-700 shadow-md hover:opacity-90"
  >
    Save
  </button>
 </div>

</form>


    </div>
     </div>
  );
};

export default CreateMenu;
