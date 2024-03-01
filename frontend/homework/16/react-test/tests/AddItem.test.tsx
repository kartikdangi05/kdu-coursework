import { render, screen, fireEvent } from '@testing-library/react';
import { expect, test } from 'vitest';
import React from 'react';
import AddItem from '../src/components/Header/AddItem';
import {ToDoList} from '../src/components/Main/ToDoList';
import { Provider } from 'react-redux';
import { store } from '../src/redux/Store';

test('renders AddItem component', () => {
  render(
    <Provider store={store}>
      <AddItem />
    </Provider>
  );
  const addButton = screen.getByText("Submit");
  expect(addButton).toBeDefined();
});

test('add item when submit button is clicked', () => {
  render(
    <Provider store={store}>
      <ToDoList />
    </Provider>
  );
  const inputElement = screen.getByTestId("item-text"); 
  fireEvent.change(inputElement, { target: { value: "Test Item" } });
  const submitButton = screen.getByText("Submit");
  fireEvent.click(submitButton);
  const itemElement = screen.getByText("Test Item");
  expect(itemElement).toBeDefined();
});
