package jobs4u.ranking.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.candidate.domain.Candidate;
import jobs4u.costumer.domain.Costumer;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.ranking.repositories.RankingRepository;
import jobs4u.usermanagement.domain.BaseRoles;
import org.example.notification.EmailService;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@UseCaseController
public class NotifyResultsController {

    private RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final CostumerManagerRepository customerManagerRepository = repositoryFactory.costumerManagers();
    private final RankingRepository rankingRepository = repositoryFactory.rankings();
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    public List<JobOpening> listJobOpeningsByManager() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CUSTOMER_MANAGER);
        List<JobOpening> jobOpeningList = new LinkedList<>();
        Optional<CostumerManager> manager = customerManagerRepository.findByEmail(authorizationService.session().get().authenticatedUser().email());
        manager.ifPresent(costumerManager -> {
            costumerManager.jobopenings().forEach(jobOpeningList::add);
        });
        return jobOpeningList;
    }

    public void notifyResults(JobOpening jo) {
        List<Candidate> rankedCandidatesList = rankingRepository.findByJobOpening(jo);
        int vacancies = jo.numberOfVacancies().value();

        List<Candidate> selectedCandidates;

        if(rankedCandidatesList.size() < vacancies) {
            selectedCandidates=rankedCandidatesList;
        }
        else {
            selectedCandidates=rankedCandidatesList.subList(0, vacancies);
        }

        notifyCandidate(selectedCandidates, jo);
        notifyCostumer(selectedCandidates, jo);
    }

    private void notifyCandidate(List<Candidate> c, JobOpening jo) {
        EmailService emailService = new EmailService();
        c.forEach(candidate -> {
            System.out.println("Notifying "+candidate+"...");
            emailService.sendEmail(candidate.user().user().email().toString(), "Job application for "+jo.identity().toString()+" results", "You have been selected for a job opening");
        });
    }

    private void notifyCostumer(List<Candidate> sc, JobOpening jo) {
        EmailService emailService = new EmailService();

        StringBuilder selectedCandidates = new StringBuilder();
        sc.forEach(c -> {
            selectedCandidates.append(c.user().user().email().toString()+", ");
        });
        selectedCandidates.deleteCharAt(selectedCandidates.length()-1);
        System.out.println("Notifying "+jo.customer().user().user().email().toString()+"...");
        emailService.sendEmail(jo.customer().user().user().email().toString(), "Job opening("+jo.identity().toString()+") results", "The selected candidates are: \n"+selectedCandidates.toString());
    }

}

