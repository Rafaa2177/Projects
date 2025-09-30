package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CurrentSession;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.PurchaseOrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * RegisterOfferController class.
 * Handles the registration of offers for announcements.
 * Author: Luis Aquino
 */
public class RegisterOfferController {

    private AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
    private PurchaseOrderRepository purchaseOrderRepository = Repositories.getInstance().getPurchaseOrderRepository();

    /**
     * Registers an offer for an announcement.
     *
     * @param announcement The announcement for which the offer is registered.
     * @param offer        The offer amount.
     */
    public void registerOffer(Announcement announcement, double offer) {
        PurchaseOrderRepository repository = Repositories.getInstance().getPurchaseOrderRepository();
        String email = CurrentSession.getEmail();
        repository.add(email, offer, announcement);
    }

    /**
     * Retrieves the list of announcements.
     *
     * @return The list of announcements.
     */
    public List<Announcement> getAnnouncements() {
        return announcementRepository.getAnnouncements();
    }

    /**
     * Validates if an offer is valid for an announcement.
     *
     * @param announcement The announcement.
     * @param offer        The offer amount.
     * @return True if the offer is valid, false otherwise.
     */
    public boolean validateOffer(Announcement announcement, double offer) {
        return Repositories.getInstance().getPurchaseOrderRepository().validateOffer(announcement, offer);
    }

    /**
     * Checks if an offer amount is within the limits set for an announcement.
     *
     * @param announcement The announcement.
     * @param offer        The offer amount.
     * @return True if the offer amount is within the limits, false otherwise.
     */
    public boolean offerInsideLimits(Announcement announcement, double offer) {
        return Repositories.getInstance().getPurchaseOrderRepository().offerInsideLimits(announcement, offer);
    }

    /**
     * Checks if an order is pending for an announcement.
     *
     * @param announcement The announcement.
     * @return True if there is a pending order, false otherwise.
     */
    public boolean checkIfOrderIsPending(Announcement announcement) {
        return purchaseOrderRepository.checkIfOrderIsPending(announcement);
    }
}
