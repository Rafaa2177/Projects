#include <stdio.h>
#include "asm.h"

int array[] = {3,5,4,1,2};

int num = 5;
int *ptr = array;

int main(){

    sort_array(ptr, num);

    for(int i = 0; i < num; i++){
        printf("%d ", array[i]);
    }
    printf("\n");

    return 0;
}


