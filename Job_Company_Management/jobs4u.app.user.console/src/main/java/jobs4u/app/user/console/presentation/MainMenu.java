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
package jobs4u.app.user.console.presentation;

import jobs4u.Application;

import jobs4u.app.user.console.presentation.myuser.ListOpeningUI;

import jobs4u.app.user.console.presentation.candidate.ListJobApplicationsUI;
import jobs4u.app.user.console.presentation.candidate.ShowNotificationsUI;

import jobs4u.usermanagement.domain.BaseRoles;

import org.example.tcp.ClientNotifications;
import org.example.tcp.Server;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends ClientUserBaseUI {

    private static final String SEPARATOR_LABEL = "--------------";

    private static final int EXIT_OPTION = 0;
    private static final String RETURN_LABEL = "Return ";


    // MAIN MENU
    private static final int CANDIDATE_OPTION = 1;
    private static final int COSTUMER_OPTION = 1;


    // Candidate Menu
    private static final int LIST_APPLICATIONS_OPTION = 1;

    //Costumer Menu
    private static final int LIST_OPENINGS_OPTION = 1;


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

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.CANDIDATE)) {
            
            final Menu candidateMenu = buildCandidateMenu();
            mainMenu.addSubMenu(CANDIDATE_OPTION, candidateMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.CUSTOMER, BaseRoles.CUSTOMER_MANAGER)){
            final Menu costumerMenu = buildCostumerMenu();
            mainMenu.addSubMenu(COSTUMER_OPTION, costumerMenu);
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;


    }

    private Menu buildCandidateMenu() {
        final Menu menu = new Menu("Manage your applications >");

        menu.addItem(LIST_APPLICATIONS_OPTION, "List my applications", new ListJobApplicationsUI()::show);
        menu.addItem(2, "List my notifications", new ShowNotificationsUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCostumerMenu(){
        final Menu menu = new Menu( "Manage your Job Opneings >");

        menu.addItem(LIST_OPENINGS_OPTION, "List my Job Openings", new ListOpeningUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }
}
