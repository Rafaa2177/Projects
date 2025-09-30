package pt.ipp.isep.dei.esoft.project.domain.enums;

import java.io.Serializable;

/**
 * FilterCriteria Enum.
 * Represents the criteria for filtering announcements.
 * Author: Diana Neves
 */
public enum FilterCriteria implements Serializable {
    /**
     * Filter by price.
     */
    PRICE,

    /**
     * Filter by city.
     */
    CITY,

    /**
     * Filter by state.
     */
    STATE;
}

