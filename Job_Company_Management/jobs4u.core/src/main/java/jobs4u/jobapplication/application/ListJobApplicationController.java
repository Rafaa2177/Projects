package jobs4u.jobapplication.application;

import jobs4u.jobapplication.domain.FileProcessor;
import jobs4u.jobapplication.domain.WordInfo;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.repositories.JobOpeningRepository;

import java.io.File;
import java.util.*;

public class ListJobApplicationController {
    private final RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private final JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings();
    private final JobApplicationRepository jobApplicationRepository = repositoryFactory.jobapplications();

    public Iterable<JobOpening> listJobOpenings() {
        return jobOpeningRepository.findAll();
    }

    public Iterable<JobApplication> listJobApplicationsByJO(JobOpening jobOpening) {
        return jobApplicationRepository.findByJobOpening(jobOpening);
    }

    public List<WordInfo> getTopWords(JobApplication ja){
        return new FileProcessor().processFiles(ja);
    }
}
