describe('AddItem component', () => {
    it('renders AddItem component', () => {
      cy.visit('/') // Assuming your app's root URL is '/'
      cy.contains('Submit').should('exist');
    });
  
    it('adds item when submit button is clicked', () => {
      cy.visit('/')
      cy.get('[data-testid=item-text]').type('Test Item');
      cy.contains('Submit').click();
      cy.contains('Test Item').should('exist');
    });
  });