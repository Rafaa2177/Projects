package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;

public class PasswordGeneratorTest {

    public void testGeneratePassword() {
        // Act
        String password = PasswordGenerator.GeneratePassword();

        // Assert
        Assertions.assertNotNull(password);
        Assertions.assertEquals(7, password.length());
    }
}
