export interface IItem {
    text: string,
    onDelete: () => void; 
}

export default function Item({ text, onDelete }: Readonly<IItem>) {
    const handleDelete = () => {
        onDelete();
    };
    return (
        <ul className="item">
            <p className="item-text">{text}</p>
            <i className="fi fi-sr-square-x delete-icon" onClick={() => handleDelete()}></i>
        </ul>
    )
}
