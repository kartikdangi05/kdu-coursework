import React from "react";
import Item from "./Item";
import { ListItem } from "../context/ToDoListContext";

interface IListItems {
  items: ListItem[];
  onDelete: (id: number) => void;
}

const Items: React.FC<IListItems> = ({ items, onDelete }) => {
  return (
    <div className="items">
      <h2>Items</h2>
      <div className="items-list">
        {items.length === 0 ? (
          'Items list is empty! '
        ) : (
          items.map((item) => (
            <Item key={item.id} text={item.text} onDelete={() => onDelete(item.id)} />
          ))
        )}
      </div>
    </div>
  );
};

export default Items;
