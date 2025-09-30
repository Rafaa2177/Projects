package jobs4u.jobapplication.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.candidate.domain.Candidate;
import jobs4u.jobopening.domain.JobOpening;

@Entity
public class JobApplication implements AggregateRoot<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false)
    private ApplicationStatus status;

    @Column
    private String candidateFilesPath;

    @ManyToOne(optional = false)
    private JobOpening jobOpening;

    @OneToOne(optional = false)
    private Candidate candidate;

    @Column
    private int interviewGrade;

    @Column
    private Status requirementsMet; // Pending, Rejected , Accepted

    @Column
    private Status result;

    
    protected JobApplication(){

    }

    public  JobApplication(String cfp, Candidate c, JobOpening jo){
        Preconditions.noneNull(c,jo);

        this.status = ApplicationStatus.Actived;
        this.result = Status.Pending;
        this.requirementsMet = Status.Pending;
        this.candidateFilesPath = cfp;
        this.jobOpening = jo;
        this.candidate = c;
    }

    public Candidate candidate() {
        return candidate;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof JobApplication)) {
            return false;
        }
        final JobApplication that = (JobApplication) other;
        return that.status==this.status && that.candidateFilesPath==this.candidateFilesPath && that.jobOpening==this.jobOpening && that.interviewGrade==this.interviewGrade && that.requirementsMet==this.requirementsMet && that.result==this.result && that.id==this.id;
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
    public Long identity() {
        return id;
    }

    public JobOpening jobopening() {
        return jobOpening;
    }

    public Status state() {
        return this.result;
    }

    public String candidateFilesPath() {
        return this.candidateFilesPath;
    }

    @Override
    public String toString() {
        return  this.id + " - " + this.candidate.toString() ;
    }

    public void addInterviewGrade(int grade) {
        this.interviewGrade = grade;
    }

    public void addRequirementsResult(boolean result) {
        this.requirementsMet = result ? Status.Accepted : Status.Rejected;
    }
}
