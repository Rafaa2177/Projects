package pt.ipp.isep.dei.esoft.project.application.service;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GmailService implements EmailService{
    private static int number=0;


    @Override
    public void sendNotificationEmailForVisitRequest(String email, VisitRequest visitRequest, Announcement announcement) {
        String path="./src/main/java/pt/ipp/isep/dei/esoft/project/ui/console/SentEmails/"+number+"notification"+email+".txt";
        String text  = "Email sent via gmail" +
                "\nYour visit rquest was " + visitRequest.getStatus() +
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
