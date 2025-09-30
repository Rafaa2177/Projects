package jobs4u.costumerManager.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.costumerManager.domain.CostumerManager;

import java.util.Optional;

public interface CostumerManagerRepository extends DomainRepository<Long, CostumerManager> {
    Optional<CostumerManager> findByEmail(EmailAddress email);
}
