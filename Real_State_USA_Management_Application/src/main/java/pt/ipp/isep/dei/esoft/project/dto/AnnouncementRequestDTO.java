 package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;

public class AnnouncementRequestDTO {
    private Property property;
    private double price;
    private double contractDuration;
    private Person agent;
    private RequestType requestType;

    public AnnouncementRequestDTO(Property property, double contractDuration, Person agent, RequestType requestType) {
        this.property = property;
        this.price = price;
        this.contractDuration = contractDuration;
        this.agent = agent;
        this.requestType = requestType;
    }

    public Property getProperty() {
        return property;
    }

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

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        if (requestType == RequestType.SALE){
            return "AnnouncementRequestDTO{" +
                    "\nproperty=" + property +
                    "\n, price=" + price +
                    "\n, agent=" + agent +
                    "\n, requestType=" + requestType +
                    '}';
        }
        return "AnnouncementRequestDTO{" +
                "property=" + property +
                ", price=" + price +
                ", contractDuration=" + contractDuration +
                ", agent=" + agent +
                ", requestType=" + requestType +
                '}';
    }
}
