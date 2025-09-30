package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.DivideStoresGUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ListDealsGUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.ListDealsControllerGUI;

import java.util.ArrayList;
import java.util.List;

public class NetManagerUI implements Runnable {
    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("US0017 - List all deals made",new ListDealsGUI()));
        options.add(new MenuItem("US0019 - Divide Store Subset", new DivideStoresGUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nNetwork Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}

