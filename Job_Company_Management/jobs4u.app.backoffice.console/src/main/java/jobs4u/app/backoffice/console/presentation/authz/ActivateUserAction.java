package jobs4u.app.backoffice.console.presentation.authz;
import eapli.framework.actions.Action;

/**
 *
 * @author Fernando
 */
public class ActivateUserAction implements Action {

    @Override
    public boolean execute() {
        return new ActivateUserUI().show();
    }
}
