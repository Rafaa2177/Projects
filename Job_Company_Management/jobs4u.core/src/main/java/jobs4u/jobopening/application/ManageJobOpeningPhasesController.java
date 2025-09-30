package jobs4u.jobopening.application;

import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.domain.Phase;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.ranking.repositories.RankingRepository;

public class ManageJobOpeningPhasesController {

    private RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings();
    private final JobApplicationRepository jobApplicationRepository = repositoryFactory.jobapplications();
    private final RankingRepository rankingRepository = repositoryFactory.rankings();

    public Iterable<JobOpening> listJobOpenings() {
        return jobOpeningRepository.findAll();
    }

    public void nextPhase(JobOpening jobOpening) {
        Phase phase = jobOpening.currentPhase();
        phase.moveToNextPhase(jobOpening, jobApplicationRepository, rankingRepository);
        jobOpeningRepository.save(jobOpening);
    }

    public void previousPhase(JobOpening jobOpening) {
        Phase phase = jobOpening.currentPhase();
        phase.moveBackToPreviousPhase(jobOpening, jobApplicationRepository, rankingRepository);
        System.out.println(jobOpening.currentPhase().getName());
        jobOpeningRepository.save(jobOpening);
        
    }
}