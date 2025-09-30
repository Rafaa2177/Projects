package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type Announcement request.
 * Author: Marco Ferreira
 */
public class AnnouncementRequest implements Serializable {
    private Property property;
    private double price;
    private double contractDuration;
    private Person agent;
    private RequestType requestType;
    private LocalDate date;
    private Status status = Status.PENDING;

    public AnnouncementRequest(Property property, double contractDuration,
                               Person agent, RequestType requestType) {
        this.property = property;
        this.price = property.getPrice();
        this.contractDuration = contractDuration;
        this.agent = agent;
        this.requestType = requestType;
    }

    public Property getProperty() {return property;}
    public void setProperty(Property property) {
        this.property = property;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(double contractDuration) {
        this.contractDuration = contractDuration;
    }

    public Person getAgent() {
        return agent;
    }

    public void setAgent(Person agent) {
        this.agent = agent;
    }

    public RequestType getRequestType() {
        return requestType;
    }
    public LocalDate getDate(){
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "{ " +
                "property = " + property +
                ",\n price = " + price +
                ",\n contractDuration = " + contractDuration +
                ",\n agent = " + agent +
                ",\n requestType = " + requestType +
                '}' +
                "\n -----------------------";
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
