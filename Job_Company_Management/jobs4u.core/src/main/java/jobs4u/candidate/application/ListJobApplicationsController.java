package jobs4u.candidate.application;


import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;

public class ListJobApplicationsController {

    private RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final JobApplicationRepository jobApplicationRepository = repositoryFactory.jobapplications();


    public Iterable<JobApplication> getMyJobApplications(Username email) {
        // TODO Auto-generated method stub
       return jobApplicationRepository.findByCandidateEmail(email);
    }


    public int getNumberOfApplicants(JobOpening jobOpening) {
        return jobApplicationRepository.countByJobOpening(jobOpening);
    }
    
}
