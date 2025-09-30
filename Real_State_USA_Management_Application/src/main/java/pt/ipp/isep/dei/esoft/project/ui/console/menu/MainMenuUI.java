package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.DisplayTheListPropertiesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class MainMenuUI implements Runnable {

    public MainMenuUI() {}


    public void run() {
        Repositories repoInit = Repositories.getInstance();
        try {
            Repositories repo1 = Utils.deserialize();
            repoInit = repo1;
        }catch (EOFException e){
            Repositories.getInstance();
        } catch (IOException e) {
            System.out.println("Failed to deserialize.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to deserialize");
            e.printStackTrace();
        }

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Display Properties", new DisplayTheListPropertiesUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

        Repositories repo = Repositories.getInstance();
        try {
            Utils.serialize(repo);
        } catch (IOException e) {
            System.out.println("Failed to serialize repositories");
            e.printStackTrace();
        }
    }


}
