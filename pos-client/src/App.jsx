import { useState } from 'react'

import './App.css'
import { Navbar } from './components/Navbar'
import { Outlet } from 'react-router-dom'
import Footer from './components/Footer'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className='bg-gradient-to-b from-blue-100 via-white to-white '>
      <Navbar></Navbar>
      <Outlet></Outlet>
      <Footer></Footer>
        
    </div>
  )
}

export default App
