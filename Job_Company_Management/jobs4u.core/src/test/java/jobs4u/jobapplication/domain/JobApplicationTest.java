package jobs4u.jobapplication.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.*;
import jobs4u.candidate.domain.Candidate;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobopening.domain.*;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobApplicationTest {
    private final Designation t = Designation.valueOf("title");
    private final Description dc = Description.valueOf("description");
    private final NumberOfVacancies nv = new NumberOfVacancies(1);

    private final Mode m = Mode.REMOTE;
    private final ContractType ct = ContractType.FULLTIME;
    private final ApplicationProcess ap = ApplicationProcess.SCREENING;
    private final JobOpeningStatus jos = JobOpeningStatus.OPEN;

    private final SystemUser su = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder())
            .with("userName", "password", "firstName", "lastName", "email@acme.com").build();
    private final Jobs4uUser ju = new Jobs4uUser(su,"address", "phone");
    private final Costumer c = new Costumer(ju, "company");

    private final JobOpening jo = new JobOpening(t, dc, c, nv, ct, m, ap, jos, new JobReference("REF1"));



    private final SystemUser csu = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder())
            .with("userName1", "password1", "firstNamee", "lastNamee", "email1@acme.com").build();
    private final Jobs4uUser cju = new Jobs4uUser(csu,"address1", "phone1");
    private final Candidate cc = new Candidate(cju);

    private final String cfp = "path";

    @Test
    void ensureJobApplicationHasCandidate() {
        assertThrows(IllegalArgumentException.class, () -> new JobApplication(cfp,null,jo));
    }

    @Test
    void ensureJobApplicationHasJobOpening() {
        assertThrows(IllegalArgumentException.class, () -> new JobApplication(cfp,cc,null));
    }

}
