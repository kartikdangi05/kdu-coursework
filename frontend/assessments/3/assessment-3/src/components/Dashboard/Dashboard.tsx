import { useSelector } from "react-redux";
import AddOns from "../AddOns/AddOns";
import StartEndDate from "../Date/StartEndDate";
import RoomType from "../RoomType/RoomType";
import { RootState } from "../../redux/store/Store";

let dashboardStyle : React.CSSProperties = {
    textAlign: 'center'
}

const submitBtn : React.CSSProperties = {
    padding: '1% 2%',
    backgroundColor: '#e27329',
    border: 0,
    color: 'white',
    fontSize: '20px',
    marginLeft: '1%'
}

export default function Dashboard() {

    const {initialData} = useSelector((state : RootState) => state.initialSlice);
    const {roomType, startDate,endDate,addOns} = useSelector((state : RootState) => state.hotel);

    const calculatePrice = () : number =>  {
        let price = 0;
        initialData.map((room) => {
            if(room.name === roomType && endDate !== null){
                let days = new Date(endDate).getDate() - new Date(startDate).getDate();
                price += days * parseInt(room.costPerNight);
                room.addOns.map((addOn)=> {
                    if(addOns.includes(addOn.name)){
                        price += days * parseInt(addOn.cost);
                    }
                })
            }
        })
        return price;
    }

    const showDetail = () => {
        if(roomType.length == 0){
            alert("Please select room type");
        }
        if(endDate == null){
            alert("Please select dates");
        }
        if(endDate!= null && endDate < startDate){
            alert("End date should be greater than start date");
        }
        console.log("Room Type : ", roomType);
        console.log("StartDate : ", startDate);
        console.log("EndDate : ", endDate);
        console.log("AddOns : ", addOns);
        console.log("Total Cost : ", calculatePrice())
    }

  return (
    <div>
        <h1 style={dashboardStyle}>Hotel Booking</h1>
        <RoomType/>
        <StartEndDate/>
        <AddOns/>
        <h1 style={{marginLeft: '1%'}}>Cost + 18% GST = {calculatePrice()}</h1>
        <button style={submitBtn} onClick={showDetail}>Submit</button>
    </div>
  )
}
