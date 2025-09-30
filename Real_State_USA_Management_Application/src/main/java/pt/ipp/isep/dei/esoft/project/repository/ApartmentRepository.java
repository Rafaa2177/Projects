package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Apartment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Apartment repository.
 */
public class ApartmentRepository implements Serializable {
    private final List<Apartment> apartments = new ArrayList<>();

    /**
     * Add residence.
     *
     * @param apartment the apartment
     */
    public void addResidence(Apartment apartment){
        apartments.add(apartment);}
}
