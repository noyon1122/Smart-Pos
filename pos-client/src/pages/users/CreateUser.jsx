import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { createUser, rolesApi } from '../../services/api';

const CreateUser = () => {
   const { register, handleSubmit, reset } = useForm();
    const [roles, setRoles] = useState([]);
  
    // Fetch all role
    useEffect(() => {
      const fetchRoles = async () => {
        try {
          const response = await rolesApi();
          setRoles(response);
        } catch (error) {
          console.error("Error fetching role:", error);
        }
      };
  
      fetchRoles();
    }, []);
  
    // Render hierarchical dropdown
    const renderRoleOptions = (roles, prefix = "") =>
      roles.map((role) => (
        <React.Fragment key={role.id}>
          <option value={role.id}>{prefix + role.authority}</option>
  
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
        await createUser(payload);
        alert("User created successfully!");
        reset();
      } catch (error) {
        console.error("Error creating user:", error);
        alert("Failed to create user.");
      }
    };
  
    return (
      <div className="shadow-md mx-40 mb-6">
        <div className="max-w-lg mx-auto mt-10 p-4">
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">
  
            {/* Title */}
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">Full Name</label>
              <input
                type="text"
                {...register("fullName", { required: true })}
                className="flex-1 border px-3 py-[3px] rounded"
              />
            </div>
  
            {/* Description */}
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">Username</label>
              <input
                type="text"
                {...register("username")}
                className="flex-1 border px-3 py-[3px] rounded"
              />
            </div>
  
            {/* URL Path */}
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">password</label>
              <input
                type="text"
                {...register("password", { required: true })}
                className="flex-1 border px-3 py-[3px] rounded"
                placeholder=""
              />
            </div>
  
            {/* Menu Class */}
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">Email</label>
              <input
                type="text"
                {...register("email")}
                className="flex-1 border px-3 py-[3px] rounded"
              />
            </div>
  
            {/* Menu Type */}
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">Mobile No</label>
              <input
                type="text"
                {...register("mobile", { required: true })}
 
                className="flex-1 border px-3 py-[3px] rounded"
              />
            </div>
  
            {/* Parent Menu */}
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">Plaza</label>
              <select
                {...register("parentMenu")}
                className="flex-1 border px-[3px] py-[3px] rounded"
              >
                <option value="">Select role</option>
                {renderRoleOptions(roles)}
              </select>
            </div>
             {/* Parent Menu */}
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">CSD</label>
              <select
                {...register("parentMenu")}
                className="flex-1 border px-[3px] py-[3px] rounded"
              >
                <option value="">Select role</option>
                {renderRoleOptions(roles)}
              </select>
            </div>

             {/* Parent Menu */}
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">Role</label>
              <select
                {...register("parentMenu")}
                className="flex-1 border px-[3px] py-[3px] rounded"
              >
                <option value="">Select role</option>
                {renderRoleOptions(roles)}
              </select>
            </div>
  
          
            <div className="flex items-center gap-3">
              <label className="w-32 text-right text-sm">Is Active</label>
              <input type="checkbox" {...register("isActive")} />
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

export default CreateUser