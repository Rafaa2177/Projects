package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.io.Serializable;
import java.util.List;


/**
 * The type Apartment.
 * Author: Diana Neves
 */
public class Apartment extends Property implements Serializable {
    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private boolean airConditioning;
    private boolean centralHeating;
    private PropertyType propertyType;
    private Person person;

    /**
     * Instantiates a new Apartment.
     *
     * @param areaM2              the area m 2
     * @param cityCentreDistance  the city centre distance
     * @param price               the price
     * @param address             the address
     * @param photos              the photos
     * @param numberBedrooms      the number bedrooms
     * @param numberBathrooms     the number bathrooms
     * @param numberParkingSpaces the number parking spaces
     * @param airConditioning     the air conditioning
     * @param centralHeating      the central heating
     * @param person              the person
     */
    public Apartment(
            double areaM2,
            double cityCentreDistance,
            double price,
            Address address,
            List<String> photos,
            int numberBedrooms,
            int numberBathrooms,
            int numberParkingSpaces,
            boolean airConditioning,
            boolean centralHeating,
            Person person) {

        super(areaM2, cityCentreDistance,price, photos, address,person, PropertyType.APARTMENT,numberBedrooms);

        propertyType = PropertyType.APARTMENT;
        this.numberBedrooms = numberBedrooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParkingSpaces = numberParkingSpaces;
        this.airConditioning = airConditioning;
        this.centralHeating = centralHeating;

    }

    @Override
    public String toString() {
        return "Apartment{" +
                "numberBedrooms=" + numberBedrooms +
                ", numberBathrooms=" + numberBathrooms +
                ", numberParkingSpaces=" + numberParkingSpaces +
                ", airConditioning=" + airConditioning +
                ", centralHeating=" + centralHeating +
                ", propertyType=" + propertyType +
                ", person=" + person +
                '}';
    }

    /**
     * Gets number bedrooms.
     *
     * @return the number bedrooms
     */
    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    /**
     * Sets number bedrooms.
     *
     * @param numberBedrooms the number bedrooms
     */
    public void setNumberBedrooms(int numberBedrooms) {
        this.numberBedrooms = numberBedrooms;
    }

    /**
     * Gets number bathrooms.
     *
     * @return the number bathrooms
     */
    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    /**
     * Sets number bathrooms.
     *
     * @param numberBathrooms the number bathrooms
     */
    public void setNumberBathrooms(int numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
    }

    /**
     * Gets number parking spaces.
     *
     * @return the number parking spaces
     */
    public int getNumberParkingSpaces() {
        return numberParkingSpaces;
    }

    /**
     * Sets number parking spaces.
     *
     * @param numberParkingSpaces the number parking spaces
     */
    public void setNumberParkingSpaces(int numberParkingSpaces) {
        this.numberParkingSpaces = numberParkingSpaces;
    }

    /**
     * Is air conditioning boolean.
     *
     * @return the boolean
     */
    public boolean isAirConditioning() {
        return airConditioning;
    }

    /**
     * Sets air conditioning.
     *
     * @param airConditioning the air conditioning
     */
    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    /**
     * Is central heating boolean.
     *
     * @return the boolean
     */
    public boolean isCentralHeating() {
        return centralHeating;
    }

    /**
     * Sets central heating.
     *
     * @param centralHeating the central heating
     */
    public void setCentralHeating(boolean centralHeating) {
        this.centralHeating = centralHeating;
    }

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * Sets property type.
     *
     * @param propertyType the property type
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets person.
     *
     * @param person the person
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
