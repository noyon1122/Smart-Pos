import React from 'react'
import walton from '../../assets/images/waltonforum.png'
const Login = () => {
  return (
    <div className="flex flex-col items-center justify-center 
                    
                   bg-gradient-to-b from-blue-100 via-white to-white pt-20">

      {/* Title */}
      <h1 className="text-4xl font-bold text-blue-900 mb-5 tracking-wide drop-shadow">
        Point of Sales
      </h1>

      {/* Login Box */}
      <div className="bg-gray-200 p-6 rounded shadow-lg w-[420px]">
        <div className="bg-white p-6 shadow-inner border rounded">
          
          <div >
             <img  src={walton} alt="Walton Logo" className="h-12 mx-auto" />
          </div>
          {/* Username */}
          <label className="block float-start mb-1 text-sm font-bold text-gray-700">
            Username:
          </label>
          <input
            type="text"
            placeholder="Enter your username"
            className="w-full border px-2 py-2 rounded mb-2 text-sm focus:outline-none "
          />

          {/* Password */}
          <label className="block float-start  mb-1 text-sm font-bold text-gray-700">
            Password:
          </label>
          <input
            type="password"
            placeholder="Enter your password"
            className="w-full border px-2 py-2 text-sm rounded mb-1 focus:outline-none "
          />

          {/* Remember me */}
          <div className="flex items-center text-sm mb-3">
            <input type="checkbox" id="remember" className="mr-2" />
            <label htmlFor="remember" className="text-gray-700">
              Remember me
            </label>
          </div>

          {/* Login Button */}
          <div className="flex justify-end">
            <button
              className="w-24 py-1.5 rounded text-white font-medium text-sm 
                         bg-gradient-to-b from-sky-400 to-sky-700 shadow-md hover:opacity-90">
              Login
            </button>
          </div>

        </div>
      </div>
    </div>
  )
}

export default Login
