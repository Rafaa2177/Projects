#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <dirent.h>
#include "functions.h"

void test_readConfigFile() {
    printf("Testing readConfigFile...\n");
    Config config = readConfigFile("test_config.yaml");

    assert(strcmp(config.input_directory, "/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/data") == 0);
    assert(strcmp(config.output_directory, "/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/candidates") == 0);
    assert(config.num_workers == 3);
    assert(config.check_interval == 5);
    assert(config.max_attachments == 15);

    printf("readConfigFile test passed!\n");
}

void test_checkFiles() {
    printf("Testing checkFiles...\n");
    int fileCount = checkFiles("/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/data");
    assert(fileCount == 48);

    printf("checkFiles test passed!\n");
}

void test_getNumCandidates() {
    printf("Testing getNumCandidates...\n");
    int candidateCount = getNumCandidates("/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/data");
    assert(candidateCount == 10);

    printf("getNumCandidates test passed!\n");
}

void test_getFirstIntsBeforeHyphen() {
    printf("Testing getFirstIntsBeforeHyphen...\n");
    int *indicatives = getFirstIntsBeforeHyphen("/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/data", 2);
    assert(indicatives[0] == 1);
    assert(indicatives[1] == 10);
    free(indicatives);

    printf("getFirstIntsBeforeHyphen test passed!\n");
}

void test_copyAndMoveFiles() {
    printf("Testing copyAndMoveFiles...\n");
    copyAndMoveFiles("/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/data/*.txt", "/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/candidates", "/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/already_read");

    int fileCount = checkFiles("/media/sf_sem4pi-23-24-2dc4/jobs4u.applicationUtilities/ApplicationFileBot/candidates");

    assert(fileCount == 48);

    printf("copyAndMoveFiles test passed!\n");
}

int main() {
    test_readConfigFile();
    test_checkFiles();
    test_getNumCandidates();
    test_getFirstIntsBeforeHyphen();
    test_copyAndMoveFiles();

    printf("All tests passed successfully!\n");
    return 0;
}
