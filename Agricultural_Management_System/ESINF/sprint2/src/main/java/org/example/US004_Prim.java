package org.example;

import application.Repositories.Repositories;
import application.domain.Edge;
import application.domain.Hub;
import application.domain.MapGraph;
import application.domain.MapVertex;

import java.util.*;

public class US004_Prim {
    Repositories repositories = Repositories.getInstance();
    public int US004_Prim(Hub verticeInicial) {
        MapGraph<Hub, Integer> graph = Repositories.getInstance().getGraphsRepository().getGraph();


        Set<Hub> verticesVisitados = new HashSet<>();
        List<Hub> caminho = new ArrayList<>();

        verticesVisitados.add(verticeInicial);
        caminho.add(verticeInicial);

        int soma = 0;
        Hub teste = null;
        Hub testeFixo = null;

        List<Hub> lista = new ArrayList<>();
        List<Hub> lista2 = new ArrayList<>();

        while (verticesVisitados.size() < graph.numVertices()) {
            int weight = Integer.MAX_VALUE;
            for (Hub hub : verticesVisitados) {
                for (Hub edge : graph.adjVertices(hub)) {
                    if (!verticesVisitados.contains(edge)) {
                        if (graph.edge(hub, edge).getWeight() < weight) {
                            weight = graph.edge(hub, edge).getWeight();
                            teste = edge;
                            testeFixo = hub;
                        }
                    }
                }
            }
            verticesVisitados.add(teste);
            lista.add(testeFixo);
            lista2.add(teste);

        }
        int distanciaTotal = 0;
        for (int i = 0; i < lista.size(); i++) {
            distanciaTotal += graph.edge(lista.get(i), lista2.get(i)).getWeight();
            System.out.println(lista.get(i).getNumId() + " -> " + lista2.get(i).getNumId() + " : " + graph.edge(lista.get(i), lista2.get(i)).getWeight() + " metros");
        }
        System.out.println("Distancia total: " + distanciaTotal + " metros");
        return distanciaTotal;

    }
}
