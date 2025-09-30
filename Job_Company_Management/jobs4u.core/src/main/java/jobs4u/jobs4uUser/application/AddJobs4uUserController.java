package jobs4u.jobs4uUser.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import jobs4u.jobs4uUser.domain.Jobs4uUserBuilder;
import jobs4u.jobs4uUser.repositories.Jobs4uUserRepository;

import java.util.Set;

@UseCaseController
public class AddJobs4uUserController {

    private final Jobs4uUserRepository jobs4uUserRepository = PersistenceContext
            .repositories().jobs4uUsers();

    public Jobs4uUser addJobs4uUser(final String username, final String password, final String firstName,
                                    final String lastName, final String email, final Set<Role> roles, final String address, final String phone) {
        return createJobs4uUser( username,password,firstName,lastName,email,roles,address, phone);
    }

    private Jobs4uUser createJobs4uUser(final String username, final String password, final String firstName,
                                        final String lastName, final String email, final Set<Role> roles, final String address, final String phone) {
        final Jobs4uUserBuilder jobs4uUserBuilder = new Jobs4uUserBuilder();
        jobs4uUserBuilder.withInfo(username,password,firstName,lastName,email,roles).withAddress(address).withPhone(phone);
        return this.jobs4uUserRepository.save(jobs4uUserBuilder.build());
    }
}
