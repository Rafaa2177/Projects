package pt.ipp.isep.dei.esoft.project.ui.console;
import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The type Publish announcement ui.
 */
public class PublishAnnouncementUI implements Runnable {
    /**
     * The Input.
     */
    Scanner input = new Scanner(System.in);
    private double sizeM2, distanceCentre;
    private  double price, contractDuration;
    private int numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, noOfPhotos;
    private boolean basement, inhabitableLoft, airConditioning, centralHeating;
    private String sunExposure, propertyType;
    private List<String> photographs;
    private Photographs photos;
    private PropertyType typeProperty;
    private Person person;
    private City city;
    private District district;
    private State state;


    /**
     * The Controller.
     */
    PublishAnnouncementController controller = new PublishAnnouncementController();

    @Override
    public void run() {
        requestData();
        showData();
    }

    private String requestPropertyType() {
        System.out.println("Property Type: ");
        System.out.println(Arrays.toString(PropertyType.values()));
        return input.nextLine();
    }

    private double requestSizeM2() {
        System.out.println("Insert area in m2:");
        return input.nextDouble();
    }

    private double requestDistanceCentre() {
        System.out.println("Insert distance from city centre (in km):");
        return input.nextDouble();
    }

    private float requestPrice() {
        System.out.println("Insert requested price (USD):");
        return input.nextFloat();
    }

    private void requestCommission() {
        input.nextLine();
        System.out.println("Choose commission type: " +
                "1 - Value" +
                "2 - Percentage");
        String commissionType = input.nextLine();
        if (commissionType.equals("1")) {
            System.out.println("Insert commission value:");
            Double value = input.nextDouble();
        } else if (commissionType.equals("2")) {
            System.out.println("Insert commission percentage:");
            Double percentage = input.nextDouble();
        }
    }

    private double requestContractDuration(){
        System.out.println("Type the duration of the contract in months: (example: 12)" );
        return input.nextDouble();
    }

    private int requestNumberOfBedrooms() {
        System.out.println("Insert number of bedrooms:");
        return input.nextInt();
    }

    private int requestNumberOfBathrooms() {
        System.out.println("Insert number of bathrooms:");
        return input.nextInt();
    }

    private int requestNumberOfParkingSpaces() {
        System.out.println("Insert number of parking spaces:");
        return input.nextInt();
    }

