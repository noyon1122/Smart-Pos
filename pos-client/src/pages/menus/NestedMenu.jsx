import React, { useState } from "react";

const NestedMenu = ({ menu, isChild = false }) => {
  const [open, setOpen] = useState(false);

  const hasChildren = menu.children && menu.children.length > 0;

  return (
    <div
      className="relative"
      onMouseEnter={() => setOpen(true)}
      onMouseLeave={() => setOpen(false)}
    >
     {hasChildren ? (
  <button
    className={`
      w-full text-left px-4 py-1.5 flex items-center justify-between hover:bg-black
      ${isChild ? "border-b border-r border-gray-300" : "border-r border-gray-300"}
    `}
  >
    <span>{menu.title}</span>
    <span className="ml-2 text-[10px]">{isChild ? "▶" : "▼"}</span>
  </button>
) : (
  <a
    href={menu.urlPath}
    className={`
      block px-4 py-1.5 text-sm hover:bg-black
      ${isChild ? "border-b border-r border-gray-300" : "border-r border-gray-300"}
    `}
  >
    {menu.title}
  </a>
)}
      {/* DROPDOWN */}
      {hasChildren && open && (
        <div
          className={`absolute bg-[#383838] text-white text-sm min-w-48 shadow-lg z-40 rounded-sm
            ${isChild ? "top-0 left-full" : "top-full left-0"}`}
        >
          {menu.children.map((child) => (
            <NestedMenu key={child.id} menu={child} isChild={true} />
          ))}
        </div>
      )}
    </div>
  );
};

export default NestedMenu;
