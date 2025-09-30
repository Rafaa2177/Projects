#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "structs.h"
#include "functions.h"
#include "time.h"


// Função para serializar e escrever no ficheiro
void escreverParaFicheiro(const char *nomeFicheiro, DataProcessor processor) {

    // Obter a data atual
    time_t t = time(NULL);
    struct tm tm = *localtime(&t);

    char filePath[256];
    snprintf(filePath, sizeof(filePath), "%s/%04d%02d%02d%02d%02d%02d_sensors.txt", nomeFicheiro, tm.tm_year +1900 , tm.tm_mon + 1, tm.tm_mday, tm.tm_hour, tm.tm_min, tm.tm_sec);

    FILE *ficheiro = fopen(filePath, "w");

    if (ficheiro == NULL) {
        fprintf(stderr, "Erro ao abrir o ficheiro.\n");
        exit(1);
    }

    SensorData* sensors = processor.sensors;

    // Escrever dados no ficheiro
    for (int i = 0; i < processor.sensor_count; i++) {

        int arraySize = sensors[i].buffer.write_position - sensors[i].buffer.window_length + 1;

        if(arraySize > 0){
            double mediana = findMedian(sensors[i].mediana,arraySize);
            fprintf(ficheiro, "%d,%d,%s,%s,%.0f#\n", sensors[i].sensor_id, sensors[i].write_counter, sensors[i].type, sensors[i].unit,mediana);
        }
        else{
            fprintf(ficheiro, "%d,%d,%s,%s,%s#\n", sensors[i].sensor_id, sensors[i].write_counter, sensors[i].type, sensors[i].unit,"error");
        }
    }

    fclose(ficheiro);
}

