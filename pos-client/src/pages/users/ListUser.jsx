import React, { useEffect, useState } from 'react'
import { usersApi } from '../../services/api';

const ListUser = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const res = await usersApi();
      console.log("users : ", res)
      setUsers(res);
    } catch (error) {
      console.error("Error fetching users:", error);
    }
  };

  return (
    <div className="shadow-md mx-10 mb-10 mt-6 p-4">
      <h2 className="text-xl font-semibold mb-4">User List</h2>

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

export default ListUser