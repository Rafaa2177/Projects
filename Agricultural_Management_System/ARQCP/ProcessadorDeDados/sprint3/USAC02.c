#include "functions.h"
#include <stdio.h>
void enqueue_value(int* array, int length, int* write, int value) {
    // Verifica se o buffer está cheio
    if (*write == length) {
        *write = 0;
    }

    // Insere o valor no buffer
    array[*write] = value;
    (*write)++;
}
