/// <reference types="cypress" />

describe('ListSpecializationPage', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/Specialization/ListSpecializationPage');

    // Intercept the call to list specializations with `cy.intercept()`
    cy.intercept('GET', 'http://localhost:4000/api/specialization/', [
      {
        id: 'ed4f34c9-73c3-4edd-a0b1-843d325c97c6',
        name: 'Pediatry',
        code: '555555',
        description: 'children',
      },
      {
        id: '7ad718d4-cfa6-4d9e-8a84-7f72a58df181',
        name: 'Psiquiatry',
        code: '515249',
        description: 'helps crazy people!',
      },
    ]).as('fetchSpecialization');
  });

  it('should load and display the list of specializations', () => {
    // Verify the page title is correct
    cy.contains('h1', 'List Specializations').should('be.visible');

    // Wait for the specializations to load
    cy.wait('@fetchSpecialization');

    // Verify the specializations are displayed
    cy.contains('Pediatry').should('be.visible');
    cy.contains('Psiquiatry').should('be.visible');
  });

  it('should display the update form when editing a specialization', () => {
    // Intercept the call to fetch specialization details
    cy.intercept('GET', 'http://localhost:4000/api/specialization/ed4f34c9-73c3-4edd-a0b1-843d325c97c6', {
      statusCode: 200,
      body: {
        id: 'ed4f34c9-73c3-4edd-a0b1-843d325c97c6',
        name: 'Pediatry',
        code: '555555',
        description: 'children',
      },
    }).as('fetchSpecializationById');

    // Click the edit button
    cy.contains('Pediatry').parent().contains('Edit').click();

    // Wait for the call to fetch specialization details
    cy.wait('@fetchSpecializationById');

    // Verify the update form is visible
    cy.contains('h1', 'Update Specialization').should('be.visible');
    cy.get('input[name="name"]').should('have.value', 'Pediatry');
  });

  it('should update a specialization successfully', () => {
    // Intercept the call to fetch specialization details
    cy.intercept('GET', 'http://localhost:4000/api/specialization/ed4f34c9-73c3-4edd-a0b1-843d325c97c6', {
      statusCode: 200,
      body: {
        id: 'ed4f34c9-73c3-4edd-a0b1-843d325c97c6',
        name: 'Pediatry',
        code: '555555',
        description: 'children',
      },
    }).as('fetchSpecializationById');

    // Intercept the call to update the specialization
    cy.intercept('PUT', 'http://localhost:4000/api/specialization/ed4f34c9-73c3-4edd-a0b1-843d325c97c6', {
      statusCode: 200,
      body: {
        id: 'ed4f34c9-73c3-4edd-a0b1-843d325c97c6',
        name: 'Pediatry',
        code: '555555',
        description: 'children',
      },
    }).as('updateSpecialization');

    // Click the edit button
    cy.contains('Pediatry').parent().contains('Edit').click();

    // Wait for the call to fetch specialization details
    cy.wait('@fetchSpecializationById');

    // Update the name and description
    cy.get('input[name="name"]').clear().type('Updated Specialization');

    // Submit the form
    cy.get('form').submit();

    // Wait for the call to update the specialization
    cy.wait('@updateSpecialization');
  });
});
