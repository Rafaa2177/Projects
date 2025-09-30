package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Purchase order repository.
 */
public class PurchaseOrderRepository implements Serializable {

    private final List<PurchaseOrder> purchaseOrders = new ArrayList<>();

    /**
     * Add.
     *
     * @param email        the email
     * @param offer        the offer
     * @param announcement the announcement
     */
    public void add(String email, double offer, Announcement announcement) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(email, offer, announcement);
        purchaseOrders.add(purchaseOrder);
    }

    /**
     * Validate offer boolean.
     *
     * @param announcement the announcement
     * @param offer        the offer
     * @return the boolean
     */
    public boolean validateOffer(Announcement announcement, double offer) {
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
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
        for(PurchaseOrder pOrder : purchaseOrders){
            if(pOrder.getClient().equals(email) && pOrder.getAnnouncement().equals(announcement)){
                if(pOrder.getStatus() == Status.PENDING) return false;
            }
        }
        return true;
    }

    public List<PurchaseOrder> getPurchaseOrders() {return purchaseOrders;}
    public List<PurchaseOrder> getOrdersByAnnouncement(Announcement announcement) {
        List<PurchaseOrder> ordersByAnnouncement = new ArrayList<>();
        List<PurchaseOrder> allOrders = purchaseOrders;

        for (PurchaseOrder order : allOrders) {
            if (order.getAnnouncement().equals(announcement)) {
                ordersByAnnouncement.add(order);
            }
        }
        return ordersByAnnouncement;
    }

}
