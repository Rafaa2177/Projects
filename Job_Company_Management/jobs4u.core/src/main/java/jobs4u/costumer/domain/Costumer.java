package jobs4u.costumer.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.jobs4uUser.domain.Jobs4uUser;

@Entity
public class Costumer implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Jobs4uUser user;

    @Column(nullable = false)
    private String company;


    public Costumer() {
        // for ORM only
    }

    public Costumer(Jobs4uUser user, String company) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        this.user = user;
        this.company = company;
    }

    public Jobs4uUser user() {
        return user;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Costumer)) {
            return false;
        }
        final Costumer that = (Costumer) other;
        return that.company == this.company && that.user.sameAs(this.user);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public Long identity() {
        return user.identity();
    }

    @Override
    public String toString() {
        return "Customer:" + user.user().name().toString() + " - " + company;
    }
}
