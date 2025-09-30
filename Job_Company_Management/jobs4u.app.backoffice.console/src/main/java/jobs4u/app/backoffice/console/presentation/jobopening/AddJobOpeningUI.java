package jobs4u.app.backoffice.console.presentation.jobopening;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.costumer.domain.Costumer;
import jobs4u.jobopening.application.AddJobOpeningController;
import jobs4u.jobopening.domain.*;

import java.util.LinkedList;
import java.util.List;

public class AddJobOpeningUI extends AbstractUI {
    private final AddJobOpeningController controller = new AddJobOpeningController();

    @Override
    protected boolean doShow() {
        JobReference jr = null;
        try {
            Designation title = Designation.valueOf(Console.readLine("Title:"));
            System.out.println("\n-------------------------------\n");
            Description description = Description.valueOf(Console.readLine("Description:"));
            System.out.println("\n-------------------------------\n");
            NumberOfVacancies numberOfVacancies = new NumberOfVacancies(Console.readInteger("Number of vacancies:"));
            System.out.println("\n-------------------------------\n");
            ContractType contractType = selectContractType();
            System.out.println("\n-------------------------------\n");
            Mode mode = selectMode();
            System.out.println("\n-------------------------------\n");
            ApplicationProcess process = selectApplicationProcess();
            System.out.println("\n-------------------------------\n");
            JobOpeningStatus status = selectJobOpeningStatus();
            System.out.println("\n-------------------------------\n");
            Costumer customer = selectCustomer();

            jr = controller.addJobOpening(title,description,customer,numberOfVacancies,contractType,mode,process,status);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
            System.out.println("You tried to add a job opening that already exists in the repository.");
        }
        return jr == null;
    }


    private Costumer selectCustomer() {
        Iterable<Costumer> customers = controller.listCustomers();
        SelectWidget<Costumer> customerSelector = new SelectWidget<>("Customers - Select a customer\n", customers);
        customerSelector.show();
        return customerSelector.selectedElement();
    }

    private JobOpeningStatus selectJobOpeningStatus() {
        List<JobOpeningStatus> statuses = List.of(JobOpeningStatus.values());
        SelectWidget<JobOpeningStatus> statusSelector = new SelectWidget<>("Job opening statuses - Select a status\n", statuses);
        statusSelector.show();
        return statusSelector.selectedElement();
    }

    private ApplicationProcess selectApplicationProcess() {
        List<ApplicationProcess> processes = List.of(ApplicationProcess.values());
        SelectWidget<ApplicationProcess> processSelector = new SelectWidget<>("Application processes - Select a process\n", processes);
        processSelector.show();
        return processSelector.selectedElement();
    }

    private Mode selectMode() {
        List<Mode> modes = List.of(Mode.values());
        SelectWidget<Mode> modeSelector = new SelectWidget<>("Modes - Select a mode\n", modes);
        modeSelector.show();
        return modeSelector.selectedElement();
    }

    private ContractType selectContractType(){
        List<ContractType> contractTypes = List.of(ContractType.values());
        SelectWidget<ContractType> ctSelector = new SelectWidget<>("Contract types - Select a type\n", contractTypes);
        ctSelector.show();
        return ctSelector.selectedElement();
    }

    @Override
    public String headline() {
        return "Add Job Opening";
    }
}
