package jobs4u.app.backoffice.console.presentation.jobapplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.example.tcp.Client;
import org.example.tcp.Server;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.jobapplication.application.EvaluateApplicationsController;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobopening.domain.JobOpening;

public class EvaluateApplicationsUI extends AbstractUI {

    private final EvaluateApplicationsController controller = new EvaluateApplicationsController();

    @Override
    protected boolean doShow() {

        JobOpening jo = selectJobOpening();
        System.out.println("\n-------------------------------\n");
        JobApplication ja = selectJobApplication(jo);
        System.out.println("\n-------------------------------\n");
        selectEvaluationProcess(ja);
        System.out.println("\n-------------------------------\n");

        return true;
    }


    private void selectEvaluationProcess(JobApplication ja){
        List<String> options = List.of("Grade Interview", "Evaluate Requirements");
        SelectWidget<String> selector = new SelectWidget<>("Select the evaluation process:\n", options);
        selector.show();

        switch(selector.selectedOption()){
            case 1:
                gradeInterview(ja);
                break;
            case 2:
                evaluateRequirements(ja);
                break;
        }
    }

    private JobOpening selectJobOpening() {
        Iterable<JobOpening> jobOpenings = controller.listJobOpenings();
        SelectWidget<JobOpening> joSelector = new SelectWidget<>("Job Openings - Select one:\n", jobOpenings);
        joSelector.show();

        System.out.println("Your selection: \n");
        System.out.println(joSelector.selectedElement().getDetails());


        return joSelector.selectedElement();
    }

    private JobApplication selectJobApplication(JobOpening jo) {
        Iterable<JobApplication> jobApplications = controller.listJobApplicationsByJO(jo);
        SelectWidget<JobApplication> jaSelector = new SelectWidget<>("Job Applications - Select one:\n", jobApplications);
        jaSelector.show();
        return jaSelector.selectedElement();
    }


    private void gradeInterview(JobApplication ja){
        int num = controller.gradeInterview(ja);
        System.out.println("Interview graded. Overall Grade: " + num + "\n");

        String username = ja.candidate().getUser().getSystemUser().username().toString();
        sendNotification("Your interview for the application with id " + ja.identity() + " was graded with a score of " + num, username);
    }

    private void evaluateRequirements(JobApplication ja){
        boolean result = controller.evaluateRequirements(ja);
        System.out.println("Requirements evaluated. Result: " + (result ? "Approved" : "Not Approved") + "\n");

        String username = ja.candidate().getUser().getSystemUser().username().toString();
        sendNotification("Your application with the id "+ ja.identity() +" was evaluated and it was " + (result ? "Approved" : "Not Approved"), username);
    }

    private void sendNotification(String message, String username){
        System.out.println("Notification is being send to the candidate\n");
        System.out.println("Candidate: " + username + "\n");

        try {
            new Client(new Socket("localhost", 1234), "server").sendNotification(message, username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String headline() {
        return "Evaluate Applications";
    }

}
