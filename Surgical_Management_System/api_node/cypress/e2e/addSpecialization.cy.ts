describe('Create Specialization Page', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/Specialization/CreateSpecializationPage');

    // Intercepta a chamada à API para criar alergias
    (cy as any).intercept('POST', '/api/specialization', {
      statusCode: 201,
      body: { message: 'Specialization created successfully!' },
    }).as('createSpecialization');
  });

  it('should display the form and submit a new specialization', () => {
    // Verifica se o título da página está correto
    cy.contains('h1', 'Add Specialization').should('be.visible');

    // Preenche o formulário
    cy.get('input[name="name"]').type('Cardiology');
    cy.get('input[name="code"]').type('93742612');
    cy.get('input[name="description"]').type('Specialization in heart diseases');

    // Submete o formulário
    cy.get('form').submit();

    // Aguarda a chamada simulada à API
    cy.wait('@createSpecialization');

    // Verifica se a notificação de sucesso é exibida
    cy.contains('Specialization created successfully!').should('be.visible');
  });


  it('should hide the success notification after 7 seconds', () => {
    // Preenche o formulário
    cy.get('input[name="name"]').type('Cardiology');
    cy.get('input[name="code"]').type('93742612');
    cy.get('input[name="description"]').type('Specialization in heart diseases');

    // Submete o formulário
    cy.get('form').submit();

    // Verifica se a notificação de sucesso aparece
    cy.contains('Specialization created successfully!').should('be.visible');

    // Aguarda 7 segundos e verifica se a notificação desapareceu
    cy.wait(7000);
    cy.contains('Specialization created successfully!').should('not.exist');
  });



});
