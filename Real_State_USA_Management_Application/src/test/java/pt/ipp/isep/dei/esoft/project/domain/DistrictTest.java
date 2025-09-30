package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;

public class DistrictTest {


    public void testToString() {
        // Arrange
        String name = "District Name";
        State state2 = new State(name);
        City city = new City(state2, "City Name");
        District district = new District(name, city);

        // Act
        String result = district.toString();

        // Assert
        Assertions.assertEquals(name, result);
    }
}
