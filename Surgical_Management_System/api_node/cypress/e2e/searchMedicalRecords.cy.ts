/**
 * Test suite for the Search Page.
 */
describe('Search Page Tests', () => {
  /**
   * Runs before each test in the suite.
   * Visits the Search Page and intercepts the API calls to fetch patient allergies, medical conditions, and patient details.
   */
  beforeEach(() => {
    cy.intercept('GET', 'https://localhost:5001/api/Patients/patientAllergy/*', {
      statusCode: 200,
      body: ["test1@example.com", "test2@example.com"],
    }).as('getAllergies');

    cy.intercept('GET', 'https://localhost:5001/api/Patients/patientMedicalCondition/*', {
      statusCode: 200,
      body: ["test3@example.com"],
    }).as('getConditions');

    cy.intercept('GET', 'https://localhost:5001/api/Patients/email/*', (req) => {
      const email = req.url.split('/').pop();
      req.reply({
        statusCode: 200,
        body: {
          id: email === "test1@example.com" ? 1 : 2,
          firstName: email === "test1@example.com" ? "John Doe" : "Jane Doe",
          email: email,
          phoneNumber: "123-456-7890",
          allergies: email === "test1@example.com" ? ["Peanuts"] : ["Dust"],
          medicalConditions: ["Asthma"],
        },
      });
    }).as('getPatientDetails');

    cy.visit('http://localhost:3000/Doctor/SearchPage');
  });

  /**
   * Test case to verify that the search page is displayed correctly.
   */
  it('Displays search page correctly', () => {
    cy.contains('Search Patient Records').should('exist');
    cy.get('select[name="searchType"]').should('exist');
    cy.get('input[type="text"]').should('exist');
    cy.contains('button', 'Search').should('exist');
  });

  /**
   * Test case to verify that patients can be searched by allergies successfully.
   */
  it('Searches patients by allergies successfully', () => {
    cy.get('select[name="searchType"]').select('allergies');
    cy.get('input[type="text"]').type('Peanuts');
    cy.contains('button', 'Search').click();

    cy.wait('@getAllergies');
    cy.wait('@getPatientDetails');

    cy.contains('John Doe').should('exist');
    cy.contains('Email: test1@example.com').should('exist');
    cy.contains('Allergies: Peanuts').should('exist');
  });

  /**
   * Test case to verify that patients can be searched by medical conditions successfully.
   */
  it('Searches patients by medical conditions successfully', () => {
    cy.get('select[name="searchType"]').select('medicalConditions');
    cy.get('input[type="text"]').type('Asthma');
    cy.contains('button', 'Search').click();

    cy.wait('@getConditions');
    cy.wait('@getPatientDetails');

    cy.contains('Jane Doe').should('exist');
    cy.contains('Email: test3@example.com').should('exist');
    cy.contains('Medical Conditions: Asthma').should('exist');
  });

  /**
   * Test case to verify that no results are handled gracefully.
   */
  it('Handles no results gracefully', () => {
    cy.intercept('GET', 'https://localhost:5001/api/Patients/patientAllergy/*', {
      statusCode: 404,
      body: [],
    }).as('getNoAllergies');

    cy.get('select[name="searchType"]').select('allergies');
    cy.get('input[type="text"]').type('NonExistentAllergy');
    cy.contains('button', 'Search').click();

    cy.wait('@getNoAllergies');
    cy.contains('No patients found.').should('exist');
  });
});
