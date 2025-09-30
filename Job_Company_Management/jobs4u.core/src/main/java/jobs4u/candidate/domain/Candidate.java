package jobs4u.candidate.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.jobs4uUser.domain.Jobs4uUser;

@Entity

public class Candidate implements AggregateRoot<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Jobs4uUser user;


    public Candidate() {
        // for ORM only (Object Relational Mapping)
    }

    public Long getId() {
        return id;
    }

    public Jobs4uUser getUser() {
        return user;
    }

    public Candidate(Jobs4uUser user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        this.user = user;
    }

    public Jobs4uUser user() {
        return this.user;
    }

    public Long id() {
        return identity();
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Candidate)) {
            return false;
        }
        final Candidate that = (Candidate) other;
        return that.user.sameAs(this.user);
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
        return "Candidate: " + user.user().email() + " - : " + user.user().name().toString();
    }


}