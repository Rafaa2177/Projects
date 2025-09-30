package pt.ipp.isep.dei.esoft.project.domain;


import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Announcement.
 * Author: Diana Neves
 */
public class Announcement implements Serializable {
    private AnnouncementRequest request;
    private Commission commission;
    private LocalDate date;
    private Status status;
    private Person agent;
    private int ID;
    private List<PurchaseOrder> orders = new ArrayList<>();
    private static int numberOfPurchaseOrders=1;


    /**
     * Instantiates a new Announcement.
     *
     * @param request    the request
     * @param commission the commission
     * @param date       the date
     * @param status     the status
     */
    public Announcement(AnnouncementRequest request, Commission commission, LocalDate date,
                        Status status) {
        this.ID = numberOfPurchaseOrders;
        this.request = request;
        this.commission = commission;
        this.date = date;
        this.status = status;
        this.agent = request.getAgent();
        numberOfPurchaseOrders++;
    }

    /**
     * Gets request.
     *
     * @return the request
     */
    public AnnouncementRequest getRequest() {
        return request;
    }

    /**
     * Sets request.
     *
     * @param request the request
     */
    public void setRequest(AnnouncementRequest request) {
        this.request = request;
    }

    /**
     * Gets commission.
     *
     * @return the commission
     */
    public Commission getCommission() {
        return commission;
    }

    /**
     * Sets commission.
     *
     * @param commission the commission
     */
    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public Person getAgent() {
        return agent;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     */
    public void setAgent(Person agent) {
        this.agent = agent;

    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public List<PurchaseOrder> getOrders() {return orders;}

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public void setOrders(List<PurchaseOrder> orders) {this.orders = orders;}

    /**
     * Remove from orders.
     *
     * @param order the order
     */
    public void removeFromOrders (PurchaseOrder order){orders.remove(order);}

    /**
     * Clear orders.
     */
    public void clearOrders(){orders.clear();}

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return ID + ": { " +
                "request = " + request +
                "\n commission = " + commission.toString() +
                ",\n date = " + date +
                ",\n status = " + status +
                ",\n agent = " + agent +
                '}' +
                "\n -----------------------";
    }

    /**
     * Has order boolean.
     *
     * @param order the order
     * @return the boolean
     */
    public boolean hasOrder(PurchaseOrder order) {
        return orders.contains(order);
    }
}