package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.sort_algorithm.HeapSort;
import pt.ipp.isep.dei.esoft.project.domain.sort_algorithm.MergeSort;
import pt.ipp.isep.dei.esoft.project.domain.sort_algorithm.Sorter;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * The type Repositories.
 */
public class Repositories implements Serializable {

    private static Repositories instance = new Repositories();
    private Properties properties;

    /**
     * The Task category repository.
     */
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    /**
     * The Organization repository.
     */
    OrganizationRepository organizationRepository = new OrganizationRepository();
    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    /**
     * The Branch repository.
     */
    BranchRepository branchRepository = new BranchRepository();
    /**
     * The Request repository.
     */
    RequestRepository requestRepository = new RequestRepository();
    /**
     * The Announcement repository.
     */
    AnnouncementRepository announcementRepository= new AnnouncementRepository();
    /**
     * The Person repository.
     */
    PersonRepository personRepository = new PersonRepository();
    /**
     * The Purchase order repository.
     */
    PurchaseOrderRepository purchaseOrderRepository = new PurchaseOrderRepository();
    /**
     * The Order repository.
     */
    OrderRepository orderRepository= new OrderRepository();

    /**
     * The Visit request repository.
     */
    VisitRequestRepository visitRequestRepository = new VisitRequestRepository(sortingAlgorithm());

    /**
     * Gets organization repository.
     *
     * @return the organization repository
     */
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    /**
     * Gets task category repository.
     *
     * @return the task category repository
     */
    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Get announcement repository announcement repository.
     *
     * @return the announcement repository
     */
    public AnnouncementRepository getAnnouncementRepository(){return announcementRepository;}

    /**
     * Gets visit request repository.
     *
     * @return the visit request repository
     */
    public VisitRequestRepository getVisitRequestRepository() {return visitRequestRepository;}

    /**
     * Get branch repository branch repository.
     *
     * @return the branch repository
     */
    public BranchRepository getBranchRepository(){ return branchRepository; }

    /**
     * Gets request repository.
     *
     * @return the request repository
     */
    public RequestRepository getRequestRepository() {
        return requestRepository;
    }

    /**
     * Gets order repository.
     *
     * @return the order repository
     */
    public OrderRepository getOrderRepository() {
        return orderRepository;
    }


    /**
     * Sets authentication repository.
     *
     * @param authenticationRepository the authentication repository
     */
    public void setAuthenticationRepository(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Sets branch repository.
     *
     * @param branchRepository the branch repository
     */
    public void setBranchRepository(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    /**
     * Sets request repository.
     *
     * @param requestRepository the request repository
     */
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    /**
     * Sets instance.
     *
     * @param instance the instance
     */
    public static void setInstance(Repositories instance) {
        Repositories.instance = instance;
    }


    /**
     * Gets purchase order repository.
     *
     * @return the purchase order repository
     */
    public PurchaseOrderRepository getPurchaseOrderRepository() {
        return purchaseOrderRepository;
    }


    /**
     * Gets person repository.
     *
     * @return the person repository
     */
    public PersonRepository getPersonRepository() {
        return personRepository;
    }


    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Repositories getInstance() {
        if (instance == null) {
            instance = new Repositories();
        }
        return instance;
    }

    private Properties getProperties(){
        if(properties==null){
            properties=System.getProperties();
            try {
                properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
                return properties;
            } catch (IOException e) {
                throw new RuntimeException("Could not load properties!",e);
            }
        }
        return properties;
    }

    /**
     * Sorting algorithm sorter.
     *
     * @return the sorter
     */
    public Sorter sortingAlgorithm(){
        Properties props = getProperties();
        String algorithm=props.getProperty("sorting.algorithm","MERGE").toUpperCase();
        switch (algorithm){
            default:
            case "MERGE":
                return new MergeSort();
            case "HEAP":
                return new HeapSort();
        }
    }


}


