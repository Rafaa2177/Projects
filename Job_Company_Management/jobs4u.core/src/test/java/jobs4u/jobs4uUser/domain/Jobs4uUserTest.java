package jobs4u.jobs4uUser.domain;

import eapli.framework.infrastructure.authz.domain.model.*;
import jobs4u.usermanagement.domain.BaseRoles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Jobs4uUserTest {
    public static SystemUser dummyUser(final String username, final Role... roles) {
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.CANDIDATE);
    }


    @Test
    public void ensureJobs4uUserEqualsFailsForDifferentUsers() throws Exception {
        final SystemUser user1 = dummyUser("dummy1", BaseRoles.ADMIN);
        final SystemUser user2 = dummyUser("dummy2", BaseRoles.ADMIN);
        final Jobs4uUser jobs4uUser = new Jobs4uUser(user1, "address1", "phone1");
        final Jobs4uUser anotherJobs4uUser = new Jobs4uUser(user2, "address2", "phone2");
        assertFalse(jobs4uUser.sameAs(anotherJobs4uUser));
    }

    @Test
    public void ensureJobs4uUserEqualsAreTheSameForTheSameInstance() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Jobs4uUser jobs4uUser = new Jobs4uUser(user, "address1", "phone1");
        assertTrue(jobs4uUser.equals(jobs4uUser));
    }

    @Test
    public void ensureJobs4uUserEqualsFailsForDifferentObjectTypes() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Jobs4uUser jobs4uUser = new Jobs4uUser(user, "address1", "phone1");
        assertFalse(jobs4uUser.equals(user));
    }

    @Test
    public void ensureJobs4uUserIsTheSameAsItsInstance() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Jobs4uUser jobs4uUser = new Jobs4uUser(user, "address1", "phone1");
        assertTrue(jobs4uUser.sameAs(jobs4uUser));
    }
}
