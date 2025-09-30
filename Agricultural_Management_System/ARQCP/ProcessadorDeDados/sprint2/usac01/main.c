#include <stdio.h>
#include "asm.h"





int main(void) {
    char input[] = "sensor_id:8#type:atmospheric_temperature#value:21.60#unit:celsius#time:2470030";
    char token[] = "sensor_id:";
    int vec[3]={0x55555555,0x55555555,0x55555555}; 

    extract_token(input, token, &vec[1]);

    printf("Output: %d\n", vec[1]);
}