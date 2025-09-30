package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.RequestType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.domain.Photographs.getPhotos;

public class Bootstrap implements Runnable {
    private Repositories repositories = Repositories.getInstance();
    private PersonRepository personRepository = repositories.getPersonRepository();
    private BranchRepository branchRepository = repositories.getBranchRepository();
    private AnnouncementRepository announcementRepository = repositories.getAnnouncementRepository();
    private VisitRequestRepository visitRequestRepository = repositories.getVisitRequestRepository();
    private RequestRepository requestRepository = repositories.getRequestRepository();
    private List<Person> agents = personRepository.getAgents();
    private List<Person> people = personRepository.getListPersonsRepository();
    private Person agent, manager, person1, person2, person3, agent2, network_manager, admin;
    private List<Announcement> announcements;


    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addAnnouncements();
    }


    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository


        State state1 = new State("Porto");
        State state2 = new State("Lisboa");

        City city1 = new City(state1, "Gondomar");
        City city2 = new City(state2, "Oeiras");

        District district1 = new District("Porto", city1);
        District district2 = new District("Lisboa", city1);

        Address address1 = new Address("Rua Padre Alfredo", city1, district1, "12344", state1);
        Address address2 = new Address("rua de sa", city2, district2, "34508", state2);


        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization(2, "1", "1", "1", address1);
        Organization organization1 = new Organization(5, "1", "1", "1", address2);
        organization.addEmployee(new Person(new Email("admin@this.app"), "XZP60mp", "rafa", "12345567", "123329876", "(351) 938-4135", Roles.AGENT));
        organization.addEmployee(new Person(new Email("employee@this.app"), "XZP60mp", "diogo", "12345567", "123329876", "(351) 938-4135", Roles.STORE_MANAGER));
        organizationRepository.add(organization);
        organizationRepository.add(organization1);
    }

    private void addTaskCategories() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_OWNER, AuthenticationController.ROLE_OWNER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_NETWORK_MANAGER, AuthenticationController.ROLE_NETWORK_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);
        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserWithRole("Agent", "agent@this.app", "agent",
                AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Owner", "owner@this.app", "owner",
                AuthenticationController.ROLE_OWNER);
        authenticationRepository.addUserWithRole("Client", "client@this.app", "client",
                AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Network Manager", "netmanager@this.app", "netmanager",
                AuthenticationController.ROLE_NETWORK_MANAGER);

        //create person managers, person clients and person agents
        manager = new Person(new Email("manager.1@this.app"), "XZP60mp", "Store-Manager rui", "12345567",
                "123329876", "(351) 938-4135", Roles.STORE_MANAGER);

        person1 = new Person(new Email("person.1@this.app"), "XZP60mp", "Client Diana", "12345567",
                "123329876", "(351) 938-4135", Roles.CLIENT);

        person2 = new Person(new Email("person.2@this.app"), "XZP60mp", "Client Luis", "11221286",
                "111221286", "(351) 914-2634", Roles.CLIENT);

        person3 = new Person(new Email("person.3@this.app"), "XZP60mp", "Client Leo", "11282286",
                "111221245", "(351) 917-9656", Roles.CLIENT);

        agent = new Person(new Email("agent.1@this.app"), "XZP60mp", "Agent luis", "12345567",
                "123329876", "(351) 123-1234", Roles.AGENT);

        agent2 = new Person(new Email("agent.2@this.app"), "XZP60mp", "Agent rafa", "12345567",
                "123329876", "(351) 123-1234", Roles.AGENT);

        network_manager = new Person(new Email("37@this.app"), "XZP60mp", "Network Manager Marco",
                "12345678", "123329876", "(351) 123-1234", Roles.STORE_NETWORK_MANAGER);

        admin   = new Person(new Email("admin.1@this.app"), "XZP60mp", "Admin marco","12345567",
                "123329876","(351) 123-1234", Roles.SYSTEM_ADMINISTRATOR);

        //adding to list
        people.add(manager);
        people.add(agent);
        people.add(person1);
        people.add(person2);
        people.add(agent2);
        people.add(network_manager);
        people.add(admin);
        personRepository.getStoreNetworkManager().add(network_manager);
        personRepository.getClients().add(person1);
        personRepository.getAgents().add(agent);
        personRepository.getAgents().add(agent2);
        personRepository.getStoreManager().add(manager);


        for (Person person : people) {
            if (person.getRole().equals(Roles.CLIENT)) {
                authenticationRepository.addUserWithRole(person.getName(), person.getId().getEmail(), person.getPwd(),
                        AuthenticationController.ROLE_CLIENT);

            } else if (person.getRole().equals(Roles.STORE_MANAGER)) {
                authenticationRepository.addUserWithRole(person.getName(), person.getId().getEmail(), person.getPwd(),
                        AuthenticationController.ROLE_STORE_MANAGER);

            } else if (person.getRole().equals(Roles.STORE_NETWORK_MANAGER)) {
                authenticationRepository.addUserWithRole(person.getName(), person.getId().getEmail(), person.getPwd(),
                        AuthenticationController.ROLE_NETWORK_MANAGER);

            } else if (person.getRole().equals(Roles.AGENT)) {
                authenticationRepository.addUserWithRole(person.getName(), person.getId().getEmail(), person.getPwd(),
                        AuthenticationController.ROLE_AGENT);

            } else if (person.getRole().equals(Roles.SYSTEM_ADMINISTRATOR)){
                authenticationRepository.addUserWithRole(person.getName(), person.getId().getEmail(), person.getPwd(),
                        AuthenticationController.ROLE_ADMIN);
                authenticationRepository.addUserWithRole(person.getName(), person.getId().getEmail(), person.getPwd(),
                        AuthenticationController.ROLE_NETWORK_MANAGER);
                authenticationRepository.addUserWithRole(person.getName(), person.getId().getEmail(), person.getPwd(),
                        AuthenticationController.ROLE_STORE_MANAGER);
            }

        }

    }

    private void addAnnouncements() {

        //addresses
        State state1 = new State("Porto");
        State state2 = new State("Lisboa");
        State state3 = new State("Coimbra");
        State state4 = new State("Braga");
        State state5 = new State("Aveiro");

        City city1 = new City(state1, "Gondomar");
        City city2 = new City(state2, "Oeiras");
        City city3 = new City(state3, "Figueira da Foz");
        City city4 = new City(state4, "Guimarães");
        City city5 = new City(state5, "Aveiro");

        District district1 = new District("Porto", city1);
        District district2 = new District("Lisboa", city1);
        District district3 = new District("Coimbra", city1);
        District district4 = new District("Braga", city1);
        District district5 = new District("Porto", city5);

        Address address1 = new Address("Rua Padre Alfredo", city1, district1, "12344", state1);
        Address address2 = new Address("rua de sa", city2, district2, "34508", state2);
        Address address3 = new Address("rua de melo", city3, district3, "34508", state3);
        Address address4 = new Address("rua professor laurentino", city4, district4, "23411", state4);
        Address address5 = new Address("rua professor rui", city5, district5, "23411", state5);


        //instance date
        LocalDate date1 = LocalDate.parse("2023-04-12", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate date2 = LocalDate.parse("2023-06-30", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate date3 = LocalDate.parse("2023-02-17", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate date4 = LocalDate.parse("2023-09-24", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate date5 = LocalDate.parse("2023-11-12", DateTimeFormatter.ISO_LOCAL_DATE);


        //Commission
        Commission commission1 = new Commission(12132, 14);
        Commission commission2 = new Commission(3098, 20);
        Commission commission3 = new Commission(30938, 20);

        //time slots
        int[][] timeslot1 = new int[2][2];
        timeslot1[0] = new int[]{12, 0};
        timeslot1[1] = new int[]{14, 0};


        //announcement 1
        Land land1 = new Land(12345, 2, 1231, getPhotos(), address1, person1);

        AnnouncementRequest announcementRequest1 = new AnnouncementRequest(land1, 12, agent, RequestType.SALE);
        Announcement announcement1 = new Announcement(announcementRequest1, commission1, date1, Status.ACCEPT);

        //announcement 2
        House house2 = new House(23456, 2, 1231, getPhotos(),
                address2, 2, 3, 2, true, false,
                true, false, "west", person1);

        AnnouncementRequest announcementRequest2 = new AnnouncementRequest(house2, 12, agent, RequestType.SALE);
        Announcement announcement2 = new Announcement(announcementRequest2, commission2, date2, Status.ACCEPT);


        //announcement3
        Apartment apartment1 = new Apartment(22342, 2, 10000, address3, getPhotos(),
                2, 3, 2, true, false, person1);

        AnnouncementRequest announcementRequest3 = new AnnouncementRequest(apartment1, 12, agent, RequestType.SALE);
        Announcement announcement3 = new Announcement(announcementRequest3, commission3, date3, Status.ACCEPT);


        //announcement request 4
        Apartment apartment2 = new Apartment(22342, 2, 12354, address3, getPhotos(),
                2, 3, 2, true, false, person1);

        AnnouncementRequest announcementRequest4 = new AnnouncementRequest(apartment2, 12, agent, RequestType.SALE);
        Announcement announcement4 = new Announcement(announcementRequest4, commission1, date4, Status.PENDING);

        //announcement request 5
        Land land2 = new Land(22342, 2, 1231, getPhotos(), address3, person1);

        AnnouncementRequest announcementRequest5 = new AnnouncementRequest(land2, 12, agent, RequestType.LEASE);
        Announcement announcement5 = new Announcement(announcementRequest5, commission3, date5, Status.PENDING);


        //store1
        Store store1 = new Store(1234212, "store@email.app", address4, 1234, announcements, manager, agents);
        Store store2 = new Store(1234212, "store@email.app", address5, 1234, announcements, manager, agents);


        //create visit requests
        VisitRequest visitRequest1 = new VisitRequest(announcement1,"12/06/2023",timeslot1,"Hello", person1);
        VisitRequest visitRequest2 = new VisitRequest(announcement2,"12/03/2023",timeslot1,"Hello", person1);
        VisitRequest visitRequest3 = new VisitRequest(announcement3,"12/04/2023",timeslot1,"Hello", person1);
        VisitRequest visitRequest4 = new VisitRequest(announcement4,"16/06/2023",timeslot1,"Hello", person1);
        VisitRequest visitRequest5 = new VisitRequest(announcement5,"18/06/2023",timeslot1,"Hello", person1);
        VisitRequest visitRequest6 = new VisitRequest(announcement1,"20/06/2023",timeslot1,"Hello", person1);
        VisitRequest visitRequest7 = new VisitRequest(announcement2,"30/06/2023",timeslot1,"Hello", person1);


        //add announcements
        announcementRepository.addAnnouncement(announcement1);
        announcementRepository.addAnnouncement(announcement2);
        announcementRepository.addAnnouncement(announcement3);
        announcementRepository.addAnnouncement(announcement4);
        announcementRepository.addAnnouncement(announcement5);

        //add announcements request
        requestRepository.addRequest(announcementRequest1);
        requestRepository.addRequest(announcementRequest2);
        requestRepository.addRequest(announcementRequest3);
        requestRepository.addRequest(announcementRequest4);
        requestRepository.addRequest(announcementRequest5);

        //add stores
        branchRepository.addStore(store1);
        branchRepository.addStore(store2);

        //add visits

        visitRequestRepository.addRequests(visitRequest1);
        visitRequestRepository.addRequests(visitRequest2);
        visitRequestRepository.addRequests(visitRequest3);
        visitRequestRepository.addRequests(visitRequest4);
        visitRequestRepository.addRequests(visitRequest5);
        visitRequestRepository.addRequests(visitRequest6);
        visitRequestRepository.addRequests(visitRequest7);


        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();

        //create orders
        PurchaseOrder purchaseOrder1 = new PurchaseOrder("ola@this.app", 123, announcement1);
        PurchaseOrder purchaseOrder2 = new PurchaseOrder("ola@this.app", 30, announcement2);

        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        List<PurchaseOrder> purchaseOrderList2 = new ArrayList<>();

        //set order state , tem de estar em accept para testar a us018 pessoal
        purchaseOrder1.setStatus(Status.ACCEPT);
        purchaseOrder2.setStatus(Status.ACCEPT);

        //add orders to repository list
        purchaseOrderList.add(purchaseOrder1);
        purchaseOrderList2.add(purchaseOrder2);

        //set order to announcements
        announcement2.setOrders(purchaseOrderList);
        announcement1.setOrders(purchaseOrderList2);

        //adding to the order repository
        orderRepository.addOrder(purchaseOrder1);
        orderRepository.addOrder(purchaseOrder2);



    }


}

