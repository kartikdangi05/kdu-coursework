import { createSlice } from "@reduxjs/toolkit";

interface IHotel{
    roomType : string;
    startDate: Date ;
    endDate: Date | null;
    addOns: string[]
}

const initialState : IHotel = {
    roomType: '',
    startDate: new Date(),
    endDate: null,
    addOns: []
}

const hotelSlice = createSlice({
    name: 'hotel',
    initialState,
    reducers: {
        addRoomType: (state,action) => {
            state.roomType = action.payload;
        },
        addstartDate: (state,action) => {
            state.startDate = action.payload;
        },
        addendDate: (state,action) => {
            state.endDate = action.payload;
        },
        addAddons: (state,action) => {
            state.addOns.push(action.payload);
        },
    }
})

export const { addRoomType, addstartDate, addendDate, addAddons } = hotelSlice.actions;
export default hotelSlice.reducer;


