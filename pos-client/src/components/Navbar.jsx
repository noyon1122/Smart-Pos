import React from 'react'
import walton from '../assets/images/waltonforum.png'
export const Navbar = () => {
  return (
    <div>

        {/* Header */}
      <div className="mx-20 p-4 flex justify-between items-center mb-6">
        <div className="flex items-center gap-4">
          <img src={walton} alt="Walton Logo" className="h-14" />
          <h1 className="text-xl font-bold"></h1>
        </div>
        <div className="text-right">
          <p className="font-bold">Welcome!</p>
          <p className="">Md Noyon Hossain</p>
          <p className='text-green-600 font-medium text-xl'> POS-এর যেকোনো সাপোর্টের জন্য 01678-028193 নম্বরে কল করুন।</p>
          <div className="text-blue-600 text-sm">
            <span className="cursor-pointer mr-2">Change Password</span>|
            <span className="cursor-pointer ml-2">Logout</span>
          </div>
        </div>
      </div>
    </div>
  )
}
