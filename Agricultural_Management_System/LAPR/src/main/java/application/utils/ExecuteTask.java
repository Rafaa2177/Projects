package application.utils;

import application.repositories.Repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecuteTask {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public void scheduleTask(String fileContent, int line) throws ParseException {

        System.out.println("\n------------------");
        // Analisa o conteúdo do arquivo
        String[] parts = fileContent.split(",");
        String dateStr = parts[0]; // Data de execução
        String timeStr = parts[4]; // Hora de execução

        Date scheduledTime = dateFormat.parse(dateStr + " " + timeStr);


        // Calcula o tempo restante até a tarefa
        long initialDelay = scheduledTime.getTime() - System.currentTimeMillis();
        if(initialDelay < 0) {
            System.out.println("\nA tarefa " + line + " já deveria ter sido executada!");
            try {
                Repositories.getInstance().getIrrigationProgRepo().irrigationRegister(Integer.parseInt(parts[1]), parts[0], Integer.parseInt(parts[2]), parts[4], tryParseInt(parts[6]));
                ;
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            // Agenda a tarefa
            System.out.println("Tarefa agendada para " + scheduledTime + "!");
            scheduleNextTask(fileContent, initialDelay);
        }
    }

    private void scheduleNextTask(String fileContent, long initialDelay) {
        // Analisa o conteúdo do arquivo
        String[] parts = fileContent.split(",");

        // Agenda a tarefa
        scheduler.schedule(() -> {
            try {
                Repositories.getInstance().getIrrigationProgRepo().irrigationRegister(Integer.parseInt(parts[1]), parts[0], Integer.parseInt(parts[2]), parts[4], tryParseInt(parts[6]));
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, initialDelay, TimeUnit.MINUTES);
    }

    // Método para encerrar o scheduler
    public void shutdownScheduler() {
        scheduler.shutdown();
    }

    public int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
