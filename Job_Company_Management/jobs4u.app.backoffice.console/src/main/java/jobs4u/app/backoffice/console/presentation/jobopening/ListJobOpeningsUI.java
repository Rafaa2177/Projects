package jobs4u.app.backoffice.console.presentation.jobopening;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.jobopening.application.ListJobOpeningsController;

public class ListJobOpeningsUI extends AbstractUI {

    private final ListJobOpeningsController controller = new ListJobOpeningsController();
    @Override
    protected boolean doShow() {
        System.out.println("Retrieving Job Openings...");
        System.out.println("------------------\n");
        listJobOpenings();

        return true;
    }


    public void listJobOpenings() {
        controller.listJobOpenings().forEach(System.out::println);
    }

    @Override
    public String headline() {
        return "List Job Openings";
    }
}
