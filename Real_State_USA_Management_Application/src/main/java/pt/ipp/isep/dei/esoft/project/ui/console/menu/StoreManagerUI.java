package pt.ipp.isep.dei.esoft.project.ui.console.menu;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.AnalyzeDealsGUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.DivideStoresGUI;

import java.util.ArrayList;
import java.util.List;

public class StoreManagerUI implements Runnable{
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("US0018 - Analyze Deals", new AnalyzeDealsGUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nStore Manager Menu:");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
