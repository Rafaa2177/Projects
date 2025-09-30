package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Apartment;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.enums.FilterCriteria;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;
import pt.ipp.isep.dei.esoft.project.domain.enums.SortOrder;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for displaying the list of properties based on search criteria.
 * Author: Diana Neves
 */
public class DisplayTheListPropertyController {

    AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();

    /**
     * Retrieves a list of announcements filtered by search criteria.
     *
     * @param propertyType    The type of property.
     * @param requestType     The type of request.
     * @param numberOfBedrooms The number of bedrooms.
     * @param filterCriteria  The filter criteria.
     * @param sortOrder       The sort order.
     * @return The filtered and sorted list of announcements.
     */
    public List<Announcement> getPropertyListBySearchCriteria(PropertyType propertyType, RequestType requestType, int numberOfBedrooms, FilterCriteria filterCriteria, SortOrder sortOrder) {
        List<Announcement> filteredAnnouncements = getAnnouncementsBySearchCriteria(requestType, propertyType, numberOfBedrooms);
        if (!filteredAnnouncements.isEmpty()) {
            if (filterCriteria == FilterCriteria.PRICE) {
                if (sortOrder == SortOrder.ASCENDING) {
                    return sortPropertiesByPriceAscending(filteredAnnouncements);
                } else {
                    return sortPropertiesByPriceDescending(filteredAnnouncements);
                }
            }
            if (filterCriteria == FilterCriteria.CITY) {
                if (sortOrder == SortOrder.ASCENDING) {
                    return sortPropertiesByCityAscending(filteredAnnouncements);
                } else {
                    return sortPropertiesByCityDescending(filteredAnnouncements);
                }
            }
            if (filterCriteria == FilterCriteria.STATE) {
                if (sortOrder == SortOrder.ASCENDING) {
                    return sortPropertiesByStateAscending(filteredAnnouncements);
                } else {
                    return sortPropertiesByStateDescending(filteredAnnouncements);
                }
            }
        }
        return filteredAnnouncements;
    }

    /**
     * Retrieves a list of announcements filtered by search criteria.
     *
     * @param requestType     The type of request.
     * @param propertyType    The type of property.
     * @param numberOfBedrooms The number of bedrooms.
     * @return The filtered list of announcements.
     */
    public List<Announcement> getAnnouncementsBySearchCriteria(RequestType requestType, PropertyType propertyType, int numberOfBedrooms) {
        List<Announcement> filteredResults = new ArrayList<>();
        for (Announcement announcement : announcementRepository.getAnnouncements()) {
            if (announcement.getRequest().getRequestType() == requestType) {
                if (announcement.getRequest().getProperty().getPropertyType() == propertyType) {
                    if (propertyType == PropertyType.LAND) {
                        filteredResults.add(announcement);
                    } else if (announcement.getRequest().getProperty().getNumberOfBedrooms() == numberOfBedrooms) {
                        filteredResults.add(announcement);
                    }
                }
            }
        }
        return filteredResults;
    }

    /**
     * Sorts the list of announcements by price in ascending order.
     *
     * @param filteredResults The list of announcements to be sorted.
     * @return The sorted list of announcements.
     */
    public static List<Announcement> sortPropertiesByPriceAscending(List<Announcement> filteredResults) {
        filteredResults.sort((o1, o2) -> {
            Property p1 = o1.getRequest().getProperty();
            Property p2 = o2.getRequest().getProperty();
            return Double.compare(p1.getPrice(), p2.getPrice());
        });
        return filteredResults;
    }

    /**
     * Sorts the list of announcements by price in descending order.
     *
     * @param filteredResults The list of announcements to be sorted.
     * @return The sorted list of announcements.
     */
    public static List<Announcement> sortPropertiesByPriceDescending(List<Announcement> filteredResults) {
        filteredResults.sort((o1, o2) -> {
            Property p1 = o1.getRequest().getProperty();
            Property p2 = o2.getRequest().getProperty();
            return Double.compare(p2.getPrice(), p1.getPrice());
        });
        return filteredResults;
    }

    /**
     * Sorts the list of announcements by city name in ascending order.
     *
     * @param filteredResults The list of announcements to be sorted.
     * @return The sorted list of announcements.
     */
    public static List<Announcement> sortPropertiesByCityAscending(List<Announcement> filteredResults) {
        filteredResults.sort((o1, o2) -> {
            Property p1 = o1.getRequest().getProperty();
            Property p2 = o2.getRequest().getProperty();
            return p1.getAddress().getCity().getName().compareTo(p2.getAddress().getCity().getName());
        });
        return filteredResults;
    }

    /**
     * Sorts the list of announcements by city name in descending order.
     *
     * @param filteredResults The list of announcements to be sorted.
     * @return The sorted list of announcements.
     */
    public static List<Announcement> sortPropertiesByCityDescending(List<Announcement> filteredResults) {
        filteredResults.sort((o1, o2) -> {
            Property p1 = o1.getRequest().getProperty();
            Property p2 = o2.getRequest().getProperty();
            return p2.getAddress().getCity().getName().compareTo(p1.getAddress().getCity().getName());
        });
        return filteredResults;
    }

    /**
     * Sorts the list of announcements by state name in ascending order.
     *
     * @param filteredResults The list of announcements to be sorted.
     * @return The sorted list of announcements.
     */
    public static List<Announcement> sortPropertiesByStateAscending(List<Announcement> filteredResults) {
        filteredResults.sort((o1, o2) -> {
            Property p1 = o1.getRequest().getProperty();
            Property p2 = o2.getRequest().getProperty();
            return p1.getAddress().getState().getName().compareTo(p2.getAddress().getState().getName());
        });
        return filteredResults;
    }

    /**
     * Sorts the list of announcements by state name in descending order.
     *
     * @param filteredResults The list of announcements to be sorted.
     * @return The sorted list of announcements.
     */
    public static List<Announcement> sortPropertiesByStateDescending(List<Announcement> filteredResults) {
        filteredResults.sort((o1, o2) -> {
            Property p1 = o1.getRequest().getProperty();
            Property p2 = o2.getRequest().getProperty();
            return p2.getAddress().getState().getName().compareTo(p1.getAddress().getState().getName());
        });
        return filteredResults;
    }
}
