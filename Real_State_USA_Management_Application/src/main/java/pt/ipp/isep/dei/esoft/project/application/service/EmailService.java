package pt.ipp.isep.dei.esoft.project.application.service;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

/**
 * EmailService interface.
 * Defines the contract for sending notification emails for visit requests.
 * Author: Rafaela Lopes and Marco Ferreira
 */
public interface EmailService {
    void sendNotificationEmailForVisitRequest(String email, VisitRequest visitRequest, Announcement announcement);
}
