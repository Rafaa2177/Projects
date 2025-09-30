package pt.ipp.isep.dei.esoft.project.domain;

import javafx.scene.transform.Scale;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The type Legacy file.
 */
public class LegacyFile {
    private static Person person = new Person(new Email("ola.1@this.app"), "ZXT12mp", "joão", "12345678", "123329876", "(351) 123-1234", Roles.AGENT);

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }


    /**
     * Add organizations from file.
     *
     * @param path the path
     * @throws IOException the io exception
     */
    public static void addOrganizationsFromFile(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        sc.nextLine();
        while (sc.hasNextLine()) {
            OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
            List<Organization> listOrg = getOrganization();

            String line = sc.nextLine();
            String[] arrOfString = line.split(";");
            int check = 0;
            for (Organization org : listOrg) {
                if (org.getName().equals("Name")) {
                    check = 1;
                    //break;
                }
            }
            if (check == 0) {

                Address address = new Address(arrOfString[27]);

                Organization org2 = new Organization(Integer.parseInt(arrOfString[25]), arrOfString[26], arrOfString[28],
                        arrOfString[29], address);
                org2.addEmployee(new Person(new Email(arrOfString[29]), "XZP60mp", arrOfString[26], "12345678",
                        "123329876", "(351) 123-1234", Roles.AGENT));

                Organization org3 = new Organization(Integer.parseInt(arrOfString[25]), arrOfString[26], arrOfString[28],
                        arrOfString[29], address);
                org3.addEmployee(new Person(new Email(arrOfString[29]), "XZP60mp", arrOfString[26], "12345678",
                        "123329876", "(351) 123-1234", Roles.AGENT));

                organizationRepository.add(org2);
                organizationRepository.add(org3);
            }
        }
        sc.close();
        addAnnouncementsLegacy(path);
    }

    /**
     * Gets organization.
     *
     * @return the organization
     */
    public static List<Organization> getOrganization() {
        return OrganizationRepository.getOrganizationList();
    }

    /**
     * Add announcements legacy.
     *
     * @param path the path
     * @throws IOException the io exception
     */
    public static void addAnnouncementsLegacy(String path) throws IOException {
        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        Scanner ler = new Scanner(new File(path));
        ler.nextLine();

        while (ler.hasNextLine()) {
            String line = ler.nextLine();
            String[] arrOfString = line.split(";");
            Person owner = new Person(new Email(arrOfString[4]), "XZP60mp", arrOfString[1], arrOfString[2], arrOfString[3],
                    arrOfString[5], Roles.CLIENT);
            boolean requestedSale;
            double requestedPrice;
            int requestedContract;
            requestedPrice = Double.parseDouble(arrOfString[19]);
            Commission comission = new Commission(requestedPrice, Double.parseDouble(arrOfString[20]));
            String comissionType;
            double area;
            double distanceFromCityCenter;
            boolean centralHeating = false;
            boolean airConditionating = false;
            boolean loft = false;
            boolean basement = false;

            if (arrOfString[24].equals("sale")) {
                requestedSale = true;
                requestedContract = -1;

            } else {
                requestedSale = false;
                requestedContract = Integer.parseInt(arrOfString[21]);
            }

            requestedPrice = Double.parseDouble(arrOfString[19]);
            comissionType = "%";

            boolean pend = false;
            Address adress = new Address(arrOfString[8]);

            String email = "";

            for (Organization org : getOrganization()) {
                if (org.getID() == Integer.parseInt(arrOfString[25])) {
                    email = org.getEmail();
                }
            }

            PersonRepository personRepository = Repositories.getInstance().getPersonRepository();
            Person agt = null;
            for (Person person1 : personRepository.getListPersonsRepository()) {
                if (person1.getId().getEmail().equals(email)) {
                    agt = person1;
                }
            }

            area = Double.parseDouble(arrOfString[7]);
            distanceFromCityCenter = Double.parseDouble(arrOfString[9]);
            String data = arrOfString[23];
            List<String> photos = new ArrayList<>();
            RequestType requestType = RequestType.SALE;
            if (arrOfString[24].equals("sale")) {
                requestType = RequestType.SALE;

            } else {
                requestType = RequestType.LEASE;
            }
            Status status = Status.ACCEPT;
            String[] dataSplit = arrOfString[22].split("-");

            int year = Integer.parseInt(dataSplit[2]);
            int month = Integer.parseInt(dataSplit[1]);
            int day = Integer.parseInt(dataSplit[0]);
            LocalDate date = LocalDate.of(year, month, day);


            if (arrOfString[6].equals("land")) {
                Property land = new Property(area, distanceFromCityCenter, requestedPrice, photos, adress, owner, PropertyType.LAND);
                AnnouncementRequest announcementRequest = new AnnouncementRequest(land, requestedContract, person, requestType);
                Announcement announcement = new Announcement(announcementRequest, comission, date, status);

                announcement.setDate(LocalDate.of(year, month, day));

                announcementRepository.addAnnouncement(announcement);
                //System.out.println(announcement);
                saveOrderFromLegacy(announcement, Double.parseDouble(arrOfString[19]), data, email);


            } else if (arrOfString[6].equals("apartment")) {

                if ((arrOfString[13].equals("Y")) && arrOfString[14].equals("Y")) {

                    centralHeating = arrOfString[13].matches("Y");
                    airConditionating = arrOfString[14].matches("Y");
                }
                if (arrOfString[13].equals("Y") && arrOfString[14].equals("N")) {

                    centralHeating = arrOfString[13].matches("Y");
                    airConditionating = arrOfString[14].matches("N");

                }
                if (arrOfString[13].equals("N") && arrOfString[14].equals("Y")) {

                    centralHeating = arrOfString[13].matches("N");
                    airConditionating = arrOfString[14].matches("Y");
                }
                if (arrOfString[13].equals("N") && arrOfString[14].equals("N")) {

                    centralHeating = arrOfString[13].matches("N");
                    airConditionating = arrOfString[14].matches("N");
                }


                Apartment habitation = new Apartment(area, distanceFromCityCenter, requestedPrice, adress, photos, Integer.parseInt(arrOfString[10]), Integer.parseInt(arrOfString[11]), Integer.parseInt(arrOfString[12]), airConditionating, centralHeating, owner);
                AnnouncementRequest announcementRequest = new AnnouncementRequest(habitation, requestedContract, person, requestType);
                Announcement announcement = new Announcement(announcementRequest, comission, date, status);
                announcement.setDate(LocalDate.of(year, month, day));
                announcementRepository.addAnnouncement(announcement);
                //System.out.println(announcement);
                saveOrderFromLegacy(announcement, Double.parseDouble(arrOfString[19]), data, email);

            } else if (arrOfString[6].equals("house")) {

                if ((arrOfString[13].equals("Y")) && arrOfString[14].equals("Y") &&
                        arrOfString[15].equals("Y") && arrOfString[16].equals("Y")) {

                    centralHeating = arrOfString[13].matches("Y");
                    airConditionating = arrOfString[14].matches("Y");
                    basement = arrOfString[15].matches("Y");
                    loft = arrOfString[16].matches("Y");
                }
                if (arrOfString[13].equals("Y") && arrOfString[14].equals("N") &&
                        arrOfString[15].equals("Y") && arrOfString[16].equals("N")) {

                    centralHeating = arrOfString[13].matches("Y");
                    airConditionating = arrOfString[14].matches("N");
                    basement = arrOfString[15].matches("Y");
                    loft = arrOfString[16].matches("N");

                }
                if (arrOfString[13].equals("N") && arrOfString[14].equals("Y") &&
                        arrOfString[15].equals("N") && arrOfString[16].equals("Y")) {

                    centralHeating = arrOfString[13].matches("N");
                    airConditionating = arrOfString[14].matches("Y");
                    basement = arrOfString[15].matches("N");
                    loft = arrOfString[16].matches("Y");
                }
                if (arrOfString[13].equals("N") && arrOfString[14].equals("N") &&
                        arrOfString[15].equals("N") && arrOfString[16].equals("N")) {

                    centralHeating = arrOfString[13].matches("N");
                    airConditionating = arrOfString[14].matches("N");
                    basement = arrOfString[15].matches("N");
                    loft = arrOfString[16].matches("N");
                }

                House house = new House(area, distanceFromCityCenter, requestedPrice, photos, adress, Integer.parseInt(arrOfString[10]), Integer.parseInt(arrOfString[11]), Integer.parseInt(arrOfString[12]), airConditionating, centralHeating, basement, loft, arrOfString[17], person);
                AnnouncementRequest announcementRequest = new AnnouncementRequest(house, requestedContract, person, requestType);
                Announcement announcement = new Announcement(announcementRequest, comission, date, status);
                String[] dataSpliT = arrOfString[22].split("-");
                announcement.setDate(LocalDate.of(year, month, day));
                announcementRepository.addAnnouncement(announcement);
                //System.out.println(announcement);
                saveOrderFromLegacy(announcement, Double.parseDouble(arrOfString[19]), data, email);


            }
        }

    }

    private static void saveOrderFromLegacy(Announcement announcement, double amount, String data, String email) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(email, amount, announcement);
        String[] dataSplit = data.split("-");

        int year = Integer.parseInt(dataSplit[2]);
        int month = Integer.parseInt(dataSplit[1]);
        int day = Integer.parseInt(dataSplit[0]);
        purchaseOrder.getAnnouncement().setDate(LocalDate.of(year, month, day));
        purchaseOrder.setStatus(Status.ACCEPT);


        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
        orderRepository.addOrder(purchaseOrder);


    }


}


