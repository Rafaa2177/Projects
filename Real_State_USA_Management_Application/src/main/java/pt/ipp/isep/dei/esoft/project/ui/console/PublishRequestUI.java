package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishRequestController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementRequest;
import pt.ipp.isep.dei.esoft.project.domain.Commission;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class PublishRequestUI implements Runnable {
    private Commission commission;

    private final PublishRequestController Controller = new PublishRequestController();
    Scanner input = new Scanner(System.in);
    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        List<AnnouncementRequest> requestList = Controller.getRequests();
        AnnouncementRequest selectedRequest = (AnnouncementRequest) Utils.showAndSelectOne(requestList, "Select one:");

        if (selectedRequest != null) {

            Boolean confirm = false;
            do {
                System.out.println(selectedRequest);

                System.out.println("\n1-Accept");
                System.out.println("2-Decline");

                int answer;
                do {
                    answer = Utils.readIntegerFromConsole("");
                } while (answer != 1 && answer != 2);
                if (answer == 1) {
                   commission = getCommission();
                    confirm = Utils.confirmEng("Are you sure you want to publish this announcement? (yes/no)");
                     if(confirm){
                         Announcement ann =new Announcement(selectedRequest,commission, LocalDate.now(),Status.ACCEPT);
                         Controller.publishAnnouncement(ann);
                         System.out.println("Announcement Published correctly!");
                     }
                }else  if (answer == 2) {
                    Utils.readLineFromConsole("Justification: ");
                    confirm = Utils.confirmEng("Are you sure you want to decline this request? (yes/no)");
                    if (confirm) {
                        selectedRequest.setStatus(Status.DECLINE);
                        System.out.println("Announcement Rejected");
                    }
                }


            } while (!confirm) ;
        }
    }

    private void writeMessage() {

    }

    public Commission getCommission(){
        System.out.println("Do you wish to input the commission as a fixed value or a percentage of the sale?\n1 - value\n2 - percentage");

        switch (input.nextInt()){
            case 1:
                System.out.println("input the value: ");
                double commissionAmount = -1;
                do{
                    System.out.println("Commission Amount:");
                    commissionAmount = input.nextDouble();
                }while(commissionAmount < 0);
                return new Commission(commissionAmount , -1);

            case 2:
                System.out.println("input the percentage (eg. : 12.5): ");
                do{
                    System.out.println("Commission Percentage:");
                    commissionAmount = input.nextDouble();
                }while(commissionAmount < 0);
                return new Commission(-1 , commissionAmount);

            default:
                System.out.println("Error null Commission");
                return new Commission(-1,-1);

        }
    }

}
