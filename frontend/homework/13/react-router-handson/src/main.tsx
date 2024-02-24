import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import { ECommerceProvider } from './context/ECommerceContext.tsx'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <ECommerceProvider>
    <App />
  </ECommerceProvider>
)
