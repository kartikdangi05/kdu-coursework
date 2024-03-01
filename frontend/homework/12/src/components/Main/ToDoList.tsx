import  { useContext } from 'react';
import Header from '../Header/Header';
import AddItem from '../Header/AddItem';
import Items from '../Items/Items';
import { TodoContext } from '../context/ToDoListContext';
import './toDoList.scss'

export function ToDoList() {
    const {
        items,
        searchItems,
        addItem: addItemContext,
        searchFunc: searchFuncContext,
        onDelete: onDeleteContext
    } = useContext(TodoContext);

    const addItemLocal = (item : string) => {
        addItemContext(item);
    };

    const searchFuncLocal = (searchStr : string) => {
        searchFuncContext(searchStr);
    };

    const onDeleteLocal = (id : number) => {
        onDeleteContext(id);
    };

    return (
        <>
            <Header searchFunc={searchFuncLocal} />
            <div className="main">
                <AddItem addItem={addItemLocal} />
                <Items items={searchItems.length > 0 ? searchItems : items} onDelete={onDeleteLocal} />
            </div>
        </>
    );
}
