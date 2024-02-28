import { createAsyncThunk, createSlice } from "@reduxjs/toolkit"
import axios from "axios"

const API_URL = 'https://fakestoreapi.com/products';

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

const initialState : ProductsState = {
    productsList: [],
    error: '',
    status: 'idle',
}

export const fetchProducts = createAsyncThunk<IProduct[]>("posts/fetchProducts", async () => {
    const response = await axios.get(API_URL);
    return response?.data;
})

const productsSlice = createSlice({
    name: 'products',
    initialState,
    reducers: {

    },
    extraReducers(builder) {
        builder
            .addCase(fetchProducts.pending, (state) => {
                state.status = "loading"
            })
            .addCase(fetchProducts.fulfilled, (state, action) => {
                state.status = "succeeded"
                state.productsList = state.productsList.concat(action.payload);
            })
            .addCase(fetchProducts.rejected, (state, action) => {
                state.status = "failed"
                state.error = action.error.message
            })
    }
})

export default productsSlice.reducer;
