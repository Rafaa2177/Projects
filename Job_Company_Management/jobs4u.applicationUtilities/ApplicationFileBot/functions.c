#include "functions.h"

Config readConfigFile(char *filename) {
    Config config;
    
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        printf("Error opening the file.\n");
        exit(EXIT_FAILURE);
    }

    // Read config file
    char line[LINE_SIZE];
    while (fgets(line, sizeof(line), file)) {
        char key[KEY_SIZE];
        char value[VALUE_SIZE];
        sscanf(line, "%[^:]: %[^\n]", key, value);
        if (strcmp(key, "input_directory") == 0) {
            strcpy(config.input_directory, value);
            config.input_directory[strlen(config.input_directory) - 1] = '\0';
        } else if (strcmp(key, "output_directory") == 0) {
            strcpy(config.output_directory, value);
            config.output_directory[strlen(config.output_directory) - 1] = '\0';
        } else if (strcmp(key, "num_workers") == 0) {
            config.num_workers = atoi(value);
        } else if (strcmp(key, "check_interval") == 0) {
            config.check_interval = atoi(value);
        } else if (strcmp(key, "max_attachments") == 0) {
            config.max_attachments = atoi(value);
        }
    }
    fclose(file);

    return config;
}

void printConfigFile(char *input_directory, char *output_directory, int num_workers, int check_interval, int max_attachments) {
    printf("\nInput directory: %s\n", input_directory);
    printf("Output directory: %s\n", output_directory);
    printf("Number of workers: %d\n", num_workers);
    printf("Check interval: %d\n", check_interval);
    printf("Max attachments: %d\n\n\n", max_attachments);
}

int checkFiles(char *input_directory) {
    // Check if the input directory exists
    DIR *folder;
    struct dirent *entry;
    int fileCount = 0;

    // Open the input directory
    folder = opendir(input_directory);
    if (folder == NULL) {
        printf("Failed to open the folder.\n");
        return 1;
    }

    // Count the number of files in the folder
    while ((entry = readdir(folder)) != NULL) {
        if (entry->d_type == DT_REG) {  // Check if it's a regular file
            fileCount++;
        }
    }
    // Close the folder
    closedir(folder);

    return fileCount;
}

int getNumCandidates(char *input_directory) {
    // Check the filenames in the directory
    DIR *folder;
    struct dirent *entry;

    folder = opendir(input_directory);
    if (folder == NULL) {
        printf("Failed to open the folder.\n");
        return 1;
    }

    int candidateCount = 0;
    int lastCandidate = 0;

    while ((entry = readdir(folder)) != NULL) {
        if (entry->d_type == DT_REG) {  // Check if it's a regular file
            // Count the number of candidates
            char *filename = entry->d_name;
            int candidateNumber = atoi(strtok(filename, "-"));
            if (candidateNumber != lastCandidate) {
                // Increment the candidate count
                lastCandidate = candidateNumber;
                candidateCount++;
            }
        }
    }

    return candidateCount;
}

int *getFirstIntsBeforeHyphen(char *str,int times) {
    char *directory = str;
    DIR *d;
    struct dirent *dir;
    int *first_ints = (int *)malloc(times * sizeof(int));
    int found = 0;

    d = opendir(directory);

    if (d) {
        while ((dir = readdir(d)) != NULL && found < times) {
            // Ignore '.' and '..' entries
            if (strcmp(dir->d_name, ".") == 0 || strcmp(dir->d_name, "..") == 0)
                continue;

            // Check if the file ends with ".txt"
            size_t length = strlen(dir->d_name);
            if (length >= 4 && strcmp(dir->d_name + length - 4, ".txt") == 0) {
                // Find the hyphen in the filename
                char *hyphen_ptr = strchr(dir->d_name, '-');
                if (hyphen_ptr != NULL) {
                    // Get the first integer before the hyphen
                    char *first_int_str = (char *)malloc((hyphen_ptr - dir->d_name + 1) * sizeof(char));
                    strncpy(first_int_str, dir->d_name, hyphen_ptr - dir->d_name);
                    first_int_str[hyphen_ptr - dir->d_name] = '\0';
                    int first_integer = atoi(first_int_str);
                    free(first_int_str);

                    // Check if the integer has already been found
                    int repeated = 0;
                    for (int i = 0; i < found; ++i) {
                        if (first_ints[i] == first_integer) {
                            repeated = 1;
                            break;
                        }
                    }

                    // If not found, add to the list of first integers
                    if (!repeated) {
                        first_ints[found] = first_integer;
                        found++;
                    }
                }
            }
        }
        closedir(d);
    }
    return first_ints;
}

