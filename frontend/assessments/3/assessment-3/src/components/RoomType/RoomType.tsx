import { useDispatch, useSelector } from "react-redux"
import { DispatchType, RootState } from "../../redux/store/Store"
import { addRoomType } from "../../redux/HotelSlice";

const roomTypeHeading: React.CSSProperties = {
    margin: '2% 0.5%',
    marginRight: '1%',
    padding: '1%',
    backgroundColor: '#e27329',
    color: 'white',
}

const inputDiv: React.CSSProperties = {
    display: 'flex',
    justifyContent: 'flex-start',
    fontSize: '20px'
}

const roomTypeInput: React.CSSProperties = {
    margin: '0.05% 0.5%',
    padding: '1%',
    textDecoration: 'none',
    border: '1px solid black',
    flex: '1'
}


export default function RoomType() {

    const { roomType } = useSelector((state: RootState) => state.hotel);
    const dispatch = useDispatch<DispatchType>();

    const onOptionChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        dispatch(addRoomType(e.target.value));
    }
    return (
        <div>
            <h2 style={roomTypeHeading}>Select Room Type</h2>
            <div style={inputDiv}>
                <div style={roomTypeInput}>
                    <input
                        type="radio"
                        name="Single Room"
                        value="Single Room"
                        id="singleRoom"
                        checked={roomType === "Single Room"}
                        onChange={onOptionChange}
                    />
                    <label htmlFor="Single Room" style={{marginLeft: '1%'}}>Single Room</label>
                </div>

                <div style={roomTypeInput}>
                    <input
                        type="radio"
                        name="Twin Room"
                        value="Twin Room"
                        id="twinRoom"
                        checked={roomType === "Twin Room"}
                        onChange={onOptionChange}
                        style={roomTypeInput}
                    />
                    <label htmlFor="Twin Room" style={{marginLeft: '1%'}}>Twin Room</label>
                </div>

                <div style={roomTypeInput}>
                    <input
                        type="radio"
                        name="Deluxe"
                        value="Deluxe"
                        id="deluxe"
                        checked={roomType === "Deluxe"}
                        onChange={onOptionChange}
                        style={roomTypeInput}
                    />
                    <label htmlFor="Deluxe" style={{marginLeft: '1%'}}>Deluxe</label>
                </div>

                <div style={roomTypeInput}>
                    <input
                        type="radio"
                        name="Presidential Suite"
                        value="Presidential Suite"
                        id="suite"
                        checked={roomType === "Presidential Suite"}
                        onChange={onOptionChange}
                    />
                    <label htmlFor="Presidential Suite" style={{marginLeft: '1%'}}>Presidential Suite</label>
                </div>
            </div>
        </div>
    )
}
