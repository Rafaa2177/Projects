package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VisitRequestRepository;

import java.util.ArrayList;
import java.util.List;
/**
 * The type Visit request controller.
 * Author: Diana Neves
 */
public class VisitRequestController {
    /**
     * The Visit request repository.
     */
    VisitRequestRepository visitRequestRepository = Repositories.getInstance().getVisitRequestRepository();
    /**
     * The Announcement repository.
     */
    AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
    /**
     * The Person repository.
     */
    PersonRepository personRepository = Repositories.getInstance().getPersonRepository();
    /**
     * Instantiates a new Visit request controller.
     */
    public VisitRequestController() {}
    /**
     * Get announcements list.
     *
     * @return the list
     */
    public List<Announcement> getAnnouncements(){return announcementRepository.getAnnouncements();}
    /**
     * Get visit request list.
     *
     * @return the list
     */
    public List<VisitRequest> getVisitRequest(){ return visitRequestRepository.getListVisitRepository();}
    /**
     * Gets person repository.
     *
     * @return the person repository
     */
    public PersonRepository getPersonRepository() {return personRepository;}
    /**
     * Create visit request.
     *
     * @param announcement the announcement
     * @param date         the date
     * @param timeSlot     the time slot
     * @param message      the message
     * @param person       the person
     */
    public void createVisitRequest(Announcement announcement, String date, int[][] timeSlot,
                                   String message, Person person) {
        VisitRequest visit = new VisitRequest(announcement, date, timeSlot, message, person);
        visitRequestRepository.addRequests(visit);
    }
    /**
     * Save time slot int [ ] [ ].
     *
     * @param timereal the timereal
     * @param count    the count
     * @return the int [ ] [ ]
     */
    public int[][] saveTimeSlot(int[][] timereal, int count) {
        //list of all visit requests made
        List<VisitRequest> list = visitRequestRepository.getListVisitRepository();
        // list of all the hours inputted by all users
        List<Integer> hoursTaked = new ArrayList<>();
        //list of hours inputted by the user logged in
        List<Integer> userHoursTaked = new ArrayList<>();
        //for all the visit requests made we will verify:
        for (VisitRequest v: list) {
            //for all the slots of the time slot (first hour and final hour, example: 15 and 20)
            for (int[] slot: v.getTimeSlot()
            ) {
                int firstHour = slot[0]; // 15
                int finalHour = slot[1]; // 20
                /* if the lis of hours inputted by all users don't have the hour inputted by this user
                (first hour), first hour will be added to the list
                 */
                if (!hoursTaked.contains(firstHour)){
                    hoursTaked.add(firstHour);
                }
                /* if the lis of hours inputted by all users don't have the hour inputted by this user
                (final hour), final hour will be added to the list
                 */
                if (!hoursTaked.contains(finalHour)){
                    hoursTaked.add(finalHour);
                }
                /*
                Now we have to verify if the list of hours taken by all users already has the hours between
                (first hour) and (final hour).
                For that we make a copy of the (first hour), calling it (hour) and while (hour) is
                different of (final hour),
                hour will be added to the list of hours taken by all the users.
                That way, all the hours between (first hour) and (final hour) will be "occupied" when the
                system wants to verify the new input of the user
                 */
                int hour = firstHour;
                while (hour != finalHour){
                    hour++;
                    if (!hoursTaked.contains(hour)){
                        hoursTaked.add(hour);
                    }
                }
            }
        }
        /*
        I dont really remember why i have this (count), but i know it is important!
        countValidSlots is for counting when the slot inputted is valid
        and added to the lists, so we use it in the verifications of the printing
         */
        int[][] timeslot = new int[count][2];
        boolean impossible2Add = false;
        int countValidSlots = 0;
        /*
        Now we have to verify if the hour inputted by the user has already been inputted
        or is between the hours inputted by the user before.
        If not we will add them to the list of hours inputted by the user,
        that way it will not be lost along the program and verifications
         */
        for (int i = 0; i < count; i++) {
            int hour = timereal[i][0] ;
            if (!userHoursTaked.contains(hour)){
                userHoursTaked.add(hour);
            }
            while (hour != timereal[i][1]){
                hour++;
                if (!userHoursTaked.contains(hour)){
                    userHoursTaked.add(hour);
                }
            }
            for (Integer h: userHoursTaked
            ) {
                if(hoursTaked.contains(h)){
                    impossible2Add = true;
                    break;
                }
            }
            if(!impossible2Add){
                for (int hourTakedByThisUser: userHoursTaked
                ) {
                    if (!hoursTaked.contains(hourTakedByThisUser)){
                        hoursTaked.add(hourTakedByThisUser);
                    }
                }
                timeslot[countValidSlots][0] = timereal[i][0];
                timeslot[countValidSlots][1] = timereal[i][1];
                countValidSlots++;
            }
            userHoursTaked = new ArrayList<>();
            impossible2Add = false;
        }
        if (countValidSlots > 0) {
            System.out.println("Timeslots:");
            for (int i = 0; i < countValidSlots; i++){
                System.out.println(timeslot[i][0] + " - " + timeslot[i][1]);
            }
            return timeslot;
        }else {
            return null;
        }
    }
}

