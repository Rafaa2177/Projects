package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.io.Serializable;

import java.time.LocalDate;

/**
 * The type Purchase order.
 */
public class PurchaseOrder implements Comparable, Serializable {

    private Status status;
    private int ID;
    private  String client_email;

    private Announcement announcement;

    private double amountOfered;

    private final Status STATUS_BY_OMISSION= Status.PENDING;

    private static int numberOfPurchaseOrders=1;

    LocalDate date;
    private Person client;

    private Store store;

    /**
     * Instantiates a new Purchase order.
     *
     * @param email        the email
     * @param amountOfered the amount ofered
     * @param announcement the announcement
     */
    public PurchaseOrder(String email, double amountOfered, Announcement announcement) {
        this.date = LocalDate.now();
        this.status = Status.PENDING;
        this.ID = numberOfPurchaseOrders;
        this.client_email = email;
        this.announcement = announcement;
        this.amountOfered = amountOfered;
        numberOfPurchaseOrders++;

    }





    /**
     * Gets client.
     *
     * @return the client
     */
    public String getClient() {
        return client_email;
    }

    /**
     * Gets amount ofered.
     *
     * @return the amount ofered
     */
    public double getAmountOfered() {
        return amountOfered;
    }

    /**
     * Gets id order.
     *
     * @return the id order
     */
    public int getIdOrder() {
        return ID;
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
     * Gets announcement.
     *
     * @return the announcement
     */
    public Announcement getAnnouncement() {
        return announcement;
    }

    public void  setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int compareTo(Object o) {
        PurchaseOrder m = (PurchaseOrder) o;
        return (int) (m.getAmountOfered()-this.getAmountOfered());
    }

    public String toString(){

        return "Id order: " + this.ID + "\nApartment: " + this.announcement.getRequest().getProperty().getAddress().getStreet() +
                "\nClient Email: " + this.client_email + "\nAmount Offered: " + this.amountOfered+ "\nStatus: "+ status;
    }

}