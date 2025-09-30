package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Serializable {
    private List<PurchaseOrder> orders = new ArrayList<>();

    /**
     * Add order.
     *
     * @param order the order
     */
    public void addOrder(PurchaseOrder order) {
        orders.add(order);
    }

    /**
     * Get order by id purchase order.
     *
     * @param id the id
     * @return the purchase order
     */
    public PurchaseOrder getOrderByID(int id){
        for (PurchaseOrder order : orders){
            if (order.getIdOrder()==id){
                if (order.getStatus()== Status.PENDING){
                    return order;

                }

            }
        }
       return null;
    }

    public List<PurchaseOrder> getOrders() {
        return orders;
    }

    /**
     * Add.
     *
     * @param email        the email
     * @param offer        the offer
     * @param announcement the announcement
     */
    public void add(String email, double offer, Announcement announcement) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(email, offer, announcement);
        orders.add(purchaseOrder);
    }

    /**
     * Validate offer boolean.
     *
     * @param announcement the announcement
     * @param offer        the offer
     * @return the boolean
     */
    public boolean validateOffer(Announcement announcement, double offer) {
        for (PurchaseOrder purchaseOrder : orders) {
            if (purchaseOrder.getAnnouncement().equals(announcement)) {
                if (purchaseOrder.getAmountOfered() == offer) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Offer inside limits boolean.
     *
     * @param announcement the announcement
     * @param offer        the offer
     * @return the boolean
     */
    public boolean offerInsideLimits(Announcement announcement, double offer){
        if (announcement.getRequest().getPrice() < offer || offer < 0) {
            return false;
        }
        return true;
    }

    /**
     * Check if order is pending boolean.
     *
     * @param announcement the announcement
     * @return the boolean
     */
    public boolean checkIfOrderIsPending(Announcement announcement){
        String email = CurrentSession.getEmail();
        for(PurchaseOrder pOrder : orders){
            if(pOrder.getClient().equals(email) && pOrder.getAnnouncement().equals(announcement)){
                if(pOrder.getStatus() == Status.PENDING) return false;
            }
        }
        return true;
    }

    public List<PurchaseOrder> getPurchaseOrders() {return orders;}

    public List<PurchaseOrder> getOrdersByAnnouncement(Announcement announcement) {
        List<PurchaseOrder> ordersByAnnouncement = new ArrayList<>();
        List<PurchaseOrder> allOrders = orders;

        for (PurchaseOrder order : allOrders) {
            if (order.getAnnouncement().equals(announcement)) {
                ordersByAnnouncement.add(order);
            }
        }
        return ordersByAnnouncement;
    }

}