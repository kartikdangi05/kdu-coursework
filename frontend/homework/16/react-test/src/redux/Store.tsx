import { combineReducers, configureStore } from "@reduxjs/toolkit";
import todoReducer from './Reducer'
import storage from "redux-persist/lib/storage";
import { persistReducer } from "redux-persist";

const persistConfig = {
  key: 'root',
  version: 1,
  storage
}

const reducer = combineReducers({
  todos: todoReducer
})

const persistedReducer = persistReducer(persistConfig,reducer);

export const store = configureStore({
    reducer: persistedReducer
});

export type RootState = ReturnType<typeof store.getState>;

