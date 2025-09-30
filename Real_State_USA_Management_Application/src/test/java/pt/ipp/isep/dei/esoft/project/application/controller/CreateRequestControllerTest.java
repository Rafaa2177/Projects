package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.BranchRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateRequestControllerTest {
    private CreateRequestController createRequestController;

    @BeforeEach
    public void setUp() {
        createRequestController = new CreateRequestController();
    }

    @Test
    public void testCreateProperty() {
        // Create sample data
        double areaM2 = 100.0;
        double cityCentreDistance = 5.0;
        float price = 100000.0f;
        List<String> photos = new ArrayList<>();
        photos.add("photo1.jpg");
        photos.add("photo2.jpg");
        Address address = new Address("Rua Padre Alfredo", new City(new State("Porto"), "Gondomar"), new District("Porto", new City(new State("Porto"), "Gondomar")), "12344", new State("Porto"));
        Person owner = new Person(new Email("owner@example.com"), "password", "John Doe", "AB123456", "123456789", "1234567890", Roles.AGENT);
        PropertyType propertyType = PropertyType.HOUSE;

        // Create property
        Property property = createRequestController.createProperty(areaM2, cityCentreDistance, price, photos, address, owner, propertyType);

        // Assert property creation
        assertNotNull(property);
        assertEquals(areaM2, property.getAreaM2());
        assertEquals(cityCentreDistance, property.getCityCentreDistance());
        assertEquals(price, property.getPrice());
        assertEquals(photos, property.getPhotos());
        assertEquals(address, property.getAddress());
        assertEquals(owner, property.getOwner());
        assertEquals(propertyType, property.getPropertyType());
    }

    @Test
    public void testCreateHouse() {
        // Create sample data
        double areaM2 = 150.0;
        double cityCentreDistance = 10.0;
        float price = 200000.0f;
        List<String> photos = new ArrayList<>();
        photos.add("photo1.jpg");
        photos.add("photo2.jpg");
        Address address = new Address("Rua Padre Alfredo", new City(new State("Porto"), "Gondomar"), new District("Porto", new City(new State("Porto"), "Gondomar")), "12344", new State("Porto"));
        int numberBedrooms = 3;
        int numberBathrooms = 2;
        int numberParkingSpaces = 1;
        boolean airConditioning = true;
        boolean centralHeating = true;
        boolean hasBasement = false;
        boolean hasInhabitableLoft = false;
        String hasSunExposure = "N";
        Person owner = new Person(new Email("owner@example.com"), "password", "John Doe", "AB123456", "123456789", "1234567890", Roles.AGENT);

        // Create house
        House house = createRequestController.createHouse(areaM2, cityCentreDistance, price, photos, address, numberBedrooms, numberBathrooms, numberParkingSpaces, airConditioning, centralHeating, hasBasement, hasInhabitableLoft, hasSunExposure, owner);

        // Assert house creation
        assertNotNull(house);
        assertEquals(areaM2, house.getAreaM2());
        assertEquals(cityCentreDistance, house.getCityCentreDistance());
        assertEquals(price, house.getPrice());
        assertEquals(photos, house.getPhotos());
        assertEquals(address, house.getAddress());
        assertEquals(numberBedrooms, house.getNumberBedrooms());
        assertEquals(numberBathrooms, house.getNumberBathrooms());
        assertEquals(numberParkingSpaces, house.getNumberParkingSpaces());
        assertEquals(airConditioning, house.isAirConditioning());
        assertEquals(centralHeating, house.isCentralHeating());
        assertEquals(hasBasement, house.isHasBasement());
        assertEquals(hasInhabitableLoft, house.isHasInhabitableLoft());
        assertEquals(hasSunExposure, house.getHasSunExposure());
        assertEquals(owner, house.getOwner());
    }

    @Test
    public void testCreateApartment() {
        // Create sample data
        double areaM2 = 80.0;
        double cityCentreDistance = 2.0;
        float price = 150000.0f;
        List<String> photos = new ArrayList<>();
        photos.add("photo1.jpg");
        photos.add("photo2.jpg");
        Address address = new Address("Rua Padre Alfredo", new City(new State("Porto"), "Gondomar"), new District("Porto", new City(new State("Porto"), "Gondomar")), "12344", new State("Porto"));
        int numberBedrooms = 2;
        int numberBathrooms = 1;
        int numberParkingSpaces = 1;
        boolean airConditioning = true;
        boolean centralHeating = true;
        Person person = new Person(new Email("owner@example.com"), "password", "John Doe", "AB123456", "123456789", "1234567890", Roles.AGENT);

        // Create apartment
        Apartment apartment = createRequestController.createApartment(areaM2, cityCentreDistance, price, photos, address, numberBedrooms, numberBathrooms, numberParkingSpaces, airConditioning, centralHeating, person);

        // Assert apartment creation
        assertNotNull(apartment);
        assertEquals(areaM2, apartment.getAreaM2());
        assertEquals(cityCentreDistance, apartment.getCityCentreDistance());
        assertEquals(price, apartment.getPrice());
        assertEquals(photos, apartment.getPhotos());
        assertEquals(address, apartment.getAddress());
        assertEquals(numberBedrooms, apartment.getNumberBedrooms());
        assertEquals(numberBathrooms, apartment.getNumberBathrooms());
        assertEquals(numberParkingSpaces, apartment.getNumberParkingSpaces());
        assertEquals(airConditioning, apartment.isAirConditioning());
        assertEquals(centralHeating, apartment.isCentralHeating());
        assertEquals(person, apartment.getPerson());
    }

}
