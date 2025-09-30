package application.UI;

import application.controller.IrrigationProgramTimeSpecifiedController;
import application.exceptions.IrrigationProgramException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import static application.utils.Utils.readDateConsole;
import static application.utils.Utils.readTimeConsole;

public class IrrigationProgramTimeSpecifiedUI {
    public void run() {
        try {
            LocalDate desiredDate = readDateConsole();
            LocalTime desiredTime = readTimeConsole();
            System.out.println();
            IrrigationProgramTimeSpecifiedController.verifyIrrigationAndRemainTime(desiredDate, desiredTime);
        } catch (IrrigationProgramException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static void dispalyParcelaEREmainTime(Map<String, Integer> remainingTimes) {
        if (remainingTimes.isEmpty()) {
            System.out.println("No irrigation programs are running at the specified time.");
        } else {
            System.out.println("     Irrigation programs currently running      ");
            System.out.println("+----------------------+-----------------------+");
            System.out.println("|      Parcel          |  Remaining Time (min) | ");
            System.out.println("+----------------------+-----------------------+");

            for (Map.Entry<String, Integer> entry : remainingTimes.entrySet()) {
                String parcel = entry.getKey();
                int remainingTime = entry.getValue();
                System.out.format("| %-20s | %-21d |\n", parcel, remainingTime);
            }
            System.out.println("+----------------------+-----------------------+");
            System.out.println();
        }
    }
}

