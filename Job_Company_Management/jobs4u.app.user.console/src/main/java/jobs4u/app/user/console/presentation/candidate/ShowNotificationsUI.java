package jobs4u.app.user.console.presentation.candidate;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import org.example.tcp.Client;
import org.example.tcp.Server;

import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ShowNotificationsUI extends AbstractUI{

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        showNotifications();
        return true;
    }

    public void showNotifications(){
        System.out.println("You are listening to notifications from the server...\n\n");
        Optional<SystemUser> user = authz.session().map(UserSession::authenticatedUser);
        if (user.isEmpty()) {
            System.out.println("No user logged in");
            return;
        }
        try {
            Socket socket = new Socket("localhost", 1234);
            Client c = new Client(socket, user.get().username().toString());


            c.getNotifications();
            while(!(Console.readLine("click [ENTER] to exit...")).equals("")){

            };

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String headline() {
       return "Show Notifications";
    }
    
}
