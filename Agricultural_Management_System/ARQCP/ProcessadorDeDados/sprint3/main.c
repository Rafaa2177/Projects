#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "structs.h"
#include "functions.h"

// EXEMPLO : ./processadordedados /etc/ttyS0 config intermedio 50

// data: caminho do ficheiro que permite receber dados do componente ColetorDeDados
// config: caminho do ficheiro de configuração do componente
// outdir: caminho do diretório para colocar os ficheiros os dados para o componente SaidaDeDados
// threads: número de leituras que faz antes de enviar dados para o componente SaidaDeDados

int main(int argc, char *argv[]) {
    if (argc != 5) {
        fprintf(stderr, "Uso: %s <caminho_ficheiro> <ficheiro_config> <diretorio_saida> <num_leituras>\n", argv[0]);
        exit(1);
    }


    const char *caminhoFicheiro = argv[1];
    const char *ficheiroConfig = argv[2];
    const char *diretorioSaida = argv[3];
    int numLeituras = atoi(argv[4]);


    // Carregar configurações do ficheiro
    FILE *configFile = fopen(ficheiroConfig, "r");
    if (configFile == NULL) {
        fprintf(stderr, "Erro ao abrir a pasta de configuração.\n");
        exit(1);
    }
    printf("%s",ficheiroConfig);

    DataProcessor processor = dynamicAlocation(diretorioSaida);

    // Código do algoritmo principal

        readFromRasp2(&processor, ficheiroConfig, caminhoFicheiro, numLeituras);

        // Loop para processar cada sensor
        SensorData sensor;
        for (int i = 0; i < processor.sensor_count; i++) {
            sensor = processor.sensors[i];
            movingMedian(sensor.mediana,sensor.buffer.buffer,sensor.buffer.write_position,sensor.buffer.window_length);
        }

        // Criar e escrever o ficheiro 'AAAAMMDDHHMMSS_sensors.txt'
        escreverParaFicheiro(diretorioSaida, processor);

}
