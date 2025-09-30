package jobs4u.costumerManager.application;

import eapli.framework.application.UseCaseController;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.jobs4uUser.domain.Jobs4uUser;

@UseCaseController
public class AddCostumerManagerController {

    private final CostumerManagerRepository costumerManagerRepository = PersistenceContext
            .repositories().costumerManagers();

    public void addCostumerManager(final Jobs4uUser user) {
        createCostumerManager(user);
    }

    private void createCostumerManager(final Jobs4uUser user) {
        this.costumerManagerRepository.save(new CostumerManager(user));
    }
}
