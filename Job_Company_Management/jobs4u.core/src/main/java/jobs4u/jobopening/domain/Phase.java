package jobs4u.jobopening.domain;

import eapli.framework.domain.model.ValueObject;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.ranking.repositories.RankingRepository;

public class Phase implements ValueObject {

    private String name;

    public Phase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void moveToNextPhase(JobOpening jobOpening, JobApplicationRepository jobApplicationRepository, RankingRepository rankingRepository) {
        switch (jobOpening.currentPhase().getName()) {
            case "Waiting":
                System.out.println("entrei");
                moveToApplication();
                break;
            case "Application":
                moveToScreening();
                break;
            case "Screening":
                if (jobOpening.interviewId() != 0) {
                    moveToInterview();
                } else {
                    moveToAnalysis();
                }
                break;
            case "Interview":
                moveToAnalysis();
                break;
            case "Analysis":
                moveToResult();
                break;
            case "Result":
                closePhase();
                break;
            case "Closed":
                System.out.println("This job opening is already closed.");
                break;
            default:
                break;
        }
    }

    public void moveBackToPreviousPhase(JobOpening jobOpening, JobApplicationRepository jobApplicationRepository, RankingRepository rankingRepository) {
        switch (jobOpening.currentPhase().getName()) {
            case "Screening":
                if (jobApplicationRepository.countByRequirementsMet(jobOpening) > 0) {
                    System.out.println("This phase has already started. You can't go back to Application phase.");
                } else {
                    moveToApplication();
                }
                break;
            case "Interview":
                if (jobApplicationRepository.countByInterviewGrade(jobOpening) > 0) {
                    System.out.println("This phase has already started. You can't go back to Screening phase.");
                } else {
                    moveToScreening();
                }
                break;
            case "Analysis":
                if (rankingRepository.countForExistingJobOpening(jobOpening) > 0) {
                    System.out.println("This phase has already started. You can't go back to Interview phase.");
                } else {
                    if (jobOpening.interviewId() != 0) {
                        moveToInterview();
                    } else {
                        moveToScreening();
                    }
                }
                break;
            case "Result":
                if (jobApplicationRepository.countByResult(jobOpening) > 0) {
                    System.out.println("This phase has already started. You can't go back to Analysis phase.");
                } else {
                    moveToAnalysis();
                }
                break;
            default:
                break;
        }
    }

    private void moveToApplication() {
        System.out.println("entrei");
        name = "Application";
    }

    private void moveToScreening() {
        name = "Screening";
    }

    private void moveToInterview() {
        name = "Interview";
    }

    private void moveToAnalysis() {
        name = "Analysis";
    }

    private void moveToResult() {
        name = "Result";
    }

    private void closePhase() {
        name = "Closed";
    }
}
