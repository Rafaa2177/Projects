package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.adapter.EmailServiceAdapter;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.service.EmailService;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VisitRequestRepository;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * ScheduleVisitResponseController class.
 * Handles the response to visit requests for announcements.
 * Author: Rafaela Lopes
 */
public class ScheduleVisitResponseController {

    private AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
    private EmailServiceAdapter adapter;
    private EmailService emailService;
    private VisitRequestRepository visitRequestRepository = Repositories.getInstance().getVisitRequestRepository();

    private AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    private List<VisitRequest> visitRequestList = visitRequestRepository.getListVisitSorted();

    /**
     * Retrieves the list of announcements.
     *
     * @return The list of announcements.
     */
    public List<Announcement> getAnnouncement() {
        return announcementRepository.findAnnouncementsByAgent(CurrentSession.getEmail());
    }

    /**
     * Retrieves a visit request by its ID.
     *
     * @param ID The ID of the visit request.
     * @return The visit request.
     */
    public VisitRequest getVisitRequestByID(int ID) {
        return visitRequestRepository.getVisitByID(ID);
    }

    /**
     * Retrieves the list of visit requests.
     *
     * @return The list of visit requests.
     */
    public List<VisitRequest> getVisitRequest() {
        return visitRequestRepository.getListVisitRepository();
    }

    /**
     * Retrieves the list of visit requests.
     *
     * @return The list of visit requests.
     */
    public List<VisitRequest> getVisitRequestList() {
        return visitRequestList;
    }

    /**
     * Accepts or declines a visit request.
     *
     * @param visitRequest The visit request.
     * @param status       The status of the visit request.
     */
    public void acceptOrDeclineVisit(VisitRequest visitRequest, Status status) {
        authenticationRepository.ensureAuthenticatedUserHasRoles(AuthenticationController.ROLE_AGENT);

        switch (status) {
            case ACCEPT:
                visitRequest.setStatus(Status.ACCEPT);
                break;
            case DECLINE:
                visitRequest.setStatus(Status.DECLINE);
                break;
            default:
                System.out.println("The status is not valid");
                break;
        }
    }

    /**
     * Sends an email notification for a visit request.
     *
     * @param visitRequest The visit request.
     */
    public void emailOption(VisitRequest visitRequest) {
        adapter = new EmailServiceAdapter();
        emailService = adapter.getEmailService();
        emailService.sendNotificationEmailForVisitRequest(visitRequest.getPerson().getId().getEmail(), visitRequest, visitRequest.getAnnouncement());
    }
}
