package application.controller;

import application.UI.IrrigationProgramTimeSpecifiedUI;
import application.domain.IrrigationProg;
import application.utils.IrrigProgrammer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static application.utils.IrrigProgrammer.getIrrigationProgramFromDay;

public class IrrigationProgramTimeSpecifiedController {

    public static void verifyIrrigationAndRemainTime(LocalDate data, LocalTime time) {
        List<IrrigationProg> irrigationProgramForDesiredDay = getIrrigationProgramFromDay(data);
        Map<String, Integer> remainingTimes = new HashMap<>();

        for (IrrigationProg irrigationProgram : irrigationProgramForDesiredDay) {
            LocalDateTime checkDateTime = LocalDateTime.of(data, time);

            Map<String, Integer> timeMap = IrrigProgrammer.timeToStopIrrigationProgram(irrigationProgram, checkDateTime);
            if (timeMap.containsKey(irrigationProgram.getParcela())) {
                int remainTime = timeMap.get(irrigationProgram.getParcela());
                if (IrrigProgrammer.isIrrigationProgramRunningAtSpecificTime(irrigationProgram, checkDateTime)) {
                    remainingTimes.put(irrigationProgram.getParcela(), remainTime);
                }
            }
        }
        IrrigationProgramTimeSpecifiedUI.dispalyParcelaEREmainTime(remainingTimes);
    }
    }

