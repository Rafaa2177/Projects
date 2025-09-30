package jobs4u.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import plugin.Plugin;

@UseCaseController
public class AddJobInterviewController {

    private RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings() ;

    private final Plugin plugin = new Plugin();

    public Iterable<JobOpening> listJobOpenings() {
        return jobOpeningRepository.findAll();
    }

    public Iterable<String> listJobInterviewModels() {
        return plugin.getInterviewModels();
    }

    public void addJobInterview(JobOpening jobOpening, Integer jobInterviewId){
        jobOpening.setInterviewId(jobInterviewId);
        jobOpeningRepository.save(jobOpening);
    }

    public String generateInterviewTemplate(int jobInterviewId){
        return plugin.getInterviewModel(jobInterviewId).generateTemplateQuestions();
    }
}
