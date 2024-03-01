import { Provider } from 'react-redux';
import './App.css';
import { ToDoList } from './components/Main/ToDoList';
import { store } from './redux/Store';
import { PersistGate } from 'redux-persist/integration/react';
import { persistStore } from 'redux-persist';

const persister = persistStore(store); 

function App() {
  return (
    <Provider store={store}>
      <PersistGate persistor={persister}>
        <ToDoList/>
      </PersistGate>
    </Provider>
  )
}

export default App;