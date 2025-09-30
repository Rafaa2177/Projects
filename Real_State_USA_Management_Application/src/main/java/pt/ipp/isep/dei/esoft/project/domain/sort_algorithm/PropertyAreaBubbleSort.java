package pt.ipp.isep.dei.esoft.project.domain.sort_algorithm;

import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;

import java.io.Serializable;
import java.util.List;

/**
 * PropertyAreaBubbleSort Class.
 * Represents the Bubble Sort algorithm for sorting a list of properties based on area.
 * Author: Marco Ferreira
 */
public class PropertyAreaBubbleSort implements Serializable {

    /**
     * Sorts a list of properties based on their area using the Bubble Sort algorithm.
     *
     * @param propertyDTOList the list of properties to be sorted
     * @param dealList        the list of purchase orders associated with the properties
     * @param sortingOption   the sorting option ("Ascending Order" or "Descending Order")
     * @return the sorted list of purchase orders
     */
    public static List<PurchaseOrder> bubbleSort(List<Property> propertyDTOList, List<PurchaseOrder> dealList, String sortingOption) {
        int n = propertyDTOList.size();
        if (sortingOption.equals("Ascending Order")) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (propertyDTOList.get(j).getAreaM2() > propertyDTOList.get(j + 1).getAreaM2()) {
                        Property temp = propertyDTOList.get(j);
                        PurchaseOrder tempDeal = dealList.get(j);
                        propertyDTOList.set(j, propertyDTOList.get(j + 1));
                        dealList.set(j, dealList.get(j + 1));
                        propertyDTOList.set(j + 1, temp);
                        dealList.set(j + 1, tempDeal);
                    }
                }
            }
        }
        if (sortingOption.equals("Descending Order")) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (propertyDTOList.get(j).getAreaM2() < propertyDTOList.get(j + 1).getAreaM2()) {
                        Property temp = propertyDTOList.get(j);
                        PurchaseOrder tempDeal = dealList.get(j);
                        propertyDTOList.set(j, propertyDTOList.get(j + 1));
                        dealList.set(j, dealList.get(j + 1));
                        propertyDTOList.set(j + 1, temp);
                        dealList.set(j + 1, tempDeal);
                    }
                }
            }
        }
        return dealList;
    }
}
