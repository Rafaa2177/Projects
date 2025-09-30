package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterOfferController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

public class RegisterOfferUI implements Runnable{

    public void run() {

        RegisterOfferController controller = new RegisterOfferController();

        int counter = 0, option;

        Announcement announcement;

        do{
            counter = 0;
            for(Announcement announce : controller.getAnnouncements()) {
                counter++;
                System.out.println(counter+ ". " + announce.toString());
            }
            option = Utils.readIntegerFromConsole("Choose the announcement you want to register an offer for: ");

            announcement = controller.getAnnouncements().get(option-1);

            if(!controller.checkIfOrderIsPending(announcement)) System.out.println("\nYou already have an offer to this property!\nSelect another Announcement!\n");

        }while(!controller.checkIfOrderIsPending(announcement));

        double offer;
        boolean validOffer = false, validLimits;
        do{
            do{
                offer = Utils.readDoubleFromConsole("Enter the amount you want to offer(" + announcement.getRequest().getPrice() + " or lower): ");
                validLimits = controller.offerInsideLimits(announcement, offer);
                if(!validLimits){
                    System.out.println("The offer you entered is invalid");
                }
            }while(!validLimits);
            if(controller.validateOffer(announcement, offer)){
                controller.registerOffer(announcement, offer);
                System.out.println("Offer registered successfully");
                validOffer = true;
            }
            else{
                System.out.println("This offer already exists with the same amount for this announcement");
                option = Utils.showAndSelectIndex(List.of("Yes", "No"), "Do you want to insert another offer?");
                if(option == 1) validOffer = true;
            }

        }while(!validOffer);

    }
}