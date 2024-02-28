import { configureStore } from "@reduxjs/toolkit";
import productsReducer from './ProducrReducer'
import SnackbarReducer from "./SnackbarReducer";

const store = configureStore({
    reducer: {
        products: productsReducer,
        snackbar: SnackbarReducer
    }
})

export default store
export type RootState = ReturnType<typeof store.getState>;
export type DispatchType = typeof store.dispatch