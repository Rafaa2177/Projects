package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * The type State.
 */
public class State implements Serializable {
    private String name;

    /**
     * Instantiates a new State.
     *
     * @param name the name
     */
    public State(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
