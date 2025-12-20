import React, { useEffect, useState } from "react";
import { hiMenusApi, menusApi } from "../../services/api";
import { useNavigate } from "react-router-dom";
import view from "../../../public/images/view.png";
import edit from "../../../public/images/edit.png";
import { useForm } from "react-hook-form";

const ListMenu = () => {
  const { register, handleSubmit, reset } = useForm();
  const [menus, setMenus] = useState([]);
  const [parentMenus, setParentMenus] = useState([]);

  // pagination state
  const [page, setPage] = useState(0);
  const [size] = useState(10);
  const [totalPages, setTotalPages] = useState(0);

  const navigate = useNavigate();

  /* ================= FETCH MENUS ================= */
  const fetchMenus = async (filters = {}, pageNo = 0) => {
    try {
      const params = {
        page: pageNo,
        size,
        parentMenu: filters.parentMenu || undefined,
        title: filters.title || undefined,
        urlPath: filters.urlPath || undefined,
      };

      const res = await menusApi(params);

      setMenus(res.content);
      setTotalPages(res.totalPages);
      setPage(res.number);
    } catch (error) {
      console.error("Error fetching menus:", error);
    }
  };

  /* ================= INITIAL LOAD ================= */
  useEffect(() => {
    fetchMenus();
  }, []);

  /* ================= PARENT MENUS ================= */
  useEffect(() => {
    const fetchParentMenus = async () => {
      try {
        const res = await hiMenusApi();
        setParentMenus(res);
      } catch (error) {
        console.error("Error fetching parent menus:", error);
      }
    };
    fetchParentMenus();
  }, []);

  /* ================= SEARCH ================= */
  const onSearch = (data) => {
    fetchMenus(data, 0);
  };

  /* ================= RESET ================= */
  const onReset = () => {
    reset();
    fetchMenus({}, 0);
  };

  /* ================= PAGINATION ================= */
  const changePage = (newPage) => {
    handleSubmit((data) => fetchMenus(data, newPage))();
  };

  /* ================= DROPDOWN OPTIONS ================= */
  const renderMenuOptions = (menus, prefix = "") =>
    menus.map((menu) => (
      <React.Fragment key={menu.id}>
        <option value={menu.id}>{prefix + menu.title}</option>
        {menu.children?.length > 0 &&
          renderMenuOptions(menu.children, `${prefix + menu.title} > `)}
      </React.Fragment>
    ));

  return (
    <div className="shadow-md mx-10 mb-10 mt-6 p-4">
      <h2 className="text-xl font-semibold mb-4">Menu List</h2>

      {/* ================= SEARCH FORM ================= */}
      <form onSubmit={handleSubmit(onSearch)} className="mb-4">
        <table className="mb-3">
          <tbody>
            <tr className="text-sm">
              <td className="pr-2">Parent Menu:</td>
              <td className="pr-4">
                <select
                  {...register("parentMenu")}
                  className="border px-2 py-1 rounded"
                >
                  <option value="">All</option>
                  {renderMenuOptions(parentMenus)}
                </select>
              </td>

              <td className="pr-2">Title:</td>
              <td className="pr-4">
                <input
                  {...register("title")}
                  className="border px-2 py-1 rounded"
                />
              </td>

              <td className="pr-2">URL Path:</td>
              <td>
                <input
                  {...register("urlPath")}
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

      {/* ================= TABLE ================= */}
      <div className="overflow-auto border rounded">
        <table className="w-full text-left border-collapse">
          <thead>
            <tr className="bg-sky-900 text-white text-sm">
              <th className="border p-2">Title</th>
              <th className="border p-2">Parent Menu</th>
              <th className="border p-2">URL Path</th>
              <th className="border p-2">Description</th>
              <th className="border p-2">Menu Type</th>
              <th className="border p-2 text-center">Action</th>
            </tr>
          </thead>

          <tbody>
            {menus.length === 0 && (
              <tr>
                <td colSpan="6" className="text-center p-4 text-gray-500">
                  No menus found.
                </td>
              </tr>
            )}

            {menus.map((menu) => (
              <tr key={menu.id} className="text-sm border hover:bg-gray-50">
                <td className="border p-2">{menu.title}</td>
                <td className="border p-2">
                  {menu.parentMenu?.title || ""}
                </td>
                <td className="border p-2">{menu.urlPath}</td>
                <td className="border p-2">{menu.description}</td>
                <td className="border p-2">{menu.menuType}</td>
                <td className="border p-2 text-center">
                  <div className="flex justify-center gap-2">
                    <button onClick={() => navigate(`/menu/show/${menu.id}`)}>
                      <img src={view} className="w-5" />
                    </button>
                    <button onClick={() => navigate(`/menu/update/${menu.id}`)}>
                      <img src={edit} className="w-5" />
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
};

export default ListMenu;
