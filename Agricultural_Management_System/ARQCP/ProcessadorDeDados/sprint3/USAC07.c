#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include "structs.h"


void createDirectories(char* directory) {
    struct stat st = {0};

    // Verifica se o diretório já existe
    if (stat(directory, &st) == -1) {
        // Tenta criar o diretório
        if (mkdir(directory, 0777) == 0) {
            printf("Diretório criado com sucesso.\n");
        } else {
            perror("Erro ao criar diretório");
        }
    }
}

DataProcessor* initDataProcessor(char outdir[]) {
    DataProcessor* processor = (DataProcessor*)malloc(sizeof(DataProcessor));
    if (processor != NULL) {
        processor->sensor_count = 0;
        processor->sensors = NULL;
        createDirectories(outdir);
    }
    return processor;
}

void freeDataProcessor(DataProcessor* processor) {
    if (processor != NULL) {
        free(processor->sensors);
        free(processor);
    }
}


DataProcessor dynamicAlocation(char outdir[]) {
    DataProcessor* processor = initDataProcessor(outdir);

    if (processor != NULL) {
        freeDataProcessor(processor);
    } else {
        printf("Erro ao inicializar o Processador de Dados.\n");
    }

    DataProcessor  processor2 = *processor;

    return processor2;
}