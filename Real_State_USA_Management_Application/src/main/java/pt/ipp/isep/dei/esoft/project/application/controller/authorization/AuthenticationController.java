package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class AuthenticationController {

    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    public static final String ROLE_AGENT = "AGENT";
    public static final String ROLE_OWNER = "OWNER";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_NETWORK_MANAGER = "NETMANAGER";
    public static final String ROLE_STORE_MANAGER = "STOREMANAGER";


    //private final ApplicationSession applicationSession;
    private final AuthenticationRepository authenticationRepository;


    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    public boolean doLogin(String email, String pwd) {
        if (authenticationRepository.doLogin(email, pwd)){
            CurrentSession.setCurrentSession(email);
        }
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    public void doLogout() {
        authenticationRepository.doLogout();
    }

    public void ensureAuthenticatedUserHasRoles(String ... roles){
        authenticationRepository.ensureAuthenticatedUserHasRoles(roles);
    }
}
