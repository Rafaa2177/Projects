package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateEmployeeUI  implements Runnable {
    Scanner input= new Scanner(System.in);
    private CreateEmployeeController controller = new CreateEmployeeController();
    private String askName, askEmail, password, askTaxNumber, askPassportCardNumber, askTelephoneNumber;
    private Roles askRole;
    private Person employee;
    List <Organization> orgs;

    public void run(){
        askData();
        submitData();
    }

    private void submitData(){
        employee = controller.createEmployee(new Email(askEmail), password, askName, askPassportCardNumber,
                askTaxNumber, askTelephoneNumber, askRole);
        for (Organization object : orgs) {
            controller.addToOrg(object,employee);
            displayOrganizations(controller.getOrganization());
        }
    }

    public void askData(){
        askName = requestString("Employee's Name: \n");
        askEmail = requestString("Employee's Email: \n");

        askTaxNumber = verifString("Employee's Tax Number: \n");

        if (Integer.parseInt(askTaxNumber)/100000000>1){
            askTaxNumber = verifString("Employee's Tax Number: \n");
        }

        askPassportCardNumber = verifString("Employee's Passport Card Number: \nC");

        if (Integer.parseInt(askPassportCardNumber)/100000000>1){
            askPassportCardNumber= verifString("Employee's Passport Card Number: \nC");
        }

        if (Integer.parseInt(askTelephoneNumber)/1000000000>1){
            askTelephoneNumber = verifString("Employee's Passport Phone: \n");
        }

        askRole = requestedRole("Select 1 role");
        orgs = selectAgencies(askRole);



    }
    private String verifString(String print) {
        String number;
        while (true) {
            System.out.print(print);
            try {
                number = input.next();
                break;

            } catch (NumberFormatException ignore) {
                System.out.println("Not valid.");
            }
        }
        return number;
    }

    private int verifInt(String print) {
        int number;
        while (true) {
            System.out.print(print);
            try {
                number =Integer.parseInt(input.next());
                break;

            } catch (NumberFormatException ignore) {
                System.out.println("Not valid.");
            }
        }
        return number;
    }

    private Roles requestedRole(String print) {

        System.out.println(print);
        int i = 1;
        for (Roles role : Roles.values()) {
            System.out.println(i + " - " + role);
            i++;
        }
        int opt = verifInt("");

        if (opt == 1) {
            return Roles.SYSTEM_ADMINISTRATOR;

        } else if (opt == 2) {
            return Roles.AGENT;

        } else if (opt == 3) {
            return Roles.STORE_MANAGER;

        } else if (opt == 4) {
            return Roles.STORE_NETWORK_MANAGER;

        } else
            return Roles.STORE_NETWORK_MANAGER;

    }
    private String requestString(String var){
        System.out.println(var);
        return input.nextLine();

    }

    private void displayOrganizations(List<Organization> orgs) {
        //display the task categories as a menu with number options to select
        int i = 1, j = 1;
        for (Organization object : orgs) {
            System.out.println(object.getID());
            displayEmployee(object.getEmployees());
            i++;
        }

    }
    private void displayEmployee(List<Person> employees) {
        //display the task categories as a menu with number options to select
        int i = 1, j = 1;
        for (Person object : employees) {
            System.out.println(object.toString());
        }

    }

    private List<Organization> selectAgencies (Roles role){

        List<Organization> organizationList = controller.getOrganization();
        List<Organization> organizationListSelected = new ArrayList<>();

        if(role.equals(Roles.STORE_NETWORK_MANAGER)){

            int exit = 1;
            while (exit == 1){

                System.out.println("Select the desired agencies");
                int op = 0;

                System.out.println(op + " - Finishing task");

                for (Organization organization : organizationList) {
                    op++;
                    System.out.println(op +" - " + organization.getID());
                }

                int select = input.nextInt();
                organizationListSelected.add(organizationList.get(select-1));

                System.out.println("Do you intend to select more agencies ?");
                System.out.println("1-Yes");
                System.out.println("2-No");
                exit = input.nextInt();
            }
        }
        else {
            System.out.println("Select the desired agency");
            int op = 0;

            for (Organization organization : organizationList){
                op++;
                System.out.println(op + " - " + organization.getID());
            }

            int select = input.nextInt();
            organizationListSelected.add(organizationList.get(select-1));
        }
        return organizationListSelected;
    }

}
