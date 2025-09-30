package jobs4u.jobapplication.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobopening.domain.JobOpening;

public interface JobApplicationRepository  extends DomainRepository<Long,JobApplication> {
    Iterable<JobApplication> findByJobOpening(JobOpening jobOpening);

    int countByJobOpening(JobOpening jobOpening);

    Iterable<JobApplication> findByCandidateEmail(Username email);

    int countByRequirementsMet(JobOpening jobOpening);

    int countByInterviewGrade(JobOpening jobOpening);

    int countByResult(JobOpening jobOpening);
}
