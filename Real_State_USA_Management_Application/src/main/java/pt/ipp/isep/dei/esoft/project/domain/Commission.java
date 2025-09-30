package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * The type Commission.
 * Author: Luis Aquino
 */
public class Commission implements Serializable {
    private double value;
    private double percentage;

    /**
     * Instantiates a new Commission.
     *
     * @param value      the value
     * @param percentage the percentage
     */
    public Commission(double value, double percentage) {

        this.percentage = percentage;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{ value = " + value +
                ", percentage=" + percentage +
                '}';
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Gets percentage.
     *
     * @return the percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * Sets percentage.
     *
     * @param percentage the percentage
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

}
