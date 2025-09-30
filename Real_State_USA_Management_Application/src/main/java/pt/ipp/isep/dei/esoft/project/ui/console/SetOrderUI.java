package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SetOrdersController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.application.service.TextEmailService;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.CurrentSession.personRepository;

/**
 * The type Set order ui.
 */
public class SetOrderUI implements Runnable{

    /**
     * The Sc.
     */
    Scanner sc = new Scanner(System.in);

    private final SetOrdersController controller= new SetOrdersController();

    private PurchaseOrder order;
    private Status status;


    @Override
    public void run() {
        displayAnnouncementByAgent();
        try {
            requestData();
        } catch (FileNotFoundException e) {
           // throw new RuntimeException(e);
        }


    }

    /**
     * Display purchase orders.
     *
     * @param announcement the announcement
     */
    public void displayPurchaseOrders(Announcement announcement) {
        List<PurchaseOrder> orderList= controller.getOrders(announcement);
        Collections.sort(orderList);
        boolean opt = true;
        for (PurchaseOrder ord: orderList){

                if (ord.getStatus()==Status.PENDING){
                    System.out.println(ord);
                    opt=false;
                }

        }
        if (opt){
            System.out.println("\nThis announcement doesn´t have any orders\n");
        }

    }

    /**
     * Display announcement by agent.
     */
    public void displayAnnouncementByAgent(){
        List<Announcement> announcements= controller.getAnnouncement();
        boolean opt=true;
        for (Announcement ann : announcements){
            if (ann.getRequest().getAgent().getId().equals(personRepository.getPerson().getId())){
                System.out.println(ann);
                displayPurchaseOrders(ann);
                opt=false;
            }
        }
        if (opt){
            System.out.println("There are no announcements published by this agent");
        }
    }


    /**
     * Get purchase order by id purchase order.
     *
     * @param ID the id
     * @return the purchase order
     */
    public PurchaseOrder getPurchaseOrderByID(int ID){
        PurchaseOrder selectedPurchaseOrder= controller.getPurchaseOrderByID(ID);
        return selectedPurchaseOrder;

    }


    /**
     * Display and select purchase order purchase order.
     *
     * @return the purchase order
     */
    public PurchaseOrder displayAndSelectPurchaseOrder(){
      int selectedIDOrder ;
      PurchaseOrder selectedOrder= null;
      while (selectedOrder==null){
          selectedIDOrder=verifInt("\n Enter the ID of the purchase order you wish to accept or decline: ");
          selectedOrder=getPurchaseOrderByID(selectedIDOrder);
          if (selectedOrder==null){
              System.out.println("This ID is not valid! Please try again");
          }
      }
      return selectedOrder;
    }

    /**
     * Request data.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public void requestData() throws FileNotFoundException {
        PurchaseOrder selectedIDOrder=displayAndSelectPurchaseOrder();
        System.out.println(selectedIDOrder);
        boolean confirm=false;
        if (selectedIDOrder!= null){
            while (confirm==false){
                System.out.println("Please confirm if this is the order that you want.   (Yes/No)");
                String answer= sc.next();
                if (answer.equalsIgnoreCase("Yes")){
                    confirm=true;

                } else if (answer.equalsIgnoreCase("No")) {
                    confirm=false;
                    displayAndSelectPurchaseOrder();
                }
            }
        } else {
            System.out.println("Invalid selection");
        }
        System.out.println(selectedIDOrder);
        assert selectedIDOrder!= null;
        acceptOrDeclineOrder(selectedIDOrder, selectedIDOrder.getAnnouncement());
    }

    /**
     * Accept or decline order.
     *
     * @param order        the order
     * @param announcement the announcement
     * @throws FileNotFoundException the file not found exception
     */
    public void acceptOrDeclineOrder(PurchaseOrder order, Announcement announcement) throws FileNotFoundException {
        List<PurchaseOrder> orderList=controller.getOrders(announcement);
        if (order!=null){
            System.out.println("Do you pretend to accept or decline the offer?  (Accept/Decline/Exit)");
            String answer=sc.next();
            if (answer.equalsIgnoreCase("Accept")){
                order.setStatus(Status.ACCEPT);
                TextEmailService.sendNotificationEmail(order.getClient(),order);
                for (PurchaseOrder ord : orderList){
                    if (ord.getAnnouncement()== announcement){
                        if (ord.getStatus()!= Status.ACCEPT) {
                            ord.setStatus(Status.DECLINE);
                            TextEmailService.sendNotificationEmail(ord.getClient(),ord);

                        }

                    }

                }
            } else if (answer.equalsIgnoreCase("Decline")) {
                order.setStatus(Status.DECLINE);
                TextEmailService.sendNotificationEmail(order.getClient(),order);
            } else if (answer.equalsIgnoreCase("Exit")) {
                //StandBy

            }


        }
    }


    /**
     * Verify limits int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public int verifyLimits(int min, int max){
        while (true) {
            try {
                int i=sc.nextInt();
                while (i<min || i> max ){
                    System.out.printf("The value should be between %d and %d\n", min,max);
                    i=sc.nextInt();
                }
                return i;
            } catch (InputMismatchException exp){
                sc.nextLine();
            }
        }

    }


    private int verifInt(String print){
        int number;
        while (true) {
            System.out.print(print);
            try {

                number = Integer.parseInt(sc.next());

                break;
            } catch (NumberFormatException ignore) {
                System.out.println("Not valid.");

            }
        }
        return number;
    }









}