package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRequestTest {

    private AnnouncementRequest request;
    private Property property;
    private double contractDuration;
    private Person agent;
    private RequestType requestType;
    private LocalDate date;
    private Status status;


    public void setUp() {
        double area = 0;
        double distanceFromCityCenter = 0;
        double requestedPrice = 0;
        List<String> photos = new ArrayList<>();
        State state2 = new State("Porto");
        City city2 = new City(state2, "Gondomar");
        District district2 = new District("Porto", city2);
        Address adress = new Address("Rua Padre Alfredo", city2, district2, "12344", state2);
        Person owner = new Person(new Email("ok@this.app"), "781623", "bianca", "12345567","123329876", "(351) 938-4135", Roles.CLIENT) ;

        property = new Property(area, distanceFromCityCenter, requestedPrice, photos, adress, owner, PropertyType.LAND);
        contractDuration = 12.0;
        agent = new Person(new Email("ok@this.app"), "781623", "joao", "12345567","123329876", "(351) 938-4135", Roles.CLIENT) ;
        requestType = RequestType.SALE;
        date = LocalDate.now();
        status = Status.PENDING;

        request = new AnnouncementRequest(property, contractDuration, agent, requestType);
    }


    public void testGetProperty() {
        // Arrange

        // Act
        Property result = request.getProperty();

        // Assert
        Assertions.assertEquals(property, result);
    }


    public void testSetProperty() {
        double area = 0;
        double distanceFromCityCenter = 0;
        double requestedPrice = 0;
        List<String> photos = new ArrayList<>();
        State state1 = new State("Porto");
        City city1 = new City(state1, "Gondomar");
        District district1 = new District("Porto", city1);
        Address adress = new Address("Rua Padre Alfredo", city1, district1, "12344", state1);
        Person owner = new Person(new Email("ok@this.app"), "781623", "joana", "12345567","123329876", "(351) 938-4135", Roles.CLIENT) ;
        // Arrange
        Property newProperty = new Property(area,distanceFromCityCenter,requestedPrice,photos, adress,owner, PropertyType.LAND);

        // Act
        request.setProperty(newProperty);

        // Assert
        Assertions.assertEquals(newProperty, request.getProperty());
    }


    public void testGetPrice() {
        // Arrange
        double expectedPrice = property.getPrice();

        // Act
        double result = request.getPrice();

        // Assert
        Assertions.assertEquals(expectedPrice, result);
    }



}

