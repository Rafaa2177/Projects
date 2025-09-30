package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;

public class AddressTest {
    // Arrange
    String street = "Main Street";
    City city = null;
    State state1 = new State("Porto");
    City city1 = new City(state1, "Gondomar");
    District district = new District("distrito",city );
    String zipCode = "12345";
    State state = new State("State");

    public void testConstructor_BlankStreet_ThrowsException() {
        // Arrange
        String street = "";
        State state1 = new State("Porto");
        City city = new City(state1, "city");
        District district = new District("District", city);
        String zipCode = "12345";
        State state = new State("State");

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Address(street, city, district, zipCode, state);
        });
    }



    public void testConstructor_BlankZipCode_ThrowsException() {
        // Arrange
        String street = "Main Street";
        State state1 = new State("Porto");
        City city = new City(state1, "city");
        District district = new District("District",city);
        String zipCode = "";
        State state = new State("State");

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Act
            new Address(street, city, district, zipCode, state);
        });
    }


    public void testConstructor_NullState_ThrowsException() {
        // Arrange
        String street = "Main Street";
        State state1 = new State("Porto");
        City city = new City(state1, "distrito");
        District district = new District("District", city);
        String zipCode = "12345";
        State state = null;

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Act
            new Address(street, city, district, zipCode, state);
        });
    }


    public void testConstructor_InvalidZipCodeLength_ThrowsException() {
        // Arrange
        String street = "Main Street";
        State state1 = new State("Porto");
        City city = new City(state1,"city");
        District district = new District("district",city);
        String zipCode = "1234";
        State state = new State("State");

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Act
            new Address(street, city, district, zipCode, state);
        });
    }


    public void testToString() {
        // Arrange
        String street = "Main Street";
        State state1 = new State("Porto");
        City city = new City(state1, "city");
}
}
