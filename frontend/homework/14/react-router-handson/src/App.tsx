import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Mutable from './components/Mutable'
import ScrollToTop from './components/ScrollToTop'
import Focus from './components/Focus'
import Timer from './components/Timer'

function App() {

  return (
    <BrowserRouter>
    <Routes>
      <Route path="/1" element={<Mutable />}/>
      <Route path="/2" element={<ScrollToTop/>}/>
      <Route path="/3" element={<Focus />}/>
      <Route path="/4" element={<Timer />}/>
    </Routes>
  </BrowserRouter>
  )
}

export default App
