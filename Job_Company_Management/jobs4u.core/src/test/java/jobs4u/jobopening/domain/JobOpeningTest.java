package jobs4u.jobopening.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobOpeningTest {

    private final Designation t = Designation.valueOf("title");
    private final Description d = Description.valueOf("description");
    private final NumberOfVacancies nv = new NumberOfVacancies(1);

    private final Mode m = Mode.REMOTE;
    private final ContractType ct = ContractType.FULLTIME;
    private final ApplicationProcess ap = ApplicationProcess.SCREENING;
    private final JobOpeningStatus jos = JobOpeningStatus.OPEN;

    private final String q = "question";

    private final SystemUser su = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder())
            .with("userName", "password", "firstName", "lastName", "email@acme.com").build();
    private final Jobs4uUser ju = new Jobs4uUser(su,"address", "phone");
    private final Costumer c = new Costumer(ju, "company");

    private final JobReference jr = new JobReference("ref");

    @Test
    void ensureJobOpeningHasTitle() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(null, d, c, nv, ct, m, ap, jos, jr));
    }

    @Test
    void ensureJobOpeningHasDescription() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(t, null, c, nv, ct, m, ap, jos, jr));
    }

    @Test
    void ensureJobOpeningHasCustomer() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(t, d, null, nv, ct, m, ap, jos, jr));
    }

    @Test
    void ensureJobOpeningHasNumberOfVacancies() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(t, d, c, null, ct, m, ap, jos, jr));
    }

    @Test
    void ensureJobOpeningHasContractType() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(t, d, c, nv, null, m, ap, jos, jr));
    }

    @Test
    void ensureJobOpeningHasMode() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(t, d, c, nv, ct, null, ap, jos, jr));
    }

    @Test
    void ensureJobOpeningHasApplicationProcess() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(t, d, c, nv, ct, m, null, jos, jr));
    }

    @Test
    void ensureJobOpeningHasStatus() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(t, d, c, nv, ct, m, ap, null, jr));
    }

    @Test
    void ensureJobOpeningHasReference() {
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(t, d, c, nv, ct, m, ap, jos, null));
    }

}
