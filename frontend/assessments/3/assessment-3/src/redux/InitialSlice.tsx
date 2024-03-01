import { createSlice } from "@reduxjs/toolkit";
import { fetchRooms } from "./Thunk/HotelThunk";

interface IAddOns{
    name: string;
    cost: string;
    currency: string;
}

interface IRooms{
    id: number;
    name: string;
    costPerNight: string;
    currency: string;
    addOns: IAddOns[]
}

interface IData{
    initialData: IRooms[];
    status: string;
    error: string | undefined;
}

const initialState : IData = {
    initialData: [],
    status: 'pending',
    error: '' 
}

const initialSlice = createSlice({
    name: "initialSlice",
    initialState,
    reducers: {

    },
    extraReducers(builder) {
        builder
            .addCase(fetchRooms.pending, (state) => {
                state.status = "loading";
            })
            .addCase(fetchRooms.fulfilled, (state, action) => {
                state.status = "succeeded";
                state.initialData = action.payload;
            })
            .addCase(fetchRooms.rejected, (state, action) => {
                state.status = "failed";
                state.error = action.error.message;
            });
    },
})

export default initialSlice.reducer;