    private boolean requestBasement() {
        System.out.println("Has basement (yes/no):");
        String a = input.nextLine();
        if (a.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean requestInhabitableLoft() {
        System.out.println("Has inhabitable loft (yes/no):");
        String a = input.nextLine();
        if (a.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean requestAirConditioning() {
        System.out.println("Has air conditioning (yes/no):");
        String a = input.nextLine();
        if (a.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean requestCentralHeating() {
        input.nextLine();
        System.out.println("Has central heating (yes/no):");
        String a = input.nextLine();
        if (a.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }

    private String requestSunExposure() {
        System.out.println("Choose sun exposure orientation:" +
                "\n1 - North" +
                "\n2 - South" +
                "\n3 - West" +
                "\n4 - East");
        return input.nextLine();
    }

    private void requestAddress() {

        System.out.println("Insert state name:");
        String stateS = input.nextLine();
        state = new State(stateS);

        System.out.println("Insert city name:");
        String cityS = input.nextLine();
        city = new City(state,cityS);

        System.out.println("Insert district name:");
        String districtS = input.nextLine();
        district = new District(districtS, city);

        System.out.println("Insert street name:");
        String street = input.nextLine();

        System.out.println("Insert zipCode:");
        String zipCode = input.nextLine();

        controller.createAddress(street, city, district, zipCode, state);
    }

    private Photographs requestPhotos() {
        System.out.println("Insert number of photos you want to submit");
        noOfPhotos = input.nextInt();
        Photographs.validatePhotos(noOfPhotos);
        Photographs.uploadPhotos(noOfPhotos);
        return photos;
    }

    private void requestGeneralData() {
        //Request the location from the console
        requestAddress();
        //Request the distance from city centre from the console
        distanceCentre = requestDistanceCentre();
        //Request the price from the console
        price = requestPrice();
        //Request the commission from the console
        requestCommission();
        //Request the contract duration from the console
        contractDuration = requestContractDuration();
        //Request the Size in m2 from the console
        sizeM2 = requestSizeM2();
    }

    private void requestData() {
        //Request the property type from the console
        propertyType = requestPropertyType();

        if (propertyType.equalsIgnoreCase("land")) {
            typeProperty = PropertyType.LAND;
            requestGeneralData();

        } else if (propertyType.equalsIgnoreCase("apartment") || propertyType.equalsIgnoreCase("house")) {
            if (propertyType.equals("apartment")){
                typeProperty = PropertyType.APARTMENT;
            }
            requestGeneralData();
            //Request the number of bedrooms from the console
            numberOfBedrooms = requestNumberOfBedrooms();
            //Request the number of bathrooms from the console
            numberOfBathrooms = requestNumberOfBathrooms();
            //Request the number of parking spaces from the console
            numberOfParkingSpaces = requestNumberOfParkingSpaces();
            //Request the available equipment from the console
            centralHeating = requestCentralHeating();
            //Request the available equipment from the console
            airConditioning = requestAirConditioning();

            //Request the number of photos
            photos = requestPhotos();

            if (propertyType.equalsIgnoreCase("house")) {
                typeProperty = PropertyType.HOUSE;
                input.nextLine();
                //Request the existence of basement from the console
                basement = requestBasement();
                //Request the existence of inhabitableLoft from the console
                inhabitableLoft = requestInhabitableLoft();
                //Request the level of sun exposure from the console
                sunExposure = requestSunExposure();
                //Request the photographs from the console
            }
        }
    }

    private void showGeneralData() {
        System.out.print("\nlocalization: " + controller.getAddress());
        System.out.println("\ndistance from city centre: " + distanceCentre + "km");
        System.out.println("\nprice of the property: " + price + "$");
        System.out.print("\ncommission: " + controller.getCommission());
        System.out.println("\narea in m2: " + sizeM2 + "m2");
    }

    private void showData() {

        System.out.println("\nproperty type: " + propertyType);
        Address address = controller.getAddress();
        Commission commission = controller.getCommission();

        if (propertyType.equals("land")) {
            showGeneralData();
            input.nextLine();

            System.out.println("\nConfirm insert data? (1 - yes\n2 - no)");
            String option = input.nextLine();

            if (option.equalsIgnoreCase("1")) {

                controller.createLand(
                        address,
                        distanceCentre,
                        price,
                        photographs,
                        sizeM2,
                        person,
                        commission,
                        contractDuration
                        );
                System.out.println("\nLand added");

            } else {
                System.out.println("\nLand not added");
            }

        } else if (propertyType.equals("residence") || propertyType.equals("house")) {

            showGeneralData();
            System.out.println("\nnumber of bedrooms: " + numberOfBedrooms);
            System.out.println("\nnumber of bathrooms " + numberOfBathrooms);
            System.out.println("\nair conditioning: " + airConditioning);
            System.out.println("\ncentral heating: " + centralHeating);
            System.out.println("\nnumber of parking spaces: " + numberOfParkingSpaces);
            System.out.println("\nnumber of photographs: " + noOfPhotos);

            if (propertyType.equals("residence")) {
                input.nextLine();

                System.out.println("\nConfirm insert data? (1 - yes\n2 - no)");
                String option = input.nextLine();

                if (option.equalsIgnoreCase("1")) {

                    controller.createResidence(
                            sizeM2,
                            distanceCentre,
                            price,
                            address,
                            photographs,
                            numberOfBedrooms,
                            numberOfBathrooms,
                            numberOfParkingSpaces,
                            airConditioning,
                            centralHeating,
                            person,
                            commission,
                            contractDuration);
                    System.out.println("\nApartment added");

                } else {
                    System.out.println("\nApartment not added");
                }
            }

            if (propertyType.equals("house")) {

                System.out.println("\nproperty has basement: " + basement);
                System.out.println("\nproperty has inhabitable loft: " + inhabitableLoft);
                System.out.println("\nproperty has sun exposure in which direction: " + sunExposure);
                System.out.print("\nnumber of photographs: " + noOfPhotos);

                System.out.println("\nConfirm insert data? (1 - yes\n2 - no)");
                String option = input.nextLine();

                if (option.equalsIgnoreCase("yes")) {
                    controller.createHouse(
                            sizeM2,
                            distanceCentre,
                            price,
                            photographs,
                            address,
                            numberOfBedrooms,
                            numberOfBathrooms,
                            numberOfParkingSpaces,
                            airConditioning,
                            centralHeating,
                            basement,
                            inhabitableLoft,
                            sunExposure,
                            person,
                            commission,
                            contractDuration);
                    System.out.println("\nHouse added");

                } else  {
                    System.out.println("\nHouse not added");
                }
            }
        }
    }
}

