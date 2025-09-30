package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementRequest;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.dto.AnnouncementRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RequestRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.PublishRequestUI;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.*;

/**
 * PublishRequestController class.
 * Handles the publishing of requests and announcements.
 * Author: Marco Ferreira
 */
public class PublishRequestController {

    private RequestRepository requestRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private PersonRepository personRepository;
    private String message = "";

    /**
     * Default constructor.
     */
    public PublishRequestController() {
        getRequestRepository();
        getAnnouncementRepository();
        getPersonRepository();
    }

    /**
     * Gets the RequestRepository instance.
     *
     * @return The RequestRepository instance.
     */
    private RequestRepository getRequestRepository() {
        if (requestRepository == null) {
            Repositories repositories = Repositories.getInstance();
            requestRepository = repositories.getRequestRepository();
        }
        return requestRepository;
    }

    /**
     * Gets the AnnouncementRepository instance.
     *
     * @return The AnnouncementRepository instance.
     */
    public AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    /**
     * Gets the PersonRepository instance.
     *
     * @return The PersonRepository instance.
     */
    public PersonRepository getPersonRepository() {
        if (personRepository == null) {
            Repositories repositories = Repositories.getInstance();
            personRepository = repositories.getPersonRepository();
        }
        return personRepository;
    }

    /**
     * Retrieves the list of announcement requests for the agent.
     *
     * @return The list of announcement requests for the agent.
     */
    public List<AnnouncementRequest> getRequests() {

        Person agent = personRepository.getPerson();

        List<AnnouncementRequest> requests = RequestRepository.getRequests();

        organizeRequestsByDate(requests);
        List<AnnouncementRequest> agentRequestList = new ArrayList<>();

        for (AnnouncementRequest request : requests) {
            if (agent == request.getAgent() && request.getStatus() == Status.PENDING) {
                agentRequestList.add(request);
            }
        }
        return agentRequestList;
    }

    /**
     * Organizes the list of announcement requests by date in descending order.
     *
     * @param requests The list of announcement requests to be organized.
     * @return The organized list of announcement requests.
     */
    public List<AnnouncementRequest> organizeRequestsByDate(List<AnnouncementRequest> requests) {
        List<AnnouncementRequest> announcementRequestListReverse = new ArrayList<>(requests);
        Collections.reverse(announcementRequestListReverse);
        return announcementRequestListReverse;
    }

    /**
     * Adds a request to the request repository.
     *
     * @param request The request to be added.
     */
    public void addRequest(AnnouncementRequest request) {
        requestRepository.addRequest(request);
    }

    /**
     * Publishes an announcement by adding it to the announcement repository.
     *
     * @param announcement The announcement to be published.
     */
    public void publishAnnouncement(Announcement announcement) {
        announcementRepository.addAnnouncement(announcement);
    }

    /**
     * Sets a message for the controller.
     *
     * @param message The message to be set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Finds an agent by their email address.
     *
     * @param agentEmail The email address of the agent.
     * @return The agent with the specified email address.
     */
    public Person findAgent(Email agentEmail) {
        return personRepository.getPersonByEmail(String.valueOf(agentEmail));
    }
}
