package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * The type City.
 * Author: Diana Neves
 */
public class City implements Serializable {
    private State state;
    private String name;

    /**
     * Instantiates a new City.
     *
     * @param state the state
     * @param name  the name
     */
    public City(State state, String name) {
        this.state = state;
        this.name = name;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
