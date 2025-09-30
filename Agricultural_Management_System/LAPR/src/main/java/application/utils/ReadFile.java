package application.utils;

import application.domain.IrrigationProg;
import application.repositories.Repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFile {
    public static void lerInstrucoes(String filePath) throws IOException{
        FileReader fileReader = new FileReader(filePath);
        LocalDate localDate = LocalDate.now();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String primeiraLinha = bufferedReader.readLine();

        List<String> irrigationTime = Arrays.asList(primeiraLinha.split(","));

        String linha;
        while ((linha = bufferedReader.readLine()) != null){
            String [] dataLine = linha.split(",");

            if (dataLine.length <= 3){
                IrrigationProg irrigationProg = new IrrigationProg(irrigationTime, localDate, dataLine[0], Integer.parseInt(dataLine[1].trim()), dataLine[2].trim() );
                Repositories.getInstance().getIrrigationProgRepo().addIrrigationProg(irrigationProg);
            } else {
                IrrigationProg irrigationProg = new IrrigationProg(irrigationTime, localDate, dataLine[0], Integer.parseInt(dataLine[1].trim()), dataLine[2].trim(), dataLine[3].trim(), Integer.parseInt(dataLine[4].trim()) );
                Repositories.getInstance().getIrrigationProgRepo().addIrrigationProg(irrigationProg);

            }

        }
        fileReader.close();


    }

    public static List<String> lerPlanoDeRega(String fileName) throws IOException {
        List<String> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Ignorar a primeira linha
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        }
        return content;
    }
}
