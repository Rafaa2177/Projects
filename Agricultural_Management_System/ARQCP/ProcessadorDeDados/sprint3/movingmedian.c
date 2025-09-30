#include "structs.h"
#include <stdio.h>
#include <stdlib.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void insertionSort(int arr[], int n) {
    int i, key, j;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

int findMedian(int arr[], int n) {
    if (n % 2 != 0) return arr[n / 2];
    return arr[(n - 1) / 2];
}

void movingMedian(int* arr, int* buffer, int size, int scope) {
    if (scope > size) {
        //printf("Window size is larger than the array size.\n");
        return;
    }

    int *window = (int *)malloc(scope * sizeof(int));

    // Fill the initial window
    for (int i = 0; i < scope; i++) {
        window[i] = buffer[i];
    }

    insertionSort(window, scope);
    arr[0] = findMedian(window, scope);

    // Move the window and find the median
    for (int i = scope; i < size; i++) {
        // Remove the element going out of the window
        int j = 0;
        while (j < scope && window[j] != buffer[i - scope]) {
            j++;
        }
        if (j < scope) {
            swap(&window[j], &window[scope - 1]);
            insertionSort(window, scope - 1);
        }

        // Add the new element
        window[scope - 1] = buffer[i];
        insertionSort(window, scope);

        // Fix the indexing here
        arr[i - scope +1] = findMedian(window, scope);
    }

    free(window);
}
