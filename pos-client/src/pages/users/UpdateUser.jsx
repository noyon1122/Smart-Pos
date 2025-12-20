import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { getUserById, plazasApi, rolesApi, updateUser } from '../../services/api';
import { useNavigate, useParams } from 'react-router-dom';

const UpdateUser = () => {
    const { register, handleSubmit, reset } = useForm();
    const [roles, setRoles] = useState([]);
    const [plazas, setPlazas] = useState([]);
    const [singleUser, setSingleUser] = useState(null)
    const { id } = useParams();
    const navigate=useNavigate()

    console.log("roles : ", roles)
    console.log("existing user : ", singleUser)

  useEffect(() => {
    if (!singleUser || roles.length === 0) return;

    const roleIds = singleUser.userRoles
        ?.map(ur => {
            // 1. Check if the Role ID is nested (Standard fetch)
            if (ur.role?.id) return String(ur.role.id);
            
            // 2. Check if the user object has a flat 'authorities' list (Common in Spring Security)
            // If userRoles is just join-table IDs, we might need to look at 'authorities'
            return null;
        })
        .filter(Boolean) ?? [];

    // If the map above failed because userRoles only contains join-table IDs, 
    // we search the master 'roles' list for matching authorities
    let finalRoleIds = roleIds;
    if (finalRoleIds.length === 0 && singleUser.authorities) {
        const authNames = singleUser.authorities.map(a => a.authority);
        finalRoleIds = roles
            .filter(r => authNames.includes(r.authority))
            .map(r => String(r.id));
    }

    const plazaId = singleUser.plazas?.id ? String(singleUser.plazas.id) : "";

    reset({
        fullName: singleUser.fullName || "",
        username: singleUser.username || "",
        email: singleUser.email || "",
        mobile: singleUser.mobile || "",
        plaza: plazaId, 
        roles: finalRoleIds, // This now uses the actual Role IDs
    });
}, [singleUser, roles, reset]);


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
        const fetchPlazas = async () => {
            try {
                const plazaResponse = await plazasApi();
                setPlazas(plazaResponse)
                console.log("Plaza response: ", plazaResponse)

            } catch (error) {
                console.error("Error fetching plaza:", error);
            }
        };

        const fetchUser = async (id) => {
            try {
                const userResponse = await getUserById(id);
                setSingleUser(userResponse)
                console.log("User response: ", userResponse)

            } catch (error) {
                console.error("Error fetching user:", error);
            }
        };
        fetchUser(id);
        fetchRoles();
        fetchPlazas();
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
            fullName: data.fullName,
            email: data.email,
            mobile: data.mobile,
            username: data.username,
            password: data.password,
            plazas: data.plaza ? { id: Number(data.plaza) } : null,
            roles: data.roles
                ? data.roles.map((id) => Number(id))
                : []

        };

        console.log("Final Payload:", payload);
        try {
            await updateUser(payload,id);
            alert("User data updated successfully!");
            navigate("/user/list")
        } catch (error) {
            console.error("Error updating user:", error);
            alert("Failed to update user.");
        }
    };

    return (
        <div className="shadow-md mx-40 mb-6">
            <div className="max-w-lg mx-auto mt-10 p-4">
                <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">

                    {/* Fullname */}
                    <div className="flex items-center gap-3">
                        <label className="w-32 text-right text-sm">Full Name</label>
                        <input
                            type="text"
                            {...register("fullName", { required: true })}
                            className="flex-1 border px-3 py-[3px] rounded"
                        />
                    </div>

                    {/* Username */}
                    <div className="flex items-center gap-3">
                        <label className="w-32 text-right text-sm">Username</label>
                        <input
                            type="text"
                            {...register("username")}
                            className="flex-1 border px-3 py-[3px] rounded"
                        />
                    </div>

                    {/* Password */}
                    <div className="flex items-center gap-3">
                        <label className="w-32 text-right text-sm">Password</label>
                        <span className='font-semibold'>******</span>
                    </div>
                    {/* New Password */}
                    <div className="flex items-center gap-3">
                        <label className="w-32 text-right text-sm">New Password</label>
                        <input
                            type="text"
                            {...register("password", { required: false })}
                            className="flex-1 border px-3 py-[3px] rounded"
                            placeholder=""
                        />
                    </div>

                    {/* Email */}
                    <div className="flex items-center gap-3">
                        <label className="w-32 text-right text-sm">Email</label>
                        <input
                            type="text"
                            {...register("email")}
                            className="flex-1 border px-3 py-[3px] rounded"
                        />
                    </div>

                    {/* Mobile */}
                    <div className="flex items-center gap-3">
                        <label className="w-32 text-right text-sm">Mobile No</label>
                        <input
                            type="text"
                            {...register("mobile", { required: true })}

                            className="flex-1 border px-3 py-[3px] rounded"
                        />
                    </div>

                    {/* Plaza */}
                    <div className="flex items-center gap-3">
                        <label className="w-32 text-right text-sm">Plaza</label>
                        <select
                            {...register("plaza")}
                            className="flex-1 border px-[3px] py-[3px] rounded"
                        >
                            <option value="">Select Plaza</option>
                            {plazas.map((p) => (
                                <option key={p.id} value={String(p.id)}> {/* Force String */}
                                    {p.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Roles as checkboxes */}
                    <div className="flex items-start gap-3">
                        <label className="w-32 text-right text-sm pt-1">Roles</label>
                        <div className="border rounded p-2 flex-1 h-28 overflow-y-auto bg-white shadow-sm">
                            {roles.map((r) => (
                                <label key={r.id} className="flex items-center gap-2">
                                    <input
                                        type="checkbox"
                                        value={String(r.id)} // Force String
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

export default UpdateUser