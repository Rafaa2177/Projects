package jobs4u.app.backoffice.console.presentation.candidate;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.candidate.application.RegisterAtmCandidateController;

public class RegisterAtmCandidateUI extends AbstractUI {

    private RegisterAtmCandidateController controller = new RegisterAtmCandidateController();
    @Override
    protected boolean doShow() {
        System.out.println("The system will automatically register all candidates");
        controller.importAllCandidates();
        return false;
    }

    @Override
    public String headline() {
        return "Register Automatically all Candidates";
    }
}
