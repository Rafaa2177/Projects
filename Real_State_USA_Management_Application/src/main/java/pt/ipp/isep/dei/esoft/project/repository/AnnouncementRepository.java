package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementRequest;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Announcement repository.
 */
public class AnnouncementRepository implements Serializable {

    private static final long serialVersionUID = -1446398935944095849L;

    private List<Announcement> announcements = new ArrayList<>();
    private List<AnnouncementRequest> announcementRequests = new ArrayList<>();

    /**
     * Gets announcements.
     *
     * @return the announcements
     */
    public  List<Announcement> getAnnouncements() {return announcements;}

    public  List<AnnouncementRequest> getAnnouncementRequest() { return announcementRequests;}

    /**
     * Add announcement.
     *
     * @param announcement the announcement
     */
    public void addAnnouncement(Announcement announcement){announcements.add(announcement);}

    public void addAnnouncementRequest(AnnouncementRequest announcementRequest){announcementRequests.add(announcementRequest);}

    /**
     * Get announcements repository list.
     *
     * @return the list
     */
    public List<Announcement> getAnnouncementsRepository(){
        return List.copyOf(announcements);
    }

    /**
     * Get announcement by order announcement.
     *
     * @param order the order
     * @return the announcement
     */
    public Announcement getAnnouncementByOrder(PurchaseOrder order){
        for (Announcement am: announcements) {
            if (am.hasOrder(order)) return am;
        }
        return null;
    }

    /**
     * Get announcements by agent list.
     *
     * @param agent the agent
     * @return the list
     */
    public List<Announcement> getAnnouncementsByAgent(Person agent){

        return announcements.stream().filter(ann -> ann.getAgent().equals(agent)).collect(Collectors.toList());

    }
    public List<Announcement> getAnnouncementList(){
        return List.copyOf(announcements);
    }


    public List<Announcement> findAnnouncementsByAgent(String agentEmail){
        List<Announcement> announcementsOfAgent = new ArrayList<>();

        for (Announcement a : this.announcements) {
            if(a.getAgent().getId().getEmail().equals(agentEmail))
                announcementsOfAgent.add(a);
        }

        return announcementsOfAgent;
    }
}