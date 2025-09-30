package jobs4u.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import plugin.Plugin;


@UseCaseController
public class AddJobRequirementsController {

    private RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings() ;

    private final Plugin plugin = new Plugin();

    public Iterable<JobOpening> listJobOpenings() {
        return jobOpeningRepository.findAll();
    }

    public Iterable<String> listJobRequirements() {
        return plugin.getRequirementsModels();
    }

    public void addJobRequirements(JobOpening jobOpening, Integer jobRequirement){
        jobOpening.setRequirementId(jobRequirement);
        jobOpeningRepository.save(jobOpening);
    }

    public String generateRequirementTemplate(int jobRequirementId){
        return plugin.getInterviewModel(jobRequirementId).generateTemplateQuestions();
    }
}
