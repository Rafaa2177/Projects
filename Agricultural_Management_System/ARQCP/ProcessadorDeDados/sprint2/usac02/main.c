#include <stdio.h>
#include "asm.h"

int main(void){

	int array[]= {0,0,0,0};

	int n = 4;

	int adArray[] = {1,2,3,4,5};

	int read = 0;

	int write = 0;
	for(int i = 0; i < n; i++){
        enqueue_value(array,n,&read,&write,adArray[i]);
        for( int j=0; j < n +1; j++){
            printf("%d ", array[j]);
        }
        printf("\n");
    }
	return 0;
}