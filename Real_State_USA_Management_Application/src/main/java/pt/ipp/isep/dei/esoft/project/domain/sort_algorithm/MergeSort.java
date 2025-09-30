package pt.ipp.isep.dei.esoft.project.domain.sort_algorithm;

import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * MergeSort Class.
 * Represents the Merge Sort algorithm for sorting a list of Visit Requests.
 * Implements the Sorter interface.
 * Author: Diana Neves
 */
public class MergeSort implements Sorter, Serializable {

    /**
     * Merges two subarrays of arr[].
     *
     * @param arr        the array of Visit Requests
     * @param l          the starting index of the first subarray
     * @param m          the middle index
     * @param r          the ending index of the second subarray
     * @param comparator the comparator used for merging
     */
    private void merge(VisitRequest arr[], int l, int m, int r, Comparator<VisitRequest> comparator) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        VisitRequest L[] = new VisitRequest[n1];
        VisitRequest R[] = new VisitRequest[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (comparator.compare(L[i], R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /**
     * Sorts the list of Visit Requests using the Merge Sort algorithm.
     *
     * @param arr        the array of Visit Requests to be sorted
     * @param l          the starting index of the subarray
     * @param r          the ending index of the subarray
     * @param comparator the comparator used for sorting
     */
    private void sort(VisitRequest arr[], int l, int r, Comparator<VisitRequest> comparator) {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m, comparator);
            sort(arr, m + 1, r, comparator);

            // Merge the sorted halves
            merge(arr, l, m, r, comparator);
        }
    }

    @Override
    public List<VisitRequest> sort(List<VisitRequest> toSort, Comparator<VisitRequest> sorter) {
        VisitRequest[] arr = toSort.toArray(new VisitRequest[0]);
        sort(arr, 0, arr.length - 1, sorter);
        return Arrays.asList(arr);
    }
}
