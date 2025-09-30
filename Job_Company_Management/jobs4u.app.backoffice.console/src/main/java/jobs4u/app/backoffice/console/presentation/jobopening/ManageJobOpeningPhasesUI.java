package jobs4u.app.backoffice.console.presentation.jobopening;

import java.util.List;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.jobopening.application.ManageJobOpeningPhasesController;
import jobs4u.jobopening.domain.JobOpening;

public class ManageJobOpeningPhasesUI extends AbstractUI{

    private final ManageJobOpeningPhasesController controller = new ManageJobOpeningPhasesController();

    @Override
    protected boolean doShow() {
        try {

            JobOpening jobOpening = selectJobOpening();
            System.out.println("\n-------------------------------\n");

            do{
                managePhase(jobOpening);
                System.out.println("\n-------------------------------\n");
            } while(doAgain());
            

        } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
            System.out.println("This Job Opening does not exist.");
        }
        return true;
    }

    public JobOpening selectJobOpening() {
        Iterable<JobOpening> jobOpenings = controller.listJobOpenings();
        SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Select a job opening\n", jobOpenings);
        jobOpeningSelector.show();
        return jobOpeningSelector.selectedElement();
    }

    public void managePhase(JobOpening jobOpening) {
        if(jobOpening.currentPhase().getName().equals("Closed")) {
            System.out.println("This job opening is already closed.");
            return;
        }
        Iterable<String> options =  List.of("Next Phase", "Previous Phase");
        if(jobOpening.currentPhase().getName().equals("Application") || jobOpening.currentPhase().getName().equals("Waiting")) options = List.of("Next Phase");


        System.out.println("Your current phase is: " + jobOpening.currentPhase().getName());
        SelectWidget<String> selector = new SelectWidget<>("Select an option", options);
        selector.show();

        int option = selector.selectedOption();
        switch (option) {
            case 1:
                controller.nextPhase(jobOpening);
                break;
            case 2:
                controller.previousPhase(jobOpening);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    } 

    public boolean doAgain() {
        System.out.println("Do you want to manage another phase? (Y/N)");
        SelectWidget<String> selector = new SelectWidget<>("Select an option", List.of("Yes", "No")); 
        selector.show();
        System.out.println("\n-------------------------------\n");
        return selector.selectedOption() == 1;
    }

    @Override
    public String headline() {
        return "Manage Job Opening Phases";    
    }
    
}
