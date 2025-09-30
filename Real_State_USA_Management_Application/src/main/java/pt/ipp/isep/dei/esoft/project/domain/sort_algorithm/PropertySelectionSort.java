package pt.ipp.isep.dei.esoft.project.domain.sort_algorithm;

import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.dto.PropertyDTO;

import java.io.Serializable;
import java.util.List;

/**
 * PropertySelectionSort Class.
 * Represents the Selection Sort algorithm for sorting a list of properties based on area.
 * Author: Your Name
 */
public class PropertySelectionSort implements Serializable {

    /**
     * Sorts a list of properties based on their area using the Selection Sort algorithm.
     *
     * @param propertyDTOList the list of properties to be sorted
     * @param dealList        the list of purchase orders associated with the properties
     * @param sortingOption   the sorting option ("Ascending Order" or "Descending Order")
     * @return the sorted list of purchase orders
     */
    public static List<PurchaseOrder> selectionSort(List<Property> propertyDTOList, List<PurchaseOrder> dealList, String sortingOption) {
        int n = propertyDTOList.size();
        if (sortingOption.equals("Descending Order")) {
            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;

                for (int j = i + 1; j < n; j++) {
                    if (propertyDTOList.get(j).getAreaM2() > propertyDTOList.get(minIndex).getAreaM2()) {
                        minIndex = j;
                    }
                }

                Property temp = propertyDTOList.get(minIndex);
                PurchaseOrder tempDeal = dealList.get(minIndex);
                propertyDTOList.set(minIndex, propertyDTOList.get(i));
                dealList.set(minIndex, dealList.get(i));
                propertyDTOList.set(i, temp);
                dealList.set(i, tempDeal);
            }
        }
        if (sortingOption.equals("Ascending Order")) {
            for (int i = 0; i < n - 1; i++) {
                int maxIndex = i;

                for (int j = i + 1; j < n; j++) {
                    if (propertyDTOList.get(j).getAreaM2() < propertyDTOList.get(maxIndex).getAreaM2()) {
                        maxIndex = j;
                    }
                }
                Property temp = propertyDTOList.get(maxIndex);
                PurchaseOrder tempDeal = dealList.get(maxIndex);
                propertyDTOList.set(maxIndex, propertyDTOList.get(i));
                dealList.set(maxIndex, dealList.get(i));
                propertyDTOList.set(i, temp);
                dealList.set(i, tempDeal);
            }
        }
        return dealList;
    }
}
