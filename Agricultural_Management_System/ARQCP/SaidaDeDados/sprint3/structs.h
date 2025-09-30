// structs.h
#ifndef STRUCTS_H
#define STRUCTS_H

typedef struct {
    int sensor_id;
    char type[50];
    char unit[20];
    double value;
} SensorData;

#endif