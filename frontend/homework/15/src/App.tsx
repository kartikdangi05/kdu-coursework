import { Provider } from 'react-redux';
import './App.css';
import { ToDoList } from './components/Main/ToDoList';
import { store } from './redux/Store';

function App() {
  return (
    <Provider store={store}>
      <ToDoList/>
    </Provider>
  )
}

export default App;
