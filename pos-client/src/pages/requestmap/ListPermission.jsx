import React, { useEffect, useState } from 'react'
import { requestmapsApi } from '../../services/api';
import { format } from 'date-fns';

const ListPermission = () => {
    const [requestmaps, setRequestmaps] = useState([]);

    useEffect(() => {
        fetchRequestmaps();
    }, []);

    const fetchRequestmaps = async () => {
        try {
            const res = await requestmapsApi();
            console.log("requestmaps : ", res)
            setRequestmaps(res);
        } catch (error) {
            console.error("Error fetching requestmaps:", error);
        }
    };

    return (
        <div className="shadow-md mx-10 mb-10 mt-6 p-4">
            <h2 className="text-xl font-semibold mb-4">RequestMap List</h2>

            <div className="overflow-auto border rounded">
                <table className="w-full text-left border-collapse">
                    <thead>
                        <tr className="bg-linear-to-t bg-sky-900 to-sky-400  text-white text-sm">
                            <th className="border p-2">Url</th>
                            <th className="border p-2">Config Attribute</th>
                            <th className="border p-2">Created Date</th>
                            <th className="border p-2">Created By</th>
                            <th className="border p-2 text-center">Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        {requestmaps.length === 0 && (
                            <tr>
                                <td colSpan="6" className="text-center p-4 text-gray-500">
                                    No requestmaps found.
                                </td>
                            </tr>
                        )}

                        {requestmaps.map((requestmap) => (
                            <tr key={requestmap.id} className="text-sm border hover:bg-gray-50">
                                <td className="border p-2">{requestmap.url}</td>
                                <td className="border p-2">{requestmap.configAttribute}</td>
                                <td className="border p-2">
                                    {format(new Date(requestmap.created), 'dd-MM-yyyy HH:mm')}
                                </td>

                                <td className="border p-2">
                                    {requestmap?.createdBy}
                                </td>
                                {/* Action Column */}
                                <td className="border p-2 text-center">
                                    <div className="flex items-center justify-center gap-3">

                                        {/* Edit button */}
                                        <button
                                            className="p-1 hover:opacity-80"
                                            onClick={() => console.log("Edit:", user.id)}
                                        >
                                            <img
                                                src="/icons/edit.png"
                                                alt="Edit"
                                                className="w-5 h-5"
                                            />
                                        </button>

                                        {/* View button */}
                                        <button
                                            className="p-1 hover:opacity-80"
                                            onClick={() => console.log("View:", user.id)}
                                        >
                                            <img
                                                src="/icons/view.png"
                                                alt="View"
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

export default ListPermission