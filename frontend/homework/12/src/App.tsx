import './App.css';
import { ToDoList } from './components/Main/ToDoList';
import { TodoProvider } from './components/context/ToDoListContext';

function App() {
  return (
    <TodoProvider>
      <ToDoList/>
    </TodoProvider>
  )
}

export default App;
