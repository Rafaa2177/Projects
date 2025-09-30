package jobs4u.candidate.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.candidate.domain.Candidate;


import java.util.Optional;

public interface CandidateRepository extends DomainRepository<Long, Candidate> {
    Iterable<Candidate> allCandidates();

    default Optional<Candidate> findById(Long id) {
        return ofIdentity(id);
    }

    boolean exists(String email);

    Candidate findByEmail(String email) ;




}