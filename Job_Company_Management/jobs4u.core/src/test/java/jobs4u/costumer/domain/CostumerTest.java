package jobs4u.costumer.domain;

import eapli.framework.infrastructure.authz.domain.model.*;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import jobs4u.usermanagement.domain.BaseRoles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CostumerTest {
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
        final Costumer costumer = new Costumer(new Jobs4uUser(user1, "address1", "phone1"), "company1");
        final Costumer anotherCostumer = new Costumer(new Jobs4uUser(user2, "address2", "phone2"), "company2");
        assertFalse(costumer.sameAs(anotherCostumer));
    }

    @Test
    public void ensureJobs4uUserEqualsAreTheSameForTheSameInstance() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Costumer costumer = new Costumer(new Jobs4uUser(user, "address1", "phone1"), "company1");
        assertTrue(costumer.equals(costumer));
    }

    @Test
    public void ensureJobs4uUserEqualsFailsForDifferentObjectTypes() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Costumer costumer = new Costumer(new Jobs4uUser(user, "address1", "phone1"), "company1");
        assertFalse(costumer.equals(user));
    }

    @Test
    public void ensureJobs4uUserIsTheSameAsItsInstance() throws Exception {
        final SystemUser user = getNewDummyUser();
        final Costumer costumer = new Costumer(new Jobs4uUser(user, "address1", "phone1"), "company1");
        assertTrue(costumer.sameAs(costumer));
    }
}
