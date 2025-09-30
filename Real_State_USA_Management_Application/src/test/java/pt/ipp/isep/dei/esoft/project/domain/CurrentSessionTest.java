package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

public class CurrentSessionTest {
    private PersonRepository personRepository;
    private CurrentSession currentSession;


    public void setUp() {
        personRepository = new PersonRepository();
        currentSession = new CurrentSession();
    }


    public void testGetUser() {
        // Arrange
        String email = "john@example.com";
        Person expectedPerson = new Person(new Email("ok@this.app"), "781623", "miguel", "12345567","123329876", "(351) 938-4135", Roles.CLIENT) ;

        List<Person> persons = new ArrayList<>();
        persons.add(expectedPerson);
        personRepository.setListPersonsRepository(persons);
        currentSession.setCurrentSession(email);

        // Act
        Person result = currentSession.getUser();

        // Assert
        Assertions.assertEquals(expectedPerson, result);
    }
}
