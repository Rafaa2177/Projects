

package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Person repository.
 */
public class PersonRepository implements Serializable {
    private List<Person> persons = new ArrayList<>();
    private List<Person> agents = new ArrayList<>();
    private List<Person> clients = new ArrayList<>();
    private List<Person> storeNetworkManager = new ArrayList<>();
    private List<Person> storeManager = new ArrayList<>();
    private List<Person> systemAdministrator = new ArrayList<>();
    private Person networkManager = null;



    /**
     * Add persons.
     *
     * @param person the person
     * @param role   the role
     * @param store  the store
     */
    public void addPersons (Person person, Roles role, Store store) {

        if (!validatePerson(person)){
            throw new IllegalArgumentException("User already exists");

        } else if(role == Roles.CLIENT){
            persons.add(person);
            clients.add(person);

        } else if (role == Roles.AGENT) {
            addAgentToStore(person, store);
            agents.add(person);

        } else if (role == Roles.SYSTEM_ADMINISTRATOR) {
            persons.add(person);
            systemAdministrator.add(person);

        } else if (role == Roles.STORE_MANAGER) {
            addManagerToStore(person, store);
            storeManager.add(person);

        } else if (role == Roles.STORE_NETWORK_MANAGER) {
            addNetworkManager(person);
            storeNetworkManager.add(person);
        }

    }

    private void addNetworkManager(Person person) {
        if (this.networkManager != null) {
            throw new IllegalArgumentException("Network Manager already exists.");
        }
        else {
            this.networkManager = person;
            persons.add(person);
        }
    }

    private void addAgentToStore(Person person, Store store){
        store.addAgent(person);
        persons.add(person);
    }

    private void addManagerToStore(Person person, Store store){
        if (store.getManager() != null){
            throw  new IllegalArgumentException("Store Manager already exists");
        } else {
            store.setManager(person);
            persons.add(person);
        }
    }

    private boolean validatePerson(Person person) {
        for (Person p: this.persons) {

            if (p.equals(person)){
                return false;
            }
        }
        return true;
    }


    /**
     * Get person person.
     *
     * @return the person
     */
    public Person getPerson (){
        Person user1 = null;
        List<Person> people = getListPersonsRepository();
        for (Person user: people){
            if (user.getId().getEmail().equals(CurrentSession.getEmail())){
                user1 = user;
            }
        }
        return user1;
    }
    public Person getPersonByEmail(String email){
        for (Person person : persons){
            if (person.getId().getEmail().equals(email)){
                return person;
            }
        }
        return null;
    }

    /**
     * Get list persons repository list.
     *
     * @return the list
     */
    public List<Person> getListPersonsRepository(){return this.persons;}

    public List<Person> getAgents() {return agents;}

    public List<Person> getClients() {return clients;}

    public List<Person> getStoreNetworkManager() {return storeNetworkManager;}

    public List<Person> getStoreManager() {return storeManager;}

    public List<Person> getSystemAdministrator() {return systemAdministrator;}

    public Person getNetworkManager() {return networkManager;}

    public void setListPersonsRepository(List<Person> persons) {
    }
}