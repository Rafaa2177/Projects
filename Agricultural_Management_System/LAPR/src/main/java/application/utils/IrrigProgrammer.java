package application.utils;

import application.domain.IrrigationProg;
import application.enums.Regularidade;
import application.exceptions.IrrigationProgramException;
import application.repositories.Repositories;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class IrrigProgrammer {

    public static Map<LocalDate, List<IrrigationProg>> getTrintaDias(){
        List<IrrigationProg> irrigationProg = Repositories.getInstance().getIrrigationProgRepo().getIrrigationProg();
        Map<LocalDate, List<IrrigationProg>> trintaDiasPrograma = new TreeMap<>();
        LocalDate startDate = irrigationProg.get(0).getStartData();

        for (int i=0; i<irrigationProg.size(); i++){
            for (int j=0; j<30; j++){
                if (irrigationProg.get(i).getRegularidade().equals(Regularidade.TODOS_DIAS.getCodigo())){
                    handleEvery(j, startDate, irrigationProg, trintaDiasPrograma, i);

                }
                if (irrigationProg.get(i).getRegularidade().equals(Regularidade.IMPARES.getCodigo())){
                    impares(j, startDate, irrigationProg, trintaDiasPrograma, i);
                }
                if (irrigationProg.get(i).getRegularidade().equals(Regularidade.PARES.getCodigo())){
                    pares(j, startDate, irrigationProg, trintaDiasPrograma, i);
                }
                if (irrigationProg.get(i).getRegularidade().equals(Regularidade.CADA_TRES.getCodigo())){
                    cadaTresDias(j, startDate, irrigationProg, trintaDiasPrograma, i);
                }
            }
        }
        return trintaDiasPrograma;
    }

    public static void handleEvery(int j, LocalDate startDate, List<IrrigationProg> irrigationProg, Map<LocalDate, List<IrrigationProg> > trintaDias, int i){
        LocalDate date = startDate.plusDays(j);
        createOrUpdateIrrigationProgram(date,irrigationProg, trintaDias,i);
    }

    public static void impares(int j, LocalDate startDate, List<IrrigationProg> irrigationProg, Map<LocalDate, List<IrrigationProg>> trintaDias, int i){
        if (startDate.getDayOfMonth()%2 == 0){
            if (j % 2 != 0){
                LocalDate date = startDate.plusDays(j);
                createOrUpdateIrrigationProgram(date,irrigationProg, trintaDias,i);
            }
        } else {
            if (j % 2 != 0){
                LocalDate date = startDate.plusDays(j-1);
                createOrUpdateIrrigationProgram(date,irrigationProg, trintaDias,i);
            }
        }
    }

    public static void pares(int j, LocalDate startDate, List<IrrigationProg> irrigationProg, Map<LocalDate, List<IrrigationProg>> trintaDias, int i){
        if (startDate.getDayOfMonth()%2 == 0){
            if (j % 2 != 0){
                LocalDate date = startDate.plusDays(j);
                createOrUpdateIrrigationProgram(date,irrigationProg, trintaDias,i);
            }
        } else {
            if (j % 2 != 0){
                LocalDate date = startDate.plusDays(j);
                createOrUpdateIrrigationProgram(date,irrigationProg, trintaDias,i);
            }
        }
    }

    public static void cadaTresDias(int j, LocalDate startDate, List<IrrigationProg> irrigationProg, Map<LocalDate, List<IrrigationProg>> trintaDias, int i){
        if (j % 3 == 0){
            LocalDate date = startDate.plusDays(j);
            createOrUpdateIrrigationProgram(date,irrigationProg, trintaDias,i);
        }
    }

    private static void createOrUpdateIrrigationProgram(LocalDate date, List<IrrigationProg> irrigationProg, Map<LocalDate, List<IrrigationProg>> trintaDias, int i){
        List<IrrigationProg> irrigationProgs = trintaDias.get(date);

        if (irrigationProgs == null) {
            irrigationProgs = new ArrayList<>();
            trintaDias.put(date, irrigationProgs);
        }
        irrigationProgs.add(new IrrigationProg(irrigationProg.get(i).getStartTime(), date, irrigationProg.get(i).getParcela(), irrigationProg.get(i).getDurationOfIrrigation(), irrigationProg.get(i).getRegularidade(), irrigationProg.get(i).getMix(),irrigationProg.get(i).getRecorrencia()));
    }



    public static boolean isIrrigationProgramRunning(IrrigationProg irrigationProg){
        boolean isRunning = false;
        LocalDateTime now = LocalDateTime.now();
        List<String> startTimes = irrigationProg.getStartTime();
        LocalDate startDate = irrigationProg.getStartData();
        List<String> invalidTimes = new ArrayList<>();

        for (String time : startTimes){
            if (time.length() == 4){
                time = "0" + time;
            }
            LocalTime startTime;
            try {
                startTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e){
                invalidTimes.add(time);
                continue;
            }
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime stopTime = startDateTime.plus(irrigationProg.getIrrigationTemporalAmount());

            if (now.isAfter(startDateTime) && now.isBefore(stopTime)) {
                isRunning = true;
                break;
            }
        }
        if (!invalidTimes.isEmpty()){
            System.out.println("Invalid time formats read from file .txt :" + invalidTimes);
        }
        return isRunning;
    }
    public static List<IrrigationProg> getIrrigationProgramFromDay(LocalDate date){
        Map<LocalDate, List<IrrigationProg>> trintaDias = getTrintaDias();
        List<IrrigationProg> irrigationProg = trintaDias.get(date);
        if (irrigationProg == null || irrigationProg.isEmpty()){
            throw new IrrigationProgramException(date);
        }
        return new ArrayList<>(irrigationProg);
    }

    public static boolean isIrrigationProgramRunningAtSpecificTime(IrrigationProg irrigationProgram, LocalDateTime checkTime) {
        boolean isRunning = false;
        List<String> startTimes = irrigationProgram.getStartTime();
        LocalDate startDate = irrigationProgram.getStartData();
        List<String> invalidTimes = new ArrayList<>();

        for (String time : startTimes) {
            if (time.length() == 4) {
                time = "0" + time;
            }
            LocalTime startTime;
            try {
                startTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                invalidTimes.add(time);
                continue;
            }
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime stopTime = startDateTime.plus(irrigationProgram.getIrrigationTemporalAmount());

            if (checkTime.isAfter(startDateTime) && checkTime.isBefore(stopTime)) {
                isRunning = true;
                break;
            }
        }
        if (!invalidTimes.isEmpty()) {
            System.out.println("Invalid time formats read from txt file: " + invalidTimes);
        }
        return isRunning;
    }

    public static Map<String,Integer> timeToStopIrrigationProgram(IrrigationProg irrigationProg, LocalDateTime verifyTime){
        List<String> startTimes = irrigationProg.getStartTime();
        LocalDate startDate = irrigationProg.getStartData();
        Map<String,Integer> remainTimeOfParcela = new HashMap<>();
        int remainMinutes;

        for (String time : startTimes){
            if (time.length() == 4){
                time = "0" + time;
            }
            LocalTime startTime;
            try {
                startTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm") );
            } catch (DateTimeParseException e){
                continue;
            }
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime stopTime = startDateTime.plus(irrigationProg.getIrrigationTemporalAmount());

            if (verifyTime.isAfter(startDateTime) && verifyTime.isBefore(stopTime)){
                Duration remainDuration = Duration.between(verifyTime, stopTime);
                remainMinutes = (int) remainDuration.toMinutes();
                remainTimeOfParcela.put(irrigationProg.getParcela(), remainMinutes);
                break;

            }
        }
        return remainTimeOfParcela;
    }

}
