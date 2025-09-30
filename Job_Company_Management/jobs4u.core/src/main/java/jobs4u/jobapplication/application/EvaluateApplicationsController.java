package jobs4u.jobapplication.application;

import eapli.framework.application.UseCaseController;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import plugin.Plugin;

@UseCaseController
public class EvaluateApplicationsController {

     private final RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings();
    private final JobApplicationRepository jobApplicationRepository = repositoryFactory.jobapplications();
    

    public Iterable<JobOpening> listJobOpenings() {
        return jobOpeningRepository.findAll();
    }

    public Iterable<JobApplication> listJobApplicationsByJO(JobOpening jobOpening) {
        return jobApplicationRepository.findByJobOpening(jobOpening);
    }

    public int gradeInterview(JobApplication ja) {
        int interviewId = ja.jobopening().interviewId();

        int grade = Plugin.getInterviewModel(interviewId).gradeInterview(ja.candidateFilesPath() + "/interviews.txt");

        ja.addInterviewGrade(grade);

        jobApplicationRepository.save(ja);

        return grade;
    }

    

    public boolean evaluateRequirements(JobApplication ja) {
        int requirementsId = ja.jobopening().requirementId();

        boolean result = Plugin.getRequirementsModel(requirementsId).resultRequirements(ja.candidateFilesPath() + "/requirements.txt");

        ja.addRequirementsResult(result);

        jobApplicationRepository.save(ja);

        return result;
    }
}
