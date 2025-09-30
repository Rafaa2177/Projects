/**
 * Test suite for the List Allergy Page.
 */
describe('ListAllergyPage', () => {
  /**
   * Runs before each test in the suite.
   * Visits the List Allergy Page and intercepts the API call to fetch all allergies.
   */
  beforeEach(() => {
    cy.visit('http://localhost:3000/Allergy/ListAllergyPage');

    // Intercepts the API call to list allergies with `cy.intercept()`
    cy.intercept('GET', 'http://localhost:4000/api/allergies/', [
      {
        id: '1',
        name: 'Peanut Allergy',
        code: '12345',
        description: 'Severe reaction to peanuts',
      },
      {
        id: '2',
        name: 'Dust Allergy',
        code: '67890',
        description: 'Allergic reaction to dust particles',
      },
    ]).as('fetchAllergies');
  });

  /**
   * Test case to verify that the list of allergies is loaded and displayed correctly.
   */
  it('should load and display the list of allergies', () => {
    // Verifies if the page title is correct
    cy.contains('h1', 'Allergy Management').should('be.visible');

    // Waits for the allergies to load
    cy.wait('@fetchAllergies');

    // Verifies if the allergies are displayed
    cy.contains('Peanut Allergy').should('be.visible');
    cy.contains('Dust Allergy').should('be.visible');
  });

  /**
   * Test case to verify that the update form is displayed when editing an allergy.
   */
  it('should display the update form when editing an allergy', () => {
    // Intercepts the API call to fetch allergy details
    cy.intercept('GET', 'http://localhost:4000/api/allergies/1', {
      statusCode: 200,
      body: {
        id: '1',
        name: 'Peanut Allergy',
        code: '12345',
        description: 'Severe reaction to peanuts',
      },
    }).as('fetchAllergyById');

    // Clicks the edit button
    cy.contains('Peanut Allergy').parent().contains('Edit').click();

    // Waits for the API call to fetch allergy details
    cy.wait('@fetchAllergyById');

    // Verifies if the update form is visible
    cy.contains('h2', 'Update Allergy').should('be.visible');
    cy.get('input[name="name"]').should('have.value', 'Peanut Allergy');
  });

  /**
   * Test case to verify that an allergy is updated successfully.
   */
  it('should update an allergy successfully', () => {
    // Intercepts the API call to fetch allergy details
    cy.intercept('GET', 'http://localhost:4000/api/allergies/1', {
      statusCode: 200,
      body: {
        id: '1',
        name: 'Peanut Allergy',
        code: '12345',
        description: 'Severe reaction to peanuts',
      },
    }).as('fetchAllergyById');

    // Intercepts the API call to update the allergy
    cy.intercept('PUT', 'http://localhost:4000/api/allergies/1', {
      statusCode: 200,
      body: {
        id: '1',
        name: 'Updated Allergy',
        code: '12345',
        description: 'Updated description',
      },
    }).as('updateAllergy');

    // Clicks the edit button
    cy.contains('Peanut Allergy').parent().contains('Edit').click();

    // Waits for the API call to fetch allergy details
    cy.wait('@fetchAllergyById');

    // Updates the name and description
    cy.get('input[name="name"]').clear().type('Updated Allergy');

    // Submits the form
    cy.get('form').submit();

    // Waits for the API call to update the allergy
    cy.wait('@updateAllergy');
  });
});
