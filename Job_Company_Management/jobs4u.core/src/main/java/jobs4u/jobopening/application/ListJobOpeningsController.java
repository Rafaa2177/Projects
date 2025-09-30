package jobs4u.jobopening.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.usermanagement.domain.BaseRoles;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@UseCaseController
public class ListJobOpeningsController {

    private final RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings();
    private final JobApplicationRepository jobApplicationRepository = repositoryFactory.jobapplications();

    public List<String> listJobOpenings() {
        Iterable<JobOpening> jobOpenings = jobOpeningRepository.findAll();
        List<String> jobOpeningsInfo = new LinkedList<>();

        for (JobOpening jobOpening : jobOpenings) {
            jobOpeningsInfo.add(formatJobOpeningInfo(jobOpening));
        }

        return jobOpeningsInfo;
    }

    private String formatJobOpeningInfo(JobOpening jobOpening) {
        return String.format(jobOpening.getInfor() + " " + "\nNumber of Applicants: " + getNumberOfApplicants(jobOpening) );
    }

    public int getNumberOfApplicants(JobOpening jobOpening) {
        return jobApplicationRepository.countByJobOpening(jobOpening);
    }

}
