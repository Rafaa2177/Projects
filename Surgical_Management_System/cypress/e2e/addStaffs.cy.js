describe('Add Staff Page - E2E Tests', () => {
  beforeEach(() => {
    cy.visit('/Admin/AddStaffPage'); 
  });

  const validStaffData = {
    firstName: 'John',
    lastName: 'Doe',
    email: 'john.doe@example.com',
    phoneNumber: '123456789',
    licenseNumber: '12345',
    specialization: 'cardiology',
    role: 'doctor',
    timeSlots: [
      {
        startDate: '2024-11-20',
        startTime: '09:00',
        endDate: '2024-11-20',
        endTime: '11:00',
      },
    ],
  };

  it('should display all form fields on page load', () => {
    cy.get('input[name="firstName"]').should('exist');
    cy.get('input[name="lastName"]').should('exist');
    cy.get('input[name="email"]').should('exist');
    cy.get('input[name="phoneNumber"]').should('exist');
    cy.get('select[name="specialization"]').should('exist');
    cy.get('select[name="role"]').should('exist');
    cy.contains('Add Time Slot').should('exist');
    cy.get('button[type="submit"]').contains('Add Staff').should('exist');
  });

  it('should allow adding and removing time slots', () => {
    cy.contains('Add Time Slot').click();
    cy.get('input[type="date"]').should('have.length', 2); // StartDate & EndDate
    cy.get('input[type="time"]').should('have.length', 2); // StartTime & EndTime

    cy.contains('✕').click(); // Remove the time slot
    cy.get('input[type="date"]').should('not.exist');
    cy.get('input[type="time"]').should('not.exist');
  });

  it('should prevent submission when required fields are empty', () => {
    cy.get('button[type="submit"]').contains('Add Staff').click();
    cy.on('window:alert', (alertText) => {
      expect(alertText).to.contain('End time must be after start time');
    });
  });

  it('should successfully submit valid data', () => {
    cy.intercept('POST', '/api/Staffs', {
      statusCode: 201,
      body: { message: 'Staff created successfully' },
    }).as('createStaff');

    // Fill form with valid data
    cy.get('input[name="firstName"]').type(validStaffData.firstName);
    cy.get('input[name="lastName"]').type(validStaffData.lastName);
    cy.get('input[name="email"]').type(validStaffData.email);
    cy.get('input[name="phoneNumber"]').type(validStaffData.phoneNumber);
    cy.get('select[name="specialization"]').select(validStaffData.specialization).should('have.value', validStaffData.specialization);
    cy.get('select[name="role"]').select(validStaffData.role);
    cy.contains('Add Time Slot').click();

    cy.get('input[type="date"]').first().type(validStaffData.timeSlots[0].startDate);
    cy.get('input[type="time"]').first().type(validStaffData.timeSlots[0].startTime);
    cy.get('input[type="date"]').eq(1).type(validStaffData.timeSlots[0].endDate);
    cy.get('input[type="time"]').eq(1).type(validStaffData.timeSlots[0].endTime);

    // Submit form
    cy.get('button[type="submit"]').contains('Add Staff').click();

    // Verify API call
    cy.wait('@createStaff')
        .its('request.body')
        .should('deep.include', {
          firstName: validStaffData.firstName,
          lastName: validStaffData.lastName,
          email: validStaffData.email,
          specialization: validStaffData.specialization,
          phoneNumber: validStaffData.phoneNumber,
          role: validStaffData.role,
          timeSlots: [
            {
              startTime: new Date(
                  `${validStaffData.timeSlots[0].startDate}T${validStaffData.timeSlots[0].startTime}`
              ).toISOString(),
              endTime: new Date(
                  `${validStaffData.timeSlots[0].endDate}T${validStaffData.timeSlots[0].endTime}`
              ).toISOString(),
            },
          ],
        });

    // Verify success notification
    cy.contains('Staff Profile created successfully!').should('be.visible');
  });
});
