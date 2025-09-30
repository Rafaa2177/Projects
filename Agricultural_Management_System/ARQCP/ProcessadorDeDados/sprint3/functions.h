// functions.h
#include "structs.h"
#ifndef FUNCTIONS_H
#define FUNCTIONS_H

// USAC02
void enqueue_value(int* array, int length, int* write, int value);

// USAC06
int extract_token(char* input, char* token, int* output);

// USAC07
DataProcessor dynamicAlocation(char outdir[]);

// USAC08 e USAC09
void readFromRasp2(DataProcessor* processor, char* configDir, char* data, int quantity);

// USAC10
void escreverParaFicheiro(const char *nomeFicheiro, DataProcessor processor);

// Complementares
void movingMedian(int* arr, int* buffer, int size, int scope);
int findMedian(int arr[], int n) ;

#endif