import { useState } from "react";

export interface IItem {
    text: string,
    onDelete: () => void,
    onChange: () => void
}

export default function Item({ text, onDelete, onChange }: Readonly<IItem>) {
    const [isChecked, setIsChecked] = useState(false);

    const handleDelete = () => {
        onDelete();
    };

    const handleCheck = () => {
        setIsChecked(true);
        onChange();
    }
    return (
        <ul className={"item" + (isChecked ? " checked" : "")} data-testid="item-ind">
        <p className="item-text">{text}</p>
            <div className="checkbox">
                <i className="fi fi-sr-square-x delete-icon" onClick={() => handleDelete()}></i>
                <button id="check-btn" onClick={() => handleCheck()}> <i className="fi fi-br-check"></i> </button>
            </div>
        </ul>
    )
}
