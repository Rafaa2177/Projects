using System;
using DDDSample1.Domain.User;
using Xunit;
using DDDSample1.Domain.Users;

namespace DDDSample1.Tests
{
    public class UserTests
    {
        [Fact]
        public void Constructor_ShouldInitializeUser_WithAllProperties()
        {
            // Arrange
            string username = "johndoe";
            string email = "john.doe@example.com";
            string password = "Password123!";
            string role = "Doctor";

            // Act
            var user = new User(role, email);

            // Assert
            Assert.Equal(email, user.email);
            Assert.Equal(role, user.role);
        }

        [Fact]
        public void Constructor_ShouldThrowArgumentException_WhenEmailIsInvalid()
        {
            // Arrange
            string email = "invalid-email"; // Invalid email
            string role = "Doctor";

            // Act & Assert
            Assert.Throws<ArgumentException>(() => new User(role, email));
        }
        
        [Fact]
        public void Constructor_ShouldThrowArgumentException_WhenRoleIsInvalid()
        {
            // Arrange
            string email = "okay@gmail.com";
            string role = "Programmer"; //Invalid role

            // Act & Assert
            Assert.Throws<ArgumentException>(() => new User(role, email));
        }

        [Fact]
        public void Update_ShouldNotChangeProperties_WhenNullValuesArePassed()
        {
            // Arrange
            var user = new User("Doctor", "john.doe@example.com");

            // Act
            user.Update(null, null);
            
            // Assert
            Assert.Equal("john.doe@example.com", user.email);
            Assert.Equal("Doctor", user.role);
        }

        [Fact]
        public void Update_ShouldThrowArgumentException_WhenEmailIsInvalid()
        {
            // Arrange
            var user = new User("Doctor", "john.doe@example.com");
            string invalidEmail = "invalid-email"; // Invalid email

            // Act & Assert
            Assert.Throws<ArgumentException>(() => user.UpdateEmail(invalidEmail));
        }
        
        
        [Fact]
        public void Update_ShouldUpdateProperties_WhenValidValuesArePassed()
        {
            // Arrange
            var user = new User("Doctor", "john.doe@example.com");
            string newEmail = "john.smith@example.com";
            string newRole = "Admin";

            // Act
            user.Update(newRole, newEmail);

            // Assert
            Assert.Equal(newEmail, user.email);
            Assert.Equal(newRole, user.role);
        }
        
        [Fact]
        public void Update_ShouldUpdateProperties_WhenValidEmailIsPassed()
        {
            // Arrange
            var user = new User("Doctor", "john.doe@example.com");
            string newEmail = "john.smith@example.com";

            // Act
            user.UpdateEmail(newEmail);

            // Assert
            Assert.Equal(newEmail, user.email);
        }
        
        [Fact]
        public void Update_ShouldUpdateProperties_WhenValidRoleIsPassed()
        {
            // Arrange
            var user = new User("Doctor", "john.doe@example.com");
            string newRole = "Admin";

            // Act
            user.UpdateRole(newRole);

            // Assert
            Assert.Equal(newRole, user.role);
        }
    }
}