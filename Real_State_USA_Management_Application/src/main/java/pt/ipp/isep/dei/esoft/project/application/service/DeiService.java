package pt.ipp.isep.dei.esoft.project.application.service;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * DeiService class.
 * Implements the EmailService interface to send notification emails for visit requests.
 * Author: Rafaela Lopes
 */
public class DeiService implements EmailService {
    private static int number = 0;

    @Override
    public void sendNotificationEmailForVisitRequest(String email, VisitRequest visitRequest, Announcement announcement) {
        String path = "./src/main/java/pt/ipp/isep/dei/esoft/project/ui/console/SentEmails/" + number + "notification" + email + ".txt";
        String text = "Email sent via dei" +
                "\nYour visit request was " + visitRequest.getStatus() +
                "\nVisit ID: " + visitRequest.getIdVisit() +
                "\nEmail sent to: " + email +
                "\nName of the responsible agent: " + announcement.getAgent() +
                "\nPhone number of the responsible agent: " + announcement.getAgent().getPhoneNumber() +
                "\nAnnouncement ID: " + announcement.getID() +
                "\nProperty Location: " + announcement.getRequest().getProperty().getAddress() +
                "\nFrom: " + announcement.getAgent().getId();

        File file1 = new File(path);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file1);
            printWriter.write(text);
            printWriter.close();
            System.out.println("The email was successfully sent to booking request with ID: " + visitRequest.getIdVisit());
            number++;
        } catch (FileNotFoundException fnfe) {
            throw new IllegalArgumentException("ERROR: email file was not found");
        }
    }
}
