// structs.h
#ifndef STRUCTS_H
#define STRUCTS_H

typedef struct {
    int sensor_id;
    char type[50];
    char unit[20];
    int buffer_len;
    int window_len;
    int timeout;
} SensorConfig;

typedef struct {
    int* buffer;
    int write_position;
    int read_position;
    int length;
    int window_length;
} CircularBuffer;


typedef struct {
    int sensor_id;
    char type[50];
    char unit[20];
    CircularBuffer buffer;
    int* mediana;
    char timestamp[25];
    int timeout;
    int lastReading;
    int write_counter;
} SensorData;


typedef struct {
    SensorData* sensors;
    SensorConfig* configs;
    int sensor_count;
    int config_count;
} DataProcessor;

#endif