#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include "structs.h"
#include "functions.h"

// Função para ler o arquivo de configuração e inicializar os sensores
int readConfigFile(DataProcessor* processor, const char* configFile) {
    FILE* file = fopen(configFile, "r");
    if (file == NULL) {
        perror("Erro ao abrir o arquivo de configuração");
        exit(EXIT_FAILURE);
    }

    // Contar o número de linhas no arquivo para alocar dinamicamente o array de sensores
        int configCount = 0;
        char line[256];
        while (fgets(line, sizeof(line), file) != NULL) {
            configCount++;
        }

    // Alocar dinamicamente o array de SensorConfig
    SensorConfig* sensorConfigs = (SensorConfig*)malloc(configCount * sizeof(SensorConfig));
    if (sensorConfigs == NULL) {
        perror("Erro ao alocar memória para os sensores");
        exit(EXIT_FAILURE);
    }

    // Reset the file pointer to the beginning of the file
    rewind(file);

    // Ler e inicializar os sensores a partir do arquivo de configuração
    for (int i = 0; i < configCount; i++) {
        fgets(line, sizeof(line), file);
        sscanf(line, "%d#%49[^#]#%19[^#]#%d#%d#%d",
               &sensorConfigs[i].sensor_id, sensorConfigs[i].type, sensorConfigs[i].unit,
               &sensorConfigs[i].buffer_len, &sensorConfigs[i].window_len, &sensorConfigs[i].timeout);
    }

    fclose(file);

    processor -> configs = sensorConfigs;

    return configCount;
}

// Função para encontrar o sensor na estrutura SensorConfig
SensorConfig findSensorConfig(SensorConfig* configs,  int sensor_id, int size) {

    SensorConfig  notFoundSensor;
    notFoundSensor.sensor_id = -1;
    for (int i = 0; i < size; i++) {
        if (configs[i].sensor_id == sensor_id) {
            return configs[i];
        }
    }
    return notFoundSensor;  // Sensor não encontrado
}

// Função para buscar um SensorData com base no sensor_id
SensorData* getSensorDataById(DataProcessor* processor, int sensor_id) {
    if (processor == NULL) {
        // Trate o ponteiro nulo, se necessário.
        printf("O ponteiro 'processor' é nulo.\n");
        return NULL;
    }

    for (int i = 0; i < processor->sensor_count; i++) {
        if (i >= 0 && i < processor->sensor_count) {
            if (processor->sensors[i].sensor_id == sensor_id) {
                return &(processor->sensors[i]);
            }
        } else {
            // Índice fora dos limites do array.
            printf("Índice fora dos limites do array de sensores.\n");
            return NULL;
        }
    }

    return NULL;  // Sensor não encontrado
}

/*void readFromRasp(char *port, int quantity){

    // Open the Raspberry's serial port
    int serialPort = open( port, O_RDWR);
    if (serialPort == -1) {
        perror("Error opening the serial port");
    }
    else{

        // Configure the serial port
        struct termios tty;
        tcgetattr(serialPort, &tty);

        // Set the transmission rate (baud rate)
        cfsetispeed(&tty, B9600);
        cfsetospeed(&tty, B9600);

        // Configure other parameters if necessary

        tcsetattr(serialPort, TCSANOW, &tty);
        // Serial port opened
        char buffer[256];
        // Check if the reading was successful
        ssize_t bytesRead = read(serialPort, buffer, sizeof(buffer));
        if (bytesRead == -1) {
            perror("Error reading from the serial port");
        }
        else{
            printf("Entrou");
        }
        // Close the port when finished
        close(serialPort);
    }
}*/

void addValueToSensorData(SensorData* sensorData,int value, int time  ){
    if (sensorData == NULL) {
        printf("O ponteiro 'sensorData' é nulo.\n");
        return;
    }
    if (time - sensorData->lastReading <= sensorData->timeout) {
        enqueue_value(sensorData->buffer.buffer,sensorData->buffer.length,&sensorData->buffer.write_position, value);

    }
    else{
        printf("Timeout excedido\n");
    }
}

void readFromRasp2(DataProcessor* processor, char* configDir, char* data, int quantity) {
    processor->config_count = readConfigFile(processor, configDir);
    processor->sensors = malloc(quantity * sizeof(SensorData));


    FILE *file;
    char line[256];

    // Open the file for reading
    file = fopen(data, "r");

    if (file == NULL) {
        perror("Error opening the file");
    } else {
        int i = 1;
        int currentTime = -1;
        while (i <= quantity) {
            fgets(line, sizeof(line), file);

            printf("\nLinha %d de %d : %s\n", i,quantity,line);

            // EXEMPLO: sensor_id:7#type:atmospheric_temperature#value:22.60#unit:celsius#time:166030
            char sensor_id[50];
            char type[50];
            char value[50];
            char unit[20];
            char tempo[50];
            sscanf(line, "sensor_id:%49[^#]#type:%49[^#]#value:%49[^#]#unit:%19[^#]#time:%49[^#]",
               sensor_id, type, value, unit, tempo);

            int intValue = 0;
            for (int i = 0; value[i] != '\0'; i++) {
                if (value[i] != '.') {
                    intValue = intValue * 10 + (value[i] - '0');
                }
            }

            SensorConfig config = findSensorConfig(processor->configs, atoi(sensor_id), processor->config_count);

            printf("Sensor_id: %d\n", atoi(sensor_id));
            printf("Type: %s\n", type);
            printf("Value: %d\n", intValue);
            printf("Unit: %s\n", unit);
            printf("Tempo: %d\n", atoi(tempo));

            if (config.sensor_id != -1) {

                SensorData* sensorData = getSensorDataById(processor, atoi(sensor_id));

                if (sensorData == NULL) {
                    SensorData newSensorData;

                    newSensorData.sensor_id = atoi(sensor_id);
                    strcpy(newSensorData.type, type);
                    strcpy(newSensorData.unit, unit);

                    CircularBuffer buffer;
                    buffer.buffer = (int *) malloc(config.buffer_len * sizeof(int));
                    buffer.read_position = 0;
                    buffer.write_position = 0;
                    buffer.length = config.buffer_len;
                    buffer.window_length = config.window_len;
                    newSensorData.buffer = buffer;
                    newSensorData.write_counter = 0;
                    newSensorData.timeout = config.timeout;
                    newSensorData.lastReading = atoi(tempo);
                    newSensorData.mediana =(int *) malloc(config.buffer_len * sizeof(SensorData));

                    processor->sensors[processor->sensor_count] = newSensorData;
                    processor->sensor_count++;

                }
                else sensorData->lastReading = currentTime;


                currentTime = atoi(tempo);

                addValueToSensorData( sensorData, intValue, currentTime);

            }
            i++;

        }
    }
}
