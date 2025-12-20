import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { getUserById } from '../../services/api';
import { format } from 'date-fns';

const ShowUser = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [user, setUser] = useState(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const res = await getUserById(id);
        setUser(res);

      } catch (error) {
        console.error("Failed to load user:", error);
      }
    };

    fetchUser();
  }, [id]);
  console.log(" user from show page : ", user)
  if (!user) return <div className="text-center mt-10">Loading...</div>;

  const Row = ({ label, value }) => (
    <div className="grid grid-cols-3 gap-4 py-1 text-sm">
      <div className="text-right font-medium text-gray-600">{label}</div>
      <div className="col-span-2 text-left">{value ?? "-"}</div>
    </div>
  );

  return (
    <div className="mx-40 my-8 bg-white shadow-md rounded p-6">
      <div className="max-w-3xl mx-auto">

        <Row label="Full Name" value={user.fullName} />
        <Row label="Username" value={user.username} />
        <Row label="Email" value={user.email} />
        <Row
          label="Roles"
          value={
            user.authorities?.length
              ? user.authorities.map(r => r.authority).join(", ")
              : "-"
          }
        />
        <Row
          label="Plaza"
          value={user.plazas ? user.plazas.name : "-"}
        />
        <Row label="Created" value={format(new Date(user.created), 'dd-MM-yyyy HH:mm')} />
        <Row label="Created By" value={user.createdBy} />

        {/* ✅ CONDITIONAL FIELDS */}
        {user.modified && (
          <Row label="Modified" value={format(new Date(user.modified), 'dd-MM-yyyy HH:mm')} />
        )}

        {user.modifiedBy && (
          <Row label="Modified By" value={user.modifiedBy} />
        )}
        <Row label="Enabled" value={user.enabled ? "Yes" : "No"} />
        <Row label="Account Expired" value={user.accountExpired ? "Yes" : "No"} />
        <Row label="Account Locked" value={user.accountLocked ? "Yes" : "No"} />
        <Row label="Password Expired" value={user.passwordExpired ? "Yes" : "No"} />
        {/* Action Buttons */}
        <div className="flex gap-4 mt-6 ml-32">
          <button
            onClick={() => navigate(`/user/update/${user.id}`)}
            className="px-6 py-1 rounded text-white bg-orange-500 hover:bg-orange-600 shadow"
          >
            Edit
          </button>

          <button
            className="px-6 py-1 rounded text-white bg-red-600 hover:bg-red-700 shadow"
          >
            Delete
          </button>
        </div>

      </div>
    </div>
  );
}

export default ShowUser