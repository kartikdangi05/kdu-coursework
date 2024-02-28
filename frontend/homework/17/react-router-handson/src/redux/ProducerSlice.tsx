import { createSlice } from "@reduxjs/toolkit";
import { fetchProducts } from "./thunk/ProductThunk";

export interface IProduct{
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;
    rating: {
        rate: number;
        count: number;
    };
}

interface ProductsState {
    productsList: IProduct[];
    error: string | undefined;
    status: 'idle' | 'loading' | 'succeeded' | 'failed';
}

const initialState: ProductsState = {
    productsList: [],
    error: '',
    status: 'idle',
}

const productsSlice = createSlice({
    name: 'products',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(fetchProducts.pending, (state) => {
                state.status = "loading";
            })
            .addCase(fetchProducts.fulfilled, (state, action) => {
                state.status = "succeeded";
                state.productsList = action.payload;
            })
            .addCase(fetchProducts.rejected, (state, action) => {
                state.status = "failed";
                state.error = action.error.message;
            });
    },
});

export default productsSlice.reducer;
