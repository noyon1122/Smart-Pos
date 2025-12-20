import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { useNavigate, useParams } from 'react-router-dom';
import { getRoleById, updateRole } from '../../services/api';

const UpdateRole = () => {
  const { register, handleSubmit, reset } = useForm();
  const [role, setRole] = useState(null);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (!role) return;

    reset({
      authority: role.authority || "",
      description: role.description || ""
    });
  }, [role, reset]);

  // Fetch all role
  useEffect(() => {
    const fetchRole = async (id) => {
      try {
        const roleResponse = await getRoleById(id);
        setRole(roleResponse)
        console.log("role response : ", roleResponse)
      } catch (error) {
        console.error("Error fetching role:", error);
      }
    };


  fetchRole(id);
  }, [id]);

  // Submit handler
  const onSubmit = async (data) => {
    const payload = {
      authority: data.authority,
      description: data.description,

    };

    console.log("Final Payload:", payload);

    try {
      await updateRole(payload,id);
      alert("Role updated successfully!");
      reset();
      navigate('/role/list')
    } catch (error) {
      console.error("Error updating role:", error);
      alert("Failed to update role.");
    }
  };

  return (
    <div className="shadow-md mx-40 mb-6">
      <div className="max-w-lg mx-auto mt-10 p-4">
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">

          {/* Title */}
          <div className="flex items-center gap-3">
            <label className="w-32 text-right text-sm">Authority</label>
            <input
              type="text"
              {...register("authority", { required: true })}
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

          <div className="text-start">
            <button
              className="w-24 py-1.5 rounded text-white font-medium text-sm
                 bg-gradient-to-b from-sky-400 to-sky-700 shadow-md hover:opacity-90"
            >
              Update
            </button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default UpdateRole