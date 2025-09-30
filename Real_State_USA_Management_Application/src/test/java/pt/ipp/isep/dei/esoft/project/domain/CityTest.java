package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;

public class CityTest {
    private City city;
    private State state;
    private String name;


    public void setUp() {
        state = new State("State");
        name = "City";

        city = new City(state, name);
    }

    public void testGetState() {
        // Arrange

        // Act
        State result = city.getState();

        // Assert
        Assertions.assertEquals(state, result);
    }


    public void testGetName() {
        // Arrange

        // Act
        String result = city.getName();

        // Assert
        Assertions.assertEquals(name, result);
    }
}
