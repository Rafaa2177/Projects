package application.UI;

import application.domain.IrrigationProg;
import application.repositories.Repositories;
import application.utils.thirtyDaysFileGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static application.utils.IrrigProgrammer.getTrintaDias;

public class thirtyDaysUI {
    public void run() {
        String filePath = "src/main/java/application/file.csv";
        Map<LocalDate, List<IrrigationProg>> thirtyDaysProgram = getTrintaDias();
        thirtyDaysFileGenerator.generateIrrigationProgramFile(filePath, thirtyDaysProgram);
        Repositories.getInstance().getIrrigationProgRepo().consumirPlanoDeRega();
    }
}
