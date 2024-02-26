import React, { createContext, useEffect, useState } from 'react';

export interface ListItem {
    id: number;
    text: string;
}

interface ChildProps {
    children: React.ReactNode
}

interface TodoContextType {
    items: ListItem[];
    searchItems: ListItem[];
    addItem: (item: string) => void;
    searchFunc: (searchStr: string) => void;
    onDelete: (id: number) => void;
    nextId: number;
}


export const TodoContext = createContext<TodoContextType>({
    items: [],
    searchItems: [],
    addItem: () => {},
    searchFunc: () => {},
    onDelete: () => {},
    nextId: 0,
});


export const TodoProvider = ({ children }: ChildProps) => {

    const [items, setItems] = useState<ListItem[]>([]);
    const [searchItems, setSearchItems] = useState<ListItem[]>([]);
    const [nextId, setNextId] = useState<number>(1);

    const addItem = (item: string): void => {
        setItems([...items, { id: nextId, text: item }]);
        setNextId(nextId + 1);
    };

    const searchFunc = (searchStr: string): void => {
        if (searchStr.length === 0) {
            setSearchItems([]);
            return;
        }
        const searchList = items.filter((item) => item.text.startsWith(searchStr));
        setSearchItems(searchList);
    };

    const onDelete = (id: number) => {
        setItems(items.filter((item) => item.id !== id));
    };

    useEffect(() => {
        setSearchItems(items);
    }, [items]);

    const value: TodoContextType = {
        items,
        searchItems,
        addItem,
        searchFunc,
        onDelete,
        nextId
    };

    return <TodoContext.Provider value={value}>{children}</TodoContext.Provider>;
};
