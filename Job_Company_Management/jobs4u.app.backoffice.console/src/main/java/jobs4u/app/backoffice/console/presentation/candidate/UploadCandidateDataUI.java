package jobs4u.app.backoffice.console.presentation.candidate;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.candidate.application.UploadCandidateDataController;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobopening.domain.JobOpening;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class UploadCandidateDataUI extends AbstractUI {

    private final UploadCandidateDataController controller = new UploadCandidateDataController();

    @Override
    protected boolean doShow() {
        JobOpening jo = selectJobOpening();
        System.out.println("\n-------------------------------\n");
        JobApplication ja = selectCandidate(jo);
        System.out.println("\n-------------------------------\n");
        String path = readPath();
        System.out.println("UPLOADING....");
        if(controller.upload(path,ja.candidateFilesPath(),ja.jobopening().interviewId(),ja.jobopening().requirementId())) System.out.println("UPLOADED SUCCESSFULLY!");
        return true;
    }

    private String readPath() {
        Path userPath = null;
        boolean isValid = false;
        StringBuilder filePath = new StringBuilder();
        while(!isValid){
            try{
                filePath = new StringBuilder(Console.readLine("Please enter a filepath: "));
                userPath = Paths.get(filePath.toString());
                if (Files.exists(userPath)){
                    isValid = true;
                }
                else System.out.println("Sorry, that file doesn't exist, try again!");
            }catch (InvalidPathException e){
                System.out.println("Path was not valid, try again!");
            }
        }

        return filePath.toString();
    }

    private JobApplication selectCandidate(JobOpening jo) {
        Iterable<JobApplication> jobApplicationList = controller.listJobApplications(jo);
        List<JobApplication> formatedJobApplicationList = new LinkedList<>();
        System.out.println("Candidates - Select a candidate\n");
        jobApplicationList.forEach(jobApplication -> {
            formatedJobApplicationList.add(jobApplication);
            System.out.println(formatedJobApplicationList.size()+" - "+ jobApplication.candidate());
        });
        System.out.println("\n");
        return formatedJobApplicationList.get(Console.readOption(1,formatedJobApplicationList.size(),0)-1);
    }

    private JobOpening selectJobOpening() {
        Iterable<JobOpening> jobOpeningList = controller.listJobOpenings();
        SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Job Openings - Select a job opening\n", jobOpeningList);
        jobOpeningSelector.show();
        return jobOpeningSelector.selectedElement();
    }

    @Override
    public String headline() {
        return "Upload Candidate Files";
    }
}
