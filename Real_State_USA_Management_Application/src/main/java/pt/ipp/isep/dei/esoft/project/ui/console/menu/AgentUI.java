package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.ListVisitRequestGUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ScheduleVisitResponseGUI;

import java.util.ArrayList;
import java.util.List;

public class AgentUI implements Runnable{

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("US002 - Publish an Announcement", new PublishAnnouncementUI()));
        options.add(new MenuItem("US008 - Publish a request made to me", new PublishRequestUI()));
        options.add(new MenuItem("US0011 - List the purchase orders grouped by properties", new SetOrderUI()));
        options.add(new MenuItem("US0015 - List the visit requests", new ListVisitRequestGUI()));
        options.add(new MenuItem("US016- Respond to visit requests", new ScheduleVisitResponseGUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}


