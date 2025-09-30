package jobs4u.ranking.application;

import eapli.framework.application.UseCaseController;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.ranking.domain.Ranking;
import jobs4u.ranking.repositories.RankingRepository;

@UseCaseController
public class RankCandidatesController {
    
    private final RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings();
    private final JobApplicationRepository jobApplicationRepository = repositoryFactory.jobapplications();
    private final RankingRepository rankingRepository = repositoryFactory.rankings();
    

    public Iterable<JobOpening> listJobOpenings() {
        return jobOpeningRepository.findByPhaseAnalysis();
    }

    public Iterable<JobApplication> listJobApplicationsByJO(JobOpening jobOpening) {
        return jobApplicationRepository.findByJobOpening(jobOpening);
    }

    public int getNumberOfJobApplications(JobOpening jobOpening) {
        return jobApplicationRepository.countByJobOpening(jobOpening);
    }

    public void rankApplication(JobApplication jobApplication, int ranking, JobOpening jobOpening, double magnitude){

        if(rankingRepository.findByJobOpeningAndRanking(jobOpening, ranking)){
           
            for (Ranking r : rankingRepository.findRankingByJobOpening(jobOpening))
            {
                // Update rankings that have a position lower than the new ranking
                if(r.ranking() >= ranking){
                    r.updateRanking(r.ranking() + 1);
                    rankingRepository.save(r);
                }

                // Delete rankings that have a position higher than the number of vacancies times the magnitude
                if(r.ranking() > (int)(jobOpening.numberOfVacancies().value() * magnitude)){
                    rankingRepository.delete(r);
                }
            }
        }

        Ranking r = new Ranking(jobApplication, ranking, jobOpening);

        rankingRepository.save(r);
        jobOpening.addRanking(r);
        jobApplicationRepository.save(jobApplication);
    }
}
