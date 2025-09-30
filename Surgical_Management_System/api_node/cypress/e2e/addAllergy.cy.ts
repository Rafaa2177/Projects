/**
 * Test suite for the Create Allergy Page.
 */
describe('Create Allergy Page', () => {
  /**
   * Runs before each test in the suite.
   * Visits the Create Allergy Page and intercepts the API call to create allergies.
   */
  beforeEach(() => {
    cy.visit('http://localhost:3000/Allergy/CreateAllergyPage');

    // Intercept the API call to create allergies
    (cy as any).intercept('POST', '/api/allergies', {
      statusCode: 201,
      body: { message: 'Allergy created successfully!' },
    }).as('createAllergy');
  });

  /**
   * Test case to verify that the form is displayed and a new allergy can be submitted.
   */
  it('should display the form and submit a new allergy', () => {
    // Check if the page title is correct
    cy.contains('h1', 'Add Allergy').should('be.visible');

    // Fill out the form
    cy.get('input[name="name"]').type('Egg Allergy');
    cy.get('input[name="code"]').type('234512123');
    cy.get('input[name="description"]').type('Severe reaction to eggs');

    // Submit the form
    cy.get('form').submit();

    // Wait for the simulated API call
    cy.wait('@createAllergy');

    // Check if the success notification is displayed
    cy.contains('Allergy created successfully!').should('be.visible');
  });

  /**
   * Test case to verify that the success notification is hidden after 7 seconds.
   */
  it('should hide the success notification after 7 seconds', () => {
    // Fill out the form
    cy.get('input[name="name"]').type('Egg Allergy');
    cy.get('input[name="code"]').type('234512123');
    cy.get('input[name="description"]').type('Severe reaction to eggs');

    // Submit the form
    cy.get('form').submit();

    // Check if the success notification appears
    cy.contains('Allergy created successfully!').should('be.visible');

    // Wait 7 seconds and check if the notification disappears
    cy.wait(7000);
    cy.contains('Allergy created successfully!').should('not.exist');
  });
});
