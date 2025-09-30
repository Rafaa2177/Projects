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
import pt.ipp.isep.dei.esoft.project.domain.LegacyFile;

public class PurchaseOrderTest {
    private Announcement announcement;
    private Person client;
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
        Commission comission = new Commission(requestedPrice,perc);
        int year = 0;
        int month = 0;
        int day = 0;
        LocalDate date1 = LocalDate.of(year,month,day);
        Status status = Status.ACCEPT;

        Property land = new Property(area, distanceFromCityCenter, requestedPrice, photos, adress, owner, PropertyType.LAND);
        AnnouncementRequest announcementRequest = new AnnouncementRequest(land, requestedContract, legacyFile.getPerson(), requestType);
        announcement = new Announcement(announcementRequest,comission, date1,status);

        client = (new Person(new Email("ok@this.app"), "781623", "beatriz", "32145567","123329768", "(351) 939-4135", Roles.CLIENT));
    }


    public void testConstructor_ValidArguments_CreatesPurchaseOrder() {
        // Arrange
        String email = "test@example.com";
        double amountOffered = 100.0;

        // Act
        PurchaseOrder purchaseOrder = new PurchaseOrder(email, amountOffered, announcement);

        // Assert
        Assertions.assertEquals(email, purchaseOrder.getClient());
        Assertions.assertEquals(amountOffered, purchaseOrder.getAmountOfered());
        Assertions.assertEquals(1, purchaseOrder.getIdOrder());
        Assertions.assertEquals(Status.PENDING, purchaseOrder.getStatus());
        Assertions.assertEquals(announcement, purchaseOrder.getAnnouncement());
    }


    public void testSetStatus_ValidStatus_SetsStatus() {
        // Arrange
        String email = "test@example.com";
        double amountOffered = 100.0;
        PurchaseOrder purchaseOrder = new PurchaseOrder(email, amountOffered, announcement);

        // Act
        purchaseOrder.setStatus(Status.ACCEPT);

        // Assert
        Assertions.assertEquals(Status.ACCEPT, purchaseOrder.getStatus());
    }


    public void testSetDate_ValidDate_SetsDate() {
        // Arrange
        String email = "test@example.com";
        double amountOffered = 100.0;
        PurchaseOrder purchaseOrder = new PurchaseOrder(email, amountOffered, announcement);
        LocalDate date = LocalDate.of(2022, 1, 1);

        // Act
        purchaseOrder.setDate(date);

        // Assert
        Assertions.assertEquals(date, purchaseOrder.getAnnouncement().getDate());
    }


    public void testCompareTo_LowerAmountOffered_ReturnsPositiveValue() {
        // Arrange
        String email1 = "test1@example.com";
        double amountOffered1 = 100.0;
        PurchaseOrder purchaseOrder1 = new PurchaseOrder(email1, amountOffered1, announcement);

        String email2 = "test2@example.com";
        double amountOffered2 = 200.0;
        PurchaseOrder purchaseOrder2 = new PurchaseOrder(email2, amountOffered2, announcement);

        // Act
        int result = purchaseOrder1.compareTo(purchaseOrder2);

        // Assert
        Assertions.assertTrue(result > 0);
    }


    public void testToString_ReturnsExpectedString() {
        // Arrange
        String email = "test@example.com";
        double amountOffered = 100.0;
        PurchaseOrder purchaseOrder = new PurchaseOrder(email, amountOffered, announcement);
        purchaseOrder.setStatus(Status.ACCEPT);

        // Act
        String result = purchaseOrder.toString();

        // Assert
        String expected = "Id order: 1\nApartment: /* endereço do apartamento */" +
                "\nClient Email: test@example.com\nAmount Offered: 100.0\nStatus: ACCEPTED";
        Assertions.assertEquals(expected, result);
    }


}
