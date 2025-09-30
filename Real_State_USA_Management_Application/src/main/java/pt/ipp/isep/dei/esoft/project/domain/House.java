package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.io.Serializable;
import java.util.List;

/**
 * The type House.
 * Author:Luis Aquino
 */
public class House extends Property implements Serializable {
    private PropertyType propertyType;
    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private boolean airConditioning;
    private boolean centralHeating;
    private boolean hasBasement;
    private boolean hasInhabitableLoft;
    private String hasSunExposure;


    /**
     * Instantiates a new House.
     * @param areaM2              the area m 2
     * @param cityCentreDistance  the city centre distance
     * @param photos              the photos
     * @param address             the address
     * @param numberBedrooms      the number bedrooms
     * @param numberBathrooms     the number bathrooms
     * @param numberParkingSpaces the number parking spaces
     * @param airConditioning     the air conditioning
     * @param centralHeating      the central heating
     * @param hasBasement         the has basement
     * @param hasInhabitableLoft  the has inhabitable loft
     * @param hasSunExposure      the has sun exposure
     */
    public House(double areaM2, double cityCentreDistance, double price,  List<String> photos,
                 Address address, int numberBedrooms, int numberBathrooms, int numberParkingSpaces,
                 boolean airConditioning, boolean centralHeating, boolean hasBasement,
                 boolean hasInhabitableLoft, String hasSunExposure, Person person) {
        super(
                areaM2,
                cityCentreDistance,
                price,
                photos,
                address,
                person,
                PropertyType.HOUSE,
                numberBedrooms);

        propertyType = PropertyType.HOUSE;
        this.numberBedrooms = numberBedrooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParkingSpaces = numberParkingSpaces;
        this.airConditioning = airConditioning;
        this.centralHeating = centralHeating;
        this.hasBasement = hasBasement;
        this.hasInhabitableLoft = hasInhabitableLoft;
        this.hasSunExposure = hasSunExposure;
    }

    @Override
    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    public void setNumberBedrooms(int numberBedrooms) {
        this.numberBedrooms = numberBedrooms;
    }

    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    public void setNumberBathrooms(int numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
    }

    public int getNumberParkingSpaces() {
        return numberParkingSpaces;
    }

    public void setNumberParkingSpaces(int numberParkingSpaces) {
        this.numberParkingSpaces = numberParkingSpaces;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isCentralHeating() {
        return centralHeating;
    }

    public void setCentralHeating(boolean centralHeating) {
        this.centralHeating = centralHeating;
    }

    public boolean isHasBasement() {
        return hasBasement;
    }

    public void setHasBasement(boolean hasBasement) {
        this.hasBasement = hasBasement;
    }

    public boolean isHasInhabitableLoft() {
        return hasInhabitableLoft;
    }

    public void setHasInhabitableLoft(boolean hasInhabitableLoft) {
        this.hasInhabitableLoft = hasInhabitableLoft;
    }

    public String getHasSunExposure() {
        return hasSunExposure;
    }

    public void setHasSunExposure(String hasSunExposure) {
        this.hasSunExposure = hasSunExposure;
    }
}


