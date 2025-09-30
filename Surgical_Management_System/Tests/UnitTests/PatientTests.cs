using System;
using System.Collections.Generic;
using Xunit;
using DDDSample1.Domain.Patients;

namespace DDDSample1.Tests
{
    public class PatientTests
    {
        /*// Teste de inicialização do paciente com todos os campos válidos
        [Fact]
        public void Constructor_ShouldInitializePatient_WithAllProperties()
        {
            // Arrange
            string firstName = "John";
            string lastName = "Doe";
            string fullName = "John Doe";
            DateTime dateOfBirth = new DateTime(1990, 1, 1);
            Gender gender = Gender.Male;
            string email = "john.doe@example.com";
            long phoneNumber = 912345678;
            List<string> medicalConditions = ["None"];
            long emergencyContact = 912345679;
            string appointments = "None";
            List<string> allergies = ["None"];

            // Act
            var patient = new Patient(firstName, lastName, fullName, dateOfBirth, gender, email, phoneNumber,
                medicalConditions, emergencyContact, appointments, allergies);

            // Assert
            Assert.Equal(firstName, patient.FirstName);
            Assert.Equal(lastName, patient.LastName);
            Assert.Equal(fullName, patient.FullName);
            Assert.Equal(dateOfBirth, patient.DateOfBirth);
            Assert.Equal(gender, patient.Gender);
            Assert.Equal(email, patient.Email);
            Assert.Equal(phoneNumber, patient.PhoneNumber);
            Assert.Equal(medicalConditions, patient.MedicalConditions);
            Assert.Equal(emergencyContact, patient.EmergencyContact);
            Assert.Equal(appointments, patient.Appointments);
            Assert.Equal(allergies, patient.Allergies);
        }

        // Teste de inicialização com primeiro nome inválido
        [Fact]
        public void Constructor_ShouldThrowArgumentException_WhenFirstNameIsInvalid()
        {
            // Arrange
            string firstName = ""; // Nome inválido
            string lastName = "Doe";
            string fullName = "John Doe";
            DateTime dateOfBirth = new DateTime(1990, 1, 1);
            Gender gender = Gender.Male;
            string email = "john.doe@example.com";
            long phoneNumber = 912345678;
            List<string> medicalConditions = ["None"];
            long emergencyContact = 912345679;
            string appointments = "None";
            List<string> allergies = ["None"];

            // Act & Assert
            Assert.Throws<ArgumentException>(() => new Patient(firstName, lastName, fullName, dateOfBirth, gender,
                email, phoneNumber, medicalConditions, emergencyContact, appointments, allergies));
        }

        // Teste de inicialização com último nome inválido
        [Fact]
        public void Constructor_ShouldThrowArgumentException_WhenLastNameIsInvalid()
        {
            // Arrange
            string firstName = "John";
            string lastName = ""; // Nome inválido
            string fullName = "John Doe";
            DateTime dateOfBirth = new DateTime(1990, 1, 1);
            Gender gender = Gender.Male;
            string email = "john.doe@example.com";
            long phoneNumber = 912345678;
            List<string> medicalConditions = ["None"];
            long emergencyContact = 912345679;
            string appointments = "None";
            List<string> allergies = ["None"];

            // Act & Assert
            Assert.Throws<ArgumentException>(() => new Patient(firstName, lastName, fullName, dateOfBirth, gender,
                email, phoneNumber, medicalConditions, emergencyContact, appointments, allergies));
        }

        // Teste de inicialização com email inválido
        [Fact]
        public void Constructor_ShouldThrowArgumentException_WhenEmailIsInvalid()
        {
            // Arrange
            string firstName = "John";
            string lastName = "Doe";
            string fullName = "John Doe";
            DateTime dateOfBirth = new DateTime(1990, 1, 1);
            Gender gender = Gender.Male;
            string email = "invalid-email"; // Email inválido
            long phoneNumber = 912345678;
            List<string> medicalConditions = ["None"];
            long emergencyContact = 912345679;
            string appointments = "None";
            List<string> allergies = ["None"];

            // Act & Assert
            Assert.Throws<ArgumentException>(() => new Patient(firstName, lastName, fullName, dateOfBirth, gender,
                email, phoneNumber, medicalConditions, emergencyContact, appointments, allergies));
        }

        // Teste de inicialização com número de telefone inválido


        // Teste de inicialização com data de nascimento no futuro
        [Fact]
        public void Constructor_ShouldThrowArgumentException_WhenDateOfBirthIsInTheFuture()
        {
            // Arrange
            string firstName = "John";
            string lastName = "Doe";
            string fullName = "John Doe";
            DateTime dateOfBirth = DateTime.Now.AddDays(1); // Data no futuro
            Gender gender = Gender.Male;
            string email = "john.doe@example.com";
            long phoneNumber = 912345678;
            List<string> medicalConditions = ["None"];
            long emergencyContact = 912345679;
            string appointments = "None";
            List<string> allergies = ["None"];

            // Act & Assert
            Assert.Throws<ArgumentException>(() => new Patient(firstName, lastName, fullName, dateOfBirth, gender,
                email, phoneNumber, medicalConditions, emergencyContact, appointments, allergies));
        }

        // Teste de atualização com valores nulos (não deve alterar nada)
        [Fact]
        public void Update_ShouldNotChangeProperties_WhenNullValuesArePassed()
        {
            // Arrange
            var patient = new Patient("Jane", "Doe", "Jane Doe", new DateTime(1985, 5, 15), Gender.Female,
                "jane.doe@example.com", 912345678, ["None"], 912345679, "None", ["None"]);

            // Act
            patient.Update();

            // Assert
            Assert.Equal("Jane", patient.FirstName);
            Assert.Equal("Doe", patient.LastName);
            Assert.Equal(new DateTime(1985, 5, 15), patient.DateOfBirth);
            Assert.Equal("jane.doe@example.com", patient.Email);
            Assert.Equal(912345678, patient.PhoneNumber);
            Assert.Equal(["None"], patient.MedicalConditions);
            Assert.Equal(912345679, patient.EmergencyContact);
            Assert.Equal("None", patient.Appointments);
            Assert.Equal(["None"], patient.Allergies);
        }

        // Teste de atualização com data de nascimento futura

        // Teste de atualização de gênero
        [Fact]
        public void Update_ShouldChangeGender_WhenValidGenderIsPassed()
        {
            // Arrange
            var patient = new Patient("Jane", "Doe", "Jane Doe", new DateTime(1985, 5, 15), Gender.Female,
                "jane.doe@example.com", 912345678, ["None"], 912345679, "None", ["None"]);
            ;

            // Act
            patient.Update(gender: Gender.Male);

            // Assert
            Assert.Equal(Gender.Male, patient.Gender);
        }

        // Teste de atualização de condições médicas
        /*[Fact]
        public void Update_ShouldChangeMedicalConditions_WhenValidConditionsArePassed()
        {
            // Arrange
            var patient = new Patient("Jane", "Doe", "Jane Doe", new DateTime(1985, 5, 15), Gender.Female,
                "jane.doe@example.com", 912345678, ["None"], 912345679, "None", ["None"]);
            List<string> newConditions = ["Allergy to peanuts"];

            // Act
            patient.Update(allergies: newConditions);

            // Assert
            Assert.Equal(newConditions, patient.Allergies);
        }#1#

        // Teste de atualização de histórico de consultas
        [Fact]
        public void Update_ShouldChangeAppointmentHistory_WhenValidHistoryIsPassed()
        {
            // Arrange
            var patient = new Patient("Jane", "Doe", "Jane Doe", new DateTime(1985, 5, 15), Gender.Female,
                "jane.doe@example.com", 912345678, ["None"], 912345679, "None", ["None"]);
            string newAppointments = "2023-12-01 Checkup";

            // Act
            patient.Update(appointmentHistory: newAppointments);

            // Assert
            Assert.Equal(newAppointments, patient.Appointments);
        }*/
    }
}
