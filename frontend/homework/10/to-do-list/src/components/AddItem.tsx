import { useState } from "react";

interface AddItemProps {
  addItem: (item : string) => void;
}

function AddItem({addItem} : Readonly<AddItemProps>) {
  const [inputValue, setInputValue] = useState("")

  const handleClick = (e : React.MouseEvent<HTMLButtonElement>) : void => {
    e.preventDefault();
    addItem(inputValue);
    setInputValue("");
  }
  
  return (
    <div className="add-items">
      <h2>Add Items</h2>
      <div className="add-item-input">
        <input type="text" name="item-text" id="item-text" value={inputValue} onChange={(e) => setInputValue(e.target.value)} />
        <button onClick={(e) => handleClick(e)} id="add-post">Submit</button>
      </div>
    </div>
  );
}

export default AddItem;