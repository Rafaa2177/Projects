package pt.ipp.isep.dei.esoft.project.ui.console.menu;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.LegacyFileUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.AnalyzeDealsGUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.DivideStoresGUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ListDealsGUI;

import java.util.ArrayList;
import java.util.List;

/**
 *Ω
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Request", new CreateRequestUI()));
        options.add(new MenuItem("Register a new Employee ", new CreateEmployeeUI()));
        options.add(new MenuItem("US0012 - Import Information from a Legacy System ", new LegacyFileUI()));
        options.add(new MenuItem("US0018 - Analyze Deals", new AnalyzeDealsGUI()));
        options.add(new MenuItem("US0017 - List all deals made",new ListDealsGUI()));
        options.add(new MenuItem("US0019 - Divide Store Subset", new DivideStoresGUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
