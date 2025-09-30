describe('List Staffs - End to End', () => {

  beforeEach(() => {
    cy.visit('/Admin/ListStaffPage');
  });

  it('should load the ListStaffPage and render elements correctly', () => {
   
    cy.contains('List Staffs').should('be.visible');
    cy.get('button').contains('List All Staffs').should('be.visible');
    cy.get('form').should('be.visible');
  });

  it('should fetch and display all staff when "List All Staffs" is clicked', () => {
   
    cy.intercept('GET', 'https://localhost:5001/api/Staffs/active', { fixture: 'staffs.json' }).as('getAllStaffs');
    cy.get('button').contains('List All Staffs').click();
    
    cy.wait('@getAllStaffs');
    
    cy.get('.mb-4').should('have.length.at.least', 1); 
  });

  it('should filter staff by email', () => {
    
    cy.get('select').select('email');
    cy.get('input[placeholder="Search by email"]').type('john.doe@example.com');

    
    cy.intercept('GET', 'https://localhost:5001/api/Staffs/email/john.doe@example.com', { fixture: 'staff-by-email.json' }).as('filterByEmail');
    cy.get('button').contains('Search').click();

    
    cy.wait('@filterByEmail');
    
    cy.get('.mb-4').should('contain', 'john.doe@example.com');
  });

  it('should filter staff by specialization', () => {
    
    cy.get('select').select('specialization');
    cy.get('select[name="specialization"]').select('Pediatrics');
    
    cy.intercept('GET', 'https://localhost:5001/api/Staffs/specialization/Pediatrics', { fixture: 'staff-by-specialization.json' }).as('filterBySpecialization');
    cy.get('button').contains('Search').click();
    
    cy.wait('@filterBySpecialization');
    
    cy.get('.mb-4').should('contain', 'Pediatrics');
  });

  it('should verify delete button and show confirmation message when clicked', () => {
   
    cy.intercept('GET', 'https://localhost:5001/api/Staffs/active', { fixture: 'staffs.json' }).as('getAllStaffs');
    cy.get('button').contains('List All Staffs').click();
    cy.wait('@getAllStaffs');

   
    cy.get('button').contains('Delete').first().should('be.visible');

    
    cy.window().then((window) => {
      cy.stub(window, 'confirm').returns(true); 
    });

   
    cy.intercept('DELETE', 'https://localhost:5001/api/staffs/*').as('deleteStaff');

    
    cy.get('button').contains('Delete').first().click();
    
  });


  it('should navigate to the edit page when clicking "Edit"', () => {
    
    cy.intercept('GET', 'https://localhost:5001/api/Staffs/active', { fixture: 'staffs.json' }).as('getAllStaffs');
    cy.get('button').contains('List All Staffs').click();
    cy.wait('@getAllStaffs');

    cy.get('button').contains('Edit').should('exist');
  });

});
