package application.utils;

import application.Repositories.Repositories;
import application.domain.Hub;
import application.domain.US01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportFiles {


    public static void importDist(String Distancias) throws IOException{
        US01 redeDist = US01.getInstance();

        BufferedReader reader = new BufferedReader(new FileReader(Distancias));
        String currentLine;
        reader.readLine();
        String[] line;

        while ((currentLine = reader.readLine()) != null){
            line = currentLine.split(",");
            redeDist.addRoute(new Hub(line[0]), new Hub(line[1]), Integer.parseInt(line[2]));
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


}

