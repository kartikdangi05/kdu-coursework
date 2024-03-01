import { Provider } from 'react-redux'
import AddItem from './AddItem'
import { store } from '../../redux/Store'
import { ToDoList } from '../Main/ToDoList'

describe('<AddItem />', () => {
  it('renders', () => {
    cy.mount(<Provider store={store}>
      <AddItem />
    </Provider>)
    cy.contains('Add Items').should('exist')
    cy.contains('Submit').should('exist')
  })

  it('adds item when submit button is clicked', () => {
    cy.mount(<Provider store={store}>
      <ToDoList />
    </Provider>)
    cy.get('input[name=item-text]').type('Test Item')
    cy.contains('Submit').click()
    cy.contains('Test Item').should('exist')
  })

})