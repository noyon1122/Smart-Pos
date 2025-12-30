import React, { useEffect, useState } from 'react'
import { requestmapsApi } from '../../services/api';
import { format } from 'date-fns';
import view from '../../../public/images/view.png'
import edit from '../../../public/images/edit.png'
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';

const ListPermission = () => {
    const [requestmaps, setRequestmaps] = useState([]);
    const { register, handleSubmit, reset } = useForm();
    // pagination state
    const [page, setPage] = useState(0);
    const [size] = useState(10);
    const [totalPages, setTotalPages] = useState(0);
    const navigate = useNavigate();

    useEffect(() => {
        fetchRequestmaps({}, page);
    }, [page]);


    const fetchRequestmaps = async (filters = {}, pageNo = 0) => {
        try {
            const params = {
                page: pageNo,
                size,
                url: filters.url || undefined,
                configAttribute: filters.configAttribute || undefined,
            };
            const res = await requestmapsApi(params);
            console.log("requestmaps : ", res)
            setRequestmaps(res.content || []);
            setTotalPages(res.totalPages);
            setPage(res.number);
        } catch (error) {
            console.error("Error fetching requestmaps:", error);
        }
    };

    /* ================= SEARCH ================= */
    const onSearch = (data) => {
        fetchRequestmaps(data, 0);
    };

    /* ================= RESET ================= */
    const onReset = () => {
        reset();
        fetchRequestmaps({}, 0);
    };

    /* ================= PAGINATION ================= */
    const changePage = (newPage) => {
        handleSubmit((data) => fetchRequestmaps(data, newPage))();
    };

    return (
        <div className="shadow-md mx-10 mb-10 mt-6 p-4">
            <h2 className="text-xl font-semibold mb-4">RequestMap List</h2>

            {/* ================= SEARCH FORM ================= */}
            <form onSubmit={handleSubmit(onSearch)} className="mb-4">
                <table className="mb-3">
                    <tbody>
                        <tr className="text-sm">

                            <td className="pr-2">Url:</td>
                            <td className="pr-4">
                                <input
                                    {...register("url")}
                                    className="border px-2 py-1 rounded"
                                />
                            </td>

                            <td className="pr-2">Config Attribute:</td>
                            <td>
                                <input
                                    {...register("configAttribute")}
                                    className="border px-2 py-1 rounded"
                                />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div className="flex gap-2">
                    <button
                        type="submit"
                        className="px-4 py-1 bg-sky-600 text-white rounded text-sm"
                    >
                        Search
                    </button>
                    <button
                        type="button"
                        onClick={onReset}
                        className="px-4 py-1 bg-gray-500 text-white rounded text-sm"
                    >
                        Reset
                    </button>
                </div>
            </form>

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
                                <td colSpan="5" className="text-center p-4 text-gray-500">
                                    No requestmaps found.
                                </td>
                            </tr>
                        )}

                        {requestmaps.map((requestmap) => (
                            <tr key={requestmap.id} className="text-sm border hover:bg-gray-50">
                                <td className="border p-2">{requestmap.url}</td>
                                <td className="border p-2">{requestmap.configAttribute}</td>
                                <td className="border p-2">
                                    {requestmap.created
                                        ? format(new Date(requestmap.created), 'dd-MM-yyyy HH:mm')
                                        : ''}
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
                                            onClick={() => navigate(`/requestmap/show/${requestmap.id}`)}
                                        >
                                            <img
                                                src={view}
                                                alt="View"
                                                className="w-5 h-5"
                                            />
                                        </button>

                                        {/* View button */}
                                        <button
                                            className="p-1 hover:opacity-80"
                                            onClick={() => navigate(`/requestmap/update/${requestmap.id}`)}
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
            {/* ================= PAGINATION ================= */}
            <div className="flex justify-center gap-2 mt-4">
                <button
                    disabled={page === 0}
                    onClick={() => changePage(page - 1)}
                    className="px-3 py-1 border rounded disabled:opacity-40"
                >
                    Prev
                </button>

                <span className="text-sm pt-1">
                    Page {page + 1} of {totalPages}
                </span>

                <button
                    disabled={page + 1 >= totalPages}
                    onClick={() => changePage(page + 1)}
                    className="px-3 py-1 border rounded disabled:opacity-40"
                >
                    Next
                </button>
            </div>
        </div>
    );
}

export default ListPermission