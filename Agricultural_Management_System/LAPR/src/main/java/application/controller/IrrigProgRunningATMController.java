package application.controller;

import application.UI.IrrigProgRunningATMUI;
import application.domain.IrrigationProg;
import application.utils.IrrigProgrammer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static application.utils.IrrigProgrammer.getIrrigationProgramFromDay;

public class IrrigProgRunningATMController {
    public static void verifyIfIsIrrigationRunningAndRemainTme() {
        List<IrrigationProg> irrigationProgramForDesiredDay = getIrrigationProgramFromDay(LocalDate.now());
        Map<String, Integer> remainingTimes = new HashMap<>();

        for (IrrigationProg irrigationProgram : irrigationProgramForDesiredDay) {
            LocalDateTime checkDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());

            Map<String, Integer> timeMap = IrrigProgrammer.timeToStopIrrigationProgram(irrigationProgram, checkDateTime);
            if (timeMap.containsKey(irrigationProgram.getParcela())) {
                int remainingTime = timeMap.get(irrigationProgram.getParcela());
                if (IrrigProgrammer.isIrrigationProgramRunning(irrigationProgram)) {
                    remainingTimes.put(irrigationProgram.getParcela(), remainingTime);
                }
            }
        }
        IrrigProgRunningATMUI.displayParcelaTempo(remainingTimes);
    }
}
