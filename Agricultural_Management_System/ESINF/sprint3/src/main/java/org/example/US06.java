package org.example;

import application.Repositories.Repositories;
import application.domain.Edge;
import application.domain.Localidades;
import application.domain.MapGraph;
import application.domain.Percurso;
import application.utils.Algorithms;
import application.utils.AlgorithmsUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US06 {

    public List<Percurso> obterTodosPercursosPossiveis(Localidades origem, int autonomy, MapGraph<Localidades,Integer> graph){

        //Verificar se os argumentos são válidos
        if(origem == null || autonomy <= 0)
            throw new IllegalArgumentException("Argumentos inválidos");

        if( graph == null)
            throw new IllegalArgumentException("Grafo não inicializado");

        if(!graph.vertices().contains(origem))
            throw new IllegalArgumentException("Origem não existe no grafo");


        List<Percurso> percursos = new ArrayList<>();
        List<Edge<Localidades,Integer>> pathList ;

        //Inicializar os arrays
        int[] distances = new int[graph.numVertices()];
        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];

        //Obter o caminho mais curto para todos os vertices desde a origem
        new Algorithms().dijkstra(graph, origem, visited, path, distances);
        Localidades destino;
        Percurso percurso;

        //Obter os percursos dos caminhos considerando a autonomia
        for(int i = 0; i < distances.length; i++){
            destino = graph.vertices().get(i);

            // Ignorar a origem e destinos iguais
            if(origem.equals(destino)) continue;

            pathList = new AlgorithmsUtility().findMinPath(graph, destino,path);

            // Verificar se o caminho é válido pelos critérios de autonomia
            if (pathList != null && distances[i] <= autonomy ){
                percurso = new Percurso(origem,destino, pathList, distances[i], autonomy);
                percursos.add(percurso);
            }
        }

        // Verificar se existem percursos possíveis
        if(percursos.isEmpty())
            throw new IllegalArgumentException("Não existem percursos possíveis para a autonomia indicada");

        // Print dos percursos
        for (Percurso p: percursos) {
            System.out.println(p);
        }

        return percursos;
    }
}
