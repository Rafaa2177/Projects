package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VisitRequestController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The type Visit request ui.
 */
public class VisitRequestUI implements Runnable {
    private String date, message;
    private int[][] timeSlot;
    private Announcement announcement;
    private Person person;

    /**
     * The Input.
     */
    Scanner input = new Scanner(System.in);

    /**
     * The Controller.
     */
    VisitRequestController controller = new VisitRequestController();

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        requestData();
    }

    private Announcement requestAnnouncement() {
        int count = 1;
        List<Announcement> announcementList = controller.getAnnouncements();
        Collections.reverse(announcementList);

        for (Announcement announcement : announcementList) {
            System.out.println("Announcement " + count + ":\n " + announcement.toString());
            System.out.println();
            System.out.println("----------------------------------------------------------");
            count++;
        }

        int option = Utils.readIntegerFromConsole("Choose the announcement you want to register an offer for: ");
        announcement = controller.getAnnouncements().get(option-1);

        return announcement;
    }

    private String requestDate() {
        System.out.println("Type the date you want to visit this property (dd/mm)");
        date = input.nextLine();
        return date;
    }

    private int[][] requestTimeSlot() {
        System.out.println("Type the hour you want to visit this property (24h format)");
        int op = 1;
        int[][] timeslot1 = new int[100][2];
        int count = 0;
        while (op == 1) {
            String timeSlot = requestString("Time slot (example: 15-16)");
            String[] timeSlotArr = timeSlot.split("-");
            timeslot1[count][0] = Integer.parseInt(timeSlotArr[0]);
            timeslot1[count][1] = Integer.parseInt(timeSlotArr[1]);
            count++;

            op = verfiInt("Want to add another timeslot? \n1: yes\n2: no\n");
            input.nextLine();
        }
        timeSlot = controller.saveTimeSlot(timeslot1, count);
        return timeSlot;
    }

    private String requestString(String print) {
        System.out.println(print);
        return input.nextLine();
    }

    /**
     * Verfi int int.
     *
     * @param print the print
     * @return the int
     */
    public int verfiInt(String print) {
        int number;
        while (true) {
            System.out.print(print);
            try {
                number = Integer.parseInt(input.next());
                //break;
                return number;

            } catch (NumberFormatException ignore) {
                System.out.println("Not valid.");
            }
        }
    }

    private String requestMessage() {
        System.out.println("Do you want to leave a message for the agent?\n" +
                "1: yes\n2: no ");
        int option = input.nextInt();
        input.nextLine();
        if (option == 1) {
            System.out.println("Write your message:");
            message = input.nextLine();
            return message;

        } else if (option == 2) {
            return "you didn't leave a message";

        } else {
            return null;
        }
    }

    private Person requestID() {
        return controller.getPersonRepository().getPerson();
    }

    private void requestData() {

        //request the property he wants to visit
        announcement = requestAnnouncement();
        System.out.println();

        //request the date he wants to visit the property
        date = requestDate();
        System.out.println();

        //request the hour he wants to visit the property
        timeSlot = requestTimeSlot();
        System.out.println();

        if (timeSlot != null) {

            //request the message
            message = requestMessage();
            System.out.println();

            //request the user ID (name and phone number)
            person = requestID();
            System.out.println(person.toString());

            createVisitRequest(announcement, date, timeSlot, message, person);

            //request to confirm data
            System.out.println("Do you want to confirm the data inputted?\n1: yes \n2: no");
            int opt = input.nextInt();

            if (opt == 1){
                System.out.println("Visit request registered");
            } else if (opt == 2) {
                System.out.println("Visit request not registered");
            } else {
                System.out.println("option not available");
            }

        } else {
            System.out.println("The hour you've written is not available ");
        }
    }

    private void createVisitRequest(Announcement announcement, String date, int[][] timeSlot, String message, Person person) {
        controller.createVisitRequest(announcement, date, timeSlot, message, person);
    }


}
