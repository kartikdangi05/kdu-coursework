import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { Provider } from 'react-redux';
import { store } from '../src/redux/Store';
import Header from '../src/components/Header/Header';
import { expect, test } from 'vitest';
import { addTodo, searchTodo } from '../src/redux/Reducer';
import { ToDoList } from '../src/components/Main/ToDoList';

test('renders Header component', () => {
  render(
    <Provider store={store}>
      <Header />
    </Provider>
  );
  const headingElement = screen.getByText("Item Lister");
  expect(headingElement).toBeDefined();
});

test('dispatches search action when input value changes and filters items', async () => {

  store.dispatch(addTodo({ id: 1, text: 'Apple' }));
  store.dispatch(addTodo({ id: 2, text: 'Banana' }));
  store.dispatch(addTodo({ id: 3, text: 'Orange' }));


  render(
    <Provider store={store}>
      <ToDoList />
    </Provider>
  );
  
  const searchInput = screen.getByPlaceholderText("Search Items...");
  fireEvent.change(searchInput, { target: { value: "Banana" } });

  await new Promise(resolve => setTimeout(resolve, 0));

  store.dispatch(searchTodo("Banana"));

  const itemElements = screen.getAllByTestId("item-ind");

  itemElements.forEach(item => {
    expect(item.textContent).toContain("Banana");
  });
});
