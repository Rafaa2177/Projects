package jobs4u.app.user.console.presentation.candidate;

import java.util.Optional;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.candidate.application.ListJobApplicationsController;
import jobs4u.jobapplication.domain.JobApplication;

public class ListJobApplicationsUI extends AbstractUI{

    private final ListJobApplicationsController controller = new ListJobApplicationsController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();


    @Override
    protected boolean doShow() {
        listMyJobApplications();
        return true;
    }


    private void listMyJobApplications() {
        Optional<Object> identity = authz.session().map(s -> s.authenticatedUser().identity());

        if (identity.isEmpty()) {
            System.out.println("No user logged in");
            return;
        }

        Iterable<JobApplication> jobApplications = controller.getMyJobApplications((Username)identity.get());

        if(jobApplications == null){
            System.out.println("You don't have any applications.\n Try to apply to some job offers.");
            return;
        }

        jobApplications.forEach(e -> {
            System.out.println(e.identity() + " - " + e.jobopening().identity() + " | Applicants : " + controller.getNumberOfApplicants(e.jobopening())+ " | State : " + e.state().toString() +   "\n");
        });
    }


    @Override
    public String headline() {
        return "List My Job Applications";
    }
    
    
}
