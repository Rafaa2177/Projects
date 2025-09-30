package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The type Property.
 */
public class Property implements Serializable {
    private double areaM2;
    private double cityCentreDistance;
    private List<String> photos;
    private Address address;
    private double price;
    private Person owner;
    private PropertyType propertyType;
    private int numberOfBedrooms;

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {return price;}

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Instantiates a new Property.
     *
     * @param areaM2             the area m 2
     * @param cityCentreDistance the city centre distance
     * @param price              the price
     * @param photos             the photos
     * @param address            the address
     * @param owner              the owner
     * @param propertyType       the property type
     */
    public Property(double areaM2, double cityCentreDistance, double price, List<String> photos,
                    Address address, Person owner, PropertyType propertyType) {
        if (areaM2 <= 0) {
            throw new IllegalArgumentException("Area must be greater than 0");
        }
        if (cityCentreDistance <= 0) {
            throw new IllegalArgumentException("Distance from city center must be greater than 0");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address must not be null");
        }

        this.areaM2 = areaM2;
        this.cityCentreDistance = cityCentreDistance;
        this.price = price;
        this.photos = photos;
        this.address = address;
        this.owner = owner;
        this.propertyType = propertyType;
        this.numberOfBedrooms = -1;
    }

    /**
     * Instantiates a new Property.
     *
     * @param areaM2             the area m 2
     * @param cityCentreDistance the city centre distance
     * @param price              the price
     * @param photos             the photos
     * @param address            the address
     * @param owner              the owner
     * @param propertyType       the property type
     * @param numberOfBedrooms   the number of bedrooms
     */
    public Property(double areaM2, double cityCentreDistance, double price, List<String> photos,
                    Address address, Person owner, PropertyType propertyType, int numberOfBedrooms) {
        if (areaM2 <= 0) {
            throw new IllegalArgumentException("Area must be greater than 0");
        }
        //if (cityCentreDistance <= 0) {
            //throw new IllegalArgumentException("Distance from city center must be greater than 0");
        //}
        if (address == null) {
            throw new IllegalArgumentException("Address must not be null");
        }

        this.areaM2 = areaM2;
        this.cityCentreDistance = cityCentreDistance;
        this.price = price;
        this.photos = photos;
        this.address = address;
        this.owner = owner;
        this.propertyType = propertyType;
        this.numberOfBedrooms = numberOfBedrooms;
    }

    /**
     * Gets area m 2.
     *
     * @return the area m 2
     */
    public double getAreaM2() {return areaM2;}

    /**
     * Gets city centre distance.
     *
     * @return the city centre distance
     */
    public double getCityCentreDistance() {return cityCentreDistance;}

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public Person getOwner() {return owner;}

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * Gets photos.
     *
     * @return the photos
     */
    public List<String> getPhotos() {
        return photos;
    }

    /**
     * Sets photos.
     *
     * @param photos the photos
     */
    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Double.compare(property.areaM2, areaM2) == 0 && Double.compare(property.cityCentreDistance, cityCentreDistance) == 0 && Objects.equals(photos, property.photos) && Objects.equals(address, property.address);
    }

    /**
     * Gets number of bedrooms.
     *
     * @return the number of bedrooms
     */
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    /**
     * Sets number of bedrooms.
     *
     * @param numberOfBedrooms the number of bedrooms
     */
    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaM2, cityCentreDistance, photos, address);
    }

    @Override
    public String toString() {
        return "{ " +
                "areaM2 = " + areaM2 +
                ", cityCentreDistance = " + cityCentreDistance +
                ", photos = " + photos +
                ", address = " + address +
                ", price = " + price +
                ", owner = " + owner +
                '}' +
                "\n ----------------------- ";
    }
}
