package jobs4u.jobopening.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.costumer.domain.Costumer;
import jobs4u.ranking.domain.Ranking;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
public class JobOpening implements AggregateRoot<JobReference> {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, nullable = false)
    private JobReference reference;

    @Column(nullable = false)
    private ApplicationProcess applicationProcess;

    @Column(nullable = false)
    private ContractType contractType;

    @Column(nullable = false)
    private JobOpeningStatus status;

    @Column(nullable = false)
    private Mode mode;

    @Column(nullable = false)
    private Designation title;

    @Column(nullable = false)
    private Description description;

    @Column(nullable = false)
    private NumberOfVacancies numberOfVacancies;

    @ManyToOne(optional = false)
    private Costumer customer;

    @Column
    private int requirementId;

    @Column
    private int interviewId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ranking> rankings;

    @Column
    private Phase currentPhase;

    @Setter
    @Getter
    private LocalDateTime activeSince;

    protected JobOpening() {
        // for ORM
    } 

    public JobOpening(Designation t, Description d, Costumer c, NumberOfVacancies nv, ContractType ct, Mode m, ApplicationProcess ap, JobOpeningStatus jos) {
        Preconditions.noneNull(t, d, c, nv, ct, m, ap, jos);
        this.reference = JobReference.newReference(c);
        this.customer = c;
        this.numberOfVacancies = nv;
        this.title = t;
        this.description = d;
        this.mode = m;
        this.applicationProcess = ap;
        this.contractType = ct;
        this.status = jos;
        this.rankings = new LinkedList<>();
        this.currentPhase = new Phase("Waiting");
        this.activeSince  = LocalDateTime.now();
    }

    public JobOpening(Designation t, Description d, Costumer c, NumberOfVacancies nv, ContractType ct, Mode m, ApplicationProcess ap, JobOpeningStatus jos, JobReference ref) {
        Preconditions.noneNull(t, d, c, nv, ct, m, ap, jos, ref);
        reference = ref;
        this.customer = c;
        this.numberOfVacancies = nv;
        this.title = t;
        this.description = d;
        this.mode = m;
        this.applicationProcess = ap;
        this.contractType = ct;
        this.status = jos;      
        this.rankings = new LinkedList<>();
        this.currentPhase = new Phase("Waiting");
        this.activeSince  = LocalDateTime.now();
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof JobOpening)) {
            return false;
        }
        final JobOpening that = (JobOpening) other;
        return that.customer==this.customer && that.numberOfVacancies==this.numberOfVacancies && that.title==this.title && that.description==this.description && that.mode==this.mode && that.applicationProcess==this.applicationProcess && that.contractType==this.contractType && that.status==this.status && that.rankings==this.rankings && that.currentPhase==this.currentPhase && that.activeSince==this.activeSince && that.reference==this.reference;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public JobReference identity() {
        return reference;
    }

    @Override
    public String toString() {
        return reference.toString()+ " - " +title.toString();
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public void addRanking(Ranking ranking) {
        rankings.add(ranking);
    }

    public String getDetails(){
        return  "\nReference: " + reference.toString() +
                "Title: " + title + 
                "\nDescription: " + description +
                "\nNumber of vacancies: " + numberOfVacancies + 
                "\nContract Type: " + contractType + 
                "\nMode: " + mode + 
                "\nApplication Process: " + applicationProcess + 
                "\nStatus: " + status
                + "\nResponsible CM: " + customer.toString()
                + "\nRankings: " + this.getRankings();
    }

    public  String getInfor(){
        return  "\nReference: " + reference.toString() +
                "\nTitle: " + title.toString() +
                "\nActive Since: " + activeSince;

    }

    public String getRankings(){
        if(this.rankings.isEmpty()){
            return "No rankings available";
        }
        return this.rankings.toString();
    }


    public Costumer customer() {
        return this.customer;
    }

    public List<Ranking> rankings() {
        return this.rankings;
    }

    public Phase currentPhase() {
        return this.currentPhase;
    }

    public int interviewId() {
        return this.interviewId;
    }

    public int requirementId(){
        return this.requirementId;
    }

    public NumberOfVacancies numberOfVacancies() {
        return numberOfVacancies;
    }

  

}
