import { useDispatch } from "react-redux";
import { addAddons } from "../../redux/HotelSlice";
import { DispatchType } from "../../redux/store/Store";

interface ICheck {
    name: string;
}


const addonInput: React.CSSProperties = {
    margin: '0.05% 0.5%',
    padding: '1%',
    textDecoration: 'none',
    border: '1px solid black',
    flex: '1'
}

export default function AddOn({ name }: Readonly<ICheck>) {
    const dispatch = useDispatch<DispatchType>();
    return (
        <div style={addonInput}>
            <input
                type="checkbox"
                value={name}
                id={name}
                onChange={() => dispatch(addAddons(name))}
            />
            <label htmlFor={name} style={{marginLeft: '1%'}}>{name}</label>
        </div>
    )
}
