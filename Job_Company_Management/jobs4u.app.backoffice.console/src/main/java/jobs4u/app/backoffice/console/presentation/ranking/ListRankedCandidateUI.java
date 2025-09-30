package jobs4u.app.backoffice.console.presentation.ranking;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.candidate.domain.Candidate;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.ranking.application.ListRankedCandidateController;

import java.util.List;

public class ListRankedCandidateUI extends AbstractUI {

    private final ListRankedCandidateController controller = new ListRankedCandidateController();

    @Override
    protected boolean doShow() {
        System.out.println("\n-------------------------------\n");
        Costumer customer = selectCustomer();
        System.out.println("\n-------------------------------\n");
        JobOpening jobOpening = selectJobOpening(customer);
        System.out.println("\n-------------------------------\n");
        List<Candidate> rankedCandidatesList = controller.listRankedCandidates(jobOpening);
        System.out.println("Ranked Candidates:");
        rankedCandidatesList.forEach(System.out::println);

        return true;
    }

    private Costumer selectCustomer() {
        Iterable<Costumer> customersList = controller.listCustomersByManager();
        SelectWidget<Costumer> customerSelector = new SelectWidget<>("Customers - Select a customer\n", customersList);
        customerSelector.show();
        return customerSelector.selectedElement();
    }

    private JobOpening selectJobOpening(Costumer customer) {
        Iterable<JobOpening> jobOpeningsList = controller.listJobOpeningsByCustomer(customer);
        SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Job Openings - Select a job opening\n", jobOpeningsList);
        jobOpeningSelector.show();
        return jobOpeningSelector.selectedElement();
    }


    @Override
    public String headline() {
        return "List Ranked Candidate";
    }
}
