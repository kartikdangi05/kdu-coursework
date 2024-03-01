import { configureStore } from "@reduxjs/toolkit";
import hotelReducer from '../HotelSlice';
import initialSliceReducer from '../InitialSlice'

const store = configureStore({
    reducer: {
        hotel: hotelReducer,
        initialSlice: initialSliceReducer
    } 
})

export default store
export type RootState = ReturnType<typeof store.getState>;
export type DispatchType = typeof store.dispatch