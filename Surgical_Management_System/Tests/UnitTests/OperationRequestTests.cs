using System;
using DDDSample1.Domain.OperationRequests;
using Xunit;

namespace DDDSample1.Tests
{
    public class OperationRequestTests
    {
        [Fact]
        public void Constructor_ShouldInitializeUser_WithAllProperties()
        {
            // Arrange
            string patientId = "1";
            string doctorId = "2";
            string operationType = "Surgery";
            DateTime operationDate = DateTime.Now;
            string priority = "High";

            // Act
            var operationRequest = new OperationRequest(patientId, doctorId, operationType, operationDate, priority);

            // Assert
            Assert.Equal(patientId, operationRequest.PatientId);
            Assert.Equal(doctorId, operationRequest.DoctorId);
            Assert.Equal(operationType, operationRequest.OperationType);
            Assert.Equal(operationDate, operationRequest.OperationDate);
            Assert.Equal(priority, operationRequest.Priority);

        }

        [Fact]
        public void Constructor_ShouldThrowArgumentException_WhenDoctorIdIsNull()
        {
            // Arrange
            string patientId = "1";
            string doctorId = null;
            string operationType = "Surgery";
            DateTime operationDate = DateTime.Now;
            string priority = "High";


            // Act & Assert
            Assert.Throws<ArgumentException>(() =>
                new OperationRequest(patientId, doctorId, operationType, operationDate, priority));
        }

        [Fact]
        public void Constructor_ShouldThrowArgumentException_WhenPatientIdIsNull()
        {
            // Arrange
            string patientId = null;
            string doctorId = "2";
            string operationType = "Surgery";
            DateTime operationDate = DateTime.Now;
            string priority = "High";


            // Act & Assert
            Assert.Throws<ArgumentException>(() =>
                new OperationRequest(patientId, doctorId, operationType, operationDate, priority));
        }
    }
}