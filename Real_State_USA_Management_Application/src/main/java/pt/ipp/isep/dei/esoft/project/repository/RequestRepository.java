package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Request repository.
 */
public class    RequestRepository implements Serializable {
    private static List<AnnouncementRequest> requests = new ArrayList<>();

    /**
     * Add request.
     *
     * @param request the request
     */
    public void addRequest(AnnouncementRequest request){requests.add(request);}

    /**
     * Gets requests.
     *
     * @return the requests
     */
    public static List<AnnouncementRequest> getRequests() {
        return requests;
    }




}