void copyAndMoveFiles(const char *pattern, const char *candidate_dir, const char *already_dir) {
    glob_t glob_result;

    // Find files matching the pattern
    glob(pattern, 0, NULL, &glob_result);

    // Create destination directory
    mkdir(candidate_dir, 0700);
    mkdir(already_dir, 0700);

    if (glob_result.gl_pathc > 0) {
        printf("\nStarting to copy files...\n\n");
        for (size_t i = 0; i < glob_result.gl_pathc; ++i) {
            pid_t child_pid = fork();
            if (child_pid == 0) {
                // Copy matching files to the destination directory
                execlp("cp", "cp", glob_result.gl_pathv[i], candidate_dir, NULL);
                perror("Error while copying the file.");
                exit(1);
            } else if (child_pid < 0) {
                perror("Error creating child process.");
                exit(1);
            }
            printf("File %s copied to %s filho número %d\n", glob_result.gl_pathv[i], candidate_dir, getpid());
        }

        // Wait for all child processes to finish
        for (size_t i = 0; i < glob_result.gl_pathc; ++i) {
            wait(NULL);
        }

        // After all files are copied, move them to already_read
        glob(pattern, 0, NULL, &glob_result); // reload the file list, as it may have changed
        printf("\nStarting to move files...\n\n");
        for (size_t i = 0; i < glob_result.gl_pathc; ++i) {
            pid_t child_pid = fork();
            if (child_pid == 0) {
                execlp("mv", "mv", glob_result.gl_pathv[i], already_dir, NULL);
                perror("Error while moving the file.");
                exit(1);
            } else if (child_pid < 0) {
                perror("Error creating child process.");
                exit(1);
            }
            printf("File %s moved to %s filho número %d\n", glob_result.gl_pathv[i], already_dir, getpid());
        }

        // Wait for all child processes to finish again
        for (size_t i = 0; i < glob_result.gl_pathc; ++i) {
            wait(NULL);
        }
    } else {
        printf("A corresponding file for the pattern was not found %s.\n", pattern);
        exit(1);
    }
}

// Function to write candidate information to output file
void writeOutputFile(const char *JobOpeningId, const char *email, const char *firstName, const char *lastName, const char *NumPhone, int indicative) {
    char candidate_dir[DIR_SIZE];
    
    // Create candidate directory path
    snprintf(candidate_dir, sizeof(candidate_dir), "ApplicationFileBot/candidates/%d-%s/", indicative, email);

    // Open output file in append mode
    FILE *outputFile = fopen("output.txt", "a");

    // Check if file opened successfully
    if (outputFile == NULL) {
        perror("Error opening output file");
        exit(EXIT_FAILURE);
    }

    // Write candidate information to output file
    fprintf(outputFile, "%s\n", candidate_dir);
    fprintf(outputFile, "%s\n", JobOpeningId);
    fprintf(outputFile, "%s\n", email);
    fprintf(outputFile, "%s\n", firstName);
    fprintf(outputFile, "%s\n", lastName);
    fprintf(outputFile, "%s\n", NumPhone);
    fprintf(outputFile, "---------------------------------------------------\n");

    // Close the output file
    fclose(outputFile);
}

// Function to create the report
void createReportFile() {
    // Check if the output file contains data
    FILE *output_file = fopen("output.txt", "r");
    if (output_file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }
    fseek(output_file, 0, SEEK_END); // Go to the end of the file
    long file_size = ftell(output_file); // Get the file size
    fclose(output_file);

    // If the output file is empty, don't create the report
    if (file_size == 0) {
        printf("Output file is empty. No report file created.\n");
        return;
    }

    // Get current time
    time_t rawtime;
    struct tm *timeinfo;
    time(&rawtime);
    timeinfo = localtime(&rawtime);

    // Create report file name
    char filename[DIR_SIZE];
    strftime(filename, DIR_SIZE, "reports/ReportFile-%Y-%m-%d-%H-%M-%S.txt", timeinfo);

    // Open report file
    FILE *report_file = fopen(filename, "w");

    // Check if report file opened successfully
    if (report_file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    // Open output file again for copying content
    output_file = fopen("output.txt", "r");
    if (output_file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    // Copy content from output file to report file
    int c;
    while ((c = fgetc(output_file)) != EOF) {
        fputc(c, report_file);
    }

    // Close files
    fclose(output_file);
    fclose(report_file);

    printf("Report file created: %s\n", filename);

    // Clean output file after copying
    cleanOutputFile();
}

// Function to clean the output file
void cleanOutputFile() {
    FILE *output_file = fopen("output.txt", "w");
    if (output_file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }
    fclose(output_file);
    printf("Output file cleaned.\n");
}
