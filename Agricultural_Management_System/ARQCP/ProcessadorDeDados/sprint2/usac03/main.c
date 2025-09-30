#include <stdio.h>
#include "asm.h"

int main(void){
	
	int array[]= {7,8,9};
	
	int length = 3;
	
	int read = 1;
	
	int write = 2;
	
	int vec[10];
	
	int result = move_num_vec(array, length, &read, &write, 9, vec);

	printf("O resultado é: %d", result);

	return 0;
}