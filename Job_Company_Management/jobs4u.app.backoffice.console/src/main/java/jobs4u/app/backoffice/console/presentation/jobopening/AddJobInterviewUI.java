package jobs4u.app.backoffice.console.presentation.jobopening;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.jobopening.application.AddJobInterviewController;
import jobs4u.jobopening.domain.JobOpening;

public class AddJobInterviewUI extends AbstractUI {
    private final AddJobInterviewController controller = new AddJobInterviewController();
    @Override
    protected boolean doShow() {

        try {

            JobOpening jobOpening = selectJobOpening();
            System.out.println("\n-------------------------------\n");
            Integer jobInterview = selectJobInterviewModel();

            controller.addJobInterview(jobOpening, jobInterview);
            System.out.println("Job Interview added successfully!");
            System.out.println("\n-------------------------------\n");
            System.out.println("Generating the questions file...");
            String path = controller.generateInterviewTemplate(jobInterview);
            System.out.println("Question file generated successfully!");
            System.out.println("You can find the file at: "+ path);


        } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
            System.out.println("You are trying to add an interview model to a job opening that does not exist.");
        }
        return true;
    }

    public JobOpening selectJobOpening() {
        Iterable<JobOpening> jobOpenings = controller.listJobOpenings();
        SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Select a job opening\n", jobOpenings);
        jobOpeningSelector.show();
        return jobOpeningSelector.selectedElement();
    }


    public Integer selectJobInterviewModel() {
        Iterable<String> ji = controller.listJobInterviewModels();
        SelectWidget<String> jiSelector = new SelectWidget<>("Select an interview model\n", ji);
        jiSelector.show();
        return jiSelector.selectedOption();
    }

    @Override
    public String headline() {
        return "Add Job Interview Model to a Job Opening";
    }
}
