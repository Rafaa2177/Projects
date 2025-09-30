package pt.ipp.isep.dei.esoft.project.domain.sort_algorithm;

import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * HeapSort Class.
 * Represents the Heap Sort algorithm for sorting a list of Visit Requests.
 * Implements the Sorter interface.
 * Author: Diana Neves
 */
public class HeapSort implements Sorter, Serializable {

    /**
     * Sorts the list of Visit Requests using the Heap Sort algorithm.
     *
     * @param arr        the array of Visit Requests to be sorted
     * @param comparator the comparator used for sorting
     */
    private void sort(VisitRequest arr[], Comparator<VisitRequest> comparator) {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i, comparator);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            VisitRequest temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0, comparator);
        }
    }

    /**
     * Heapify a subtree rooted with node i which is an index in arr[].
     *
     * @param arr        the array of Visit Requests
     * @param N          the size of the heap
     * @param i          the index of the node
     * @param comparator the comparator used for heapifying
     */
    void heapify(VisitRequest arr[], int N, int i, Comparator<VisitRequest> comparator) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && comparator.compare(arr[l], arr[largest]) > 0)
            largest = l;

        // If right child is larger than largest so far
        if (r < N && comparator.compare(arr[r], arr[largest]) > 0)
            largest = r;

        // If largest is not root
        if (largest != i) {
            VisitRequest swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest, comparator);
        }
    }

    @Override
    public List<VisitRequest> sort(List<VisitRequest> toSort, Comparator<VisitRequest> sorter) {
        VisitRequest[] arr = toSort.toArray(new VisitRequest[0]);
        sort(arr, sorter);
        return Arrays.asList(arr);
    }
}
