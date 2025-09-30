package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.RegisterOfferUI;
import pt.ipp.isep.dei.esoft.project.ui.console.VisitRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClientUI implements Runnable {
    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Schedule a Visit", new VisitRequestUI()));
        options.add(new MenuItem("Register a new offer", new RegisterOfferUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
