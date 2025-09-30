package pt.ipp.isep.dei.esoft.project.application.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.service.EmailService;
import pt.ipp.isep.dei.esoft.project.application.service.TextEmailService;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceAdapterTest {

    EmailServiceAdapter adapter;


    @BeforeEach
    void setUp(){
        this.adapter = new EmailServiceAdapter();
    }


    @Test
    void ensureDefaultIsTextEmailService(){
        EmailService service = adapter.getEmailService();

        assert service instanceof TextEmailService;
    }

    @Test
    void ensureCanGetDefaultExternally(){
        EmailService service = adapter.getEmailService("textEmailService");

        assert service instanceof TextEmailService;
    }

    @Test
    void ensureErrorForNonExistentClassIsCovered(){

        Throwable t = assertThrows(IllegalArgumentException.class,
                () -> {
                    adapter.getEmailService("NON EXISTENT");
                });

        assertEquals("ERROR: Email Service Implementation was not found.", t.getMessage());
    }
}