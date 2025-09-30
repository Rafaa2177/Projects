package jobs4u.candidate.application;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.candidate.domain.Candidate;
import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.usermanagement.domain.BaseRoles;

import java.util.*;

public class ListCandidatesController {

    private final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();

    public List<EmailAddress> getAllCandidateEmails() {
        Iterable<Candidate> candidatesIterable = candidateRepository.findAll();
        List<EmailAddress> emails = new ArrayList<>();
        for (Candidate candidate : candidatesIterable) {
            if (candidate.getUser().getSystemUser().hasAny((BaseRoles.CANDIDATE)) ){
                EmailAddress email = candidate.user().getSystemUser().email();
                emails.add(email);
            }
        }
        return emails;
    }

    public Iterable<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }




}
