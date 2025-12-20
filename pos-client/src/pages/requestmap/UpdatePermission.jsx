import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { useNavigate, useParams } from 'react-router-dom';
import { getRequestmapById, rolesApi, updateRequestmap,  } from '../../services/api';

const UpdatePermission = () => {
    const { register, handleSubmit, reset } = useForm();
    const [roles, setRoles] = useState([]);
    const [requestmap,setRequestmap]=useState(null)
    const navigate = useNavigate();
    const {id}=useParams();

    useEffect(() => {
  if (!requestmap) return;

  reset({
    url: requestmap.url || "",
    roles: requestmap.configAttribute
      ? requestmap.configAttribute.split(",")
      : []
  });
}, [requestmap, reset]);

    // Fetch all role
    useEffect(() => {
        const fetchRoles = async () => {
            try {
                const rolesResponse = await rolesApi();

                setRoles(rolesResponse);

            } catch (error) {
                console.error("Error fetching role:", error);
            }
        };

        const fetchRequestmap = async (id) => {
                try {
                    const requestmapResponse = await getRequestmapById(id);
                    setRequestmap(requestmapResponse)
                    console.log("request mapResponse : ", requestmapResponse)

                } catch (error) {
                    console.error("Error fetching mapResponse:", error);
                }
            };
            
        fetchRequestmap(id);
        fetchRoles();
        
    }, [id]);

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
            url: data.url,
            configAttribute: data.roles ? data.roles.join(",") : ""
        };
        console.log("Final Payload:", payload);

        try {
            await updateRequestmap(payload, id);
            alert("Url permission updated successfully!");
            reset();
            navigate('/requestmap/list')

        } catch (error) {
            console.error("Error updating permission:", error);
            alert("Failed to update permission.");
        }
    };

    return (
        <div className="shadow-md mx-40 mb-6">
            <div className="max-w-lg mx-auto mt-10 p-4">
                <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">

                    {/* Title */}
                    <div className="flex items-center gap-3">
                        <label className="w-32 text-right text-sm">Url Path</label>
                        <input
                            type="text"
                            {...register("url", { required: true })}
                            className="flex-1 border px-3 py-[3px] rounded"
                        />
                    </div>

                    {/* Parent Menu */}
                    <div className="flex items-start gap-3">
                        <label className="w-32 text-right text-sm mt-1">Roles</label>

                        <div className="border rounded p-2 flex-1 h-28 overflow-y-auto bg-white shadow-sm">
                            {roles.map((r) => (
                                <label key={r.id} className="flex items-center gap-2 py-1">
                                    <input
                                        type="checkbox"
                                        value={r.authority}
                                        {...register("roles")}
                                    />
                                    <span className="text-sm">{r.authority}</span>
                                </label>
                            ))}
                        </div>
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

export default UpdatePermission