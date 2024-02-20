import React, { useEffect, useState } from 'react'
import './toDoList.scss';
import Header from './Header';
import AddItem from './AddItem';
import Items from './Items';

export interface ListItem {
    id: number;
    text: string;
}

export function ToDoList() {
    const [items, setItems] = useState<ListItem[]>([]);
    const [searchItems, setSearchItems] = useState<ListItem[]>(items);
    const [nextId, setNextId] = useState<number>(1);
    const [isSearch, setIsSearch] = useState<boolean>(false);

    const addItem = (item : string): void => {
        setItems([...items, { id: nextId, text: item }]);
        setNextId(nextId + 1);
    };

    const searchFunc = (searchStr : string) : void => {
        if(searchStr.length == 0){
            setIsSearch(false); return;
        }
        setIsSearch(true);
        const searchList = items.filter((item) => {
            return item.text.startsWith(searchStr);
        })
        setSearchItems(searchList);
    }

    const onDelete = (id: number) => {
        setItems(items.filter(item => item.id !== id));
    };

    useEffect(() => {

    },[])

    return (
        <>
            <Header searchFunc={searchFunc}/>

            <div className="main">
                <AddItem addItem={addItem} />
                {
                    isSearch ? <Items items={searchItems} onDelete={onDelete}/> : <Items items={items} onDelete={onDelete}/>
                }
            </div>
        </>
    )
}
