import { useDispatch, useSelector } from "react-redux";
import { DispatchType, RootState } from "../../redux/store/Store";
import { addendDate, addstartDate } from "../../redux/HotelSlice";

const dateHeading: React.CSSProperties = {
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

const dateInput: React.CSSProperties = {
  margin: '0.05% 0.5%',
  padding: '1%',
  textDecoration: 'none',
  border: '1px solid black',
  flex: '1'
}

export default function StartEndDate() {
  const dispatch = useDispatch<DispatchType>();
  const {startDate} = useSelector((state : RootState) => state.hotel);

  const onStartDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(addstartDate(e.target.value));
  }

  const onEndDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if(new Date(e.target.value) < new Date(startDate)){
      alert("End date should be greater than start date");
    }
    else
      dispatch(addendDate(e.target.value));
  }
  return (
    <div>
      <h2 style={dateHeading}>Select Date</h2>
      <div style={inputDiv}>
        <div style={dateInput}>
          <label htmlFor="startDate" style={{marginRight: '5%'}}>Enter Start Date</label>
          <input
            type="date"
            onChange={onStartDateChange}
            required
            style={{fontSize: '20px'}}
          />
        </div>

        <div style={dateInput}>
          <label htmlFor="endDate" style={{marginRight: '5%'}}>Enter End Date</label>
          <input
            type="date"
            onChange={onEndDateChange}
            required
            style={{fontSize: '20px'}}
          />
        </div>
      </div>
    </div>
  )
}
