import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { getUserById, plazasApi, rolesApi } from '../../services/api';
import { useParams } from 'react-router-dom';

const UpdateUser = () => {
    const { register, handleSubmit, reset } = useForm();
    const [roles, setRoles] = useState([]);
    const [plazas, setPlazas] = useState([]);
    const [singleUser, setSingleUser] = useState(null)
    const { id } = useParams();

    console.log("roles : ", roles)
    console.log("existing user : ", singleUser)

    useEffect(() => {
        if (!singleUser || roles.length === 0) return;

        reset({
            fullName: singleUser.fullName ?? "",
            email: singleUser.email ?? "",
            mobile: singleUser.mobile ?? "",
            username: singleUser.username ?? "",
            plaza: singleUser.plazas?.id ?? "",
            roles: singleUser.roles?.map(r => String(r.id)) ?? [],
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
                            {...register("plaza")}
                            className="flex-1 border px-[3px] py-[3px] rounded"
                        >
                            <option value="">Select Plaza</option>
                            {plazas.map((p) => (
                                <option key={p.id} value={p.id}>
                                    {p.name}
                                </option>
                            ))}
                        </select>
                    </div>


                    {/* Parent Menu */}
                    {/* Roles as checkboxes */}
<div className="flex items-start gap-3">
    <label className="w-32 text-right text-sm pt-1">Roles</label>
    <div className="border rounded p-2 flex-1 h-28 overflow-y-auto bg-white shadow-sm">
        {roles.map((r) => (
            <label key={r.id} className="flex items-center gap-2">
                <input
                    type="checkbox"
                    value={String(r.id)} // Use id as value
                    {...register("roles")} // RHF will manage array automatically
                    defaultChecked={singleUser?.roles?.some(role => role.id === r.id)} // Pre-check existing roles
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
                            Save
                        </button>
                    </div>

                </form>
            </div>
        </div>
    );
}

export default UpdateUser