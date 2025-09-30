package jobs4u.jobopening.application;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.costumer.domain.Costumer;
import jobs4u.costumer.repositories.CostumerRepository;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobopening.domain.*;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.usermanagement.domain.BaseRoles;

import java.util.Optional;


public class AddJobOpeningController {
    private RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings();
    private CostumerRepository customerRepository = repositoryFactory.costumers();
    private CostumerManagerRepository costumerManagerRepository = repositoryFactory.costumerManagers();
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    public Iterable<Costumer> listCustomers() {
        return customerRepository.findAll();
    }

    public JobReference addJobOpening(Designation title, Description description, Costumer customer, NumberOfVacancies numberOfVacancies, ContractType contractType, Mode mode, ApplicationProcess process, JobOpeningStatus status) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CUSTOMER_MANAGER);
        JobOpening jobOpening = new JobOpening(title, description, customer, numberOfVacancies, contractType, mode, process, status);
        jobOpeningRepository.save(jobOpening);
        Optional<CostumerManager> costumerManager = costumerManagerRepository.findByEmail(authorizationService.session().get().authenticatedUser().email());
        costumerManager.ifPresent(manager -> {
                manager.addJobOpening(jobOpening);
                costumerManagerRepository.save(manager);
        });
        return jobOpening.identity();
    }
}
