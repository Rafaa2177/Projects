package org.example;

import application.Repositories.Repositories;
import application.domain.*;
import application.utils.ImportFiles;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
        String horario = "src/main/resources/horarios_hubs";
        IdealVertices.getMapGraph();

        System.out.println("####################### US01 #######################\n");
        US01 redeDist = US01.getInstance();
        System.out.println(redeDist.getRedeDistribuicao().toString());

        Localidades verticeInicial = redeDist.getRedeDistribuicao().vertices().get(0);
        int autonomy = 100000;

        System.out.println("\n####################### US06 #######################\n");

        new US06().obterTodosPercursosPossiveis(verticeInicial, autonomy, redeDist.getRedeDistribuicao());

        System.out.println("\n####################### US07 #######################\n");


        int descarregar = 15*60;
        int carregar = 30*60;
        int velocidade = (int) ( 100 / 3.6);
        LocalTime currentTime = LocalTime.now();

        US07 us07 = new US07();
        List<StructureOfDelivery> caminhos = us07.maximizarHubs(verticeInicial,currentTime, autonomy, velocidade, carregar, descarregar);
        us07.print(verticeInicial, autonomy, velocidade, carregar, descarregar, caminhos, currentTime);


        System.out.println("\n####################### US08 #######################\n");

        US08 us08 = new US08();
        int nHub = 5;
        int a = 300000;
        int v = 90;
        Localidades origem = redeDist.getRedeDistribuicao().vertices().get(0);
        us08.print(origem, nHub, a, v);


        System.out.println("\n####################### US09 #######################\n");

        new US09().obterClusters(3, new IdealVertices().getMapGraph());

        System.out.println("\n####################### US11 #######################\n");
        ImportFiles.importHours(horario);

    }
}



