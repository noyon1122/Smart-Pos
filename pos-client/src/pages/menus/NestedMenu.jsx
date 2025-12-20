import React, { useState } from "react";

const NestedMenu = ({ menu, isChild = false }) => {
  const [open, setOpen] = useState(false);

  const hasChildren = Array.isArray(menu.children) && menu.children.length > 0;

  return (
    <div
      className="relative"
      onMouseEnter={() => setOpen(true)}
      onMouseLeave={() => setOpen(false)}
    >
      {/* MENU ITEM */}
      {hasChildren ? (
        <button
          type="button"
          className={`
            w-full text-left px-4 py-1.5 flex items-center justify-between
            text-white hover:bg-black
            ${isChild ? "border-b border-r border-gray-300" : "border-r border-gray-300"}
          `}
        >
          <span>{menu.title}</span>

          {/* ✅ WHITE ARROW */}
          <span className="ml-2 text-[10px] text-white">
            <svg
              className="w-5 h-5  fill-current text-white"
              viewBox="0 0 20 20"
            >
              {isChild ? (
                <path d="M7 5l5 5-5 5V5z" /> // Right Arrow
              ) : (
                <path d="M5 7l5 5 5-5H5z" /> // Down Arrow
              )}
            </svg>
          </span>
        </button>
      ) : (
        <a
          href={menu.urlPath}
          className={`
            block px-4 py-1.5 text-sm text-white hover:bg-black
            ${isChild ? "border-b border-r border-gray-300" : "border-r border-gray-300"}
          `}
        >
          {menu.title}
        </a>
      )}

      {/* DROPDOWN */}
      {hasChildren && open && (
        <div
          className={`
            absolute bg-[#383838] text-white text-sm min-w-48
            shadow-lg z-40 rounded-sm
            ${isChild ? "top-0 left-full" : "top-full left-0"}
          `}
        >
          {menu.children.map((child) => (
            <NestedMenu
              key={child.id}
              menu={child}
              isChild={true}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default NestedMenu;
