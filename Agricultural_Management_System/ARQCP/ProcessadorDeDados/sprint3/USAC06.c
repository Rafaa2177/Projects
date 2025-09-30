#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "functions.h"

int extract_token(char* input, char* token, int* output) {
    char* token_ptr = strstr(input, token);

    if (token_ptr != NULL) {
        // Move o ponteiro para o início do valor
        token_ptr += strlen(token);
        token_ptr--;

        // Verifica se o caractere após o token é ':'
        if (*token_ptr == ':') {
            token_ptr++; // Move para o próximo caractere após ':'

            // Converte o valor para inteiro
            *output = atoi(token_ptr);

            // Move o ponteiro para o próximo caractere após o valor, até o próximo '#'
            while (*token_ptr != '\0' && *token_ptr != '#') {
                token_ptr++;
            }

            // Verifica se encontrou o caractere '#' e o substitui por '\0' para encerrar a string
            if (*token_ptr == '#') {
                *token_ptr = '\0';
            }

            *output= (int) token_ptr;

            return 1; // Retorna 1 indicando sucesso
        }
    }

    // Se o token não for encontrado ou o formato estiver errado, define a saída como -1
    *output = -1;
    return 0; // Retorna 0 indicando falha
}
