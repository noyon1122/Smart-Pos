import React, { useState } from 'react'
import walton from '../assets/images/waltonforum.png'
import useAuth from '../hooks/useAuth';
import { useNavigate } from 'react-router-dom';
export const Navbar = () => {
    
  const {user,logout}=useAuth();
  console.log("user: ",user);
  const navigate=useNavigate();
  const handleLogout = () => {
    logout();
    console.log("log out");
    navigate('/login');
  };
    const [openDropdown, setOpenDropdown] = useState(null);
   const menuItems = [
    { name: "Home", link: "#" },
    {
      name: "System",
      submenu: ["Manage User", "Roles", "Settings"],
    },
    {
      name: "Purchase",
      submenu: ["New Order", "Suppliers", "Reports"],
    },
    {
      name: "Inventory",
      submenu: ["Stock", "Products", "Categories"],
    },
    {
      name: "Sales",
      submenu: ["New Sale", "Invoices", "Reports"],
    },
    {
      name: "Account",
      submenu: ["Profile", "Billing", "History"],
    },
    {
      name: "List",
      submenu: ["Customers", "Vendors", "Products"],
    },
    {
      name: "Approved",
      submenu: ["Pending", "Approved", "Rejected"],
    },
    {
      name: "Knowledge",
      submenu: ["Articles", "Guides", "FAQs"],
    },
  ];

  const toggleDropdown = (name) => {
    setOpenDropdown(openDropdown === name ? null : name);
  };
  return (
    <div>

        {/* Header */}
      <div className="mx-20 p-4 flex justify-between items-center ">
        <div className="flex items-center gap-4">
          <img src={walton} alt="Walton Logo" className="h-14" />
          <h1 className="text-xl font-bold"></h1>
        </div>
        <div className="text-right">
          <p className="font-bold">Welcome!</p>
          <p className="text-sm font-medium">{user?.fullName || "Guest"}</p>
          <p className='text-green-600 font-medium text-xl'> POS-এর যেকোনো সাপোর্টের জন্য 01678-028193 নম্বরে কল করুন।</p>
          <div className="text-blue-600 text-sm">
            <span className="cursor-pointer mr-2">Change Password</span>|
             <button
            onClick={handleLogout}
            
          >
            <span className="cursor-pointer ml-2">Logout</span>
          </button>
           
          </div>
        </div>
      </div>

       <nav className="bg-black text-white font-semibold shadow-md">
      <div className="flex justify-center mx-auto px-4">
        <div className="flex h-8 items-center">
        
          {/* Desktop Menu */}         
           <div className="hidden md:flex space-x-6 items-center">
            {menuItems.map((item) => (
              <div key={item.name} className="relative border-r px-3">
                {!item.submenu ? (
                  <a href={item.link} className="">
                    {item.name}
                  </a>
                ) : (
                  <>
                    <button
                      onClick={() => toggleDropdown(item.name)}
                      className="flex items-center hover:text-gray-200 focus:outline-none"
                    >
                      {item.name}
                      <svg
                        className="ml-1 w-4 h-4"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="M19 9l-7 7-7-7"
                        ></path>
                      </svg>
                    </button>

                    {openDropdown === item.name && (
                      <div className="absolute mt-2 w-40 bg-black text-white rounded-md shadow-lg z-50">
                        {item.submenu.map((subItem) => (
                          <a
                            key={subItem}
                            href="#"
                            className="block px-4 py-1 mb-0.5 border-b"
                          >
                            {subItem}
                          </a>
                        ))}
                      </div>
                    )}
                  </>
                )}
              </div>
            ))}
          </div>

          {/* Mobile Menu Button */}
          <div className="md:hidden">
            <button
              onClick={() => toggleDropdown("mobile")}
              className="focus:outline-none"
            >
              <svg
                className="w-6 h-6"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M4 6h16M4 12h16M4 18h16"
                />
              </svg>
            </button>
          </div>
        </div>

        {/* Mobile Menu */}
        {openDropdown === "mobile" && (
          <div className="md:hidden mt-2 space-y-2 px-2 pb-4">
            {menuItems.map((item) => (
              <div key={item.name} className="relative">
                {!item.submenu ? (
                  <a
                    href={item.link}
                    className="block py-2 px-3  rounded"
                  >
                    {item.name}
                  </a>
                ) : (
                  <>
                    <button
                      onClick={() => toggleDropdown(item.name)}
                      className="w-full text-left py-2 px-3 rounded flex justify-between items-center"
                    >
                      {item.name}
                      <span>▼</span>
                    </button>
                    {openDropdown === item.name && (
                      <div className="pl-4 mt-1 space-y-1">
                        {item.submenu.map((subItem) => (
                          <a
                            key={subItem}
                            href="#"
                            className="block py-1 px-3  rounded"
                          >
                            {subItem}
                          </a>
                        ))}
                      </div>
                    )}
                  </>
                )}
              </div>
            ))}
          </div>
        )}
      </div>
    </nav>
    </div>
  )
}
