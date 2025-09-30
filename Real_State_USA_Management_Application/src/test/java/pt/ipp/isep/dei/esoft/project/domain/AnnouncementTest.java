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


public class AnnouncementTest {
    private Announcement announcement;
    private AnnouncementRequest request;
    private Commission commission;
    private LocalDate date;
    private Status status;
    private Person agent;
    LegacyFile legacyFile = new LegacyFile();


    public void setUp() {
        double area = 0;
        double distanceFromCityCenter = 0;
        double requestedPrice = 0;
        double perc = 0;
        List<String> photos = new ArrayList<>();
        State state1 = new State("Porto");
        City city1 = new City(state1, "Gondomar");
        District district1 = new District("Porto", city1);
        Address adress = new Address("Rua Padre Alfredo", city1, district1, "12344", state1);
        Person owner = new Person(new Email("ok@this.app"), "781623", "joana", "12345567","123329876", "(351) 938-4135", Roles.CLIENT) ;
        int requestedContract = 0;
        RequestType requestType = RequestType.LEASE;
        Property land = new Property(area, distanceFromCityCenter, requestedPrice, photos, adress, owner, PropertyType.LAND);
        request = new AnnouncementRequest(land, requestedContract, legacyFile.getPerson(), requestType);
        commission = new Commission(requestedPrice,perc);
        date = LocalDate.now();
        status = Status.PENDING;
        agent = (new Person(new Email("ok@this.app"), "781623", "beatriz", "32145567","123329768", "(351) 939-4135", Roles.CLIENT));

        announcement = new Announcement(request, commission, date, status);
    }


    public void testGetRequest() {
        // Arrange

        // Act
        AnnouncementRequest result = announcement.getRequest();

        // Assert
        Assertions.assertEquals(request, result);
    }


    public void testSetRequest() {
        double area = 0;
        double distanceFromCityCenter = 0;
        double requestedPrice = 0;
        List<String> photos = new ArrayList<>();
        State state1 = new State("Porto");
        City city1 = new City(state1, "Gondomar");
        District district1 = new District("Porto", city1);
        Address adress = new Address("Rua Padre Alfredo", city1, district1, "12344", state1);
        Person owner = new Person(new Email("ok@this.app"), "781623", "joana", "12345567","123329876", "(351) 938-4135", Roles.CLIENT) ;
        int requestedContract = 0;
        RequestType requestType = RequestType.LEASE;
        Property land = new Property(area, distanceFromCityCenter, requestedPrice, photos, adress, owner, PropertyType.LAND);

        // Arrange
        AnnouncementRequest newRequest = new AnnouncementRequest(land, requestedContract, legacyFile.getPerson(), requestType);

        // Act
        announcement.setRequest(newRequest);

        // Assert
        Assertions.assertEquals(newRequest, announcement.getRequest());
    }

/*
    public void testGetCommission() {
        // Arrange

        // Act
        Commission result = announcement.getComission();

        // Assert
        Assertions.assertEquals(commission, result);
    }
 */


}


