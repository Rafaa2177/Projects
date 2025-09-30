package jobs4u.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.domain.JobReference;
import jobs4u.jobopening.repositories.JobOpeningRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryJobOpeningRepository extends InMemoryDomainRepository<JobOpening, JobReference>
    implements JobOpeningRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public JobOpening findByReference(String reference) {
        System.out.println("Finding job opening by reference " + reference);
        return matchOne(e -> e.identity().toString().equals(reference)).orElse(null);
    }

    @Override
    public List<JobOpening> findByCustomer(Costumer c) {
        System.out.println("Finding job openings by customer " + c);
        List<JobOpening> jo = new LinkedList<>();
        match(e -> e.customer().equals(c)).iterator().forEachRemaining(jo::add);
        return jo;
    }

    @Override
    public List<JobOpening> findByPhaseAnalysis() {
        System.out.println("Finding job openings by phase analysis");
        List<JobOpening> jo = new LinkedList<>();
        match(e -> e.currentPhase().getName().equals("Analysis")).iterator().forEachRemaining(jo::add);
        return jo;
    }
}
