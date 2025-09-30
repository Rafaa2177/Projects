package jobs4u.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import jobs4u.candidate.domain.Candidate;
import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.costumer.domain.Costumer;
import jobs4u.costumer.repositories.CostumerRepository;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.*;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.ranking.domain.Ranking;
import jobs4u.ranking.repositories.RankingRepository;

import java.util.Iterator;


public class JobsBootstrapper implements Action {
   private final CostumerRepository costumerRepository = PersistenceContext.repositories().costumers();
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobopenings();
    private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobapplications();
    private final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();
    private final CostumerManagerRepository costumerManagerRepository = PersistenceContext.repositories().costumerManagers();
    private final RankingRepository rankingRepository = PersistenceContext.repositories().rankings();

    @Override
    public boolean execute() {
        addJobOpenings();
        addJOtoCM();
        addJobApplications();
        addRankedCandidate();
        updateJobOpening();
        return true;
    }

    private void updateJobOpening() {
        Iterator<Ranking> rankings = rankingRepository.findAll().iterator();
        JobOpening jo = jobOpeningRepository.findAll().iterator().next();
        Ranking r = rankings.next();
        Ranking r1 = rankings.next();
        jo.addRanking(r);
        jo.addRanking(r1);
        jobOpeningRepository.save(jo);
    }

    private void addRankedCandidate() {
        Iterator<JobApplication> jobapplications = jobApplicationRepository.findAll().iterator();
        JobApplication ja = jobapplications.next();
        Ranking r = new Ranking(ja, 1, ja.jobopening());

        JobApplication ja1= jobapplications.next();
        Ranking r1 = new Ranking(ja1,2,ja1.jobopening());

        rankingRepository.save(r);
        rankingRepository.save(r1);
    }

    private void addJOtoCM() {
        JobOpening jo = jobOpeningRepository.findAll().iterator().next();
        CostumerManager cm = costumerManagerRepository.findAll().iterator().next();

        cm.addJobOpening(jo);
        costumerManagerRepository.save(cm);
    }

    private void addJobApplications() {
        JobOpening jo = jobOpeningRepository.findAll().iterator().next();
        Iterator<Candidate> candidates = candidateRepository.findAll().iterator();
        Candidate c = candidates.next();
        String cfp = "jobs4u.applicationUtilities/ApplicationFileBot/candidates/2-janedoe@email.com";

        JobApplication ja = new JobApplication(cfp, c, jo);

        Candidate c1 = candidates.next();
        String cfp1 = "jobs4u.applicationUtilities/ApplicationFileBot/candidates/1-johndoe@email.com";

        JobApplication ja1 = new JobApplication(cfp1,c1,jo);

        jobApplicationRepository.save(ja);
        jobApplicationRepository.save(ja1);
    }


    private void addJobOpenings() {
        Designation t = Designation.valueOf("title");
        Description d = Description.valueOf("description");
        NumberOfVacancies nv = new NumberOfVacancies(2);

        Mode m = Mode.REMOTE;
        ContractType ct = ContractType.FULLTIME;
        ApplicationProcess ap = ApplicationProcess.SCREENING;
        JobOpeningStatus jos = JobOpeningStatus.OPEN;

        Costumer c = costumerRepository.findAll().iterator().next();

        JobReference jr = new JobReference("IBM-000123");

        JobOpening jo1 = new JobOpening(t, d, c, nv, ct, m, ap, jos,jr);

        jo1.setInterviewId(1);
        jo1.setRequirementId(1);

        jobOpeningRepository.save(jo1);
    }

}
