package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.domain.sort_algorithm.PropertyAreaBubbleSort;
import pt.ipp.isep.dei.esoft.project.domain.sort_algorithm.PropertySelectionSort;
import pt.ipp.isep.dei.esoft.project.dto.PropertyDTO;
import pt.ipp.isep.dei.esoft.project.domain.Property;

import pt.ipp.isep.dei.esoft.project.mapper.PropertyMapper;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.*;

/**
 * Controller class for listing deals.
 * Author: Marco Ferreira
 */
public class ListDealsController {

    private OrderRepository orderRepository;
    private List<PurchaseOrder> orders = new ArrayList<>();
    private List<PurchaseOrder> dealList = new ArrayList<>();

    private List<String> algList = new ArrayList<>();
    private List<String> orderList = new ArrayList<>();
    private List<Property> propertyList = new ArrayList<>();

    /**
     * Default constructor for ListDealsController.
     */
    public ListDealsController() {
        orderRepository = getOrderRepository();
        orders = orderRepository.getOrders();
        dealList = createDealList();
        propertyList = getPropertyList();
    }

    /**
     * Retrieves the list of deals.
     *
     * @return The list of deals.
     */
    public List<PurchaseOrder> getDealList() {
        return dealList;
    }

    /**
     * Creates the list of deals based on the orders.
     *
     * @return The list of deals.
     */
    public List<PurchaseOrder> createDealList() {
        for (PurchaseOrder order : orders) {
            if (order.getStatus() == Status.ACCEPT) {
                dealList.add(order);
            }
        }
        return dealList;
    }

    private String algOption;
    private String orderOption;

    private OrderRepository getOrderRepository() {
        if (orderRepository == null) {
            Repositories repositories = Repositories.getInstance();
            orderRepository = repositories.getOrderRepository();
        }
        return orderRepository;
    }

    /**
     * Retrieves the available sorting algorithm options.
     *
     * @return The list of sorting algorithm options.
     */
    public List<String> getAlgOptions() {
        algList.add(0, "Bubble Sort");
        algList.add(1, "Selection Sort");
        return algList;
    }

    /**
     * Retrieves the available sorting order options.
     *
     * @return The list of sorting order options.
     */
    public List<String> getOrderList() {
        orderList.add(0, "Ascending Order");
        orderList.add(1, "Descending Order");
        return orderList;
    }

    /**
     * Sorts the properties using the specified sorting algorithm and order.
     *
     * @param sortingAlg   The sorting algorithm to be used.
     * @param sortingOrder The sorting order to be applied.
     */
    public void sortProperties(String sortingAlg, String sortingOrder) {
        if (sortingAlg.equals("Bubble Sort")) {
            PropertyAreaBubbleSort.bubbleSort(propertyList, dealList, sortingOrder);
        } else if (sortingAlg.equals("Selection Sort")) {
            PropertySelectionSort.selectionSort(propertyList, dealList, sortingOrder);
        }
    }

    /**
     * Retrieves the list of properties.
     *
     * @return The list of properties.
     */
    public List<Property> getPropertyList() {
        List<Property> propertyList = new ArrayList<>();
        for (PurchaseOrder order : dealList) {
            propertyList.add(order.getAnnouncement().getRequest().getProperty());
        }
        return propertyList;
    }
}
