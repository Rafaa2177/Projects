package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

public class ApartmentTest {
    private Apartment apartment;
    private double areaM2;
    private double cityCentreDistance;
    private double price;
    private Address address;
    private List<String> photos;
    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private boolean airConditioning;
    private boolean centralHeating;
    private Person person;


    public void setUp() {
        areaM2 = 100.0;
        cityCentreDistance = 5.0;
        price = 200000.0;
        State state2 = new State("Porto");
        City city2 = new City(state2, "Gondomar");
        District district2 = new District("Porto", city2);
        address = new Address("Street", city2, district2, "1234-567", state2);
        photos = new ArrayList<>();
        numberBedrooms = 2;
        numberBathrooms = 1;
        numberParkingSpaces = 1;
        airConditioning = true;
        centralHeating = true;
        person = new Person(new Email("ok@this.app"), "781623", "dinis", "12345567","123329876", "(351) 938-4135", Roles.CLIENT) ;

        apartment = new Apartment(
                areaM2,
                cityCentreDistance,
                price,
                address,
                photos,
                numberBedrooms,
                numberBathrooms,
                numberParkingSpaces,
                airConditioning,
                centralHeating,
                person
        );
    }


    public void testGetNumberBedrooms() {
        // Arrange

        // Act
        int result = apartment.getNumberBedrooms();

        // Assert
        Assertions.assertEquals(numberBedrooms, result);
    }


    public void testSetNumberBedrooms() {
        // Arrange
        int newNumberBedrooms = 3;

        // Act
        apartment.setNumberBedrooms(newNumberBedrooms);

        // Assert
        Assertions.assertEquals(newNumberBedrooms, apartment.getNumberBedrooms());
    }


    public void testGetNumberBathrooms() {
        // Arrange

        // Act
        int result = apartment.getNumberBathrooms();

        // Assert
        Assertions.assertEquals(numberBathrooms, result);
    }

}
