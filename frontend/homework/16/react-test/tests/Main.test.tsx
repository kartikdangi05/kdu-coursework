// ToDoList.test.tsx
import { render, screen } from '@testing-library/react';
import { expect, test } from 'vitest'
import React from 'react'
import { ToDoList } from '../src/components/Main/ToDoList';
import { Provider } from 'react-redux';
import { store } from '../src/redux/Store';

test('renders ToDoList component', () => {
  render(<Provider store={store}>
    <ToDoList />
  </Provider>);
  const headerElement = screen.getByText("Item Lister");
  expect(headerElement).toBeDefined();
});
