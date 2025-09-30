package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The type Store.
 */
public class Store implements Serializable {
   private int phoneNumber;
   private String email;
   private Address address;
   private int id;
   private List<Announcement> announcements;
   private List<Person> employees;
   private Person manager;


    /**
     * Instantiates a new Store.
     *
     * @param phoneNumber   the phone number
     * @param email         the email
     * @param address       the address
     * @param id            the id
     * @param announcements the announcements
     * @param manager       the manager
     * @param employees        the employees
     */
    public Store(int phoneNumber, String email, Address address, int id, List<Announcement> announcements, Person manager,List<Person> employees) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.id = id;
        this.announcements = announcements;
        this.manager = manager;
        this.employees = employees;
    }

    /**
     * Gets agents.
     *
     * @return the agents
     */
    public List<Person> getEmployees() {
        return employees;
    }

    /**
     * Sets agents.
     *
     * @param agents the agents
     */
    public void setEmployees(List<Person> agents) {
        this.employees = agents;
    }

    /**
     * Add agent boolean.
     *
     * @param agent the agent
     * @return the boolean
     */
    public boolean addAgent(Person agent){
        return this.employees.add(agent);
    }

    /**
     * Set manager.
     *
     * @param manager the manager
     */
    public void setManager(Person manager){
        this.manager = manager;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets announcements.
     *
     * @return the announcements
     */
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * Sets announcements.
     *
     * @param announcements the announcements
     */
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * Gets manager.
     *
     * @return the manager
     */
    public Person getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return "Store{" +
                "phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", id=" + id +
                ", announcements=" + announcements +
                ", employees=" + employees +
                ", manager=" + manager +
                '}';
    }
}
