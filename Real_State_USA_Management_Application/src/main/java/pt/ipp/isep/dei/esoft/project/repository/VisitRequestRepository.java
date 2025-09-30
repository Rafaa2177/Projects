package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.domain.sort_algorithm.Sorter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The type Visit request repository.
 */
public class VisitRequestRepository implements Serializable {
    private static  List<VisitRequest> visitRequests = new ArrayList<>();
    private Sorter sorter;

    /**
     * Instantiates a new Visit request repository.
     *
     * @param sorter the sorter
     */
    public VisitRequestRepository(Sorter sorter){
        this.sorter= sorter;
    }


    /**
     * Get list visit repository list.
     *
     * @return the list
     */
    public List<VisitRequest> getListVisitRepository(){
        return List.copyOf(visitRequests);
    }

    /**
     * The List sorted.
     */
    List<VisitRequest> listSorted = new ArrayList<>();

    /**
     * Add requests.
     *
     * @param visitRequest the visit request
     */
    public void addRequests (VisitRequest visitRequest) {
        visitRequests.add(visitRequest);
        schedulevisitRepository.add(visitRequest);

    }

    private List<VisitRequest> schedulevisitRepository = new ArrayList<>();

    /**
     * Get visit by id visit request.
     *
     * @param id the id
     * @return the visit request
     */
    public VisitRequest getVisitByID(int id){
        for (VisitRequest visitRequest : schedulevisitRepository){
            if (visitRequest.getIdVisit() == id){
                if (visitRequest.getStatus() == Status.PENDING){
                    return visitRequest;
                }
            }
        }
        return null;
    }


    /**
     * Gets agent list.
     *
     * @param person the person
     * @return the agent list
     */
    public List<VisitRequest> getAgentList(Person person) {
        List<VisitRequest> newList = new ArrayList<>();
        for (int i = 0 ; i < visitRequests.size(); i++){
            if (visitRequests.get(i).getAnnouncement().getRequest().getAgent().equals(person)){
                newList.add(visitRequests.get(i));
            }
        }
        return newList;
    }


    /**
     * Check agent list boolean.
     *
     * @param person the person
     * @return the boolean
     */
    public Boolean checkAgentList(Person person) {
        int x = 0;
        for (int i = 0; i < visitRequests.size(); i++){
            if (visitRequests.get(i).getAnnouncement().getRequest().getAgent().equals(person)){
                x++;
            }
        }
        if(x > 0) {
            return false;
        } else {
            return  true;
        }
    }

    /**
     * Gets sorted visit request list.
     *
     * @param list  the list
     * @param begin the begin
     * @param end   the end
     * @return the sorted visit request list
     */
    public List<VisitRequest> getSortedVisitRequestList(List<VisitRequest> list, LocalDate begin, LocalDate end) {
        List<VisitRequest> newList = new ArrayList<>();
        listSorted = new ArrayList<>();
        ZoneId zId = ZoneId.systemDefault();

        for (int i = 0; i < list.size(); i++){
            Date beginDate = Date.from(begin.atStartOfDay(zId).toInstant());
            Date endDate = Date.from(end.atStartOfDay(zId).toInstant());

            String requestDate = list.get(i).getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateRequest = null;

            try {
                dateRequest = sdf.parse(requestDate);
            } catch (ParseException e) {
                throw new RuntimeException("The date format in the system is wrong!");
            }

            if(dateRequest.after(beginDate) && dateRequest.before(endDate)){
                newList.add(list.get(i));
            }

        }
        listSorted = sorter.sort(newList);
        return listSorted;
    }

    /**
     * Get list visit sorted list.
     *
     * @return the list
     */
    public List<VisitRequest> getListVisitSorted(){
        return listSorted;
    }

}
