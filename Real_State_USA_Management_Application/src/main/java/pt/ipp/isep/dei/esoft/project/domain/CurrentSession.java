package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.Serializable;
import java.util.List;

/**
 * The type Current session.
 * Author:Diana Neves
 */
public class CurrentSession implements Serializable {
    private static String email;
    /**
     * The constant personRepository.
     */
    public static PersonRepository personRepository = Repositories.getInstance().getPersonRepository();

    /**
     * Set current session.
     *
     * @param id the id
     */
    public static void setCurrentSession(String id){ email = id;}

    /**
     * Get email string.
     *
     * @return the string
     */
    public static String getEmail(){
        return email;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public static Person getUser() {
        Person emp1 = null;
        List<Person> employees = personRepository.getListPersonsRepository();
        for (Person emp :employees ) {
            if (emp.getId().equals(email)) {
                emp1 = emp;
            }
        }
        return emp1;
    }


}