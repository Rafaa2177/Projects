package org.example;

import application.domain.*;
import application.utils.ImportFiles;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");

        System.out.println("####################### US01 #######################\n");
        US01 redeDist = US01.getInstance();
        System.out.println(redeDist.getRedeDistribuicao().toString());

        System.out.println("\n####################### US02 #######################\n");

        IdealVertices idealVertices = new IdealVertices();
        idealVertices.printIdealVertices();

        System.out.println("\n####################### US03 #######################\n");

        int autonomy = 300000;
        new US03().findMaxMinimumPath(autonomy);

        System.out.println("\n####################### US04 #######################\n");

        Hub verticeInicial = redeDist.getRedeDistribuicao().vertices().get(0);
        new US004_Prim().US004_Prim(verticeInicial);
    }


}
