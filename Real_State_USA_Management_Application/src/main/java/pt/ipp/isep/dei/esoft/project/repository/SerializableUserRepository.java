package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.User;
import pt.isep.lei.esoft.auth.domain.model.UserRole;
import pt.isep.lei.esoft.auth.domain.store.UserRoleStore;
import pt.isep.lei.esoft.auth.domain.store.UserStore;
import pt.isep.lei.esoft.auth.mappers.UserMapper;
import pt.isep.lei.esoft.auth.mappers.UserRoleMapper;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.Serializable;
import java.util.*;

/**
 * The type Serializable user repository.
 */
public class SerializableUserRepository implements Serializable{
    /**
     * The type Internal user role.
     */
    public final class InternalUserRole implements Serializable{
    private final String id;
    private final String description;


        /**
         * Instantiates a new Internal user role.
         *
         * @param id          the id
         * @param description the description
         */
        public InternalUserRole(String id, String description) {
        this.id = id;
        this.description = description;
    }

        /**
         * Gets id.
         *
         * @return the id
         */
        public String getId() {
        return id;
    }

        /**
         * Gets description.
         *
         * @return the description
         */
        public String getDescription() {
        return description;
    }

        /**
         * To dto user role dto.
         *
         * @return the user role dto
         */
        public UserRoleDTO toDTO(){
        return new UserRoleDTO(id,description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternalUserRole)) return false;
        InternalUserRole that = (InternalUserRole) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

    /**
     * The type Internal user.
     */
    public final class InternalUser implements Serializable{
        private final String id;
        private final String name;
        private final String email;
        private final String password;
        private final List<InternalUserRole> roles;


        /**
         * Instantiates a new Internal user.
         *
         * @param id       the id
         * @param name     the name
         * @param email    the email
         * @param password the password
         * @param roles    the roles
         */
        public InternalUser(String id,String name, String email, String password, List<InternalUserRole> roles) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.roles = roles;
            this.id=id;
        }

        /**
         * Instantiates a new Internal user.
         *
         * @param dto      the dto
         * @param email    the email
         * @param password the password
         */
        public InternalUser(UserDTO dto, String email,String password) {
            this.name=dto.getName();
            this.password=password;
            this.id=dto.getId();
            this.email=email;
            List<InternalUserRole> str = new ArrayList<>();
            for (UserRoleDTO role : dto.getRoles()) {
                str.add(new InternalUserRole(role.getId(),role.getDescription()));
            }
            this.roles=new ArrayList<>(str);
        }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Gets email.
         *
         * @return the email
         */
        public String getEmail() {
            return email;
        }

        /**
         * Gets password.
         *
         * @return the password
         */
        public String getPassword() {
            return password;
        }

        /**
         * Gets roles.
         *
         * @return the roles
         */
        public List<InternalUserRole> getRoles() {
            return roles;
        }

        /**
         * To dto user dto.
         *
         * @return the user dto
         */
        public UserDTO toDTO(){
            List<UserRoleDTO> roles = new ArrayList<>();
            for (InternalUserRole role : this.roles) {
                roles.add(role.toDTO());
            }
            return new UserDTO(id,name,roles);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof InternalUser)) return false;
            InternalUser that = (InternalUser) o;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    private Set<InternalUserRole> roles=new HashSet<>();
    private Set<InternalUser> internalUsers=new HashSet<>();


    /**
     * Add user role boolean.
     *
     * @param id          the id
     * @param description the description
     * @return the boolean
     */
    public boolean addUserRole(String id, String description) {
        InternalUserRole role = new InternalUserRole(id,description);
        return this.roles.add(role);
    }

    /**
     * Exists role boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean existsRole(String id) {
        return this.roles.contains(id);
    }

    /**
     * Remove role boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean removeRole(String id) {
        return roles.removeIf(r -> r.getId().equals(id));
    }

    /**
     * Gets user roles.
     *
     * @return the user roles
     */
    public List<UserRoleDTO> getUserRoles() {
        List<UserRoleDTO> dtos = new ArrayList<>();
        for (InternalUserRole role : roles) {
            dtos.add(role.toDTO());
        }
        return dtos;
    }

    private InternalUserRole findById(String id){
        for (InternalUserRole role : roles) {
            if(role.getId().equals(id))
                return role;
        }
        return null;
    }

    /**
     * Gets role.
     *
     * @param id the id
     * @return the role
     */
    public Optional<UserRoleDTO> getRole(String id) {
        InternalUserRole role = findById(id);
        if(role==null){
            return Optional.empty();
        }
            return Optional.of(role.toDTO());

    }

    /**
     * Change user role description boolean.
     *
     * @param id          the id
     * @param description the description
     * @return the boolean
     */
    public boolean changeUserRoleDescription(String id, String description) {
        InternalUserRole found = findById(id);
        if(found==null){
            return false;
        }
            roles.remove(found);
        return roles.add(new InternalUserRole(id,description));

    }

    /**
     * Add user with role boolean.
     *
     * @param name   the name
     * @param email  the email
     * @param pwd    the pwd
     * @param roleId the role id
     * @param userId the user id
     * @return the boolean
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId, String userId) {

        InternalUserRole role = findById(roleId);
        if(role==null){
            return false;
        }

        InternalUser iu = new InternalUser(userId,name,email,pwd,List.of(role));
        return internalUsers.add(iu);
    }

    /**
     * Add user with roles boolean.
     *
     * @param name    the name
     * @param email   the email
     * @param pwd     the pwd
     * @param rolesId the roles id
     * @param userId  the user id
     * @return the boolean
     */
    public boolean addUserWithRoles(String name, String email, String pwd, String[] rolesId,String userId) {
        List<InternalUserRole> roles = new ArrayList<>();
        for (String id: rolesId){
            InternalUserRole iur = findById(id);
            if(iur==null){
                continue;
            }
            roles.add(iur);
        }
        InternalUser iu = new InternalUser(userId,name,email,pwd,roles);
        return internalUsers.add(iu);
    }

    /**
     * Get all users list.
     *
     * @return the list
     */
    public List<InternalUser> getAllUsers(){
        return new ArrayList<>(this.internalUsers);
    }

    /**
     * Get all roles list.
     *
     * @return the list
     */
    public List<InternalUserRole> getAllRoles(){
        return new ArrayList<>(this.roles);
    }
}
