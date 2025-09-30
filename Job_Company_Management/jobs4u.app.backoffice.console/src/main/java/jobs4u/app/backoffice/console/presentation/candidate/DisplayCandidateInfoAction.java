package jobs4u.app.backoffice.console.presentation.candidate;

import eapli.framework.actions.Action;

public class DisplayCandidateInfoAction implements Action {
    @Override
    public boolean execute() {
        return new DisplayCandidateInfoUI().doShow();
    }


}
