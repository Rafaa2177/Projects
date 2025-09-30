#include "functions.h"

// Define circular buffer structure
typedef struct {
    int *buffer;
    int in;
    int out;
    int size;
} CircularBuffer;

typedef struct {
    int indicative[1000];
    int num_candidates;
} Indicatives;

// Global variable to check if Ctrl+C was pressed
volatile sig_atomic_t ctrlc_pressed = 0;

// Signal handler for Ctrl+C
void sigint_handler(int signum) {
    if (signum == SIGINT) {
        printf("\nCtrl+C recebido. Aguardando término dos processos filhos...\n");
        ctrlc_pressed = 1;
    }
}


// Function to create a semaphore
void createSemaphore(sem_t **sem, char *name, int initialValue) {
    *sem = sem_open(name, O_CREAT | O_EXCL, 0644, initialValue);
    if (*sem == SEM_FAILED) {
        perror("sem_open");
        exit(1);
    }
}

// Function to perform semaphore up operation
void upSemaphore(sem_t **sem) {
    if (sem_post(*sem) == -1) {
        perror("sem_post");
        exit(1);
    }
}

// Function to perform semaphore down operation
void downSemaphore(sem_t **sem) {
    while (sem_wait(*sem) == -1) {
        if (errno != EINTR) {
            perror("sem_wait");
            exit(1);
        }
        // Se errno for EINTR, a chamada foi interrompida por um sinal e deve ser tentada novamente
    }
}


// Function to remove semaphores
void removeSemaphores() {
    printf("Removing semaphores...\n");
    sem_unlink("/shm_empty");
    sem_unlink("/shm_full");
    sem_unlink("/shm_mutex");
    sem_unlink("/shm_new_candidate");
    sem_unlink("/shm_num_candidates");
    printf("Semaphores removed.\n");
}

// Function to insert a candidate into the circular buffer
void insertCandidate(CircularBuffer *cb, Indicatives *indicatives_shared_data, sem_t *empty, sem_t *full, sem_t *mutex, sem_t *new_candidate, int *index) {
    downSemaphore(&empty); // Wait for empty semaphore
    downSemaphore(&mutex); // Wait for mutex semaphore

    cb->buffer[cb->in] = indicatives_shared_data->indicative[*index];
    cb->in = (cb->in + 1) % cb->size;

    (*index)++;

    upSemaphore(&mutex); // Release mutex semaphore
    upSemaphore(&full); // Release full semaphore

    if (*index == indicatives_shared_data->num_candidates) {
        sleep(5);

        // Create a report file
        createReportFile();
        *index = 0;
        
        upSemaphore(&new_candidate); // Notify for new candidates
    }
}

