package jobs4u.ranking.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobopening.domain.JobOpening;

@Entity
public class Ranking implements AggregateRoot<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private JobApplication jobApplication;

    @ManyToOne
    private JobOpening jobOpening;

    @Column
    private int ranking;

    protected Ranking() {
        // for ORM
    }

    public Ranking(JobApplication jobApplication, Integer ranking, JobOpening jobOpening) {
        Preconditions.noneNull(jobApplication,ranking,jobOpening);
        this.jobApplication = jobApplication;
        this.ranking = ranking;
        this.jobOpening = jobOpening;
    }
   
    public JobApplication getCandidateApplication() {
        return this.jobApplication;
    }


    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Ranking)) {
            return false;
        }
        final Ranking that = (Ranking) other;
        return that.jobApplication==this.jobApplication && that.jobOpening==this.jobOpening && that.id==this.id;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Application ID: " + this.jobApplication.identity() + " - Position: " + ranking;
    }


    public int ranking() {
        return ranking;
    }

    public void updateRanking(int i) {
        this.ranking = i;
    }

}
