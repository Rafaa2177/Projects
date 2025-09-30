#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <unistd.h>
#include "functions.h"


// Função para alocar dinamicamente a estrutura de dados e configurar o componente
void configureSaidaDeDados(char *outputDir) {

    struct stat st = {0};

    // Verifica se o diretório já existe
    if (stat(outputDir, &st) == -1) {
        // Tenta criar o diretório
        if (mkdir(outputDir, 0777) == 0) {
            printf("Diretório criado com sucesso.\n");
        } else {
            perror("Erro ao criar diretório");
        }
    }

}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        fprintf(stderr, "Uso: %s <input_directory> <output_directory> <execution_period>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char* inputdDir = argv[1];
    char* outputDir = argv[2];


    // Converte o terceiro argumento para inteiro (periodicidade)
    int executionPeriod = atoi(argv[3]);

    // Configura o componente SaidaDeDados
    configureSaidaDeDados(outputDir);

    // Chamada periódica
    runPeriodically(outputDir,inputdDir,executionPeriod);


    return 0;
}
