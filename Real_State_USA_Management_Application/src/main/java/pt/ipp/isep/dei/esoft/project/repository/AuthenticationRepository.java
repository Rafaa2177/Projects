package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Authentication repository.
 */
public class AuthenticationRepository implements Serializable {
    private final transient AuthFacade authenticationFacade = new AuthFacade();

    private SerializableUserRepository serializableUserRepository = new SerializableUserRepository();

    /**
     * Do login boolean.
     *
     * @param email the email
     * @param pwd   the pwd
     * @return the boolean
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Do logout.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Gets current user session.
     *
     * @return the current user session
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Add user role boolean.
     *
     * @param id          the id
     * @param description the description
     * @return the boolean
     */
    public boolean addUserRole(String id, String description) {
        serializableUserRepository.addUserRole(id,description);
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Add user with role boolean.
     *
     * @param name   the name
     * @param email  the email
     * @param pwd    the pwd
     * @param roleId the role id
     * @return the boolean
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        serializableUserRepository.addUserWithRole(name,email,pwd,roleId,email);
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }

    /**
     * Ensure authenticated user has roles.
     *
     * @param roles the roles
     */
    public void ensureAuthenticatedUserHasRoles(String ... roles){
        UserSession currentSession = authenticationFacade.getCurrentUserSession();

        for (int i=0; i< roles.length; i++){
            if (currentSession.isLoggedInWithRole(roles[i])){
                return;

            }

        }
        throw new IllegalArgumentException();
    }


    /**
     * Sync.
     */
    public void sync(){
        List<SerializableUserRepository.InternalUser> ius=serializableUserRepository.getAllUsers();
        List<SerializableUserRepository.InternalUserRole> iurs= serializableUserRepository.getAllRoles();


        for (SerializableUserRepository.InternalUserRole iur : iurs) {
            addUserRole(iur.getId(),iur.getDescription());
        }


        for (SerializableUserRepository.InternalUser internalUser : ius) {
            List<SerializableUserRepository.InternalUserRole> iur = internalUser.getRoles();
            String iurr="";
            if(!iur.isEmpty()){
                iurr=iur.get(0).getId();
            }
            addUserWithRole(internalUser.getName(),internalUser.getEmail(),internalUser.getPassword(),iurr );
        }
    }

}
