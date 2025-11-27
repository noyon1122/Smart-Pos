import React, { useState } from 'react'
import walton from '../assets/images/waltonforum.png'
import useAuth from '../hooks/useAuth';
import { useMenus } from "../hooks/useMenus";
import { useNavigate } from 'react-router-dom';
import NestedMenu from '../pages/menus/NestedMenu';
export const Navbar = () => {
    
  const {user,logout}=useAuth();
  console.log("user: ",user);
  const navigate=useNavigate();

  const { menus, loading } = useMenus();

  console.log(menus);
  const [openDropdown, setOpenDropdown] = useState(null);
  const handleLogout = () => {
    logout();
    console.log("log out");
    navigate('/login');
  };
   

  const toggleDropdown = (name) => {
    setOpenDropdown(openDropdown === name ? null : name);
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div className='bg-gradient-to-b from-blue-100 via-white to-white' >

        {/* Header */}
      <div className="mx-28 p-3 flex justify-between items-center ">
        <div className="flex items-center gap-4 ">
          <img src={walton} alt="Walton Logo" className="h-18 " />
          <h1 className="text-xl font-bold"></h1>
        </div>
        <div className="text-right">
          <p className="font-bold text-sm">Welcome!</p>
          <p className="text-xs font-medium">{user?.fullName || "Guest"}</p>
          <p className='text-green-600 font-medium text-lg'> POS-এর যেকোনো সাপোর্টের জন্য 01678-028193 নম্বরে কল করুন।</p>
          <div className="text-blue-600 text-sm">
            <span className="cursor-pointer text-xs font-bold mr-2">Change Password</span>|
             <button
            onClick={handleLogout}
            
          >
            <span className="cursor-pointer ml-2 text-xs font-bold">Logout</span>
          </button>
           
          </div>
        </div>
      </div>

       <nav className="bg-[#383838] text-white font-semibold shadow-md">
      <div className="flex justify-center mx-auto px-4">
        <div className="flex h-8 items-center">
        
          {/* Desktop Menu */}         
          <div className="hidden md:flex items-center">
            {menus.map((menu) => (
              <div 
                key={menu.id} 
                className="relative  border-r"
                onMouseEnter={() => setOpenDropdown(menu.id)}
                onMouseLeave={() => setOpenDropdown(null)}
              >
                {/* Main menu item */}
                <NestedMenu menu={menu} />
              </div>
            ))}
        </div>

        </div>
      </div>
    </nav>
    </div>
  )
}


