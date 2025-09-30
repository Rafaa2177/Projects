#ifndef FUNCTIONS_H
#define FUNCTIONS_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <dirent.h>
#include <errno.h>
#include <time.h>
#include <signal.h>
#include <sys/stat.h>
#include <glob.h>
#include <semaphore.h>
#include <sys/mman.h>
#include <fcntl.h>


#define KEY_SIZE 100
#define VALUE_SIZE 100
#define LINE_SIZE 200
#define CANDIDATE_INFO_SIZE 100
#define DIR_SIZE 100
#define MAX_INFO_SIZE 100
#define BUFFER_SIZE 3

typedef struct {
    char input_directory[100];
    char output_directory[100];
    int num_workers;
    int check_interval;
    int max_attachments;
} Config;

Config readConfigFile(char *filename);
void printConfigFile(char *input_directory, char *output_directory, int num_workers, int check_interval, int max_attachments);
int checkFiles(char *input_directory);
int getNumCandidates(char *input_directory);
int *getFirstIntsBeforeHyphen(char *str, int vezes);
void copyAndMoveFiles(const char *pattern, const char *candidate_dir, const char *already_dir);
void writeOutputFile(const char *JobOpeningId, const char *email, const char *firstName, const char *lastName, const char *NumPhone, int indicative);
void createReportFile();
void cleanOutputFile();
#endif