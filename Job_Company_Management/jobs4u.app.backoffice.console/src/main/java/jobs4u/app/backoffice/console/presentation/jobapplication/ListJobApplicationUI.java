package jobs4u.app.backoffice.console.presentation.jobapplication;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.jobapplication.domain.WordInfo;
import jobs4u.jobapplication.application.ListJobApplicationController;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobopening.domain.*;

import java.util.List;


public class ListJobApplicationUI extends AbstractUI {
    private final ListJobApplicationController controller = new ListJobApplicationController();

    @Override
    protected boolean doShow() {

        try {
            JobOpening jo = selectJobOpening();
            if (jo == null) {
                return false;
            }
            System.out.println("\n-------------------------------\n");
            JobApplication ja = selectJobApplication(jo);
            if (ja == null) {
                return false;
            }
            System.out.println("\n-------------------------------\n");
            System.out.println("Top 20 words in the candidate files:");
            displayResults(ja);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
            System.out.println("Some error occurred. Please try again later.");
        }
        return false;
    }

    private JobOpening selectJobOpening() {
        Iterable<JobOpening> jobOpenings = controller.listJobOpenings();
        if (jobOpenings == null) {
            System.out.println("No job openings found.");
            return null;
        }
        SelectWidget<JobOpening> joSelector = new SelectWidget<>("Job Openings - Select one:\n", jobOpenings);
        joSelector.show();
        return joSelector.selectedElement();
    }

    private JobApplication selectJobApplication(JobOpening jo) {
        Iterable<JobApplication> jobApplications = controller.listJobApplicationsByJO(jo);
        if (jobApplications == null) {
            System.out.println("No job applications found.");
            return null;
        }
        SelectWidget<JobApplication> jaSelector = new SelectWidget<>("Job Applications - Select one:\n", jobApplications);
        jaSelector.show();
        return jaSelector.selectedElement();
    }

    private void displayResults(JobApplication ja){
        System.out.println("Candidate Files Path: " + ja.candidateFilesPath());
        List<WordInfo> wordInfos = controller.getTopWords(ja);

        for (int i = 0; i < Math.min(20, wordInfos.size()); i++) {
            WordInfo wordInfo = wordInfos.get(i);
            System.out.printf("%s (Count: %d) - Files: %s%n", wordInfo.getWord(), wordInfo.getCount(), wordInfo.getFiles());
        }
    }


    @Override
    public String headline() {
        return "List Job Applications For a Job Opening";
    }
}
