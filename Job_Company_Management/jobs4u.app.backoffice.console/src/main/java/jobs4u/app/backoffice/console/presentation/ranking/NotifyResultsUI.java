package jobs4u.app.backoffice.console.presentation.ranking;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.ranking.application.NotifyResultsController;

public class NotifyResultsUI extends AbstractUI {

    private final NotifyResultsController controller = new NotifyResultsController();

    @Override
    protected boolean doShow() {
        JobOpening jo = selectJobOpening();
        System.out.println("NOTIFYING RESULTS....");
        controller.notifyResults(jo);
        System.out.println("RESULTS NOTIFIED SUCCESSFULLY!");
        return true;
    }

    private JobOpening selectJobOpening() {
        Iterable<JobOpening> customersList = controller.listJobOpeningsByManager();
        SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Job Openings - Select a job opening\n", customersList);
        jobOpeningSelector.show();
        return jobOpeningSelector.selectedElement();
    }

    @Override
    public String headline() {
        return "Notify Results";
    }
}
