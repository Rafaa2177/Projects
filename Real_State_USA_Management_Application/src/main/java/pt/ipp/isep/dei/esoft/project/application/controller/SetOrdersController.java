package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Set orders controller.
 * Author: Rafaela Lopes
 */
public class SetOrdersController {
    /**
     * The Authentication repository.
     */
    public AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    private AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
    private OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
    private List<PurchaseOrder> orders = new ArrayList<>();
    private PersonRepository personRepository = Repositories.getInstance().getPersonRepository();

        private Person agent;

    /**
     * Instantiates a new Set orders controller.
     */
    /*
    public SetOrdersController()  {
        getAnnouncementRepository();
        getOrderRepository();
        getEmployeeRepository();
        agent = getEmployeeFromSession();


    }

     */

    /**
     * Instantiates a new Set orders controller.
     *
     * @param authenticationRepository the authentication repository
     * @param announcementRepository   the announcement repository
     * @param orderRepository          the order repository
     */
    /*
    public SetOrdersController(AuthenticationRepository authenticationRepository, AnnouncementRepository announcementRepository, OrderRepository orderRepository) {
        this.authenticationRepository = authenticationRepository;
        this.announcementRepository = announcementRepository;
        this.orderRepository = orderRepository;
    }


    private AnnouncementRepository getAnnouncementRepository() {
        Repositories repositories = Repositories.getInstance();
        announcementRepository = repositories.getAnnouncementRepository();
        return announcementRepository;
    }

     */

    /**
     * Gets order repository.
     *
     * @return the order repository
     */

    /*
    public OrderRepository getOrderRepository() {
        Repositories repositories = Repositories.getInstance();
        orderRepository = repositories.getOrderRepository();

        return orderRepository;
    }


     */

    /**
     * Gets employee repository.
     *
     * @return the employee repository
     */
    /*
    public EmployeeRepository getEmployeeRepository() {
        Repositories repositories = Repositories.getInstance();
        employeeRepository = repositories.getEmployeeRepository();

        return employeeRepository;
    }
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }
     */

    /**
     * Gets announcement.
     *
     * @return the announcement
     */
    public List<Announcement> getAnnouncement() {
        return announcementRepository.getAnnouncementList();
    }

    /**
     * Gets orders.
     *
     * @param announcement the announcement
     * @return the orders
     */
    public List<PurchaseOrder> getOrders(Announcement announcement) {
        System.out.println(announcement);
        System.out.println(announcement.getOrders().size());
        return announcement.getOrders();
    }

    private Person getEmployeeFromSession()  {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return personRepository.getPersonByEmail(email.toString());
    }

    /**
     * Gets announcement by order.
     *
     * @param order the order
     * @return the announcement by order
     */
    public Announcement getAnnouncementByOrder(PurchaseOrder order) {
        return announcementRepository.getAnnouncementByOrder(order);
    }

    /**
     * Sets order.
     *
     * @param order  the order
     * @param status the status
     * @return the order
     * @throws FileNotFoundException the file not found exception
     */
    public boolean setOrder(PurchaseOrder order, Status status) throws FileNotFoundException {
        String email = order.getClient();
        Person agent = getEmployeeFromSession();
        Announcement announcement = getAnnouncementByOrder(order);
        return agent.setOrder(announcement, order, status, email);


    }

    /**
     * Gets purchase order by id.
     *
     * @param id the id
     * @return the purchase order by id
     */
    public PurchaseOrder getPurchaseOrderByID(int id) {
        return orderRepository.getOrderByID(id);
    }
}

