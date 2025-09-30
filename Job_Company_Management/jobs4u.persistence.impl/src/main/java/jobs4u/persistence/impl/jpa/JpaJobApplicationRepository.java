package jobs4u.persistence.impl.jpa;

import javax.naming.spi.DirStateFactory.Result;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;
import jobs4u.Application;
import jobs4u.jobapplication.domain.*;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;

public class JpaJobApplicationRepository extends JpaAutoTxRepository<JobApplication, Long, Long>
        implements JobApplicationRepository {

    public JpaJobApplicationRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaJobApplicationRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<JobApplication> findByJobOpening(JobOpening jobOpening) {
        final TypedQuery<JobApplication> q = createQuery("SELECT ja FROM JobApplication ja WHERE ja.jobOpening=:jr ", JobApplication.class);
        q.setParameter("jr", jobOpening);
        return q.getResultList();
    }

    @Override
    public int countByJobOpening(JobOpening jobOpening) {
        final TypedQuery<Long> q = createQuery("SELECT COUNT(ja) FROM JobApplication ja WHERE ja.jobOpening=:jr ", Long.class);
        q.setParameter("jr", jobOpening);
        return q.getResultList().size();
    }

    @Override
    public Iterable<JobApplication> findByCandidateEmail(Username email) {
        final TypedQuery<JobApplication> q = createQuery("SELECT ja FROM JobApplication ja WHERE ja.candidate.user.systemUser.username=:email ", JobApplication.class);
        q.setParameter("email", email);
        return q.getResultList();
    }

    @Override
    public int countByRequirementsMet(JobOpening jobOpening) {
        final TypedQuery<Long> q = createQuery("SELECT COUNT(ja) FROM JobApplication ja WHERE ja.jobOpening=:jr AND ja.requirementsMet != 0", Long.class);
        q.setParameter("jr", jobOpening);
        return q.getSingleResult().intValue();
    }

    @Override
    public int countByInterviewGrade(JobOpening jobOpening) {
        final TypedQuery<Long> q = createQuery("SELECT COUNT(ja) FROM JobApplication ja WHERE ja.jobOpening=:jr AND ja.interviewGrade != -1", Long.class);
        q.setParameter("jr", jobOpening);
        return q.getSingleResult().intValue();
    }

    @Override
    public int countByResult(JobOpening jobOpening) {
        final TypedQuery<Long> q = createQuery("SELECT COUNT(ja) FROM JobApplication ja WHERE ja.jobOpening = :jr AND ja.result != 0", Long.class);
        q.setParameter("jr", jobOpening);
        return q.getSingleResult().intValue();
    }
}
