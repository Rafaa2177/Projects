package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

public class CreateEmployeeController {
    private AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    private OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();

    /*
    public CreateEmployeeController(){
        getOrganizationRepository();
        getauthenticationRepository();
    }

    public CreateEmployeeController(AuthenticationRepository authenticationRepository,OrganizationRepository organizationRepository){
         this.authenticationRepository=authenticationRepository;
         this.organizationRepository= organizationRepository;
    }

     */

    public Person createEmployee(Email email, String pwd, String name, String passPort,
                                 String taxNumber, String phone, Roles role)  {
        return new Person(email, pwd, name, passPort, taxNumber, phone, role );

    }
    public void addToOrg(Organization Assign,Person employee){
        Assign.addEmployee(employee);
    }
    public List<Organization> getOrganization() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        return OrganizationRepository.getOrganizationList();
    }


    public void CreateEmployee(String askEmail, String askName, int askTelephoneNumber, int askTaxNumber, Roles askRole, int askPassportCardNumber) {

    }
}


