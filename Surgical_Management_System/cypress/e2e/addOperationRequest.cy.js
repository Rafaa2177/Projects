describe('E2E Test for Operation Request Page', () => {
  beforeEach(() => {
    // Simular a API de pacientes
    cy.intercept('GET', '**/api/Patients', {
      statusCode: 200,
      body: [
        {
          id: '1',
          fullName: 'John Doe',
          recordNumber: { value: '353d4901-1a9c-4180-8a7f-df092696fb76' },
        },
        {
          id: '2',
          fullName: 'Jane Smith',
          recordNumber: { value: 'bdac5d22-e429-4bf8-a6eb-0bca1aa85b5f' },
        },
      ],
    }).as('getPatients');

    // Simular a API de médicos
    cy.intercept('GET', '**/api/Staffs/active', {
      statusCode: 200,
      body: [
        {
          id: '1',
          fullName: 'Dr. Alice Johnson',
          licenseNumber: { value: '8dbbc6bd-3edc-4254-8732-1c1f31d445d9' },
          role: 0,
        },
        {
          id: '2',
          fullName: 'Dr. Bob Brown',
          licenseNumber: { value: '9dbbc6bd-3edc-4254-8732-1c1f31d445d9' },
          role: 0,
        },
      ],
    }).as('getDoctors');

    // Simular o envio da requisição
    cy.intercept('POST', '**/api/OperationRequest', {
      statusCode: 201,
      body: { message: 'Operation Request created successfully!' },
    }).as('createOperationRequest');

    // Visitar a página com o email do médico na query string
    cy.visit('/Staff/OperationRequestPage');
  });

  it('should load the page and display all form elements', () => {
    cy.wait('@getPatients');
    cy.wait('@getDoctors');

    // Verificar título
    cy.get('h1').should('contain', 'Create Operation Request');

    // Verificar se os campos do formulário estão presentes
    cy.get('select[name="PatientId"]').should('exist');
    cy.get('select[name="DoctorId"]').should('exist');
    cy.get('select[name="OperationType"]').should('exist');
    cy.get('input[name="OperationDate"]').should('exist');
    cy.get('select[name="Priority"]').should('exist');
  });

  it('should successfully submit the form and display a success message', () => {
    cy.wait('@getPatients');
    cy.wait('@getDoctors');

    // Preencher o formulário
    cy.get('select[name="PatientId"]').select('353d4901-1a9c-4180-8a7f-df092696fb76').invoke('val').then((value) => {
      cy.log('Selected PatientId:', value);
    });
    cy.get('select[name="DoctorId"]').select('8dbbc6bd-3edc-4254-8732-1c1f31d445d9');
    cy.get('select[name="OperationType"]').select('Neurology');
    cy.get('input[name="OperationDate"]').type('2024-12-01');
    cy.get('select[name="Priority"]').select('High');

    // Submeter o formulário
    cy.get('button[type="submit"]').click();

    // Verificar o envio da requisição
    cy.wait('@createOperationRequest').then((interception) => {
      cy.log('Request Body:', JSON.stringify(interception.request.body));
    });

    cy.request({
      method: 'POST',
      url: 'https://localhost:5001/api/OperationRequest', 
      body: {
        PatientId: '353d4901-1a9c-4180-8a7f-df092696fb76',
        DoctorId: '8dbbc6bd-3edc-4254-8732-1c1f31d445d9',
        OperationType: 'Neurology',
        OperationDate: '2024-12-01',
        Priority: 'High',
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
    });


    // Verificar a exibição da mensagem de sucesso
    cy.get('.bg-green-200').should('contain', 'Operation Request created successfully!');
  });

  it('should handle errors when the form submission fails', () => {
    // Simular falha no envio da requisição
    cy.intercept('POST', '**/api/OperationRequest', {
      statusCode: 400,
      body: { error: 'Invalid data provided.' },
    }).as('createOperationRequestError');

    cy.wait('@getPatients');
    cy.wait('@getDoctors');

    // Preencher o formulário
    cy.get('select[name="PatientId"]').select('353d4901-1a9c-4180-8a7f-df092696fb76');
    cy.get('select[name="DoctorId"]').select('8dbbc6bd-3edc-4254-8732-1c1f31d445d9');
    cy.get('select[name="OperationType"]').select('Neurology');
    cy.get('input[name="OperationDate"]').type('2024-12-01');
    cy.get('select[name="Priority"]').select('High');

    // Submeter o formulário
    cy.get('button[type="submit"]').click();

    // Verificar a requisição falha
    cy.wait('@createOperationRequestError');

    // Verificar se o erro foi exibido no console
    cy.on('window:alert', (alertText) => {
      expect(alertText).to.contain('Invalid data provided.');
    });
  });
});
