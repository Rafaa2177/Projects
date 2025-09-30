describe('Add Pacient Page', () => {
  beforeEach(() => {
    // Aceder à pagina para criar o patient profile
    cy.visit('/Admin/AddPacientPage'); 
  });

  it('should display the patient creation form correctly', () => {
    // Verifica se os elementos do formulário estão visíveis
    cy.get('input[name="firstName"]').should('be.visible');
    cy.get('input[name="lastName"]').should('be.visible');
    cy.get('input[name="fullName"]').should('be.visible');
    cy.get('input[name="dateOfBirth"]').should('be.visible');
    cy.get('input[name="phoneNumber"]').should('be.visible');
    cy.get('input[name="email"]').should('be.visible');
    cy.contains('Create Patient Profile').should('be.visible');
  });

  it('should successfully create a patient', () => {
    // Preenche os campos do formulário
    cy.get('input[name="firstName"]').type('John');
    cy.get('input[name="lastName"]').type('Doe');
    cy.get('input[name="fullName"]').type('John Doe');
    cy.get('input[name="dateOfBirth"]').type('1990-01-01');
    cy.get('input[name="phoneNumber"]').type('1234567890');
    cy.get('input[name="email"]').type('johndoe@example.com');

    // Verificar os dados enviados
    cy.intercept('POST', '**/api/Patients', {
      statusCode: 201,
      body: { id: 1, message: 'Patient created successfully' },
    }).as('createPatient');

    // Submete o formulário
    cy.contains('Create Patient Profile').click();

    // Verifica se a requisição foi enviada corretamente
    cy.wait('@createPatient').its('request.body').should('deep.include', {
      firstName: 'John',
      lastName: 'Doe',
      fullName: 'John Doe',
      dateOfBirth: '1990-01-01',
      phoneNumber: '1234567890',
      email: 'johndoe@example.com',
    });

    // Verifica se a notificação de sucesso aparece
    cy.contains('Patient Profile created successfully!').should('be.visible');

    // Aguarda e verifica se a notificação desaparece após 7 segundos
    cy.wait(7000);
    cy.contains('Patient Profile created successfully!').should('not.exist');
  });

  it('should display an error when the API fails', () => {
    // Preenche os campos do formulário
    cy.get('input[name="firstName"]').type('Jane');
    cy.get('input[name="lastName"]').type('Doe');
    cy.get('input[name="fullName"]').type('Jane Doe');
    cy.get('input[name="dateOfBirth"]').type('1985-05-15');
    cy.get('input[name="phoneNumber"]').type('9876543210');
    cy.get('input[name="email"]').type('janedoe@example.com');

    // Simulação de falha na API
    cy.intercept('POST', '**/api/Patients', {
      statusCode: 500,
      body: { error: 'Internal Server Error' },
    }).as('createPatientFail');

    // Submete o formulário
    cy.contains('Create Patient Profile').click();

    // Verifica se a requisição foi enviada corretamente
    cy.wait('@createPatientFail');

    // Verifica se a mensagem de erro na consola aparece
    cy.on('window:alert', (str) => {
      expect(str).to.include('Error:');
    });
  });

  it('should validate required fields', () => {
    // Clica no botão de criar sem preencher os campos
    cy.contains('Create Patient Profile').click();

    // Verifica se os campos obrigatórios têm erros
    cy.get('input[name="firstName"]:invalid').should('have.length', 1);
    cy.get('input[name="lastName"]:invalid').should('have.length', 1);
    cy.get('input[name="dateOfBirth"]:invalid').should('have.length', 1);
    cy.get('input[name="phoneNumber"]:invalid').should('have.length', 1);
    cy.get('input[name="email"]:invalid').should('have.length', 1);
  });

  it('should reset the form after successful submission', () => {
    // Preenche os campos do formulário
    cy.get('input[name="firstName"]').type('Alice');
    cy.get('input[name="lastName"]').type('Smith');
    cy.get('input[name="fullName"]').type('Alice Smith');
    cy.get('input[name="dateOfBirth"]').type('2000-12-12');
    cy.get('input[name="phoneNumber"]').type('5551234567');
    cy.get('input[name="email"]').type('alicesmith@example.com');

    // Intercepta a requisição POST 
    cy.intercept('POST', '**/api/Patients', {
      statusCode: 201,
      body: { id: 2, message: 'Patient created successfully' },
    }).as('createPatientSuccess');

    // Submete o formulário
    cy.contains('Create Patient Profile').click();

    // Aguarda o sucesso
    cy.wait('@createPatientSuccess');
    
  });
});


