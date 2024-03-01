import {useSelector } from "react-redux";
import AddOn from "./AddOn";
import { RootState } from "../../redux/store/Store";

interface IAddOn{
    name: string;
    cost: string;
    currency: string;
}

const addOnHeading : React.CSSProperties = {
    margin: '2% 0.5%',
    padding: '1%',
    backgroundColor: '#e27329',
    color: 'white'
}

const inputDiv: React.CSSProperties = {
    display: 'flex',
    justifyContent: 'flex-start',
    fontSize: '20px'
}

export default function AddOns() {
    const {initialData} = useSelector((state : RootState) => state.initialSlice);
    const {roomType} = useSelector((state : RootState) => state.hotel);

    let addOns : IAddOn[] = [];
    const findRoom = initialData.find((data) => data.name === roomType);
    if(findRoom){
        addOns = findRoom.addOns;
    }
    return (
        <div>
            <h2 style={addOnHeading}>Select Additional addons / preferences</h2>
            <div style={inputDiv}>
                {
                    addOns.map((data) => {
                        return <AddOn key={data.name} name={data.name}/>
                    })
                }
            </div>
        </div>
    )
}
