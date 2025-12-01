import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { createMenu, menusApi } from "../../services/api";

const CreateMenu = () => {
  const { register, handleSubmit, reset } = useForm();
  const [parentMenus, setParentMenus] = useState([]);

  // Fetch parent menus
  useEffect(() => {
    const fetchParentMenus = async () => {
      try {
        const response = await menusApi();
        setParentMenus(response);
      } catch (error) {
        console.error("Error fetching menus:", error);
      }
    };

    fetchParentMenus();
  }, []);

  // Render hierarchical dropdown
  const renderMenuOptions = (menus, prefix = "") =>
    menus.map((menu) => (
      <React.Fragment key={menu.id}>
        <option value={menu.id}>{prefix + menu.title}</option>

        {menu.children?.length > 0 &&
          renderMenuOptions(menu.children, `${prefix + menu.title} > `)}
      </React.Fragment>
    ));

  // Submit handler
  const onSubmit = async (data) => {
    const payload = {
      title: data.title,
      description: data.description,
      urlPath: data.urlPath,                    // FIXED NAME
      menuClass: data.menuClass,
      menuType: data.menuType || "MAIN_MENU",
      sortOrder: Number(data.sortOrder) || 0,

      isExternal: data.isExternal || false,
      isOpenNewTab: data.isOpenNewTab || false,
      isActive: data.isActive || false,

      parentMenu: data.parentMenu
        ? { id: Number(data.parentMenu) }     // FIXED NESTED OBJECT
        : null,
    };

    console.log("Final Payload:", payload);

    try {
      await createMenu(payload);
      alert("Menu created successfully!");
      reset();
    } catch (error) {
      console.error("Error creating menu:", error);
      alert("Failed to create menu.");
    }
  };

  return (
    <div className="shadow-md mx-40 mb-6">
      <div className="max-w-lg mx-auto mt-10 p-4">
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">

          {/* Title */}
          <div className="flex items-center gap-3">
            <label className="w-32 text-right text-sm">Title</label>
            <input
              type="text"
              {...register("title", { required: true })}
              className="flex-1 border px-3 py-[3px] rounded"
            />
          </div>

          {/* Description */}
          <div className="flex items-center gap-3">
            <label className="w-32 text-right text-sm">Description</label>
            <input
              type="text"
              {...register("description")}
              className="flex-1 border px-3 py-[3px] rounded"
            />
          </div>

          {/* URL Path */}
          <div className="flex items-center gap-3">
            <label className="w-32 text-right text-sm">URL Path</label>
            <input
              type="text"
              {...register("urlPath", { required: true })}
              className="flex-1 border px-3 py-[3px] rounded"
              placeholder=""
            />
          </div>

          {/* Menu Class */}
          <div className="flex items-center gap-3">
            <label className="w-32 text-right text-sm">Menu Class</label>
            <input
              type="text"
              {...register("menuClass")}
              className="flex-1 border px-3 py-[3px] rounded"
            />
          </div>

          {/* Menu Type */}
          <div className="flex items-center gap-3">
            <label className="w-32 text-right text-sm">Menu Type</label>
            <input
              type="text"
              {...register("menuType")}
              defaultValue="MAIN_MENU"
              
              className="flex-1 border px-3 py-[3px] rounded bg-gray-100"
            />
          </div>

          {/* Parent Menu */}
          <div className="flex items-center gap-3">
            <label className="w-32 text-right text-sm">Parent Menu</label>
            <select
              {...register("parentMenu")}
              className="flex-1 border px-[3px] py-[3px] rounded"
            >
              <option value="">Select parent menu</option>
              {renderMenuOptions(parentMenus)}
            </select>
          </div>

          {/* Checkbox Fields */}
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
              className="flex-1 border px-3 py-[3px] rounded"
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
