package org.example;

import application.Repositories.Repositories;
import application.domain.Edge;
import application.domain.Localidades;
import application.domain.MapGraph;
import application.utils.Algorithms;
import application.utils.AlgorithmsUtility;

import java.util.*;
import java.util.stream.Collectors;

public class US09 {
    MapGraph<Localidades, Integer> graph = IdealVertices.getMapGraph();
    List<int[]> paths = obterTodosPercursosMaisCurtos();

    public Map<Localidades, List<Localidades>> obterClusters(int numClusters, MapGraph<Localidades, Integer> graph) {

        // Verificar se os argumentos são válidos
        if(numClusters < 1) throw new IllegalArgumentException("O número de clusters tem de ser superior a 0");
        if(graph.numVertices() == 0) throw new IllegalArgumentException("Não existem vértices no grafo");
        if(graph.numEdges() == 0) throw new IllegalArgumentException("Não existem edges no grafo");
        if(numClusters > graph.numVertices())
            throw new IllegalArgumentException("O número de clusters tem de ser inferior ao número de localidades");

        // Obter os hubs do grafo
        List<Localidades> hubs = obterHubs(numClusters, graph);

        // Verificar se existe um caminho entre os hubs
        // Se não existir, remover a edge com maior peso do grafo e verificar novamente
        while(existeCaminhoEntreHubs(hubs)){
            Edge<Localidades, Integer> maiorEdge = contarEdges();
            removerMaiorEdge(maiorEdge, graph);
            paths = obterTodosPercursosMaisCurtos();
        }

        // Definir os clusters
        Map<Localidades, List<Localidades>> clusters = definirClusters(numClusters);
        // Verificar se o número de clusters corresponde ao número de hubs
        if (clusters.size() < numClusters) throw new IllegalArgumentException("O número de clusters não corresponde ao número de hubs");

        // Imprimir os clusters
        System.out.println("Clusters: ");
        for (Map.Entry<Localidades, List<Localidades>> entry : clusters.entrySet()) {
            System.out.println("Hub: " + entry.getKey() + "\n Localidades: " + entry.getValue());
        }
        return clusters;
    }

    public Map<Localidades, List<Localidades>> definirClusters(int numClusters){
        Map<Localidades, List<Localidades>> clusters = new HashMap<>();
        // Percorrer todos os hubs
        for (Localidades hub : obterHubs(numClusters, graph)) {
            for (Localidades loc: graph.vertices()) {
                // Verificar se o hub já existe no map
                if(!clusters.containsKey(hub)) {
                    List<Localidades> localidadesDoHub = new ArrayList<>();
                    // Verificar se existe um caminho entre o hub e a localidade
                    if(existeCaminhoEntreHubs(loc, hub)){
                        localidadesDoHub.add(loc);
                    }
                    clusters.put(hub, localidadesDoHub);
                }
                else {
                    if(existeCaminhoEntreHubs(loc, hub)) clusters.get(hub).add(loc);
                }

            }
        }
        return clusters;
    }


    public List<Localidades> obterHubs(int numClusters, MapGraph<Localidades, Integer> graph){
        // Obter os hubs do grafo calculados previamente na US02
        List<Localidades> hubs = new  IdealVertices().sortVertices().stream().
                limit(numClusters).collect(Collectors.toList());

        // Verificar se existem hubs suficientes para o número de clusters pretendidos
        if (hubs.size() < numClusters)
            throw new IllegalArgumentException("Não existem hubs suficientes para o número de clusters pretendidos." +
                    "\nHubs disponíveis: " + hubs.size() + "\nClusters pretendidos: "
                    + numClusters + "\nTente novamente com um número de clusters inferior");
        if( hubs.size() == 0) throw new IllegalArgumentException("Não existem hubs no grafo");

        return hubs;
    }

    // Verificar se existe um caminho entre dois hubs
    public boolean existeCaminhoEntreHubs(List<Localidades> hubs){
        for(int i = 0; i < hubs.size(); i++){
            for(int j = i+1; j < hubs.size(); j++){
                if(existeCaminhoEntreHubs(hubs.get(i), hubs.get(j))) return true;
            }
        }
        return false;
    }

    public boolean existeCaminhoEntreHubs(Localidades hubInicial, Localidades hubFinal){
        int index = graph.key(hubInicial);
        // Obter o caminho mais curto entre os dois hubs
        List<Edge<Localidades,Integer>> pathList = new AlgorithmsUtility().findMinPath(graph, hubFinal, paths.get(index));
        // Verificar se o caminho existe
        return pathList != null;
    }

    public List<int[]> obterTodosPercursosMaisCurtos(){

        Localidades origem ;

        List<int[]> paths = new ArrayList<>();

        // Percorrer todos os vértices do grafo
        for (int i=0 ; i< graph.vertices().size(); i++){

            int[] distances = new int[graph.numVertices()];
            boolean[] visited = new boolean[graph.numVertices()];
            int[] path = new int[graph.numVertices()];

            origem = graph.vertices().get(i);

            // Obter o caminho mais curto entre a origem e todos os outros vértices
            new Algorithms().dijkstra(graph, origem, visited, path, distances);
            // Adicionar o caminho à lista de paths
            paths.add(path);
        }

        return paths;
    }


    public int contadorDeEdgeNoCaminho( List<int[]> paths, Edge<Localidades,Integer> edge){
        int soma = 0;
        // Percorrer todos os caminhos possíveis no grafo
        for (int i = 0; i < paths.size(); i++) {
            for (int j = i; j < paths.size(); j++) {
                // Obter a lista de edges de um caminho
                List<Edge<Localidades,Integer>> pathList = new AlgorithmsUtility().findMinPath(graph, graph.vertices().get(j), paths.get(i));
                // Verificar se a edge existe no caminho
                if (pathList != null && (pathList.contains(edge) || pathList.contains(graph.edge(edge.getVDest(), edge.getVOrig())))) {
                    soma++;
                }
            }
        }
        return soma;
    }

    public Edge<Localidades,Integer> contarEdges(){
        int maxNum = Integer.MIN_VALUE;
        Edge<Localidades,Integer> maxEdge = null;

        // Percorrer todas as edges do grafo
        for (Edge<Localidades, Integer> edge : graph.edges()) {
            if(contadorDeEdgeNoCaminho(paths, edge) > maxNum){
                maxNum = contadorDeEdgeNoCaminho(paths, edge);
                maxEdge = edge;
            }
        }

        // Verificar se existe alguma edge no grafo e/ou no caminho
        if(maxEdge == null) throw new IllegalArgumentException("Não existe nenhuma edge no grafo");
        if(maxNum == 0) throw new IllegalArgumentException("Não existe nenhuma edge no caminho");

        return maxEdge;
    }


    public void removerMaiorEdge(Edge maiorEdge, MapGraph<Localidades, Integer> graph){
        // Verificar se a edge existe no grafo e/ou é válida
        if(maiorEdge == null) throw new IllegalArgumentException("A edge não existe no grafo");
        if(graph.edge((Localidades) maiorEdge.getVOrig(),(Localidades) maiorEdge.getVDest()) == null )
            throw new IllegalArgumentException("A edge não existe no grafo");

        // Remover a edge do grafo e verificar se foi removida
        // A edge é bidirecional, logo é necessário remover em ambos os sentidos
        Localidades origem = (Localidades) maiorEdge.getVOrig();
        Localidades destino = (Localidades) maiorEdge.getVDest();
        graph.removeEdge(origem, destino);
        graph.removeEdge(destino, origem);
        assert graph.edge(origem, destino) == null;
    }


}
