package jobs4u.app.backoffice.console.presentation.jobopening;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.jobopening.application.AddJobRequirementsController;
import jobs4u.jobopening.domain.*;

public class AddJobRequirementsUI extends AbstractUI {

    private final AddJobRequirementsController controller = new AddJobRequirementsController();
    @Override
    protected boolean doShow() {

        try {

            JobOpening jobOpening = selectJobOpening();
            System.out.println("\n-------------------------------\n");
            Integer jobRequirement = selectJobRequirements();

            controller.addJobRequirements(jobOpening, jobRequirement);
            System.out.println("Job Requirement added successfully!");
            System.out.println("\n-------------------------------\n");
            System.out.println("Generating requirement model file...");
            String path = controller.generateRequirementTemplate(jobRequirement);
            System.out.println("Requirement model file generated successfully!");
            System.out.println("You can find the file at: "+ path);


        } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
            System.out.println("You are trying to add a requirement to a job opening that does not exist.");
        }
        return true;
    }

    public JobOpening selectJobOpening() {
        Iterable<JobOpening> jobOpenings = controller.listJobOpenings();
        SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Select a job opening\n", jobOpenings);
        jobOpeningSelector.show();
        return jobOpeningSelector.selectedElement();
    }


    public Integer selectJobRequirements() {
        Iterable<String> jobRequirements = controller.listJobRequirements();
        SelectWidget<String> jobOpeningSelector = new SelectWidget<>("Select a requirement model\n", jobRequirements);
        jobOpeningSelector.show();
        return jobOpeningSelector.selectedOption();
    }

    @Override
    public String headline() {
        return "Add Job Requirements to a Job Opening";
    }
}
