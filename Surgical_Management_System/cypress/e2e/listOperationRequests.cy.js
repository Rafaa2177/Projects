describe('E2E Tests for ListOperationRequestPage', () => {
  beforeEach(() => {
    cy.intercept('GET', '**/api/OperationRequest', {
      statusCode: 200,
      body: [
        {
          id: '1',
          doctorId: 'D001',
          patientId: 'P001',
          operationType: 'Surgery',
          operationDate: '2024-11-22',
          priority: 'High',
        },
        {
          id: '2',
          doctorId: 'D002',
          patientId: 'P002',
          operationType: 'Checkup',
          operationDate: '2024-12-01',
          priority: 'Medium',
        },
      ],
    }).as('getOperationRequests');
    
    cy.intercept('GET', '**/api/Patients', {
      statusCode: 200,
      body: [
        { id: 'P001', recordNumber: { value: '12345' }, fullName: 'John Doe' },
        { id: 'P002', recordNumber: { value: '67890' }, fullName: 'Jane Smith' },
      ],
    }).as('getPatients');

   
    cy.intercept('GET', '**/api/Staffs/active', {
      statusCode: 200,
      body: [
        { id: 'D001', licenseNumber: { value: '98765' }, fullName: 'Dr. Alice' },
        { id: 'D002', licenseNumber: { value: '54321' }, fullName: 'Dr. Bob' },
      ],
    }).as('getDoctors');

   
    cy.intercept('PUT', '**/api/OperationRequest/*', {
      statusCode: 200,
      body: { message: 'Operation request updated successfully!' },
    }).as('updateOperationRequest');

   
    cy.intercept('DELETE', '**/api/OperationRequest/*', {
      statusCode: 200,
      body: { message: 'Operation request deleted successfully!' },
    }).as('deleteOperationRequest');

    
    cy.visit('/Staff/ListOperationRequestPage');
  });

  it('should display a list of operation requests', () => {
    cy.wait('@getOperationRequests');
    cy.get('ul > li').should('have.length', 2); // Verifica que há dois itens na lista
    cy.get('ul > li').first().within(() => {
      cy.contains('Doctor ID: D001');
      cy.contains('Operation Type: Surgery');
      cy.contains('Priority: High');
    });
  });

  
  
  it('checks if edit button exists', () => {
    cy.get('button').contains('Editar').first().click();
    cy.get('button').contains('Save').click();
  });

  it('checks if delete button exists', () => {
    cy.get('button').contains('Apagar').first().click();
    // Verifique se a exclusão foi bem-sucedida (se necessário, adicione mais validações)
  });

  it('should show a loading spinner during API requests', () => {
    cy.intercept('GET', '**/api/OperationRequest', (req) => {
      req.reply((res) => {
        res.send(200, []);
      });
    }).as('delayedOperationRequests');

    cy.get('p').contains('Loading...', { timeout: 10000 }).should('be.visible');
    cy.wait('@delayedOperationRequests');
    cy.get('p').contains('Loading...').should('not.exist');
  });
});
