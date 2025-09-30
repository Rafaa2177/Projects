SRC_DIR = ProcessadorDeDados
S3_DIR = $(SRC_DIR)/sprint3

main: main.o USAC02.o USAC06.o USAC07.o USAC0809.o USAC10.o
	gcc main.o USAC02.o USAC06.o USAC07.o USAC0809.o USAC10.o -z noexecstack -o main
main.o: $(S3_DIR)/main.c $(S3_DIR)/asm.h $(S3_DIR)/functions.h $(S3_DIR)/structs.h
	gcc -g -Wall -Wextra -c $(S3_DIR)/main.c -o main.o
USAC02.o : $(S3_DIR)/USAC02.s
	gcc -g -Wall -Wextra -c $(S3_DIR)/USAC02.s -o USAC02.o
USAC06.o : $(S3_DIR)/USAC06.s
	gcc -g -Wall -Wextra -c $(S3_DIR)/USAC06.s -o USAC06.o
USAC07.o : $(S3_DIR)/USAC07.c $(S3_DIR)/functions.h $(S3_DIR)/structs.h
	gcc -g -Wall -Wextra -c $(S3_DIR)/USAC07.c -o $(S3_DIR)/USAC07.o
USAC0809.o : $(S3_DIR)/USAC0809.c $(S3_DIR)/functions.h $(S3_DIR)/structs.h
	gcc -g -Wall -Wextra -fanalyzer -c $(S3_DIR)/USAC0809.c -o USAC07.o
USAC10.o : $(S3_DIR)/USAC10.c $(S3_DIR)/functions.h $(S3_DIR)/structs.h
	gcc -g -Wall -Wextra -c $(S3_DIR)/USAC10.c -o USAC07.o
run : main
	./main ./ProcessadorDeDados/etc/ttyS0 config intermedio 50
clean:
	rm *.o main

