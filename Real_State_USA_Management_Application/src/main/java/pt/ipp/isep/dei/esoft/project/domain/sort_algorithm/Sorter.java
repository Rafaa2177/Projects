package pt.ipp.isep.dei.esoft.project.domain.sort_algorithm;

import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Sorter Interface.
 * Represents a generic sorter for a list of visit requests.
 * Author: Diana Neves
 */
public interface Sorter extends Serializable {

    /**
     * Sorts a list of visit requests using a custom sorter.
     *
     * @param toSort the list of visit requests to be sorted
     * @param sorter the comparator used for sorting
     * @return the sorted list of visit requests
     */
    List<VisitRequest> sort(List<VisitRequest> toSort, Comparator<VisitRequest> sorter);

    /**
     * Sorts a list of visit requests using the default comparator.
     * This method calls the custom sorter with the default comparator.
     *
     * @param toSort the list of visit requests to be sorted
     * @return the sorted list of visit requests
     */
    default List<VisitRequest> sort(List<VisitRequest> toSort) {
        return sort(toSort, VisitRequest::compareTo);
    }
}
