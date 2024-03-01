import React, { useEffect } from "react";
import Item from "./Item";
import { ListItem } from "../context/ToDoListContext";
import { useDispatch, useSelector } from "react-redux";
import { addChecked, deleteChecked, deleteTodo } from "../../redux/Reducer";
import { RootState } from "../../redux/Store";

interface IListItems {
  items: ListItem[];
}

const Items: React.FC<IListItems> = ({ items }) => {

  const dispatch = useDispatch();
  const {checkedItems} = useSelector((state : RootState) => state.todos);
  
  useEffect(() => {

  },[items])
  return (
    <div className="items">
      <h2>Items</h2>
      <div className="items-list">
        {items.length === 0 ? (
          'Items list is empty! '
        ) : (
          items.map((item) => (
            <Item key={item.id} text={item.text} onDelete={() => dispatch(deleteTodo(item.id))} onChange={() => dispatch(addChecked(item.id))} data-testid="item-ind"/>
          ))
        )}
      </div>
      {
        items.length !== 0 && checkedItems.length !== 0 ?  <button id="clear-btn" onClick={() => dispatch(deleteChecked())}>Clear checked items</button> : ''
      }
    </div>
  );
};

export default Items;
