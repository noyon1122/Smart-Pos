import React, { useEffect, useState } from 'react'
import { plazasApi, rolesApi, usersApi } from '../../services/api';
import { useNavigate } from 'react-router-dom';
import view from '../../../public/images/view.png'
import edit from '../../../public/images/edit.png'
import { useForm } from 'react-hook-form';


const ListUser = () => {
  const { register, handleSubmit, reset } = useForm();
  const [users, setUsers] = useState([]);
  const [roles, setRoles] = useState([]);
    const [plazas, setPlazas] = useState([]);

  const navigate=useNavigate()

   // pagination state
    const [page, setPage] = useState(0);
    const [size] = useState(10);
    const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    fetchUsers();
    fetchRoles();
    fetchRPlazas();
  }, []);

  const fetchUsers = async (filters = {}, pageNo = 0) => {
  try {
    const params = {
      page: pageNo,
      size,
      plazas: filters.plazas || undefined,
      role: filters.role || undefined,
      fullName: filters.fullName || undefined,
      username: filters.username || undefined,
    };

    const res = await usersApi(params);

    setUsers(res.content || []);   // 🔥 FIX
    setTotalPages(res.totalPages);
    setPage(res.number);
  } catch (error) {
    console.error("Error fetching users:", error);
  }
};


   const fetchRoles = async () => {
        try {
          const rolesResponse = await rolesApi();
  
          setRoles(rolesResponse);
  
        } catch (error) {
          console.error("Error fetching role:", error);
        }
      };
      const fetchRPlazas = async () => {
        try {
          const plazaResponse = await plazasApi();
          setPlazas(plazaResponse)
          console.log("Plaza response: ", plazaResponse)
  
        } catch (error) {
          console.error("Error fetching plaza:", error);
        }
      };
  
      

    /* ================= SEARCH ================= */
const onSearch = (data) => {
  fetchUsers(data, 0);
};

  /* ================= RESET ================= */
  const onReset = () => {
    reset();
    fetchUsers({}, 0);
  };

  /* ================= PAGINATION ================= */
  const changePage = (newPage) => {
    handleSubmit((data) => fetchUsers(data, newPage))();
  };


  return (
    <div className="shadow-md mx-10 mb-10 mt-6 p-4">
      <h2 className="text-xl font-semibold mb-4">User List</h2>

       {/* ================= SEARCH FORM ================= */}
      <form onSubmit={handleSubmit(onSearch)} className="mb-4">
        <table className="mb-3">
          <tbody>
            <tr className="text-sm">
              <td className="pr-2">Plaza:</td>
              <td className="pr-4">
                <select
                  {...register("plazas")}
                  className="border px-2 py-1 rounded"
                >
                  <option value="">Select Plaza</option>
                   {plazas.map((p) => (
                <option key={p.id} value={p.id}>
                  {p.name}
                </option>
              ))}
                 
                </select>
              </td>
              <td className="pr-2">Role:</td>
              <td className="pr-4">
                <select {...register("role")}

                  className="border px-2 py-1 rounded"
                >
                   <option value="">Select Role</option>
                   {roles.map((r) => (
                <option key={r.id} value={r.id}>
                  {r.authority}
                </option>
              ))}
            
                </select>
              </td>
              </tr>
              <tr>
              <td className="pr-2">username:</td>
              <td>
                <input
                  {...register("username")}
                  className="border px-2 py-1 rounded"
                />
              </td>
               <td className="pr-2">FullName:</td>
              <td>
                <input
                  {...register("fullName")}
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
              <th className="border p-2">Full Name</th>
              <th className="border p-2">Username</th>
              <th className="border p-2">Email</th>
              <th className="border p-2">Plaza</th>
              <th className="border p-2">Enabled?</th>
              <th className="border p-2 text-center">Action</th>
            </tr>
          </thead>

          <tbody>
            {users.length === 0 && (
              <tr>
                <td colSpan="6" className="text-center p-4 text-gray-500">
                  No users found.
                </td>
              </tr>
            )}

            {users.map((user) => (
              <tr key={user.id} className="text-sm border hover:bg-gray-50">
                <td className="border p-2">{user.fullName}</td>
                <td className="border p-2">{user.username}</td>
                <td className="border p-2">{user.email}</td>
                <td className="border p-2">{user.plazas ? user.plazas.plazaName : " "}</td>
                <td className="border p-2">
                  {user.enabled ? "Yes" : "No"}
                </td>
                {/* Action Column */}
                <td className="border p-2 text-center">
                  <div className="flex items-center justify-center gap-3">

                    {/* View button */}
                    <button
                      className="p-1 hover:opacity-80"
                      onClick={() => navigate(`/user/show/${user.id}`)}
                    >
                      <img
                        src={view}
                        alt="Edit"
                        className="w-5 h-5"
                      />
                    </button>

                    {/* Edit button */}
                    <button
                      className="p-1 hover:opacity-80"
                      onClick={() => navigate(`/user/update/${user.id}`)}
                    >
                      <img
                        src={edit}
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

export default ListUser