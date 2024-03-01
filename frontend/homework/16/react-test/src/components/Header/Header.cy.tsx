// cypress/integration/todoList.spec.js

import { Provider } from "react-redux";
import { store } from "../../redux/Store";
import { ToDoList } from "../Main/ToDoList";

describe('ToDoList functionality', () => {

  it('should filter items when searching', () => {
    cy.mount(<Provider store={store}>
      <ToDoList />
    </Provider>)

    cy.get('input[name=item-text]').type('Apple')
    cy.contains('Submit').click()

    cy.get('input[name=item-text]').type('Banana')
    cy.contains('Submit').click()

    cy.get('input[name=item-text]').type('Orange')
    cy.contains('Submit').click()


    cy.get('#search').type('Banana');
    cy.wait(100);

    cy.get('.item-text').should('contain', 'Banana');
    cy.get('.item-text').should('not.contain', 'Apple');
    cy.get('.item-text').should('not.contain', 'Orange');
  });
});
