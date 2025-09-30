using System;
using System.Collections.Generic;
using DDDSample1.Domain.Staff;
using Xunit;
using Assert = Xunit.Assert;

namespace DDDSample1.Tests
{
    public class StaffTests
    {
        [Fact]
        public void Constructor_ShouldInitializeStaff_WithAllProperties()
        {
            // Arrange
            string firstName = "Alice";
            string lastName = "Smith";
            string fullName = "Alice Smith";
            var specializations = "Gynecology" ;
            string email = "alice.smith@example.com";
            string phoneNumber = "123456789";
            var timeSlots = new List<TimeSlots>(); // lista de horários vazia para simplificar
            Roles role = Roles.Doctor;

            // Act
            var staff = new Staff(firstName, lastName, specializations, email, phoneNumber, timeSlots, role, active: true);

            // Assert
            Assert.Equal(firstName, staff.FirstName);
            Assert.Equal(lastName, staff.LastName);
            Assert.Equal(fullName, staff.FullName);
            Assert.Equal(specializations, staff.Specialization);
            Assert.Equal(email, staff.Email);
            Assert.Equal(phoneNumber, staff.PhoneNumber);
            Assert.Equal(timeSlots, staff.TimeSlots);
            Assert.Equal(role, staff.Role);
            Assert.True(staff.Active);
        }

        [Fact]
        public void Update_ShouldUpdateProperties_WhenNewValuesArePassed()
        {
            // Arrange
            var staff = new Staff("Alice", "Smith", "Nephrology", "alice.smith@example.com", "123456789", new List<TimeSlots>(), Roles.Nurse, active: true);
            string newFirstName = "Alice";
            string newLastName = "Johnson";
            var newSpecializations =  "Oncology" ;
            string newEmail = "alice.johnson@example.com";
            string newPhoneNumber = "987654321";
            var newTimeSlots = new List<TimeSlots>();

            // Act
            staff.Update(newFirstName, newLastName, newSpecializations, newEmail, newPhoneNumber, newTimeSlots);

            // Assert
            Assert.Equal(newFirstName, staff.FirstName);
            Assert.Equal(newLastName, staff.LastName);
            Assert.Equal("Alice Johnson", staff.FullName);
            Assert.Equal(newSpecializations, staff.Specialization);
            Assert.Equal(newEmail, staff.Email);
            Assert.Equal(newPhoneNumber, staff.PhoneNumber);
            Assert.Equal(newTimeSlots, staff.TimeSlots);
        }

        [Fact]
        public void MarkAsInactive_ShouldSetActiveToFalse()
        {
            // Arrange
            var staff = new Staff("Alice", "Smith",   "Cardiology" , "alice.smith@example.com", "123456789", new List<TimeSlots>(), Roles.Doctor, active: true);

            // Act
            staff.MarkAsInative();

            // Assert
            Assert.False(staff.Active);
        }

        [Fact]
        public void MarkAsActive_ShouldSetActiveToTrue()
        {
            // Arrange
            var staff = new Staff("Alice", "Smith", "Ophthalmology" , "alice.smith@example.com", "123456789", new List<TimeSlots>(), Roles.Doctor, active: true);
            staff.MarkAsInative(); // Torna inativo para testar a ativação

            // Act
            staff.MarkAsActive();

            // Assert
            Assert.True(staff.Active);
        }
    }
}