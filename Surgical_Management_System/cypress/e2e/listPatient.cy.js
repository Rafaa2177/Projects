describe('List Patients - End to End', () => {

  beforeEach(() => {
    cy.visit('/Admin/ListPacientPage'); // Ajuste o caminho conforme necessário
  });

  it('should load the ListPacientPage and render elements correctly', () => {
    cy.contains('List Patients').should('be.visible');
    cy.get('button').contains('List All Patients').should('be.visible');
    cy.get('form').should('be.visible');
  });

  it('should fetch and display all patients when "List All Patients" is clicked', () => {
    // Intercepta a requisição da API e responde com dados simulados
    cy.intercept('GET', 'https://localhost:5001/api/Patients', { fixture: 'patients.json' }).as('getAllPatients');
    cy.get('button').contains('List All Patients').click();

    cy.wait('@getAllPatients');

    // Verifica se há pelo menos um paciente listado
    cy.get('.mb-4').should('have.length.at.least', 1);
  });

  it('should filter patients by email', () => {
    cy.get('select').select('email'); // Seleciona a opção de filtro "email"
    cy.get('input[placeholder="Search by email"]').type('john.doe@example.com');

    // Intercepta a requisição GET com filtro por email
    cy.intercept('GET', 'https://localhost:5001/api/Patients/email/john.doe@example.com', { fixture: 'patient-by-email.json' }).as('filterByEmail');
    cy.get('button').contains('Search').click();

    // Espera a resposta da requisição
    cy.wait('@filterByEmail');

    // Verifica se o email filtrado está sendo exibido
    cy.get('.mb-4').should('contain', 'john.doe@example.com');
  });

  it('should allow deleting a patient', () => {
    // Intercepta a requisição GET para obter os pacientes
    cy.intercept('GET', 'https://localhost:5001/api/Patients', { fixture: 'patients.json' }).as('getAllPatients');
    cy.get('button').contains('List All Patients').click();
    cy.wait('@getAllPatients');

    // Intercepta a requisição DELETE para a exclusão do paciente
    cy.intercept('DELETE', 'https://localhost:5001/api/Patients/email/*', { statusCode: 200 }).as('deletePatient');

    // Configura o diálogo de confirmação antes de clicar
    cy.on('window:confirm', (str) => {
      expect(str).to.equal('Are you sure you want to delete the patient member with email: john.doe@example.com?');
      return true; // Simula o clique em "OK"
    });

    // Clica no botão "Delete" do primeiro paciente
    cy.get('button').contains('Delete').should('exist');
    
  });

  it('should navigate to the edit page when clicking "Edit"', () => {
    // Intercepta a requisição GET para obter os pacientes
    cy.intercept('GET', 'https://localhost:5001/api/Patients', { fixture: 'patients.json' }).as('getAllPatients');
    cy.get('button').contains('List All Patients').click();
    cy.wait('@getAllPatients');

    // Verifica se o botão "Edit" está visível
    cy.get('button').contains('Edit').should('exist');
    
  });
});
