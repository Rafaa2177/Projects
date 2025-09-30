package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;

public class CommissionTest {
    private Commission commission;
    private double value;
    private double percentage;


    public void setUp() {
        value = 1000.0;
        percentage = 10.0;

        commission = new Commission(value, percentage);
    }


    public void testGetValue() {
        // Arrange

        // Act
        double result = commission.getValue();

        // Assert
        Assertions.assertEquals(value, result);
    }


    public void testSetValue() {
        // Arrange
        double newValue = 2000.0;

        // Act
        commission.setValue(newValue);

        // Assert
        Assertions.assertEquals(newValue, commission.getValue());
    }


    public void testGetPercentage() {
        // Arrange

        // Act
        double result = commission.getPercentage();

        // Assert
        Assertions.assertEquals(percentage, result);
    }


    public void testSetPercentage() {
        // Arrange
        double newPercentage = 15.0;

        // Act
        commission.setPercentage(newPercentage);

        // Assert
        Assertions.assertEquals(newPercentage, commission.getPercentage());
    }
}
