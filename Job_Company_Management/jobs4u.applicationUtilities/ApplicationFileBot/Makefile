prog: main.o functions.o
	gcc -g -o prog main.o functions.o -z noexecstack

main.o: main.c
	gcc -c -Wall -Wextra -fanalyzer -o main.o main.c
	
functions.o: functions.c
	gcc -c -Wall -Wextra -fanalyzer -o functions.o functions.c

clean:
	rm -f *.o prog 

test_functions.o: test_functions.c functions.o
	gcc -c -Wall -Wextra -o test_functions.o test_functions.c

test: test_functions.o functions.o
	clear
	gcc -g -o test test_functions.o functions.o -lc -z noexecstack
	./test

reset:
	rm -rf data
	rm -rf candidates/*
	rm -rf reports/*
	cp -r backup data
	rm ../../../../dev/shm/*

run: prog
	./prog