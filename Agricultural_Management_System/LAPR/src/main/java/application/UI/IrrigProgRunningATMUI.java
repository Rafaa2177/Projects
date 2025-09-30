package application.UI;

import application.controller.IrrigProgRunningATMController;
import application.exceptions.IrrigationProgramException;

import java.util.Map;

public class IrrigProgRunningATMUI {
    public void run() {
        try {
            System.out.println();
            IrrigProgRunningATMController.verifyIfIsIrrigationRunningAndRemainTme();
        } catch (IrrigationProgramException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static void displayParcelaTempo(Map<String, Integer> remainingTimes) {
        if (remainingTimes.isEmpty()) {
            System.out.println("There are no irrigation programs running at the moment");
        } else {
            System.out.println("     Irrigation Programs that are running at the moment      ");
            System.out.println("+----------------------+-----------------------+");
            System.out.println("|      Parcel          |  Remain Time (min) | ");
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
