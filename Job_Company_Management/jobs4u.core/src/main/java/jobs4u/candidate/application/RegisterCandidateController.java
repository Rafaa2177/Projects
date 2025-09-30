package jobs4u.candidate.application;

import eapli.framework.application.UseCaseController;

import eapli.framework.infrastructure.authz.domain.model.Role;
import jobs4u.candidate.domain.Candidate;
import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import jobs4u.jobs4uUser.domain.Jobs4uUserBuilder;
import jobs4u.jobs4uUser.repositories.Jobs4uUserRepository;

import java.util.Set;

@UseCaseController
public class RegisterCandidateController {


    private final Jobs4uUserRepository jobs4uUserRepository = PersistenceContext.repositories().jobs4uUsers();

    private final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();

    public void registerCandidate(final String username, final String password, final String firstName,
                                       final String lastName, final String email, final Set<Role> roles, final String address, final String phone) {

        createCandidate(username,password,firstName,lastName,email,roles, address, phone);
    }

    private void createCandidate(final String username, final String password, final String firstName,
                                 final String lastName, final String email, final Set<Role> roles, final String address, final String phone) {
        final Jobs4uUserBuilder jobs4uUserBuilder = new Jobs4uUserBuilder();
        jobs4uUserBuilder.withInfo(username,password,firstName,lastName,email,roles).withAddress(address).withPhone(phone);
        Jobs4uUser u = this.jobs4uUserRepository.save(jobs4uUserBuilder.build());
        this.candidateRepository.save(new Candidate(u));
    }






}