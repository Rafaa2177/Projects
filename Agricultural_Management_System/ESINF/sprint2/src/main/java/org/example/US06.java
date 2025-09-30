package org.example;

import application.Repositories.Repositories;
import application.domain.Edge;
import application.domain.Hub;
import application.domain.MapGraph;
import application.domain.Percurso;
import application.utils.Algorithms;

import java.util.ArrayList;
import java.util.List;

public class US06 {

    public List<Percurso> obterTodosPercursosPossiveis(Hub origem, int autonomy){

        List<Percurso> percursos = new ArrayList<>();

        MapGraph<Hub, Integer> graph = Repositories.getInstance().getGraphsRepository().getGraph();

        int maxDistance = Integer.MIN_VALUE;
        List<Edge<Hub,Integer>> pathList = new ArrayList<>();

        int[] distances = new int[graph.numVertices()];
        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];

        new Algorithms().dijkstra(graph, origem, visited, path, distances);
        Hub destino = null;
        Percurso percurso = null;

        for(int i = 0; i < pathList.size(); i++){
            destino = graph.vertices().get(i);
            pathList = new Algorithms().findMinPath(graph, destino, path);
            percurso = new Percurso(origem,destino, pathList, maxDistance, autonomy);
            percursos.add(percurso);
        }

        return percursos;
    }
}
