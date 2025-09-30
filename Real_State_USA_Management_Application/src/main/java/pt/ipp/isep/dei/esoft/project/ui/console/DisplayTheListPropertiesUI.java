package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DisplayTheListPropertyController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.enums.FilterCriteria;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;
import pt.ipp.isep.dei.esoft.project.domain.enums.SortOrder;

import java.util.List;
import java.util.Scanner;

public class
DisplayTheListPropertiesUI implements Runnable {
    Scanner input = new Scanner(System.in);
    private int numberOfBedrooms;
    private PropertyType propertyType;
    private RequestType requestType;
    private FilterCriteria filterCriteria;

    DisplayTheListPropertyController controller = new DisplayTheListPropertyController();

    public void run() {
        requestData();
    }

    private void requestData() {

        propertyType = requestTypeOfProperty();
        requestType = requestTypeOfRequest();
//this requests the type of property from the console
        if (propertyType != PropertyType.LAND) {
//if the user wants to display a land, he will only have to tell if he wants to lease or sale
//in case the user wants a habitation, we will ask the type of habitation(apartment or house),
//if he wants to lease or sale and how many bedrooms he wants

////requests the type of habitation from the console (land or habitation)
////requests the type of request (sale or lease)
            numberOfBedrooms = selectNumberOfBedrooms();


//requests the request (lease or sale) but in this  casa, to the LAND
        }

        filterCriteria = requestFilterCriteria();
        SortOrder sortOrder = requestSortOrder();

        List<Announcement> results = controller.getPropertyListBySearchCriteria(propertyType, requestType, numberOfBedrooms, filterCriteria, sortOrder);
        //Imprimir
        printResults(results);
    }

    private void printResults(List<Announcement> results) {
        if (results.isEmpty()) {
            System.out.println("There are no available properties");
        } else {
            for (Announcement announcement : results) {
                Address address = announcement.getRequest().getProperty().getAddress();

                System.out.println("City: " + address.getCity());
                System.out.println("State: " + address.getState());

                System.out.println("Price: " + announcement.getRequest().getProperty().getPrice());
                System.out.println();
                System.out.println("-------------------------------------");
                System.out.println();
            }
        }
    }

    public PropertyType requestTypeOfProperty() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Type of Property:");
        System.out.println();
        int counter = 1;
//for loop to iterate between the different words within the HabitationType enumeration
        for (PropertyType t : PropertyType.values()) {
            System.out.println(counter + ". " + t);
            counter++;
        }
        System.out.println();
        System.out.print("Choose: ");
        int userInput = input.nextInt();

        if (userInput == 1) {
            return PropertyType.LAND;
        } else if (userInput == 2) {
            return PropertyType.HOUSE;
        } else if (userInput == 3) {
            return PropertyType.APARTMENT;
        } else {
            System.out.println("Invalid input: " + userInput);
            return null;
        }
    }

  /*  public PropertyType requestTypeOfHabitation() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Type of Habitation:");
        System.out.println();
//for loop to iterate between the different words within the HabitationType enumeration
        for (PropertyType t : PropertyType.values()) {
            System.out.println(t);
        }
        System.out.println();
        System.out.print("Choose:");
        String userInput = input.nextLine();

        if (userInput.equalsIgnoreCase("Apartment")) {
            return PropertyType.APARTMENT;
        } else if (userInput.equalsIgnoreCase("House")) {
            return PropertyType.HOUSE;
        } else {
            System.out.println("Invalid input: " + userInput);
            return null;
        }
    }*/

    public RequestType requestTypeOfRequest() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Type of Request:");
        System.out.println();
//for loop to iterate between the different words within the HabitationType enumeration
        for (RequestType t : RequestType.values()) {
            System.out.println(t);
        }
        System.out.println();
        System.out.print("Choose:");
        String userInput = input.nextLine();

        if (userInput.equalsIgnoreCase("sale")) {
            return RequestType.SALE;
        } else if (userInput.equalsIgnoreCase("lease")) {
            return RequestType.LEASE;
        } else {
            System.out.println("Invalid input: " + userInput);
            return null;
        }
    }

    public int selectNumberOfBedrooms() {
        System.out.println("How many bedrooms do you want your property to have? : (1 to 5?)");
        numberOfBedrooms = Integer.parseInt(input.nextLine());
        return numberOfBedrooms;
    }

    public FilterCriteria requestFilterCriteria() {
        System.out.println("Filter Criteria:");

        for (FilterCriteria t : FilterCriteria.values()) {
            System.out.println(t);
        }
        System.out.println();
        System.out.print("Choose:");
        String userInput = input.nextLine();

        if (userInput.equalsIgnoreCase("price")) {
            return FilterCriteria.PRICE;
        } else if (userInput.equalsIgnoreCase("city")) {
            return FilterCriteria.CITY;
        } else if (userInput.equalsIgnoreCase("state")) {
            return FilterCriteria.STATE;
        } else {
            System.out.println("Invalid input: " + userInput);
            return null;
        }
    }

    public SortOrder requestSortOrder() {
        System.out.println("Sort Order:");

        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        String option = input.nextLine();
        if (option.equalsIgnoreCase("1")) {
            return SortOrder.ASCENDING;
        } else if (option.equalsIgnoreCase("2")) {
            return SortOrder.DESCENDING;
        } else {
            System.out.println("invalid input");
            return null;
        }
    }
}