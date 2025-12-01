import React, { useState } from "react";

const NestedMenu = ({ menu, isChild = false }) => {
  const [open, setOpen] = useState(false);

  const hasChildren = menu.children && menu.children.length > 0;

  return (
    <div
      className="relative "
      onMouseEnter={() => setOpen(true)}
      onMouseLeave={() => setOpen(false)}
    >
      {/* MAIN ITEM */}
      {hasChildren ? (
        // Parent menu (dropdown)
        <button className="w-full text-left px-4 flex items-center  justify-between hover:bg-black">
          <span>{menu.title}</span>
          <span className="ml-2 text-sm">
            {isChild ? "▶" : "▼"}
          </span>
        </button>
      ) : (
        // Leaf menu with URL
        <a
          href={menu.urlPath}
          className="block px-4 text-sm hover:bg-black "
        >
          {menu.title}
        </a>
      )}

      {/* DROPDOWN */}
      {hasChildren && open && (
        <div
          className={`absolute bg-[#383838] text-white text-sm min-w-48 border-b shadow-lg z-50 rounded-sm
          ${isChild ? "top-0 left-full" : "top-full left-0"}`}
        >
          {menu.children.map((child) => (
             <div 
        key={child.id}
        className="border-b border-gray-300 min-w-48 h-7"
      >
        <NestedMenu menu={child} isChild={true} />
      </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default NestedMenu;
