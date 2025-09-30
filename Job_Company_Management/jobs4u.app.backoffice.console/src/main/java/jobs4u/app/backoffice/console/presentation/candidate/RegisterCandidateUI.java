package jobs4u.app.backoffice.console.presentation.candidate;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.candidate.application.RegisterCandidateController;
import jobs4u.candidate.domain.PasswordGenerator;
import jobs4u.usermanagement.application.AddUserController;
import jobs4u.usermanagement.domain.BaseRoles;

import java.util.HashSet;
import java.util.Set;

public class RegisterCandidateUI extends AbstractUI {

    private final AddUserController theController = new AddUserController();
    RegisterCandidateController controller = new RegisterCandidateController();
    @Override
    protected boolean doShow() {

        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");
        final String email = Console.readLine("Email");
        final String phoneNumber = Console.readLine("Phone");
        final String address = Console.readLine("Address");

       final String password = PasswordGenerator.generatePassword();


        try {
            final Set<Role> roles = new HashSet<>();
            roles.add(BaseRoles.CANDIDATE);

            this.controller.registerCandidate(email,password,firstName,lastName,email,roles, address, phoneNumber);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That username is already in use.");
        }

        return false;
    }


    @Override
    public String headline() {
        return "Register Candidate";
    }

    }


