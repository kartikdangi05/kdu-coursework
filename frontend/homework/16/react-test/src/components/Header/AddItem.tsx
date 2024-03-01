import { useState } from "react";
import { useDispatch } from "react-redux";
import { addTodo } from "../../redux/Reducer";

function AddItem() {
  const [inputValue, setInputValue] = useState("")
  const [id, setId] = useState(1);
  const dispatch = useDispatch();

  const handleClick = (e : React.MouseEvent<HTMLButtonElement>) : void => {
    e.preventDefault();
    addItem(inputValue);
    setInputValue("");
  }

  const addItem = (item : string) => {
      dispatch(addTodo({id,text : item}));
      setId(id => id + 1);
  };

  
  return (
    <div className="add-items">
      <h2>Add Items</h2>
      <div className="add-item-input">
        <input type="text" name="item-text" id="item-text" value={inputValue} onChange={(e) => setInputValue(e.target.value)} data-testid="item-text" />
        <button onClick={(e) => handleClick(e)} id="add-post">Submit</button>
      </div>
    </div>
  );
}

export default AddItem;