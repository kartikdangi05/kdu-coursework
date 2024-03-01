import { Provider } from "react-redux";
import { store } from "../../redux/Store";
import { ToDoList } from "../Main/ToDoList";

describe('<Items />', () => {
  it('renders Items component', () => {
    cy.mount(<Provider store={store}>
      <ToDoList />
    </Provider>)
    cy.contains('Items').should('exist');
  });

  it('deletes an item when delete button is clicked', () => {
    cy.mount(<Provider store={store}>
      <ToDoList />
    </Provider>)

    cy.get('input[name=item-text]').type('Apple')
    cy.contains('Submit').click()

    cy.get('input[name=item-text]').type('Banana')
    cy.contains('Submit').click()

    cy.get('input[name=item-text]').type('Orange')
    cy.contains('Submit').click()

    cy.contains('Apple').should('exist');
    cy.get('.delete-icon').first().click();
    cy.contains('Apple').should('not.exist');
    cy.contains('Banana').should('exist');
    cy.contains('Orange').should('exist');
  });
})