package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ScheduleVisitResponseController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Scanner;

public class ScheduleVisitResponseUI implements Runnable{

    private static final Scanner sc = new Scanner(System.in);
    private ScheduleVisitResponseController controller = new ScheduleVisitResponseController();

    public void run (){
        int cont = getPendingVisits();
            if (cont != 0){

            }

       // displayAnnouncementsByAgent();
    }

    public void displayAnnouncementsByAgent(List<VisitRequest> visitRequests){
        List<Announcement> announcementList = controller.getAnnouncement();
        boolean opt = true;
        for (Announcement ann : announcementList){
            if (ann.getAgent().getId().getEmail().equals(CurrentSession.getUser().getId().getEmail())){
                System.out.println(ann);
                displayvisits(ann, visitRequests);
                opt = false;
            }
        }
        if (opt){
            System.out.println("\n\t This Agent does not have any announcements!");
            System.exit(1);
        }
    }

    public void displayvisits(Announcement announcement, List<VisitRequest> visitRequests){
        if (visitRequests.isEmpty()){
            System.out.println( " No visits found");
        } else {
            for (VisitRequest vis : visitRequests){
                if (vis.getAnnouncement() == announcement){
                    if (vis.getStatus() == Status.PENDING){
                        System.out.println(vis);
                    }
                }
            }
        }


    }

    public VisitRequest getVisitByID(int id){
        return controller.getVisitRequestByID(id);


    }

    public int getPendingVisits(){
        List< VisitRequest> visitRequests = controller.getVisitRequest();
        int cont = 0;

        for (VisitRequest vis : visitRequests){
            if ( vis.getStatus() == Status.PENDING){
                cont ++;
            }
        }
        return cont;
    }

    private int veriFy(String print){
        int number;
        while (true){
            System.out.println(print);
            try {
                number = Integer.parseInt(sc.next());
                break;
            } catch (NumberFormatException exp){
                System.out.println("Not valid");
            }
        }
        return number;
    }


    private String requestString(String print){
        System.out.println(print);
        return sc.nextLine();

    }

    public void RequestData(){
        VisitRequest selectedIdVisit = displayAndSelectVisit();
        System.out.println(selectedIdVisit);
        boolean confirm = false;

        if (selectedIdVisit != null){
            while (!confirm){
                System.out.println("\nPlease confirm if this is the booling request that you want to accept or decline\n\t 1- Yes \n\t 2-No");
                System.out.println("\nType your option");
                int opt = veriFy(" ");
                if (opt == 1){
                    confirm = true;
                    
                } else if (opt == 2) {
                    confirm = false;
                    displayAndSelectVisit();
                }

            }
        }  else {
            System.out.println("Invalid Selection");
        }
        System.out.println("Do you want to accept or decline the booking request? \n\t1- Accept \n\t 2- Decline \n\t 3- Cancel the operation");
        System.out.println("\nType your option");
        int opts = veriFy(" ");
        Status status;
        switch (opts){
            case 1:
                status = Status.PENDING;
                break;
            case 2: status = Status.DECLINE;
                break;
            default:
                System.out.println("You choose an invalid option");
        }



            

    }

    private VisitRequest displayAndSelectVisit(){
        int selectedIdVisit;
        VisitRequest selectedVisit = null;

        while (selectedVisit == null){
            selectedIdVisit = veriFy("\nEnter the id of the booking request that you want to accept or decline");
            selectedVisit = getVisitByID(selectedIdVisit);

            if (selectedVisit == null){
                System.out.println("\nThe id that u selected is not valid. Please try again! ");

            }


        }
        return null; // change
    }






}
