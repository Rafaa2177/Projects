cmake_minimum_required(VERSION 3.27)
project(ProcessadorDeDados C )

set(CMAKE_C_STANDARD 11)

add_executable(ARQCP
        sprint3/functions.h
        sprint3/structs.h
        sprint3/USAC07.c
        sprint3/USAC0809.c
        sprint3/USAC10.c
        sprint3/main.c
        sprint3/USAC02.c
        sprint3/USAC06.c
        sprint3/movingmedian.c
)

target_include_directories(ARQCP PRIVATE ProcessadorDeDados/sprint3)