int main() {
    // Set the signal handler for Ctrl+C
    struct sigaction sa;
    sa.sa_handler = sigint_handler;
    sa.sa_flags = 0; // Ensure SA_RESTART is not set
    sigemptyset(&sa.sa_mask);
    sigaction(SIGINT, &sa, NULL);

    Config config = readConfigFile("config.yaml");
    printConfigFile(config.input_directory, config.output_directory, config.num_workers, config.check_interval, config.max_attachments);

    int num_workers = config.num_workers;
    int fd;
    int index = 0;

    // Create shared memory for circular buffer
    if ((fd = shm_open("/shm_cb", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) == -1) {
        perror("shm_open");
        exit(1);
    }

    // Set the size of the shared memory
    size_t shm_size = sizeof(CircularBuffer) + num_workers * sizeof(int);
    if (ftruncate(fd, shm_size) == -1) {
        perror("ftruncate");
        exit(1);
    }

    // Map the shared memory to the circular buffer
    CircularBuffer *cb = (CircularBuffer *)mmap(NULL, shm_size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (cb == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }

    // Initialize the circular buffer
    cb->buffer = (int *)(cb + 1);
    cb->in = 0;
    cb->out = 0;
    cb->size = num_workers;

    // Create shared memory for the number of candidates
    if((fd = shm_open("/shm_indicatives", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) == -1) {
        perror("shm_open");
        exit(1);
    }

    // Set the size of the shared memory
    size_t shm_size_indicatives = sizeof(int) * 1000;
    if(ftruncate(fd, shm_size_indicatives) == -1) {
        perror("ftruncate");
        exit(1);
    }

    // Map the shared memory to the number of candidates
    Indicatives *indicatives_shared_data = (int *)mmap(NULL, shm_size_indicatives, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if(indicatives_shared_data == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }

    // Create semaphores
    sem_t *empty, *full, *mutex, *new_candidate, *num_candidates_sem;
    createSemaphore(&empty, "/shm_empty", num_workers);
    createSemaphore(&full, "/shm_full", 0);
    createSemaphore(&mutex, "/shm_ mutex", 1);
    createSemaphore(&new_candidate, "/shm_new_candidate", 1);
    createSemaphore(&num_candidates_sem, "/shm_num_candidates", 0);

    // Fork the process for checking new candidates
    pid_t checker_pid = fork();
    if (checker_pid == 0) {
        // Set the signal handler for the child process
        struct sigaction child_sa;
        child_sa.sa_handler = sigint_handler; // Reutilizamos o handler principal
        child_sa.sa_flags = 0;
        sigemptyset(&child_sa.sa_mask);
        sigaction(SIGINT, &child_sa, NULL);

        // Child process for checking new candidates
        while(1) {
            // Check if Ctrl+C was pressed
            if (ctrlc_pressed) {
                printf("\nSinal Ctrl+C recebido. Terminando o processo de verificação de novos candidatos...\n");
                exit(0);
            }

            // Notify the parent process that there are new candidates
            downSemaphore(&new_candidate);
            while (checkFiles(config.input_directory) == 0) {
                // Check if Ctrl+C was pressed
                if (ctrlc_pressed) {
                    printf("\nSinal Ctrl+C recebido. Terminando o processo de verificação de novos candidatos...\n");
                    exit(0);
                }

                sleep(config.check_interval);
                printf("Checking files... Parent: PID %d\n", getpid());
            }

            int num_candidates = getNumCandidates(config.input_directory);
            int *indicatives = getFirstIntsBeforeHyphen(config.input_directory, num_candidates);

            indicatives_shared_data->num_candidates = num_candidates;

            // Store the number of candidates in shared memory
            for (int i = 0; i < num_candidates; i++) {
                indicatives_shared_data->indicative[i] = indicatives[i];
                upSemaphore(&num_candidates_sem);
            }

            sleep(config.check_interval);
        }
        exit(0);
    }

    pid_t worker_pids[num_workers];
    for (int i = 0; i < num_workers; i++) {
        worker_pids[i] = fork();
        // Child process    
        if (worker_pids[i] == 0) {

            struct sigaction child_sa;
            child_sa.sa_handler = sigint_handler; // Reutilizamos o handler principal
            child_sa.sa_flags = 0;
            sigemptyset(&child_sa.sa_mask);
            sigaction(SIGINT, &child_sa, NULL);

            while (1) {
                if (ctrlc_pressed) {
                    printf("\nSinal Ctrl+C recebido. Terminando o processo filho de worker...\n");
                    exit(0);
                }

                downSemaphore(&full); // Wait for full semaphore
                downSemaphore(&mutex); // Wait for mutex semaphore

                int index = cb->buffer[cb->out];
                cb->out = (cb->out + 1) % cb->size;

                // Process the data of the candidate
                char JobOpeningId[CANDIDATE_INFO_SIZE], email[CANDIDATE_INFO_SIZE], firstName[CANDIDATE_INFO_SIZE], lastName[CANDIDATE_INFO_SIZE], NumPhone[CANDIDATE_INFO_SIZE];
                char filename[100];
                sprintf(filename, "data/%d-candidate-data.txt", index);
                FILE *files = fopen(filename, "r");

                if(files == NULL) {
                    perror("Error opening file");
                    printf("%s %d-candidate-data.txt\n", config.input_directory, index); // Print file path for debugging
                    exit(EXIT_FAILURE);
                }

                fscanf(files, "%s %s %s %s %s", JobOpeningId, email, firstName, lastName, NumPhone);
                fclose(files);

                char candidate_dir[DIR_SIZE];
                char already_dir[DIR_SIZE];
                char data_dir[DIR_SIZE];
                sprintf(candidate_dir, "candidates/%d-%s/", index, email);
                sprintf(already_dir, "data/already_read/%d-%s/", index, email);
                sprintf(data_dir, "data/%d-*", index);
                copyAndMoveFiles(data_dir, candidate_dir, already_dir);
                writeOutputFile(JobOpeningId, email, firstName, lastName, NumPhone, index);

                upSemaphore(&mutex); // Release mutex semaphore
                upSemaphore(&empty); // Release empty semaphore             
            }
            exit(0);
        }
    }

    // Parent process  
    while(1) {
        // Check if Ctrl+C was pressed
        if (ctrlc_pressed) {
            printf("\nSinal Ctrl+C recebido. Terminando o programa...\n");

            munmap(cb, shm_size);
            munmap(indicatives_shared_data, shm_size_indicatives);
            shm_unlink("/shm_cb");
            shm_unlink("/shm_indicatives");

            // Create a report file
            createReportFile();

            // Cleanup semaphores
            removeSemaphores();

            for (int i = 0; i < num_workers; i++) {
                kill(worker_pids[i], SIGINT);
            }
            kill(checker_pid, SIGINT);

            for (int i = 0; i < num_workers; i++) {
                waitpid(worker_pids[i], NULL, 0);
            }
            waitpid(checker_pid, NULL, 0);

            exit(0);
        }

        downSemaphore(&num_candidates_sem); // Wait for num_candidates semaphore
        insertCandidate(cb, indicatives_shared_data, empty, full, mutex, new_candidate, &index);
       
    }

    return 0;
}