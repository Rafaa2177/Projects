describe('ListMedicalConditionPage', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/MedicalCondition/ListMedicalConditionPage');

    // Interceta a chamada para listar alergias com `cy.intercept()`
    cy.intercept('GET', 'http://localhost:4000/api/medicalCondition/', [
      {
        id: '1',
        name: 'Dental Abcess',
        code: '12345',

      },
      {
        id: '2',
        name: 'Diabetes',
        code: '67890',
      },
    ]).as('fetchMedicalConditions');
  });

  it('should load and display the list of allergies', () => {
    // Verifica se o título da página está correto
    cy.contains('h1', 'Medical Condition Management').should('be.visible');

    // Aguarda o carregamento das alergias
    cy.wait('@fetchMedicalConditions');

    // Verifica se as alergias são exibidas
    cy.contains('Dental Abcess').should('be.visible');
    cy.contains('Diabetes').should('be.visible');
  });

  it('should display the update form when editing an allergy', () => {
    // Interceta a chamada para buscar detalhes da alergia
    cy.intercept('GET', 'http://localhost:4000/api/medicalCondition/1', {
      statusCode: 200,
      body: {
        id: '1',
        name: 'Dental Abcess',
        code: '12345',
      },
    }).as('fetchMedicalConditionById');

    // Clica no botão de editar
    cy.contains('Dental Abcess').parent().contains('Edit').click();

    // Aguarda a chamada para buscar detalhes da alergia
    cy.wait('@fetchMedicalConditionById');

    // Verifica se o formulário de atualização está visível
    cy.contains('h2', 'Update Medical Condition').should('be.visible');
    cy.get('input[name="name"]').should('have.value', 'Dental Abcess');
  });

  it('should update an allergy successfully', () => {
    // Interceta a chamada para buscar detalhes da alergia
    cy.intercept('GET', 'http://localhost:4000/api/medicalCondition/1', {
      statusCode: 200,
      body: {
        id: '1',
        name: 'Dental Abcess',
        code: '12345',

      },
    }).as('fetchMedicalConditionById');

    // Interceta a chamada para atualizar a alergia
    cy.intercept('PUT', 'http://localhost:4000/api/medicalCondition/1', {
      statusCode: 200,
      body: {
        id: '1',
        name: 'Updated Allergy',
        code: '12345',

      },
    }).as('updateMedicalCondition');

    // Clica no botão de editar
    cy.contains('Dental Abcess').parent().contains('Edit').click();

    // Aguarda a chamada para buscar detalhes da alergia
    cy.wait('@fetchMedicalConditionById');

    // Atualiza o nome e a descrição
    cy.get('input[name="name"]').clear().type('Updated Medical Condition');


    // Submete o formulário
    cy.get('form').submit();

    // Aguarda a chamada para atualizar a alergia
    cy.wait('@updateMedicalCondition');

  });
});
