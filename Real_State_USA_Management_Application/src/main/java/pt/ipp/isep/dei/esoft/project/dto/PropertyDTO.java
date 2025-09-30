package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.util.List;

public class PropertyDTO {
    private double areaM2;
    private double cityCentreDistance;
    private List<String> photos;
    private String address;
    private double price;
    private String owner;
    private PropertyType propertyType;

    // Constructors
    public PropertyDTO() {
    }

    public PropertyDTO(double areaM2, double cityCentreDistance, double price, List<String> photos, String address, String owner, PropertyType propertyType) {
        this.areaM2 = areaM2;
        this.cityCentreDistance = cityCentreDistance;
        this.price = price;
        this.photos = photos;
        this.address = address;
        this.owner = owner;
        this.propertyType = propertyType;
    }

    // Getters and Setters
    public double getArea() {
        return areaM2;
    }

    public void setAreaM2(double areaM2) {
        this.areaM2 = areaM2;
    }

    public double getCityCentreDistance() {
        return cityCentreDistance;
    }

    public void setCityCentreDistance(double cityCentreDistance) {
        this.cityCentreDistance = cityCentreDistance;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public String toString() {
        return "PropertyDTO{" +
                "areaM2=" + areaM2 +
                ", cityCentreDistance=" + cityCentreDistance +
                ", photos=" + photos +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", owner='" + owner + '\'' +
                ", propertyType=" + propertyType +
                '}';
    }
}
