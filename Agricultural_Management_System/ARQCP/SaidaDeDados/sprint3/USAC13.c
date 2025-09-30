#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <dirent.h>
#include "functions.h"
#include "structs.h"
#include <time.h>
#include <stdbool.h>

typedef struct {
    char filename[50];
    struct tm timestamp;
} FileEntry;

// Função para buscar um SensorData com base no sensor_id
SensorData getSensorDataById(SensorData list[], int sensor_id) {

    SensorData notFoundSensor;
    notFoundSensor.sensor_id = -1;

    for (int i = 0; i < 10; i++) {
        if (list[i].sensor_id == sensor_id) {
            return list[i];
        }
    }

    return notFoundSensor;  // Sensor não encontrado
}

bool verifyDate(char name[], time_t referenceTime) {
    FileEntry fileEntry;

    // Extrai informações de data e hora do nome do arquivo
    if (sscanf(name, "%4d%2d%2d%2d%2d%2d.txt",
               &fileEntry.timestamp.tm_year,
               &fileEntry.timestamp.tm_mon,
               &fileEntry.timestamp.tm_mday,
               &fileEntry.timestamp.tm_hour,
               &fileEntry.timestamp.tm_min,
               &fileEntry.timestamp.tm_sec) == 6) {
        // Ajusta a estrutura tm para o formato correto
        fileEntry.timestamp.tm_year -= 1900;
        fileEntry.timestamp.tm_mon -= 1;

        // Compara as datas
        time_t fileTime = mktime(&fileEntry.timestamp);

        if (difftime(fileTime, referenceTime) > 0) {
            printf("O ficheiro %s é recente\n", name);
            return true;
        }

        return false;
    }
}

void writeToFile(SensorData list[], int num, char caminhoSaida[]){

    // Abre o arquivo de saída para escrita (cria se não existir)
    FILE *arquivoSaida = fopen(caminhoSaida, "w");

    if (arquivoSaida != NULL) {
        for (int j = 0; j < num; ++j) {
            if(list[j].value != 0){
                fprintf(arquivoSaida, "Sensor ID: %d\nTipo: %s\nUnidade: %s\nValor: %.2f\n\n",
                        list[j].sensor_id, list[j].type, list[j].unit, list[j].value);

            }
            else {
                fprintf(arquivoSaida, "Sensor ID: %d\nTipo: %s\nUnidade: %s\nValor: %s\n\n",
                        list[j].sensor_id, list[j].type, list[j].unit, "error");
            }

        }
    } else {
        printf("Erro ao abrir o arquivo de saída \n");
        exit(EXIT_FAILURE);
    }

    // Fecha o arquivo de saída
    fclose(arquivoSaida);
}

// Função para processar os dados e criar o arquivo de texto
void processAndCreateFile(char *outputDir, char *inputDir, time_t currentTime) {

    DIR *dir;
    struct dirent *entry;
    SensorData list[11];


    // Abre o diretório de entrada
        if ((dir = opendir(inputDir)) != NULL) {

            // Cria o caminho para o arquivo de saída
            char caminhoSaida[256];
            sprintf(caminhoSaida, "%s/sensores.txt", outputDir);


            int i=0;
            int num=0;

            // Lê os arquivos do diretório
            while ((entry = readdir(dir)) != NULL) {
                if (entry->d_type == DT_REG) { // Verifica se é um arquivo regular

                    if(currentTime ==0 || verifyDate(entry->d_name, currentTime)){


                    // Caminho completo para o arquivo de entrada
                    char caminhoEntrada[256];
                    sprintf(caminhoEntrada, "%s/%s", inputDir, entry->d_name);

                    // Abre o arquivo de entrada
                    FILE *arquivoEntrada = fopen(caminhoEntrada, "r");

                    if (arquivoEntrada != NULL) {
                        // Leitura dos dados do arquivo
                        char linha[256];
                        char sensorID[50], sensorTipo[50], unidade[50], valorStr[50], status[50];

                        printf("/%s\n", entry->d_name);

                        i=0;
                        while (fgets(linha, sizeof(linha), arquivoEntrada) != NULL) {
                            // Processa cada linha do arquivo
                            // EXEMPLO : 4,0,atmospheric_humidity,percentage,error#
                            sscanf(linha, "%[^,],%[^,],%[^,],%[^,],%[^#]#", sensorID, status, sensorTipo, unidade, valorStr);


                            // Converte o valor para número real com duas casas decimais
                            double valor = atof(valorStr);
                            valor = valor / 100;

                            SensorData  sensor = getSensorDataById(list,atoi(sensorID));

                            if(sensor.sensor_id == -1){
                                sensor.sensor_id = atoi(sensorID);
                                sensor.value = valor;
                                strcpy(sensor.unit,unidade);
                                strcpy(sensor.type,sensorTipo);

                                list[i] = sensor;
                                i++;
                                num++;
                            }
                            else{
                                sensor.value = valor;
                            }

                        }

                        // Fecha o arquivo de entrada
                        fclose(arquivoEntrada);
                    } else {
                        printf("Erro ao abrir o arquivo de entrada %s\n", caminhoEntrada);
                    }

                    }
                }
            }
            writeToFile(list, num, caminhoSaida);



            // Fecha o diretório
            closedir(dir);
        } else {
            perror("Erro ao abrir o diretório");
        }
}

// Função principal que lê periodicamente os últimos arquivos
void runPeriodically(char *outputDir, char *inputDir, int executionPeriod){
    time_t currentTime = 0;
    while (1) {

        printf("Executou\n");
        processAndCreateFile(outputDir, inputDir, currentTime);

        // Obter a data atual
        time(&currentTime);


        // Aguarda o próximo período de execução
        sleep(executionPeriod);
    }
}


