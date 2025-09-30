package jobs4u.jobopening.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.domain.JobReference;

import java.util.List;

public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {

    JobOpening findByReference(String reference);

    List<JobOpening> findByCustomer(Costumer c);


    List<JobOpening> findByPhaseAnalysis();
}
