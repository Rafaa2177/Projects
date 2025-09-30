package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VisitRequestRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller class for listing visit requests.
 * Author: Diana Neves
 */
public class ListVisitRequestController {

    private Repositories repositories = Repositories.getInstance();
    private Person person = repositories.getPersonRepository().getPerson();

    /**
     * Checks if the agent has a visit request list.
     *
     * @return True if the agent has a visit request list, false otherwise.
     */
    public Boolean checkAgentList() {
        return repositories.getVisitRequestRepository().checkAgentList(person);
    }

    /**
     * Retrieves the visit request list for the agent.
     *
     * @return The visit request list for the agent.
     */
    public List<VisitRequest> getAgentList() {
        return repositories.getVisitRequestRepository().getAgentList(person);
    }

    /**
     * Retrieves the sorted visit request list within the specified date range.
     *
     * @param list  The list of visit requests to be sorted.
     * @param begin The start date of the range.
     * @param end   The end date of the range.
     * @return The sorted visit request list within the specified date range.
     */
    public List<VisitRequest> getSortedVisitRequestList(List<VisitRequest> list, LocalDate begin, LocalDate end) {
        return repositories.getVisitRequestRepository().getSortedVisitRequestList(list, begin, end);
    }

    /**
     * Retrieves the list of visit requests.
     *
     * @return The list of visit requests.
     */
    public List<VisitRequest> getVisits() {
        VisitRequestRepository visitRequestRepository = Repositories.getInstance().getVisitRequestRepository();
        return visitRequestRepository.getListVisitRepository();
    }
}
