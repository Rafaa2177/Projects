package jobs4u.costumerManager.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobs4uUser.domain.Jobs4uUser;

import java.util.LinkedList;
import java.util.List;

@Entity
public class CostumerManager implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Jobs4uUser user;

    @OneToMany
    private List<JobOpening> jobOpenings;

    @OneToMany
    private List<Costumer> customers;


    public CostumerManager() {
        // for ORM only
    }

    public CostumerManager(Jobs4uUser user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        this.user = user;
        this.jobOpenings = new LinkedList<>();
        this.customers = new LinkedList<>();
    }

    public void addJobOpening(JobOpening jobOpening) {
        if (jobOpening == null) {
            throw new IllegalArgumentException();
        }
        this.jobOpenings.add(jobOpening);
    }

    public void addCustomer(Costumer customer) {
        if (customer == null) {
            throw new IllegalArgumentException();
        }
        this.customers.add(customer);
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof CostumerManager)) {
            return false;
        }
        final CostumerManager that = (CostumerManager) other;
        return that.jobOpenings==this.jobOpenings && that.customers==this.customers && that.user.sameAs(this.user);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public Long identity() {
        return id;
    }

    public Jobs4uUser user() {
        return user;
    }

    public List<JobOpening> jobopenings() {
        return jobOpenings;
    }

    public List<Costumer> costumers() {
        return customers;
    }


}
