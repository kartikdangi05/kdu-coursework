import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import ECommerce from './components/Main/ECommerce'

export default function App() {

  return (
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<ECommerce/>}/>
          <Route path='/product/:id' element={<ECommerce/>}/>
        </Routes>
      </BrowserRouter>
  )
}
