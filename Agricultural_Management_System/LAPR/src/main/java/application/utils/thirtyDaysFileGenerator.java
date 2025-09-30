package application.utils;

import application.domain.IrrigationProg;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class thirtyDaysFileGenerator {

        public static void generateIrrigationProgramFile(String fileName, Map<LocalDate, List<IrrigationProg>> thirtyDaysProgram) {
            clearFile(fileName);
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write("Date,Parcel, Irrigation Duration , Start Time, Stop Time,Regularity, Mix\n");
                for (LocalDate date : thirtyDaysProgram.keySet()) {
                    LocalDate dataInicial = LocalDate.now();
                    List<IrrigationProg> programs = thirtyDaysProgram.get(date);
                    LocalTime start = LocalTime.now();
                    LocalTime end = LocalTime.now();
                    for (IrrigationProg program : programs) {
                        start = LocalTime.parse(program.getStartTime().get(0));
                        end = LocalTime.parse(program.getStartTime().get(1));
                    }
                    for (IrrigationProg program : programs) {
                            writer.write(date + ",");
                            writer.write(program.getParcela() + ",");
                            writer.write(program.getDurationOfIrrigation() + ",");
                            writer.write(start + ",");
                            LocalTime startTime = start.plusMinutes(program.getDurationOfIrrigation());
                            start = start.plusMinutes(program.getDurationOfIrrigation());
                            writer.write(startTime + ",");
                            writer.write(program.getRegularidade() + ",");
                            if (program.checkFertilization(date,dataInicial)){
                                writer.write(program.getMix() + "\n");
                            } else {
                                writer.write(  "- \n");
                            }


                        }

                    for (IrrigationProg program : programs) {
                        writer.write(date + ",");
                       // System.out.println(program.getRecorrencia());
                        // System.out.println(program.getMix());
                        writer.write(program.getParcela() + ",");
                        writer.write(program.getDurationOfIrrigation() + ",");
                        writer.write(end    + ",");
                        LocalTime endTime = end.plusMinutes(program.getDurationOfIrrigation());
                        end = end.plusMinutes(program.getDurationOfIrrigation());
                        writer.write(endTime + ",");
                        writer.write(program.getRegularidade() + ",");
                        if (program.checkFertilization(date,dataInicial)){
                            writer.write(program.getMix() + "\n");
                        }else {
                            writer.write(  "- \n");
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void clearFile(String fileName) {
            try (FileWriter writer = new FileWriter(fileName, false)) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


