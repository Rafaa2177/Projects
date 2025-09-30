package org.example;

import application.Repositories.Repositories;
import application.domain.Edge;
import application.domain.Hub;
import application.domain.MapGraph;
import application.domain.Percurso;
import application.utils.Algorithms;
import org.example.Main;

import java.util.ArrayList;
import java.util.List;

public class US03 {

    public Percurso findMaxMinimumPath(int autonomy){

        MapGraph<Hub, Integer> graph = Repositories.getInstance().getGraphsRepository().getGraph();

        int maxDistance = Integer.MIN_VALUE;
        List<Edge<Hub,Integer>> pathList = new ArrayList<>();

        Hub origem = null;
        Hub origemFinal = null;
        Hub destino = null;

        for (int i=0 ; i< graph.vertices().size(); i++){

            int[] distances = new int[graph.numVertices()];
            boolean[] visited = new boolean[graph.numVertices()];
            int[] path = new int[graph.numVertices()];

            origem = graph.vertices().get(i);

            new Algorithms().dijkstraWithAutonomy(graph, origem, visited, path, distances, autonomy);

            int index = findMaxWeight(distances);

            if(distances[index] > maxDistance){

                maxDistance = distances[index];
                origemFinal = origem;
                destino = graph.vertices().get(index);
                pathList = new Algorithms().findMinPath(graph, destino, path);

            }
        }

        if (pathList.get(0) == null){
            System.out.println("Autonomia: " + autonomy + " metros");
            System.out.println("Não existe nenhum caminho possível com a autonomia dada.");
            return null;
        }

        List<Hub> chargingHubs = findWhereCharges(autonomy, pathList);

        Percurso percurso = new Percurso(destino,origemFinal, pathList, chargingHubs, maxDistance, autonomy);
        System.out.println(percurso);

        return percurso;
    }

    public List<Hub> findWhereCharges(int autonomy, List<Edge<Hub,Integer>> hubs){

        int tempAutonomy = autonomy;
        List<Hub> chargingHubs = new ArrayList<>();

        for (Edge<Hub, Integer> edge : hubs) {
           if(edge.getWeight() > tempAutonomy){
                chargingHubs.add(edge.getVOrig());
                tempAutonomy = autonomy;
             }
            tempAutonomy = tempAutonomy - edge.getWeight();
        }

        return chargingHubs;
    }

    private int findMaxWeight(int[] distancias){
        int max = 0;
        int index = -1;
        for (int i = 0; i < distancias.length; i++) {
            if (distancias[i] > max) {
                max = distancias[i];
                index = i;
            }
        }
        return index;
    }
}
