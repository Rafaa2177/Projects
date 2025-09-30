/**
 * Test suite for the Medical Record Page.
 */
describe('Medical Record Page', () => {
  /**
   * Runs before each test in the suite.
   * Visits the Medical Record Page and intercepts the API calls to fetch allergies, medical conditions, and patient details.
   */
  beforeEach(() => {
    cy.intercept('GET', 'https://localhost:5001/api/Patients/allergy/*', {
      statusCode: 200,
      body: ["Peanuts", "Dust", "Pollen"],
    }).as('getAllergies');

    cy.intercept('GET', 'https://localhost:5001/api/Patients/medicalCondition/*', {
      statusCode: 200,
      body: ["Asthma", "Diabetes"],
    }).as('getConditions');

    cy.intercept('GET', 'https://localhost:5001/api/Patients/email/*', {
      statusCode: 200,
      body: {
        id: 1,
        name: "John Doe",
        email: "john.doe@example.com",
      },
    }).as('getPatient');

    cy.visit('http://localhost:3000/Doctor/MedicalRecordPage');
  });

  /**
   * Test case to verify that a patient can be searched by email and their allergies and medical conditions are displayed.
   */
  it('Searches for a patient by email', () => {
    cy.get('input[name="email"]').type('john.doe@example.com');
    cy.contains('button', 'Search').click();

    cy.wait('@getAllergies');
    cy.wait('@getConditions');
    cy.wait('@getPatient');

    cy.contains('Allergies').should('exist');
    cy.contains('Peanuts').should('exist');
    cy.contains('Dust').should('exist');

    cy.contains('Medical Conditions').should('exist');
    cy.contains('Asthma').should('exist');
    cy.contains('Diabetes').should('exist');
  });

  /**
   * Test case to verify that allergies can be updated successfully.
   */
  it('Should update allergies successfully', () => {
    // Search for a patient
    cy.get('input[name="email"]').type('john.doe@example.com');
    cy.contains('button', 'Search').click();

    cy.wait('@getPatient');
    cy.wait('@getAllergies');

    cy.contains('Allergies').should('exist');
    cy.contains('Peanut').should('exist');
    cy.contains('Dust').should('exist');

    // Open the update modal
    cy.contains('button', 'Update Records').click({ force: true });

    // Update allergies
    cy.contains('button', 'Update Allergies').click({force: true});
  });
});
