import React, { useEffect, useState } from 'react'
import { rolesApi } from '../../services/api';
import { format } from 'date-fns';
import view from '../../../public/images/view.png'
import edit from '../../../public/images/edit.png'
import { useNavigate } from 'react-router-dom';

const ListRole = () => {
    const [roles, setRoles] = useState([]);
    const navigate=useNavigate();

    useEffect(() => {
        fetchRoles();
    }, []);

    const fetchRoles = async () => {
        try {
            const res = await rolesApi();
            console.log("roles : ", res)
            setRoles(res);
        } catch (error) {
            console.error("Error fetching roles:", error);
        }
    };

    return (
        <div className="shadow-md mx-10 mb-10 mt-6 p-4">
            <h2 className="text-xl font-semibold mb-4">Role List</h2>

            <div className="overflow-auto border rounded">
                <table className="w-full text-left border-collapse">
                    <thead>
                        <tr className="bg-linear-to-t bg-sky-900 to-sky-400  text-white text-sm">
                            <th className="border p-2">Authority</th>
                            <th className="border p-2">Description</th>
                            <th className="border p-2">Created By</th>
                            <th className="border p-2">Created Date</th>
                            <th className="border p-2 text-center">Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        {roles.length === 0 && (
                            <tr>
                                <td colSpan="6" className="text-center p-4 text-gray-500">
                                    No roles found.
                                </td>
                            </tr>
                        )}

                        {roles.map((role) => (
                            <tr key={role.id} className="text-sm border hover:bg-gray-50">
                                <td className="border p-2">{role.authority}</td>
                                <td className="border p-2">{role.description}</td>
                                <td className="border p-2">{role.createdBy}</td>
                                <td className="border p-2">
                                    {format(new Date(role.created), 'dd-MM-yyyy HH:mm')}
                                </td>
                                {/* Action Column */}
                                <td className="border p-2 text-center">
                                    <div className="flex items-center justify-center gap-3">

                                        {/* view button */}
                                        <button
                                            className="p-1 hover:opacity-80"
                                             onClick={() => navigate(`/role/show/${role.id}`)}
                                        >
                                            <img
                                                src={view}
                                                alt="show"
                                                className="w-5 h-5"
                                            />
                                        </button>

                                        {/* Edit button */}
                                        <button
                                            className="p-1 hover:opacity-80"
                                            onClick={() => navigate(`/role/update/${role.id}`)}
                                        >
                                            <img
                                                src={edit}
                                                alt="Edit"
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
}

export default ListRole