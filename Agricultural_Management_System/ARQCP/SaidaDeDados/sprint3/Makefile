saidadedados: main.o
	gcc main.o -z noexecstack -o saidadedados
main.o: main.c asm.h
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o
run : saidadedados
	./saidadedados
clean:
	rm *.o saidadedados

