
package jobs4u.candidate;

import eapli.framework.infrastructure.authz.domain.model.*;
import jobs4u.candidate.domain.Candidate;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import jobs4u.usermanagement.domain.BaseRoles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CandidateTest {

    public static SystemUser dummyUser(final String username, final Role... roles) {
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.CANDIDATE);
    }


    @Test
    public void ensureCandidateEqualsFailsForDifferentUsers() throws Exception {
        final SystemUser user1 = dummyUser("dummy1", BaseRoles.ADMIN);
        final SystemUser user2 = dummyUser("dummy2", BaseRoles.ADMIN);
        final Candidate aCandidate = new Candidate(new Jobs4uUser(user1, "firstName1", "lastName1"));
        final Candidate anotherCandidate = new Candidate(new Jobs4uUser(user2, "firstName2", "lastName2"));
        assertFalse(aCandidate.sameAs(anotherCandidate));
    }

    @Test
    public void ensureCandidateEqualsAreTheSameForTheSameInstance() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Candidate aCandidate = new Candidate(new Jobs4uUser(user, "firstName", "lastName"));
        assertTrue(aCandidate.equals(aCandidate));
    }

    @Test
    public void ensureCandidateEqualsFailsForDifferentObjectTypes() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Candidate aCandidate = new Candidate(new Jobs4uUser(user, "firstName", "lastName"));
        assertFalse(aCandidate.equals(user));
    }

    @Test
    public void ensureCandidateIsTheSameAsItsInstance() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Candidate aCandidate = new Candidate(new Jobs4uUser(user, "firstName", "lastName"));
        assertTrue(aCandidate.sameAs(aCandidate));
    }

}




