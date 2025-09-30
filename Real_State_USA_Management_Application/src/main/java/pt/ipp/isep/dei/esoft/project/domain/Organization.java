
package pt.ipp.isep.dei.esoft.project.domain;


import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Organization.
 */
public class Organization implements Serializable {
    private int ID;

    /**
     * The Id string.
     */
    String idString = String.valueOf(ID);
    /**
     * The Employees.
     */
    List<Person> employees = new ArrayList<>();
    /**
     * The Persons.
     */
    List<Person> persons = new ArrayList<>();
    //List<Task> tasks = new ArrayList<>();
    private String name;
    private String phone;
    private String email;
    private final Address address;
    private int numberOfProperties = 0;

    /**
     * This method is the constructor of the organization.
     *
     * @param vatNumber The vat number of the organization. This is the identity of the organization, therefore it                  cannot be changed.
     * @param name      the name
     * @param phone     the phone
     * @param email     the email
     * @param address   the address
     */
    public Organization(int vatNumber, String name, String phone, String email, Address address) {
        this.ID = vatNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        numberOfProperties++ ;
    }

    /**
     * Gets number of properties.
     *
     * @return the number of properties
     */
    public int getNumberOfProperties() {return numberOfProperties;}

    /**
     * Sets number of properties.
     *
     * @param numberOfProperties the number of properties
     */
    public void setNumberOfProperties(int numberOfProperties) {this.numberOfProperties = numberOfProperties;}

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * This method checks if an employee works for the organization.
     *
     * @param employee The employee to be checked.
     * @return True if the employee works for the organization.
     */
    public boolean employs(Person employee) {
        return employees.contains(employee);
    }

    /**
     * This method creates a new task.
     *
     * @param reference            The reference of the task to be created.
     * @param description          The description of the task to be created.
     * @param informalDescription  The informal description of the task to be created.
     * @param technicalDescription The technical description of the task to be created.
     * @param duration             The duration of the task to be created.
     * @param cost                 The cost of the task to be created.
     * @param taskCategory         The task category of the task to be created.
     * @param employee             The employee of the task to be created.
     * @return optionalValue
     */
    /*
    public Optional<Task> createTask(String reference, String description, String informalDescription,
                                     String technicalDescription, Integer duration, Double cost,
                                     TaskCategory taskCategory, Person employee) {

        //TODO: we could also check if the employee works for the organization before proceeding
        //checkIfEmployeeWorksForOrganization(employee);

        // When a Task is added, it should fail if the Task already exists in the list of Tasks.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Task> optionalValue = Optional.empty();

        Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                taskCategory, employee);

        if (addTask(task)) {
            optionalValue = Optional.of(task);
        }
        return optionalValue;
    }

     */

    /**
     * This method adds a task to the list of tasks.
     *
     * @param task The task to be added.
     * @return True if the task was added successfully.
     */
    /*
    private boolean addTask(Task task) {
        boolean success = false;
        if (validate(task)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = tasks.add(task.clone());
        }
        return success;

    }

     */

    /**
     * This method validates the task, checking for duplicates.
     *
     * @param task The task to be validated.
     * @return True if the task is valid.
     */
    /*
    private boolean validate(Task task) {
        return tasksDoNotContain(task);
    }

     */

    /**
     * This method checks if the task is already in the list of tasks.
     *
     * @param task The task to be checked.
     * @return True if the task is not in the list of tasks.
     */
    /*
    private boolean tasksDoNotContain(Task task) {
        return !tasks.contains(task);
    }

     */

    /**
     * These methods check if the organization has an employee with the given email.
     *
     * @param email The email to be checked.
     * @return True if the organization has an employee with the given email.
     */
    public boolean anyEmployeeHasEmail(String email) {
        boolean result = false;
        for (Person employee : employees) {
            if (employee.hasId(new Email(email))) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        Organization that = (Organization) o;
        return idString.equals(that.idString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    /**
     * Add employee boolean.
     *
     * @param employee the employee
     * @return the boolean
     */
//add employee to organization
    public boolean addEmployee(Person employee) {
        boolean success = false;
        if (validateEmployee(employee)) {
            success = employees.add(employee);
        }
        return success;
    }

    /**
     * Add persons.
     *
     * @param person the person
     */
    public void addPersons(Person person) {
        persons.add(person);
    }

    private boolean validateEmployee(Person employee) {
        return employeesDoNotContain(employee);
    }

    private boolean employeesDoNotContain(Person employee) {
        return !employees.contains(employee);
    }

    //Clone organization
    public Organization clone() {
        Organization clone = new Organization(this.ID, this.name, this.phone, this.email, this.address);
        clone.name = (this.name);
        clone.phone = (this.phone);
        clone.email = (this.email);

        for (Person in : this.employees) {
            clone.employees.add(in.clone());
        }

/*
        for (Task in : this.tasks) {
            clone.tasks.add(in.clone());
        }

 */


        return clone;
    }

    /**
     * Gets employees.
     *
     * @return the employees
     */
    public List<Person> getEmployees() {
        return employees;
    }

    /**
     * Get persons list.
     *
     * @return the list
     */
    public List<Person> getPersons(){
        return persons;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "ID =" + ID +
                ", idString ='" + idString + '\'' +
                ", employees =" + employees +
                ", persons =" + persons +
                ", name ='" + name + '\'' +
                ", phone ='" + phone + '\'' +
                ", email ='" + email + '\'' +
                ", address =" + address +
                ", numberOfProperties =" + numberOfProperties +
                '}';
    }
}
