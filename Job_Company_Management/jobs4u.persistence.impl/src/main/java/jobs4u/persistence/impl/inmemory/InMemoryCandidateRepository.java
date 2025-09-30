package jobs4u.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.candidate.domain.Candidate;
import jobs4u.candidate.repositories.CandidateRepository;


public class InMemoryCandidateRepository
            extends InMemoryDomainRepository<Candidate, Long>
            implements CandidateRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Candidate> allCandidates() {
         return match(e -> true);
    }

    @Override
    public boolean exists(String email) {
        return match(e -> e.user().getSystemUser().email().equals(email)).iterator().hasNext();
    }

    @Override
    public Candidate findByEmail(String email) {
        return matchOne(e -> e.user().getSystemUser().email().equals(email)).get();
    }


}
