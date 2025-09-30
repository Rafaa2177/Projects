using System;
using System.Net.Http;
using System.Threading.Tasks;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Patients;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.User;
using Moq;
using Xunit;

namespace DDDSample1.Tests.IsolatedTests
{
    public class PatientServiceTest
    {
        private readonly Mock<IPatientRepository> _mockRepo;
        private readonly Mock<IUnitOfWork> _mockUnitOfWork;
        private readonly PatientService _patientService;
        private readonly Mock<IUserRepository> _mockUserRepository;
        private readonly UserService _userService;
        private readonly EmailService _emailService;
        private readonly HttpClient _client;

        public PatientServiceTest()
        {
            _mockRepo = new Mock<IPatientRepository>();
            _mockUnitOfWork = new Mock<IUnitOfWork>();
            _mockUserRepository = new Mock<IUserRepository>();
            _client = new HttpClient();
            _emailService = new EmailService(_mockUnitOfWork.Object, _mockUserRepository.Object, _client);
            _userService = new UserService(_mockUnitOfWork.Object, _mockUserRepository.Object, _client, _emailService);
            _patientService = new PatientService(_mockUnitOfWork.Object, _mockRepo.Object, _userService, _emailService);
        }

        [Fact]
        public async Task RegisterPatient_ShouldThrowArgumentException_WhenEmailIsInvalid()
        {
            // Arrange
            var creatingPatientDto = new CreatingPatientDto
            {
                FirstName = "John",
                LastName = "Doe",
                FullName = "John Doe",
                DateOfBirth = new DateTime(1990, 1, 1),
                Gender = Gender.Male,
                Email = "invalid-email",
                PhoneNumber = 912345678,
                EmergencyContact = 912345679,
                AppointmentHistory = "None"
            };

            // Act & Assert
            await Assert.ThrowsAsync<ArgumentException>(() => _patientService.AddAsync(creatingPatientDto));
        }

        

        

        [Fact]
        public async Task RegisterPatient_ShouldThrowArgumentException_WhenFirstNameIsTooLong()
        {
            // Arrange
            var creatingPatientDto = new CreatingPatientDto
            {
                FirstName = new string('A', 101), // 101 characters
                LastName = "Doe",
                FullName = "John Doe",
                DateOfBirth = new DateTime(1990, 1, 1),
                Gender = Gender.Male,
                Email = "john.doe@example.com",
                PhoneNumber = 912345678,
                EmergencyContact = 912345679,
                AppointmentHistory = "None"
            };

            // Act & Assert
            await Assert.ThrowsAsync<ArgumentException>(() => _patientService.AddAsync(creatingPatientDto));
        }

        [Fact]
        public async Task RegisterPatient_ShouldThrowArgumentException_WhenLastNameIsTooLong()
        {
            // Arrange
            var creatingPatientDto = new CreatingPatientDto
            {
                FirstName = "John",
                LastName = new string('A', 101), // 101 characters
                FullName = "John Doe",
                DateOfBirth = new DateTime(1990, 1, 1),
                Gender = Gender.Male,
                Email = "john.doe@example.com",
                PhoneNumber = 912345678,
                EmergencyContact = 912345679,
                AppointmentHistory = "None"
            };

            // Act & Assert
            await Assert.ThrowsAsync<ArgumentException>(() => _patientService.AddAsync(creatingPatientDto));
        }
    }
}