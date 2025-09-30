package jobs4u.costumer.application;

import eapli.framework.application.UseCaseController;
import jobs4u.costumer.domain.Costumer;
import jobs4u.costumer.repositories.CostumerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.jobs4uUser.domain.Jobs4uUser;

@UseCaseController
public class AddCostumerController {

    private final CostumerRepository costumerRepository = PersistenceContext
            .repositories().costumers();

    public void addCostumer(final Jobs4uUser user, final String company) {
        createCostumer(user,company);
    }

    private void createCostumer(final Jobs4uUser user, final String company) {
        this.costumerRepository.save(new Costumer(user, company));
    }
}
