package jobs4u.jobopening.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import jobs4u.costumer.domain.Costumer;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class JobReference implements ValueObject, Comparable<JobReference> {



    private static final long serialVersionUID = -1820803667379631580L;

    private final String reference;

    protected JobReference() {
        // for ORM
        reference = null;
    }

    public JobReference(final String value) {
        Preconditions.nonEmpty(value);
        reference = value;
    }

    /**
     * Factory method.
     *
     * @param value
     *
     * @return
     */
    public static JobReference valueOf(final String value) {
        return new JobReference(value);
    }

    /**
     * Factory method.
     *
     * @return
     */
    public static JobReference newReference(Costumer c) {
        Preconditions.nonNull(c);
        JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobopenings();
        return valueOf(c.identity().toString() + "-" + jobOpeningRepository.findByCustomer(c).size());
    }

    @Override
    public int compareTo(final JobReference o) {
        return reference.compareTo(o.reference);
    }

    @Override
    public String toString() {
        return reference;
    }
}