import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface Todo {
  id: number;
  text: string;
}

export interface TodoState {
  todos: Todo[];
  searchItems: Todo[];
  checkedItems: number[]
}

const initialState: TodoState = {
  todos: [],
  searchItems: [],
  checkedItems: []
};

export const todoSlice = createSlice({
  name: 'todos',
  initialState,
  reducers: {
    addTodo: (state, action: PayloadAction<Todo>) => {
      state.todos.push(action.payload);
    },
    deleteTodo: (state, action: PayloadAction<number>) => {
      state.todos = state.todos.filter(todo => todo.id !== action.payload);
      state.searchItems = state.searchItems.filter(todo => todo.id !== action.payload);
    },
    searchTodo: (state, action: PayloadAction<string>) => {
      state.searchItems = state.todos.filter(todo => todo.text.includes(action.payload));
    },
    addChecked: (state, action: PayloadAction<number>) => {
      state.checkedItems.push(action.payload);
    },
    deleteChecked: (state) => {
      state.todos = state.todos.filter((todo) => !state.checkedItems.includes(todo.id));
      state.searchItems = state.searchItems.filter((todo) => !state.checkedItems.includes(todo.id));
      state.checkedItems = [];
    }
  },
});

export const { addTodo, searchTodo, deleteTodo, addChecked, deleteChecked } = todoSlice.actions;

export default todoSlice.reducer;

