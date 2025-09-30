package jobs4u.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;
import jobs4u.Application;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.domain.JobReference;
import jobs4u.jobopening.domain.Phase;
import jobs4u.jobopening.repositories.JobOpeningRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaJobOpeningRepository extends JpaAutoTxRepository<JobOpening, Long, JobReference>
        implements JobOpeningRepository {

    public JpaJobOpeningRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaJobOpeningRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public JobOpening findByReference(String reference) {
        System.out.println("Finding job opening by reference 2 " + reference);
        JobReference jobReference = new JobReference(reference);
        final Map<String, Object> params = new HashMap<>();
        params.put("ref", jobReference);
        return matchOne("e.reference=:ref", params).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<JobOpening> findByCustomer(Costumer c) {
        final TypedQuery<JobOpening> q = createQuery("SELECT jo FROM JobOpening jo WHERE jo.customer=:ct ", JobOpening.class);
        q.setParameter("ct", c);
        return q.getResultList();
    }

    @Override
    public List<JobOpening> findByPhaseAnalysis() {
        final TypedQuery<JobOpening> q = createQuery("SELECT jo FROM JobOpening jo WHERE jo.currentPhase=:cf", JobOpening.class);
        q.setParameter("cf", new Phase("Analysis"));
        return q.getResultList();
    }
}
