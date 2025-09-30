package jobs4u.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.candidate.domain.Candidate;
import jobs4u.candidate.repositories.CandidateRepository;

import java.util.HashMap;
import java.util.Map;


class JpaCandidateRepository
        extends JpaAutoTxRepository<Candidate, Long, Long>
        implements CandidateRepository {

    public JpaCandidateRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaCandidateRepository(final String puname) {
        super(puname,
                "id");
    }


    @Override
    public Iterable<Candidate> allCandidates() {
        return findAll();
    }

    @Override
    public boolean exists(String email) {
        final Map<String, Object> params = new HashMap<>();
        EmailAddress email1 = EmailAddress.valueOf(email);
        params.put("email", email1);
        return matchOne("e.user.systemUser.email=:email", params).isPresent();
    }

    @Override
    public Candidate findByEmail(String email) {
        final Map<String, Object> params = new HashMap<>();
        EmailAddress email1 = EmailAddress.valueOf(email);
        params.put("email", email1);
        return matchOne("e.user.systemUser.email=:email", params).get();
    }


}