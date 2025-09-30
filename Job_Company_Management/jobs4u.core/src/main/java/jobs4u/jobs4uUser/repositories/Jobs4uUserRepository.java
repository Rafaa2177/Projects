package jobs4u.jobs4uUser.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import org.apache.commons.mail.Email;

import java.util.Optional;

public interface Jobs4uUserRepository  extends DomainRepository<Long, Jobs4uUser> {
}
