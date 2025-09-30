package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.io.Serializable;
import java.util.List;

/**
 * The type Land.
 * Author:Diana Neves
 */
public class Land extends Property implements Serializable {
    private PropertyType propertyType;

    /**
     * Instantiates a new Land.
     *
     * @param areaM2             the area m 2
     * @param cityCentreDistance the city centre distance
     * @param price              the price
     * @param photos             the photos
     * @param address            the address
     * @param owner              the owner
     */
    public Land(double areaM2, double cityCentreDistance, double price, List<String> photos, Address address, Person owner) {
        super(areaM2, cityCentreDistance, price, photos, address, owner, PropertyType.LAND);

        propertyType = PropertyType.LAND;

    }
}
