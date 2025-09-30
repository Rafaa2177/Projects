package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.isep.lei.esoft.auth.domain.model.Email;

import javax.management.relation.Role;

public class PersonTest {

    private Person person;


    public void setup() {
        Email email = new Email("test@example.com");
        String password = "Password123";
        String name = "John Doe";
        String passportNumber = "12345678";
        String taxNumber = "123456789";
        String phoneNumber = "(123) 456-7890";
        Roles role = Roles.CLIENT;

        person = new Person(email, password, name, passportNumber, taxNumber, phoneNumber, role);
    }


    public void testConstructor_ValidArguments_ObjectCreated() {
        // Arrange
        Email email = new Email("test@example.com");
        String password = "Password123";
        String name = "John Doe";
        String passportNumber = "12345678";
        String taxNumber = "123456789";
        String phoneNumber = "(123) 456-7890";
        Roles role = Roles.CLIENT;

        // Act
        Person person = new Person(email, password, name, passportNumber, taxNumber, phoneNumber, role);

        // Assert
        Assertions.assertNotNull(person);
        Assertions.assertEquals(email, person.getId());
        Assertions.assertEquals(name, person.getName());
        Assertions.assertEquals(passportNumber, person.getPassportNumber());
        Assertions.assertEquals(taxNumber, person.getTaxNumber());
        Assertions.assertEquals(phoneNumber, person.getPhoneNumber());
        Assertions.assertEquals(role, person.getPersonRole());
    }


    public void testConstructor_NullPassword_ExceptionThrown() {
        // Arrange
        Email email = new Email("test@example.com");
        String password = null;
        String name = "John Doe";
        String passportNumber = "12345678";
        String taxNumber = "123456789";
        String phoneNumber = "(123) 456-7890";
        Roles role = Roles.CLIENT;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Person(email, password, name, passportNumber, taxNumber, phoneNumber, role));
    }


    public void testValidatePassword_ValidPassword_ReturnsTrue() {
        // Arrange
        String password = "P4ssword";

        // Act
        boolean isValid = person.validatePassword(password);

        // Assert
        Assertions.assertTrue(isValid);
    }


    public void testValidatePassword_InvalidPassword_ReturnsFalse() {
        // Arrange
        String password = "password";

        // Act
        boolean isValid = person.validatePassword(password);

        // Assert
        Assertions.assertFalse(isValid);
    }


    public void testClone_CreatesCopyOfPerson() {
        // Act
        Person clone = person.clone();

        // Assert
        Assertions.assertEquals(person.getId(), clone.getId());
        Assertions.assertEquals(person.getPwd(), clone.getPwd());
        Assertions.assertEquals(person.getName(), clone.getName());
        Assertions.assertEquals(person.getPassportNumber(), clone.getPassportNumber());
        Assertions.assertEquals(person.getTaxNumber(), clone.getTaxNumber());
        Assertions.assertEquals(person.getPhoneNumber(), clone.getPhoneNumber());
        Assertions.assertEquals(person.getPersonRole(), clone.getPersonRole());
    }


    public void testSendEmail_CreatesPasswordFile() {
        // Act & Assert (The test assumes the method writes the file successfully)
        Assertions.assertDoesNotThrow(() -> person.sendEmail());
} }
