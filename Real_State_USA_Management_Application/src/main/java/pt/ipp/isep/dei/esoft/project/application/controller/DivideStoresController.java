package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.sort_algorithm.BruteForceAlgorithm;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for dividing stores.
 * Author: Diana Neves
 */
public class DivideStoresController {
    public int totalCount = 0;
    public List<Organization> subset1 = new ArrayList<>();
    public List<Organization> subset2 = new ArrayList<>();
    public int diff;
    public double time;

    public List<Organization> organizations = getOrganizations();

    /**
     * Retrieves the list of organizations.
     *
     * @return The list of organizations.
     */
    public List<Organization> getOrganizations() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        organizations = organizationRepository.getOrganizationList();
        return organizations;
    }

    /**
     * Retrieves the list of announcements.
     *
     * @return The list of announcements.
     */
    public List<Announcement> getAnnouncement() {
        AnnouncementRepository announcementsRepository = new AnnouncementRepository();
        return announcementsRepository.getAnnouncementsRepository();
    }

    /**
     * Calculates the number of properties for each organization.
     */
    public void getNumberOfProperties() {
        int counter;
        for (Organization org : organizations) {
            counter = 0;
            for (Announcement ann : getAnnouncement()) {
                if (org.getEmail().equals(ann.getAgent().getId().getEmail())) {
                    counter++;
                    totalCount++;
                }
            }

            org.setNumberOfProperties(counter);
        }
    }

    /**
     * Executes the brute force algorithm for dividing the stores.
     *
     * @param numberStoresForDivision The number of stores to be divided into.
     */
    public void executeBruteForce(int numberStoresForDivision) {
        BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
        bruteForceAlgorithm.numberOfStoresDivisionList(organizations, numberStoresForDivision);

        subset1 = bruteForceAlgorithm.getSubset1();
        subset2 = bruteForceAlgorithm.getSubset2();
        diff = bruteForceAlgorithm.getMinDiff();
        time = bruteForceAlgorithm.getTime();
    }

    /**
     * Retrieves subset2.
     *
     * @return The subset2 list.
     */
    public List<Organization> getSubset2() {
        return subset2;
    }

    /**
     * Retrieves subset1.
     *
     * @return The subset1 list.
     */
    public List<Organization> getSubset1() {
        return subset1;
    }

    /**
     * Retrieves the execution time of the algorithm.
     *
     * @return The execution time.
     */
    public double getTime() {
        return time;
    }

    /**
     * Retrieves the difference in the number of properties between the subsets.
     *
     * @return The difference in the number of properties.
     */
    public int getDiff() {
        return diff;
    }
}
