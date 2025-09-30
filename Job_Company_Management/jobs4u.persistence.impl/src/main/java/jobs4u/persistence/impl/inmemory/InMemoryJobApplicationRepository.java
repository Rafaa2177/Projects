package jobs4u.persistence.impl.inmemory;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;

public class InMemoryJobApplicationRepository extends InMemoryDomainRepository<JobApplication, Long>
        implements JobApplicationRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<JobApplication> findByJobOpening(JobOpening jobOpening) {
        return match(e -> e.jobopening().identity().equals(jobOpening));
    };

    @SuppressWarnings("unchecked")
    @Override
    public int countByJobOpening(JobOpening jobOpening) {
        return (int) ((DomainRepository<Long, JobApplication>) match(e -> e.jobopening().identity().equals(jobOpening))).count();
    }

    @Override
    public Iterable<JobApplication> findByCandidateEmail(Username username) {
        return match(e -> e.candidate().user().user().username().equals(username));
    }

    @Override
    public int countByRequirementsMet(JobOpening jobOpening) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByRequirementsMet'");
    }

    @Override
    public int countByInterviewGrade(JobOpening jobOpening) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByInterviewGrade'");
    }

    @Override
    public int countByResult(JobOpening jobOpening) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByResult'");
    }

}
