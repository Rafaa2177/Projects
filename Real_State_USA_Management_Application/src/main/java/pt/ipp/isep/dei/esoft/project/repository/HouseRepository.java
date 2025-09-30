package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.House;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type House repository.
 */
public class HouseRepository implements Serializable {
    private final List<House> houses = new ArrayList<>();

    /**
     * Add house.
     *
     * @param house the house
     */
    public void addHouse(House house){houses.add(house);}

}
