package org.example;

import application.Repositories.GraphRepository;
import application.Repositories.Repositories;
import application.domain.Hub;
import application.domain.MapGraph;
import application.utils.Algorithms;

import java.util.*;

import org.jgrapht.alg.interfaces.VertexScoringAlgorithm;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

public class IdealVertices {
    private GraphRepository graphRepository = null;
    private Algorithms algorithms = null;
    public IdealVertices(){
        getGraphRepository();
        algorithms = new Algorithms();
    }

    private void getGraphRepository() {
        if (graphRepository == null) {
            Repositories repositories = Repositories.getInstance();
            graphRepository = repositories.getGraphsRepository();
        }
    }

    public void printIdealVertices() {
        Set<Hub> sortedHubs = sortVertices();
        for(Hub hub: sortedHubs){
            System.out.println(hub);
        }
    }

    public Set<Hub> sortVertices() {
        MapGraph<Hub, Integer> graph = graphRepository.getGraph();
        Comparator<Hub> comparator = new Comparator<>() {
            @Override
            public int compare(Hub h1, Hub h2) {
                int c = graph.inDegree(h2) - graph.inDegree(h1);

                if(c==0){
                    int numVertices = graph.numVertices();
                    boolean[] visited = new boolean[numVertices];
                    int[] path = new int[numVertices];
                    int[] distances = new int[numVertices];

                    algorithms.dijkstra(graph,h1,visited,path,distances);
                    int s1 = distanceSum(distances);

                    algorithms.dijkstra(graph,h2,visited,path,distances);
                    int s2 = distanceSum(distances);
                    c = s1-s2;
                }

                if(c==0){
                    double c1=0,c2=0;

                    /*int numVertices = graph.numVertices();
                    boolean[] visited = new boolean[numVertices];
                    int[] path = new int[numVertices];
                    int[] distances = new int[numVertices];
                    List<Hub> minPath;
                    MapGraph<Hub, Integer> mst = algorithms.prim(graph,h1,visited,path,distances);
                    for(int i=0; i<graph.numVertices(); i++){
                        if(mst.vertex(i) != h1) {
                            minPath = algorithms.findMinPathVertices(mst, mst.vertex(i), path);
                            if (minPath.contains(h1)) c1++;
                            if (minPath.contains(h2)) c2++;
                        }
                    }

                    mst = algorithms.prim(graph,h2,visited,path,distances);
                    for(int i=0; i<graph.numVertices(); i++){
                        if(mst.vertex(i) != h2) {
                            minPath = algorithms.findMinPathVertices(mst, mst.vertex(i), path);
                            if (minPath.contains(h1)) c1++;
                            if (minPath.contains(h2)) c2++;
                        }
                    }*/


                    Graph<Hub, Double> g = new SimpleGraph<>(Double.class);
                    for(Hub hOrig: graph.vertices()){
                        g.addVertex(hOrig);
                    }
                    for (Hub hOrig : graph.vertices()) {
                        for (Hub hAdj : graph.adjVertices(hOrig)) {
                            g.addEdge(hOrig, hAdj, (double)graph.edge(hOrig, hAdj).getWeight());
                        }
                    }
                    VertexScoringAlgorithm<Hub, Double> bc = new BetweennessCentrality<>(g);

                    c1 = bc.getVertexScore(h1);
                    c2 = bc.getVertexScore(h2);
                    c = 1;
                    if(c1-c2 < 0) c = -1;
                }

                return c;
            }
        };
        Set<Hub> sortedHubs = new TreeSet<>(comparator);

        sortedHubs.addAll(graph.vertices());

        return sortedHubs;
    }

    private int distanceSum(int[] distance){
        int sum=0;
        for (int i = 0; i<distance.length; i++){
            sum+=distance[i];
        }
        return sum;
    }
}
