package org.example.notification;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailService {

    public void sendEmail(String destination, String subject, String message){

        try {
            Email email = new SimpleEmail();
            email.setHostName("frodo.dei.isep.ipp.pt");
            email.setSmtpPort(25);
            email.setSSLOnConnect(false);
            email.setFrom("customermanager@jobs4u.org", "Jobs4U Customer Manager");
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(destination);
            email.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

    }

}
