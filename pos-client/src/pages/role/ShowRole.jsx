import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { getRoleById } from '../../services/api';
import { format } from 'date-fns';

const ShowRole = () => {
 const { id } = useParams();
  const navigate = useNavigate();
  const [role, setRole] = useState(null);

  useEffect(() => {
    const fetchRole = async () => {
      try {
        const res = await getRoleById(id);
        setRole(res);
      } catch (error) {
        console.error("Failed to load menu:", error);
      }
    };

    fetchRole();
  }, [id]);

  if (!role) return <div className="text-center mt-10">Loading...</div>;

  const Row = ({ label, value }) => (
    <div className="grid grid-cols-3 gap-4 py-1 text-sm">
      <div className="text-right font-medium text-gray-600">{label}</div>
      <div className="col-span-2 text-left">{value ?? "-"}</div>
    </div>
  );

  return (
    <div className="mx-40 my-8 bg-white shadow-md rounded p-6">
      <div className="max-w-3xl mx-auto">

        <Row label="Authority" value={role.authority} />
        <Row label="Description" value={role.description} />
        <Row label="Created" value={format(new Date(role.created), 'dd-MM-yyyy HH:mm')} />  
        <Row label="Created By" value={role.createdBy} />

        {/* ✅ CONDITIONAL FIELDS */}
        {role.modified && (
          <Row label="Modified" value={format(new Date(role.modified), 'dd-MM-yyyy HH:mm')} />
        )}

        {role.modifiedBy && (
          <Row label="Modified By" value={role.modifiedBy} />
        )}

        {/* Action Buttons */}
        <div className="flex gap-4 mt-6 ml-32">
          <button
            onClick={() => navigate(`/role/update/${role.id}`)}
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

export default ShowRole