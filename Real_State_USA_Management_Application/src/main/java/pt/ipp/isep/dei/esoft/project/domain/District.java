package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * The type District.
 * Author: Marco Ferreira
 */
public class District implements Serializable {
    private String name;
    private City city;

    /**
     * Instantiates a new District.
     *
     * @param name the name
     * @param city the city
     */
    public District(String name, City city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return name;
    }
}
