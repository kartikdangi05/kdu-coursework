import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { IProduct } from "../ProducerSlice";

const API_URL = 'https://fakestoreapi.com/products';

export const fetchProducts = createAsyncThunk<IProduct[]>(
    "posts/fetchProducts",
    async () => {
        const response = await axios.get(API_URL);
        if (response.data) {
            return response.data;
        } else {
            throw new Error("Failed to fetch products");
        }
    }
);
