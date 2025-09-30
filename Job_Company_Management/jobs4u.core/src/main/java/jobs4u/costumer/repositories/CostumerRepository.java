package jobs4u.costumer.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.costumer.domain.Costumer;
import jobs4u.costumerManager.domain.CostumerManager;

import java.util.List;

public interface CostumerRepository extends DomainRepository<Long, Costumer> {

    List<Costumer> findByManager(CostumerManager manager);
}
