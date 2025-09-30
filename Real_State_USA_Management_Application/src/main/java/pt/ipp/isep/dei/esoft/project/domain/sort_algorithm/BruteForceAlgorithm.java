package pt.ipp.isep.dei.esoft.project.domain.sort_algorithm;

import pt.ipp.isep.dei.esoft.project.domain.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * BruteForceAlgorithm Class.
 * Represents a brute-force algorithm for dividing a list of organizations into two subsets with the minimum difference in the total number of properties.
 * Author: Diana Neves
 */
public class BruteForceAlgorithm {

    public List<Organization> subset1 = new ArrayList<>();
    public List<Organization> subset2 = new ArrayList<>();

    int minDiff;
    int subsetSize;
    double time;

    /**
     * Divides the list of organizations into two subsets based on the number of stores.
     *
     * @param organizations the list of organizations
     * @param n             the number of stores to consider
     */
    public void numberOfStoresDivisionList(List<Organization> organizations, int n) {
        List<Organization> nOrganizations = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nOrganizations.add(organizations.get(i));
        }
        findMinSubsetDifference(nOrganizations);

    }

    /**
     * Finds the minimum difference in the total number of properties between two subsets.
     *
     * @param organizations the list of organizations
     */
    public void findMinSubsetDifference(List<Organization> organizations) {
        int n = organizations.size(); //n = number of stores

        minDiff = Integer.MAX_VALUE;
        subsetSize = (int) Math.pow(2, n);

        int totalSubset1 = 0;
        int totalSubset2 = 0;

        long starTime = System.nanoTime();

        for (int i = 0; i < subsetSize; i++) {
            int subsetSum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subsetSum += organizations.get(j).getNumberOfProperties();
                }
            }

            int difference = Math.abs(subsetSum - (getTotalNumberAds(organizations) - subsetSum));
            if (difference < minDiff) {
                minDiff = difference;
                subset1.clear();
                subset2.clear();

                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) != 0) {
                        subset1.add(organizations.get(j));
                        totalSubset1 += organizations.get(j).getNumberOfProperties();
                    } else {
                        subset2.add(organizations.get(j));
                        totalSubset2 += organizations.get(j).getNumberOfProperties();
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        System.out.println("List: " + organizations.toString());
        System.out.println("Difference: " + minDiff);

        time = (endTime - starTime) / 1000000000.0;
        System.out.printf("Time: %.15f seconds%n", time);
    }

    private int getTotalNumberAds(List<Organization> organizations) {
        int totalAnnouncements = 0;
        for (Organization organization : organizations) {
            totalAnnouncements += organization.getNumberOfProperties();
        }
        return totalAnnouncements;
    }

    /**
     * Gets the first subset of organizations.
     *
     * @return the subset1 list
     */
    public List<Organization> getSubset1() {
        System.out.println("Subset 1: " + subset1);
        return subset1;
    }

    /**
     * Gets the second subset of organizations.
     *
     * @return the subset2 list
     */
    public List<Organization> getSubset2() {
        System.out.println("Subset 2: " + subset1);
        return subset2;
    }

    /**
     * Gets the minimum difference in the total number of properties between the subsets.
     *
     * @return the minDiff value
     */
    public int getMinDiff() {
        return minDiff;
    }

    /**
     * Gets the size of the subset.
     *
     * @return the subsetSize value
     */
    public int getSubsetSize() {
        return subsetSize;
    }

    /**
     * Gets the execution time of the algorithm.
     *
     * @return the time value
     */
    public double getTime() {
        return time;
    }
}
