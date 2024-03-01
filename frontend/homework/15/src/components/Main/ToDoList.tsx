import Header from '../Header/Header';
import AddItem from '../Header/AddItem';
import Items from '../Items/Items';
import './toDoList.scss'
import { useSelector } from 'react-redux';
import { RootState } from '../../redux/Store';


export function ToDoList() {
    const {todos, searchItems} = useSelector((state : RootState) => state.todos);
    return (
        <>
            <Header/>
            <div className="main">
                <AddItem />
                <Items items={searchItems.length > 0 ? searchItems : todos} />
            </div>
        </>
    );
}


