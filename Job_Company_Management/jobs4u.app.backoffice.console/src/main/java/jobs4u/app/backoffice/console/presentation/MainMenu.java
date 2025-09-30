/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package jobs4u.app.backoffice.console.presentation;

import jobs4u.Application;
import jobs4u.app.backoffice.console.presentation.authz.*;
import jobs4u.app.backoffice.console.presentation.candidate.*;
import jobs4u.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import jobs4u.app.backoffice.console.presentation.jobapplication.EvaluateApplicationsUI;
import jobs4u.app.backoffice.console.presentation.jobapplication.ListJobApplicationUI;
import jobs4u.app.backoffice.console.presentation.jobapplication.RankCandidatesUI;
import jobs4u.app.backoffice.console.presentation.jobopening.AddJobInterviewUI;
import jobs4u.app.backoffice.console.presentation.jobopening.AddJobOpeningUI;
import jobs4u.app.backoffice.console.presentation.jobopening.AddJobRequirementsUI;
import jobs4u.app.backoffice.console.presentation.jobopening.ListJobOpeningsUI;
import jobs4u.app.backoffice.console.presentation.jobopening.ManageJobOpeningPhasesUI;
import jobs4u.app.backoffice.console.presentation.ranking.ListRankedCandidateUI;
import jobs4u.app.backoffice.console.presentation.ranking.NotifyResultsUI;
import jobs4u.app.common.console.presentation.authz.MyUserMenu;
import jobs4u.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import org.example.tcp.Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;


    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACTIVATE_USER_OPTION = 5;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // CANDIDATES
    private static final int OP_REGISTER_CANDIDATE_OPTION = 1;
    private static final int OP_REGISTER_ATM_CANDIDATE_OPTION = 2;
    private static final int OP_DISPLAY_CANDIDATE_INFO_OPTION = 3;
    private static final int OP_LIST_CANDIDATES = 4;
    private static final int OP_UPLOAD_REQUIREMENT_OPTION = 5;



    // JOB OPENING MANAGERS
    private static final int ADD_JOB_OPENING_OPTION = 1;
    private static final int LIST_JOB_OPENING_OPTION = 2;
    private static final int ADD_JOB_REQUIREMENTS_OPTION = 3;
    private static final int ADD_JOB_INTERVIEW_OPTION = 4;
    private static final int MANAGE_JOB_OPENING_PHASES_OPTION = 5;
    
    // JOB APPLICATION MANAGERS
    private static final int RANK_JOB_APPLICATIONS_JA_OPTION = 1;
    private static final int LIST_RANKED_CANDIDATES_JA_OPTION = 2;
    private static final int LIST_JOB_APPLICATIONS_JA_OPTION = 3;
    private static final int EVALUATE_APPLICATIONS_JA_OPTION = 4;
    private static final int NOTIFY_RESULTS_JA_OPTION = 5;
    private static final int UPLOAD_FILE_JA_OPTION = 6;


    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    // USERS
    private static final int USERS_OPTION = 2;

    // OPERATOR
    private static final int OPERATOR_CAND_OPTION = 2;

    // CUSTOMER MANAGERS
    private static final int CUSTOMER_MANAGERS_JO_OPTION = 2;
    private static final int CUSTOMER_MANAGERS_JA_OPTION = 3;


    
    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }




    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.OPERATOR)) {
            final Menu operatorsMenu = buildOperatorCandidateMenu();
            mainMenu.addSubMenu(OPERATOR_CAND_OPTION, operatorsMenu);
        }

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.CUSTOMER_MANAGER)){
            final Menu customerManagersJOMenu = buildCustomerManagersJOMenu();
            final Menu customerManagersJAMenu = buildCustomerManagersJAMenu();
            mainMenu.addSubMenu(CUSTOMER_MANAGERS_JO_OPTION, customerManagersJOMenu);
            mainMenu.addSubMenu(CUSTOMER_MANAGERS_JA_OPTION, customerManagersJAMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }



    private Menu buildCustomerManagersJOMenu(){
        final Menu menu = new Menu("Job Opening Management >");

        menu.addItem(ADD_JOB_OPENING_OPTION, "Add job opening", new AddJobOpeningUI()::show);
        menu.addItem(LIST_JOB_OPENING_OPTION, "List job opening", new ListJobOpeningsUI()::show);
        menu.addItem(ADD_JOB_REQUIREMENTS_OPTION, "Add requirements to a job opening", new AddJobRequirementsUI()::show);
        menu.addItem(ADD_JOB_INTERVIEW_OPTION, "Add interview to a job application", new AddJobInterviewUI()::show);
        menu.addItem(MANAGE_JOB_OPENING_PHASES_OPTION, "Manage Job Opening Phases", new ManageJobOpeningPhasesUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }
        
    private Menu buildCustomerManagersJAMenu(){
        final Menu menu = new Menu("Job Application Management >");

        menu.addItem(RANK_JOB_APPLICATIONS_JA_OPTION, "Rank candidates of a job opening by their application", new RankCandidatesUI()::show);
        menu.addItem(LIST_RANKED_CANDIDATES_JA_OPTION, "List ranked candidates of a job opening", new ListRankedCandidateUI()::show);
        menu.addItem(LIST_JOB_APPLICATIONS_JA_OPTION, "List job applications for a job opening", new ListJobApplicationUI()::show);
        menu.addItem(EVALUATE_APPLICATIONS_JA_OPTION, "Evaluate Applications", new EvaluateApplicationsUI()::show);
        menu.addItem(NOTIFY_RESULTS_JA_OPTION, "Notify Candidates/Customer about job opening results", new NotifyResultsUI()::show);
        menu.addItem(UPLOAD_FILE_JA_OPTION, "Upload candidate interview responses", new UploadCandidateDataUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Register User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACTIVATE_USER_OPTION, "Activate User", new ActivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildOperatorCandidateMenu(){
        final Menu menu = new Menu("Candidate >");
        menu.addItem(OP_REGISTER_CANDIDATE_OPTION, "Register Manually Candidate", new RegisterCandidateAction());
        menu.addItem(OP_REGISTER_ATM_CANDIDATE_OPTION, "Register Automatically Candidates", new RegisterAtmCandidateAction());
        menu.addItem(OP_DISPLAY_CANDIDATE_INFO_OPTION, "Display Candidate Info", new DisplayCandidateInfoAction());
        menu.addItem(OP_LIST_CANDIDATES, "List all Candidates", new ListCandidatesAction());
        menu.addItem(OP_UPLOAD_REQUIREMENT_OPTION, "Upload requirements", new UploadCandidateDataUI()::show);


        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }




}
