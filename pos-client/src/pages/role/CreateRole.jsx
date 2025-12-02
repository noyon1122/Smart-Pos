import React from 'react'
import { useForm } from 'react-hook-form';
import { createRole } from '../../services/api';

const CreateRole = () => {
 const { register, handleSubmit, reset } = useForm();

 
   // Submit handler
   const onSubmit = async (data) => {
     const payload = {
       authority: data.authority,
       description: data.description,
      
     };
 
     console.log("Final Payload:", payload);
 
     try {
       await createRole(payload);
       alert("Role created successfully!");
       reset();
     } catch (error) {
       console.error("Error creating role:", error);
       alert("Failed to create role.");
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
               Save
             </button>
           </div>
 
         </form>
       </div>
     </div>
   );
 };

export default CreateRole