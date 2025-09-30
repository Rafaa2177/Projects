package jobs4u.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;
import jobs4u.Application;
import jobs4u.candidate.domain.Candidate;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.ranking.domain.Ranking;
import jobs4u.ranking.repositories.RankingRepository;

import java.util.List;

public class JpaRankingRepository 
        extends JpaAutoTxRepository<Ranking, Long, Long>
        implements RankingRepository{

    public JpaRankingRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaRankingRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }


    @Override
    public List<Candidate> findByJobOpening(JobOpening jobOpening) {
        final TypedQuery<Candidate> q = createQuery("SELECT ja.candidate FROM Ranking r INNER JOIN JobApplication ja ON r.jobApplication=ja WHERE r.jobOpening=:jo ORDER BY r.ranking ASC", Candidate.class);
        q.setParameter("jo", jobOpening);
        return q.getResultList();
    }

    

    @Override
    public int countForExistingJobOpening(JobOpening jobOpening) {
        final TypedQuery<Long> q = createQuery("SELECT COUNT(ja) FROM Ranking ja WHERE ja.jobOpening = :jobOpening", Long.class);
        q.setParameter("jobOpening", jobOpening);
        return q.getSingleResult().intValue();
    }

    @Override
    public boolean findByJobOpeningAndRanking(JobOpening jobOpening, int ranking) {
        final TypedQuery<Ranking> q = createQuery("SELECT r FROM Ranking r WHERE r.jobOpening = :jobOpening AND r.ranking = :ranking", Ranking.class);
        q.setParameter("jobOpening", jobOpening);
        q.setParameter("ranking", ranking);
        return q.getResultList().size() > 0;
    }

    @Override
    public List<Ranking> findRankingByJobOpening(JobOpening jobOpening) {
        final TypedQuery<Ranking> q = createQuery("SELECT r FROM Ranking r WHERE r.jobOpening = :jobOpening ORDER BY r.ranking DESC", Ranking.class);
        q.setParameter("jobOpening", jobOpening);
        return q.getResultList();
    }
}
