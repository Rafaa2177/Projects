describe('Create Medical Condition Page', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/MedicalCondition/CreateMedicalConditionPage');

    // Intercepta a chamada à API para criar alergias
    (cy as any).intercept('POST', '/api/medicalCondition', {
      statusCode: 201,
      body: { message: 'Medical Condition created successfully!' },
    }).as('createMedicalCondition');
  });

  it('should display the form and submit a new allergy', () => {
    // Verifica se o título da página está correto
    cy.contains('h1', 'Add Medical Condition').should('be.visible');

    // Preenche o formulário
    cy.get('input[name="name"]').type('Diabetes Medical Condition');
    cy.get('input[name="code"]').type('234512123');


    // Submete o formulário
    cy.get('form').submit();

    // Aguarda a chamada simulada à API
    cy.wait('@createMedicalCondition');

    // Verifica se a notificação de sucesso é exibida
    cy.contains('Medical Condition created successfully!').should('be.visible');
  });

});
