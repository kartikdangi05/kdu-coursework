import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = 'https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json';

export const fetchRooms = createAsyncThunk(
    "initialSlice/fetchRooms",
    async () => {
        const response = await axios.get(API_URL);
        if (response.data) {
            return response.data["roomTypes"];
        } else {
            throw new Error("Failed to fetch products");
        }
    }
)