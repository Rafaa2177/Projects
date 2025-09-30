package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;

public class StateTest {
    public void testGetName() {
        // Arrange
        String name = "California";
        State state = new State(name);

        // Act
        String result = state.getName();

        // Assert
        Assertions.assertEquals(name, result);
    }


    public void testSetName() {
        // Arrange
        String name = "California";
        State state = new State("");

        // Act
        state.setName(name);

        // Assert
        Assertions.assertEquals(name, state.getName());
    }


    public void testToString() {
        // Arrange
        String name = "California";
        State state = new State(name);

        // Act
        String result = state.toString();

        // Assert
        Assertions.assertEquals(name, result);
    }
}

