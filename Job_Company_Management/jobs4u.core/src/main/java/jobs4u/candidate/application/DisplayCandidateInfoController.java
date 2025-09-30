package jobs4u.candidate.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.candidate.domain.Candidate;
import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;

import java.util.Optional;

public class DisplayCandidateInfoController {


            private final AuthorizationService authz = AuthzRegistry.authorizationService();

            private final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();

            public Iterable<Candidate> allCandidates() {

                return this.candidateRepository.findAll();
            }
            public Optional<Candidate> findByID(long ID) {

                return this.candidateRepository.findById(ID);

            }
        }



