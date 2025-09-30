package pt.ipp.isep.dei.esoft.project.application.service;

import pt.ipp.isep.dei.esoft.project.application.service.EmailService;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.*;

public class TextEmailService implements EmailService {

    private static int number=0;

    public static void sendNotificationEmail(String email, PurchaseOrder order)  {
        try {
            String path="./src/main/java/pt/ipp/isep/dei/esoft/project/ui/console/SentEmails/"+number+"notification"+email+".txt";
            //String text =String.format("Your purchase order was %d.\nOrder id: %d\n Email sent to: %d", order.getStatus(), order.getIdOrder(), email);
            String text = "Your purchase order was "+ order.getStatus()+".\nOrder id:"+ order.getIdOrder()+"\n Email sent to: "+email+"\n\n";
            File file1= new File(path);
            FileWriter fileWriter = new FileWriter(file1,true);
            fileWriter.write(text);
            fileWriter.close();
            System.out.println( "Notification successfully sent to order with id: " + order.getIdOrder());
            number++;

        } catch (IOException exp){
            System.out.println(exp.getMessage());
        }


    }

    public void sendNotificationEmailForVisitRequest(String email, VisitRequest visitRequest, Announcement announcement) {
        String path="./src/main/java/pt/ipp/isep/dei/esoft/project/ui/console/SentEmails/"+number+"notification"+email+".txt";
        String text  = "Your visit rquest was " + visitRequest.getStatus() +
                "\n Visit id: " + visitRequest.getIdVisit() +
                "\n Email sent to: " + email +
                "\n Name of the responsible agent: " + announcement.getAgent() +
                "\n Phone number of the responsible agent: " + announcement.getAgent().getPhoneNumber() +
                "\n Announcement id: "+ announcement.getID() +
                "\n Property Location: " + announcement.getRequest().getProperty().getAddress() +
                "\n From: " + announcement.getAgent().getId();

        File file1 = new File(path);
        PrintWriter printWriter;
        try{
            printWriter = new PrintWriter(file1);
            printWriter.write(text);
            printWriter.close();
            System.out.println("The email was sucessfully sent to booking request with id: " + visitRequest.getIdVisit());
            number++;
        } catch (FileNotFoundException fnfe){
            throw new IllegalArgumentException("ERROR: email file was not found");
        }





    }

}

