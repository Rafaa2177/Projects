package jobs4u.ranking.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.candidate.domain.Candidate;
import jobs4u.costumer.domain.Costumer;
import jobs4u.costumer.repositories.CostumerRepository;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.ranking.domain.Ranking;
import jobs4u.ranking.repositories.RankingRepository;
import jobs4u.usermanagement.domain.BaseRoles;

import java.util.*;

@UseCaseController
public class ListRankedCandidateController {

    private RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final CostumerManagerRepository customerManagerRepository = repositoryFactory.costumerManagers();
    private final JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings();
    private final RankingRepository rankingRepository = repositoryFactory.rankings();
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    public List<Costumer> listCustomersByManager() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CUSTOMER_MANAGER);
        List<Costumer> customersList = new LinkedList<>();
        Optional<CostumerManager> manager = customerManagerRepository.findByEmail(authorizationService.session().get().authenticatedUser().email());
        manager.ifPresent(costumerManager -> {
            costumerManager.costumers().forEach(customersList::add);
        });
        return customersList;
    }

    public List<JobOpening> listJobOpeningsByCustomer(Costumer costumer) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CUSTOMER_MANAGER);
        return jobOpeningRepository.findByCustomer(costumer);
    }

    public List<Candidate> listRankedCandidates(JobOpening jobOpening) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CUSTOMER_MANAGER);

        List<Candidate> rankedCandidatesList = rankingRepository.findByJobOpening(jobOpening);

        return rankedCandidatesList;
    }
}
