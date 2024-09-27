import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import ECommerce from './components/Main/ECommerce'
import { ECommerceContext } from './context/ECommerceContext'
import ProductPage from './components/ProductPage/ProductPage'
import { useContext, useEffect } from 'react'
import axios, { AxiosResponse } from 'axios'

export default function App() {

  const {setProducts, toggleLoading} = useContext(ECommerceContext);

  const fetchData = async (): Promise<void> => {
    toggleLoading(true);
    try {
      const response: AxiosResponse = await axios.get('https://fakestoreapi.com/products');
      setProducts(response.data);
    } catch (error: unknown) {
      console.error('Failed to fetch data:', error);
    } finally {
      toggleLoading(false);
    }
  };


  useEffect(() => {
    fetchData();
  }, []);

  return (
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<ECommerce/>}/>
          <Route path='/product/:id' element={<ProductPage/>}/>
        </Routes>
      </BrowserRouter>
  )
}
