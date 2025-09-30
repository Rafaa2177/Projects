package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.BranchRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

/**
 * The type Create request controller.
 */
public class CreateRequestController {

    Repositories repositories = new Repositories();
    private BranchRepository branchRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    /**
     * Instantiates a new Create request controller.
     */
    public CreateRequestController() {
        getBranchRepository();
    }

    /**
     * Instantiates a new Create request controller.
     *
     * @param branchRepository the branch repository
     */
    public CreateRequestController (BranchRepository branchRepository){
        this.branchRepository = branchRepository;
    }

    public BranchRepository getBranchRepository(){
        if (branchRepository == null) {
            Repositories repositories = Repositories.getInstance();
            branchRepository = repositories.getBranchRepository();
        }
        return branchRepository;
    }
    private AuthenticationRepository getAuthenticationRepository(){
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Create property.
     * @param areaM2             the area m 2
     * @param cityCentreDistance the city centre distance
     * @param photos             the photos
     * @param address            the address
     * @return the property
     */
    public Property createProperty(double areaM2, double cityCentreDistance, float price, List<String> photos, Address address, Person owner, PropertyType propertyType){
       return new Property(areaM2,cityCentreDistance,price,photos,address,owner, propertyType);
    }

    /**
     * Create house.
     * @param areaM2              the area m 2
     * @param cityCentreDistance  the city centre distance
     * @param photos              the photos
     * @param address             the address
     * @param numberBedrooms      the number bedrooms
     * @param numberBathrooms     the number bathrooms
     * @param numberParkingSpaces the number parking spaces
     * @param airConditioning     the air conditioning
     * @param centralHeating      the central heating
     * @param hasBasement         the has basement
     * @param hasInhabitableLoft  the has inhabitable loft
     * @param hasSunExposure      the has sun exposure
     * @return the house
     */
    public House createHouse(double areaM2, double cityCentreDistance, float price, List<String> photos, Address address, int numberBedrooms, int numberBathrooms, int numberParkingSpaces, boolean airConditioning, boolean centralHeating, boolean hasBasement, boolean hasInhabitableLoft, String hasSunExposure,Person owner){
        return new House(areaM2,cityCentreDistance,price, photos,address,numberBedrooms,numberBathrooms,numberParkingSpaces,airConditioning,centralHeating,hasBasement,hasInhabitableLoft,hasSunExposure,owner);
    }

    /**
     * Create apartment residence.
     *

     * @param areaM2              the area m 2
     * @param cityCentreDistance  the city centre distance
     * @param photos              the photos
     * @param address             the address
     * @param numberBedrooms      the number bedrooms
     * @param numberBathrooms     the number bathrooms
     * @param numberParkingSpaces the number parking spaces
     * @param airConditioning     the air conditioning
     * @param centralHeating      the central heating
     * @return the residence
     */
    public Apartment createApartment(double areaM2, double cityCentreDistance, float price, List<String> photos, Address address, int numberBedrooms, int numberBathrooms, int numberParkingSpaces, boolean airConditioning, boolean centralHeating, Person person){
        return new Apartment(areaM2, cityCentreDistance, price, address, photos, numberBedrooms, numberBathrooms, numberParkingSpaces, airConditioning, centralHeating, person);
    }

    /**
     * Get announcements list.
     *
     * @return the list
     */
    public List<AnnouncementRequest> getAnnouncements(){
    return Repositories.getInstance().getRequestRepository().getRequests();}

    /**
     * Get agent list.
     *
     * @return the list
     */
    public List<Person> getAgentList(Store store){
        List<Person> agents = null;
        List<Person> employees = store.getEmployees();
        for (Person person:employees) {
          if ( person.getPersonRole() == Roles.AGENT){
              agents.add(person);
          }
        }
        if (agents == null) System.out.println("Agent list is null");
        return agents;
    }

   public Person getOwnerFromSession(){
       Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
       String name = getAuthenticationRepository().getCurrentUserSession().getUserName();
        Person owner = new Person(email,name);
        return owner;
   }
}
