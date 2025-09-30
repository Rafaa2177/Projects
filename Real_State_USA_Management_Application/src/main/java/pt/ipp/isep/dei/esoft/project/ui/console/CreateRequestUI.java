package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.repository.BranchRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The type Create request ui.
 */
public class CreateRequestUI implements Runnable {
    private Property property;
    private Property house;
    private Property apartment;
    private PropertyType propertyType;
    private RequestType requestType;
    private String sunExposure;
    private double distanceCityCentre,areaM2;
    private float price;
    private int numberBedrooms, numberParking, numberBathrooms, noOfPhotos, rentPrice = 0;
    private boolean basement, centralHeating, inhabitableLoft , equipment, airConditioning;
private Store store;
    private Photographs photos;
    private Address address;
    private Person owner,agent;
    private LocalDate date;
    private double contractDuration = -1;

    /**
     * The Controller.
     */
    CreateRequestController Controller = new CreateRequestController();

    private CreateRequestController getController() {
        return Controller;
    }

    public static final Scanner input = new Scanner(System.in);

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println("----------- Publish Property ------------");
        store = requestStore();
        agent = requestAgent();
        requestRequestType();
        requestData();
        requestPropertyInfo();
        confirmData();
    }

    private Person requestAgent() {
        int contador = 0;
        List<Person> agentList = null;
        for (Person agent : store.getEmployees()){
            if (agent.getPersonRole() == Roles.AGENT){
                System.out.println(contador + "º - " + agent);
                agentList.add(agent);
            }
        }
        System.out.println("Pick an agent by index:");
        int pick = input.nextInt();
        return agentList.get(pick - 1);
    }

    public Store requestStore(){
       BranchRepository branchRepository = Controller.getBranchRepository();
       List <Store> storeList = branchRepository.getStoreList();
        int counter = 1;
        for ( Store store : storeList) {
            System.out.println(counter + " - " + store);
        }
        System.out.println("Pick a store by the index:");
        return storeList.get(input.nextInt() - 1);
    }
    private void requestPropertyInfo() {
        System.out.println("Which property type does your property fill in?");
        System.out.println("1 - Land");
        System.out.println("2 - House");
        System.out.println("3 - Apartment");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
              createLand();
            case 2:
                requestDataResidence();
                requestDataHouse();
                createHouse();
            case 3:
                requestDataResidence();
                createApartment();
        }
    }


    /**
     *Asks the user to confirm the data entered for a property announcement and prints the details entered.
     *If the property type is not "Land", it also prints additional details based on the habitation type.
     *If the user confirms the data is correct, the method prints a message saying the announcement was published correctly.
     *If the user indicates the data is incorrect, the method runs again.
     */
    private void confirmData() {
        System.out.println("Is this Data correct?(true/false)");
        System.out.println("Area in m2: " + areaM2);
        System.out.println("Request type: " + requestType);
        System.out.println("Address: " + address);
        boolean confirmation = input.nextBoolean();
        if (confirmation){
            if (property != null){
                publishLand();
            }else if (apartment != null){
                publishApartment();
            }else if (house != null){
                publishHouse();
            }

        }
    }




    private void requestRequestType() {
        try {
            int choice = -1;
            while (choice != 1 && choice != 2) {
                System.out.println("Input a option please");
                System.out.println(("1 - Sale"));
                System.out.println("2 - Lease");
                choice = input.nextInt();
            }
            if (choice == 1) {
                requestType = RequestType.SALE;
            }
            if (choice == 2) {
                requestType = RequestType.LEASE;
            }
        }
        catch(InputMismatchException e){
            System.out.println("Input was not an integer");
        }

    }

    /**
     * Request data.
     */
    public void requestData() {
        if (requestType == RequestType.SALE){
            price = requestPrice();
            input.nextLine();
        }
        if (requestType == RequestType.LEASE){
            price = requestPrice();
            rentPrice = requestRentPrice();
            input.nextLine();
        }
        areaM2 = requestAreaM2();
        distanceCityCentre = requestDistanceCityCentre();
        address = requestAddress();
        photos = requestPhotos();
        date = getDate();
        owner = Controller.getOwnerFromSession();
    }

    private int requestRentPrice() {
        System.out.println("Input rent price(in USD):");
        input.nextLine();
        return input.nextInt();
    }

    private LocalDate getDate() {
       return LocalDate.now();
    }

    /**
     * Check property type.
     */


    private void showAgentList(Store store) {
        List<Person> agentList = Controller.getAgentList(store);
        int contador = 1;
        for (Person person : agentList) {
            System.out.printf(contador + "º - ");
            System.out.println(person.toString());
            contador++;
        }
    }

    /**
     * Create land.
     */
    public void createLand(){
        property = Controller.createProperty(areaM2,distanceCityCentre,price,photos.getPhotos(),address,owner, PropertyType.LAND);
    }
    private void createHouse() {
        house = Controller.createHouse(areaM2,distanceCityCentre,price,photos.getPhotos(),address,numberBedrooms,numberBathrooms,numberParking,airConditioning,centralHeating,basement,inhabitableLoft,sunExposure,owner);
    }
    private void createApartment() {
        apartment = Controller.createApartment(areaM2,distanceCityCentre,price, photos.getPhotos(),address, numberBedrooms, numberBathrooms, numberParking, airConditioning, centralHeating, owner);
    }

    /**
     * Publish land.
     */
    public void publishLand(){
        createLand();
        AnnouncementRequest request = new AnnouncementRequest(property ,contractDuration, agent, requestType);
        request.setDate(date);
        Repositories.getInstance().getRequestRepository().addRequest(request);
    }
    private void publishHouse() {
        createHouse();
        AnnouncementRequest request = new AnnouncementRequest(house,contractDuration,agent,requestType);
        request.setDate(date);
        Repositories.getInstance().getRequestRepository().addRequest(request);
    }
    private void publishApartment() {
        createApartment();
        AnnouncementRequest request = new AnnouncementRequest(apartment,contractDuration,agent,requestType);
        request.setDate(date);
        Repositories.getInstance().getRequestRepository().addRequest(request);
    }
    private Person getOwnerFromSession() {
        Person owner = Controller.getOwnerFromSession();
        return owner;
    }
    private Double requestAreaM2() {
        System.out.println("Area in M2:");
        return input.nextDouble();
    }

    private Photographs requestPhotos() {
        System.out.println("Insert number of photos you want to submit");
        noOfPhotos = input.nextInt();
        Photographs.validatePhotos(noOfPhotos);
        Photographs.uploadPhotos(noOfPhotos);
        return photos;
    }

    private void requestDataResidence() {
        numberBedrooms = requestNumberBedrooms();

        numberParking = requestNumberParking();

        numberBathrooms = requestNumberBathrooms();

        requestEquipment();
    }
    private void requestDataHouse() {
        basement = requestBasement();

        inhabitableLoft = requestInhabitableLoft();

        sunExposure = requestSunExposure();
    }
    private Address requestAddress() {
        System.out.println("Address:");
        System.out.println("State:");
        String state = input.nextLine();
        State state1 = new State(state);
        System.out.println("City:");
        String city = input.nextLine();
        City city1 = new City(state1,city);
        System.out.println("District:");
        String district = input.nextLine();
        District district1 = new District(district, city1);
        System.out.println("Street name:");
        input.nextLine();
        String street = input.nextLine();
        System.out.println("Zip Code:");
        String zipCode = input.nextLine();

        address = new Address(street,city1,district1,zipCode,state1);
        return address;
    }

    private Double requestDistanceCityCentre() {
        System.out.println("Distance from city centre(in km):");
        return input.nextDouble();
    }
    private float requestPrice() {
        System.out.println("Please input the property's evaluation price (in USD):");
        return  input.nextFloat();
    }
    private Integer requestNumberBedrooms() {
        System.out.println("Number of bedrooms:");
        return input.nextInt();
    }
    private Integer requestNumberParking() {
        System.out.println("Number of parking spots:");
        return input.nextInt();
    }
    private Integer requestNumberBathrooms() {
        System.out.println("Number of bathrooms:");
        return input.nextInt();
    }

    private void requestEquipment() {
        System.out.println("Equipment(true/false):");
        equipment = input.nextBoolean();
        if (equipment){
            input.nextLine();
            System.out.println("Has Central Heating?(true/false)");
            centralHeating = input.nextBoolean();
            System.out.println("Has air conditioning?(true/false)");
            airConditioning = input.nextBoolean();
        }
    }
    private Boolean requestBasement() {
        System.out.println("Basement(true/false):");
        return input.nextBoolean();
    }
    private Boolean requestInhabitableLoft() {
        System.out.println("Inhabitable loft(true/false):");
        return input.nextBoolean();
    }
    private String requestSunExposure() {
        input.nextLine();
        System.out.println("Sun exposure (N,W,E,S):");
        return input.nextLine();
    }




    /**
     * Sets property type.
     *
     * @param propertyType the property type
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
}
