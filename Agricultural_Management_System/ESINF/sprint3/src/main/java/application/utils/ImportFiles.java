package application.utils;

import application.Repositories.Repositories;
import application.domain.Localidades;
import org.example.US01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

public class ImportFiles {


    public static void importDist(String Distancias) throws IOException{
        US01 redeDist = US01.getInstance();

        BufferedReader reader = new BufferedReader(new FileReader(Distancias));
        String currentLine;
        reader.readLine();
        String[] line;

        while ((currentLine = reader.readLine()) != null){
            line = currentLine.split(",");
            redeDist.addRoute(new Localidades(line[0]), new Localidades(line[1]), Integer.parseInt(line[2]));
        }
        Repositories.getInstance().getGraphsRepository().setGraph(redeDist.getRedeDistribuicao());
    }

    public static void importLocal(String locais) throws IOException{
        US01 redeDist = US01.getInstance();

        BufferedReader reader = new BufferedReader(new FileReader(locais));
        String currentLine;
        reader.readLine();
        String[] line;

        while ((currentLine = reader.readLine()) != null){
            line = currentLine.split(",");
            redeDist.addHub(line[0], Double.parseDouble(line[1]), Double.parseDouble(line[2]));
        }
        Repositories.getInstance().getGraphsRepository().setGraph(redeDist.getRedeDistribuicao());
    }

    public static void importHours(String horarios) throws IOException {
        US01 rede = US01.getInstance();

        BufferedReader reader = new BufferedReader(new FileReader(horarios));
        String currentLine;
        String[] line;

        while ((currentLine = reader.readLine()) != null) {
            line = currentLine.split(",");
            if (line.length >= 3) {
                String hubId = line[0];
                String openingTime = line[1];
                String closingTime = line[2];

                // Verificar se o hub já existe
                Localidades localidades = rede.getHubById(hubId);


                if (localidades != null) {
                    // Hub já existe, redefine os horários
                    localidades.getHorario().setOpenTime(LocalTime.parse(openingTime));
                    localidades.getHorario().setCloseTime(LocalTime.parse(closingTime));

                    System.out.println("Hub ID: " + localidades.getNumId() +
                            ", Opening Time: " + openingTime +
                            ", Closing Time: " + closingTime);
                } else {
                    // Hub não existe, emite mensagem de erro ou cria um novo hub
                    System.out.println("Erro: Hub " + hubId + " não encontrado.");
                }
            }else {
                System.out.println("Erro: Formato inválido na linha - " + currentLine);
            }
        }
    }
}




