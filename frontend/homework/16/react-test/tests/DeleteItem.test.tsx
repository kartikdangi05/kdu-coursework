import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { Provider } from 'react-redux';
import { store } from '../src/redux/Store';
import { expect, test } from 'vitest';
import { addTodo } from '../src/redux/Reducer';
import { ToDoList } from '../src/components/Main/ToDoList';

test('renders Items component', () => {
    render(
        <Provider store={store}>
            <ToDoList />
        </Provider>
    );
    const headingElement = screen.getByText("Items");
    expect(headingElement).toBeDefined();
});

test('delete item when delete button is clicked', () => {

    store.dispatch(addTodo({ id: 1, text: 'Apple' }));
    store.dispatch(addTodo({ id: 2, text: 'Banana' }));
    store.dispatch(addTodo({ id: 3, text: 'Orange' }));

    render(
        <Provider store={store}>
            <ToDoList />
        </Provider>
    );

    const deleteButton = screen.getByText('Apple')?.parentElement?.querySelector('.delete-icon');
    if (deleteButton)
        fireEvent.click(deleteButton);

    expect(screen.queryByText('Apple')).toBeNull();
});
