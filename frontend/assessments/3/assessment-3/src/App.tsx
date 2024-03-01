import { useEffect } from 'react'
import './App.css'
import Dashboard from './components/Dashboard/Dashboard'
import { useDispatch } from 'react-redux'
import { DispatchType } from './redux/store/Store'
import { fetchRooms } from './redux/Thunk/HotelThunk'

function App() {

  const dispatch = useDispatch<DispatchType>();

useEffect(() => {
  dispatch(fetchRooms());
},[])

  return (
    <Dashboard/>
  )
}

export default App
