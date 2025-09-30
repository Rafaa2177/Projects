package jobs4u.jobapplication.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.*;
import jobs4u.candidate.domain.Candidate;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobopening.domain.*;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import jobs4u.usermanagement.domain.BaseRoles;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordCounterTest {

    public static SystemUser dummyUser(final String username, final Role... roles) {
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    @Test
    public void testCountWords() {

        // Create a job opening
        Designation t = Designation.valueOf("title");
        Description d = Description.valueOf("description");
        NumberOfVacancies nv = new NumberOfVacancies(1);

        Mode m = Mode.REMOTE;
        ContractType ct = ContractType.FULLTIME;
        ApplicationProcess ap = ApplicationProcess.SCREENING;
        JobOpeningStatus jos = JobOpeningStatus.OPEN;

        Jobs4uUser user = new Jobs4uUser(dummyUser("dummy", BaseRoles.CUSTOMER), "address1", "phone1");

        Costumer c = new Costumer(user, "Company Name");

        JobReference jr = new JobReference("IBM-000123");

        JobOpening jo = new JobOpening(t, d, c, nv, ct, m, ap, jos,jr);

        jo.setInterviewId(1);
        jo.setRequirementId(1);


        // Create a candidate
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CANDIDATE);
        roles.add(BaseRoles.CLIENT_USER);
        Candidate ca = new Candidate(user);

        String cfp = "../jobs4u.applicationUtilities/ApplicationFileBot/candidates/test";

        JobApplication ja = new JobApplication(cfp, ca, jo);

        assertTrue(Files.exists(Path.of(ja.candidateFilesPath())));


        List<WordInfo> wordInfos = new FileProcessor().processFiles(ja);

        wordInfos.forEach(wi -> System.out.println(wi.getWord() + " " + wi.getCount() + " " + wi.getFiles()));


        assertEquals(21, wordInfos.size());
        assertEquals("twenty", wordInfos.get(0).getWord());
        assertEquals(20, wordInfos.get(0).getCount());
        assertTrue(wordInfos.get(0).getFiles().contains("test.txt"));

        assertEquals("fourteen", wordInfos.get(6).getWord());
        assertEquals(14, wordInfos.get(6).getCount());
        assertTrue(wordInfos.get(6).getFiles().contains("test.txt"));
    }

}
