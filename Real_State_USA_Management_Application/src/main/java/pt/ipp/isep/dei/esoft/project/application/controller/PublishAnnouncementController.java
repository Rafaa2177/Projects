package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.ApartmentRepository;
import pt.ipp.isep.dei.esoft.project.repository.HouseRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.time.LocalDate;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.domain.enums.RequestType.SALE;

/**
 * Controller class for publishing announcements.
 * Author: Diana Neves
 */
public class PublishAnnouncementController {

    private Address address;
    private Commission commission;
    private Repositories repositories;
    private Photographs photographs;
    private LocalDate currentDate;
    private HouseRepository houseRepository = new HouseRepository();
    private ApartmentRepository apartmentRepository = new ApartmentRepository();
    private AnnouncementRepository announcementRepository = new AnnouncementRepository();
    private Person agent;

    /**
     * Creates an address.
     *
     * @param street   The street of the address.
     * @param city     The city of the address.
     * @param district The district of the address.
     * @param zipCode  The ZIP code of the address.
     * @param state    The state of the address.
     * @return The created address.
     */
    public Address createAddress(String street, City city, District district, String zipCode, State state) {
        this.address = new Address(street, city, district, zipCode, state);
        return this.address;
    }

    /**
     * Creates a property.
     *
     * @param price        The price of the property.
     * @param sizeM2       The size of the property in square meters.
     * @param distanceCentre    The distance from the property to the city center.
     * @param photos       The list of photographs of the property.
     * @param location     The address location of the property.
     * @param person       The person associated with the property.
     * @param propertyType The type of the property.
     * @return The created property.
     */
    public Property createProperty(double price, double sizeM2, double distanceCentre, List<String> photos,
                                   Address location, Person person, PropertyType propertyType) {
        return new Property(
                sizeM2,
                distanceCentre,
                price,
                photos,
                location,
                person,
                propertyType);
    }

    /**
     * Creates a land and its associated announcement.
     *
     * @param address          The address of the land.
     * @param distanceCentre   The distance from the land to the city center.
     * @param price            The price of the land.
     * @param photographs      The list of photographs of the land.
     * @param area             The area of the land.
     * @param person           The person associated with the land.
     * @param commission       The commission associated with the announcement.
     * @param contractDuration The contract duration of the announcement.
     */
    public void createLand(
            Address address,
            double distanceCentre,
            double price,
            List<String> photographs,
            double area,
            Person person,
            Commission commission,
            double contractDuration
    ) {
        Land land = new Land(
                area,
                distanceCentre,
                price,
                photographs,
                address,
                person
        );
        AnnouncementRequest announcementRequest = new AnnouncementRequest(land, contractDuration, person, SALE);
        announcementRepository.addAnnouncementRequest(announcementRequest);
        Announcement announcement = new Announcement(announcementRequest, commission, currentDate, Status.ACCEPT);
        announcementRepository.addAnnouncement(announcement);
    }

    /**
     * Creates a residence (apartment) and its associated announcement.
     *
     * @param areaM2           The area of the apartment in square meters.
     * @param cityCentre       The distance from the apartment to the city center.
     * @param price            The price of the apartment.
     * @param address          The address of the apartment.
     * @param photos           The list of photographs of the apartment.
     * @param numberOfBedrooms The number of bedrooms in the apartment.
     * @param numberOfBathrooms The number of bathrooms in the apartment.
     * @param numberOfParkingSpaces The number of parking spaces in the apartment.
     * @param airConditioning  Indicates if the apartment has air conditioning.
     * @param centralHeating   Indicates if the apartment has central heating.
     * @param person           The person associated with the apartment.
     * @param commission       The commission associated with the announcement.
     * @param contractDuration The contract duration of the announcement.
     */
    public void createResidence(
            double areaM2,
            double cityCentre,
            double price,
            Address address,
            List<String> photos,
            int numberOfBedrooms,
            int numberOfBathrooms,
            int numberOfParkingSpaces,
            boolean airConditioning,
            boolean centralHeating,
            Person person,
            Commission commission,
            double contractDuration) {

        Apartment apartment = new Apartment(
                areaM2,
                cityCentre,
                price,
                address,
                photos,
                numberOfBedrooms,
                numberOfBathrooms,
                numberOfParkingSpaces,
                airConditioning,
                centralHeating,
                person
        );

        AnnouncementRequest announcementRequest = new AnnouncementRequest(apartment, contractDuration, person, SALE);
        announcementRepository.addAnnouncementRequest(announcementRequest);
        Announcement announcement = new Announcement(announcementRequest, commission, currentDate, Status.ACCEPT);
        announcementRepository.addAnnouncement(announcement);
    }

    /**
     * Creates a house and its associated announcement.
     *
     * @param areaM2             The area of the house in square meters.
     * @param cityCentreDistance The distance from the house to the city center.
     * @param price              The price of the house.
     * @param photos             The list of photographs of the house.
     * @param address            The address of the house.
     * @param numberBedrooms     The number of bedrooms in the house.
     * @param numberBathrooms    The number of bathrooms in the house.
     * @param numberParkingSpaces The number of parking spaces in the house.
     * @param airConditioning    Indicates if the house has air conditioning.
     * @param centralHeating     Indicates if the house has central heating.
     * @param hasBasement        Indicates if the house has a basement.
     * @param hasInhabitableLoft Indicates if the house has an inhabitable loft.
     * @param hasSunExposure     The sun exposure of the house.
     * @param person             The person associated with the house.
     * @param commission         The commission associated with the announcement.
     * @param contractDuration   The contract duration of the announcement.
     */
    public void createHouse(
            double areaM2,
            double cityCentreDistance,
            double price,
            List<String> photos,
            Address address,
            int numberBedrooms,
            int numberBathrooms,
            int numberParkingSpaces,
            boolean airConditioning,
            boolean centralHeating,
            boolean hasBasement,
            boolean hasInhabitableLoft,
            String hasSunExposure,
            Person person,
            Commission commission,
            double contractDuration) {

        House house = new House(
                areaM2,
                cityCentreDistance,
                price,
                photos,
                address,
                numberBedrooms,
                numberBathrooms,
                numberParkingSpaces,
                airConditioning,
                centralHeating,
                hasBasement,
                hasInhabitableLoft,
                hasSunExposure,
                person
        );
        AnnouncementRequest announcementRequest = new AnnouncementRequest(house, contractDuration, person, SALE);
        announcementRepository.addAnnouncementRequest(announcementRequest);
        Announcement announcement = new Announcement(announcementRequest, commission, currentDate, Status.ACCEPT);
        announcementRepository.addAnnouncement(announcement);
    }

    public void setDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public void setRepositories(Repositories repositories) {
        this.repositories = repositories;
    }

    public Photographs getPhotographs() {
        return photographs;
    }

    public void setPhotographs(Photographs photographs) {
        this.photographs = photographs;
    }

    public Address getAddress() {
        return address;
    }

    public Commission getCommission() {
        return commission;
    }

    public Repositories getRepositories() {
        return repositories;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public HouseRepository getHouseRepository() {
        return houseRepository;
    }

    public void setHouseRepository(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public ApartmentRepository getResidenceRepository() {
        return apartmentRepository;
    }

    public void setResidenceRepository(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public AnnouncementRepository getAnnouncementRepository() {
        return announcementRepository;
    }

    public void setAnnouncementRepository(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }
}
