package jobs4u.app.backoffice.console.presentation.candidate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import eapli.framework.actions.Action;

public class RegisterCandidateAction implements Action {



    @Override
    public boolean execute() {
        return new RegisterCandidateUI().doShow();
    }
}
