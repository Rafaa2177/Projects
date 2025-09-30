package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enums.Status;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The type Visit request.
 */
public class VisitRequest implements Comparable<VisitRequest>, Serializable {

    private Announcement announcement;
    private String date, message;
    private int[][] timeSlot;
    private Person person;

    private int idVisit;

    private static int numberOfScheduleVisits = 1;

    private Status status = Status.PENDING;


    /**
     * Gets announcement.
     *
     * @return the announcement
     */
    public Announcement getAnnouncement() {return announcement;}

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {return date;}

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {return message;}

    /**
     * Get time slot int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getTimeSlot() {return timeSlot;}

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    public int getIdVisit() {
        return idVisit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Instantiates a new Visit request.
     *
     * @param announcement the announcement
     * @param date         the date
     * @param timeSlot     the time slot
     * @param message      the message
     * @param person       the person
     */
    public VisitRequest(Announcement announcement, String date, int[][] timeSlot, String message, Person person){
        this.idVisit= numberOfScheduleVisits;
        this.announcement = announcement;
        this.date = date;
        this.timeSlot = timeSlot;
        this.message = message;
        this.person = person;
        numberOfScheduleVisits ++;
    }

    @Override
    public String toString() {
        return "VisitRequest" + idVisit +  "{" +
                "\n announcement " + announcement +
                "\n date: '" + date + '\'' +
                ",\n message: '" + message + '\'' +
                ",\n timeSlot: " + Arrays.deepToString(timeSlot) +
                ",\n person: " + person +
                ",\n status: " + status +
                "}" +
                "\n --------------------------------------------------------------------------------------------------------------------------------------";
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(VisitRequest o) {
        // dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
        Date thisDate = sdf.parse(date);
        Date otherDate = sdf.parse(o.date);

        return thisDate.compareTo(otherDate);

        } catch (ParseException e) {
            throw new RuntimeException("One of the dates is not in the correct format: dd/mm/yyyy",e);
        }
    }
}
