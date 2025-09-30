package application.utils;

import application.domain.*;

import java.util.*;
import java.util.function.BinaryOperator;

public class AlgorithmsUtility extends Algorithms{


    public static <V, E> Map<V, Integer> betweennessCentrality(Graph<V, E> graph) {
        Map<V, Integer> centrality = new HashMap<>();

        for (V vertex : graph.vertices()) {
            centrality.put(vertex, 0);
        }

        for (V source : graph.vertices()) {
            LinkedList<V> queue = new LinkedList<>();
            queue.add(source);

            Map<V, Integer> numShortestPaths = new HashMap<>();
            numShortestPaths.put(source, 1);

            Map<V, Integer> dependency = new HashMap<>();
            for (V vertex : graph.vertices()) {
                dependency.put(vertex, 0);
            }

            Map<V, Integer> distance = new HashMap<>();
            distance.put(source, 0);

            while (!queue.isEmpty()) {
                V currentVertex = queue.poll();

                for (V neighbor : graph.adjVertices(currentVertex)) {
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, distance.get(currentVertex) + 1);
                        queue.add(neighbor);
                    }

                    if (distance.get(neighbor) == distance.get(currentVertex) + 1) {
                        numShortestPaths.put(neighbor, numShortestPaths.getOrDefault(neighbor, 0) + numShortestPaths.get(currentVertex));
                        dependency.put(neighbor, dependency.get(neighbor) + 1);
                    }
                }
            }

            for (V vertex : graph.vertices()) {
                if (!vertex.equals(source)) {
                    Integer currentCentrality = centrality.get(vertex);
                    Integer currentDependency = dependency.get(vertex);
                    Integer currentNumShortestPaths = numShortestPaths.get(vertex);

                    if (currentCentrality == null) {
                        currentCentrality = 0;
                    }
                    if (currentDependency == null) {
                        currentDependency = 0;
                    }
                    if (currentNumShortestPaths == null) {
                        currentNumShortestPaths = 1; // Avoid division by zero
                    }

                    centrality.put(vertex, currentCentrality + (currentDependency / currentNumShortestPaths));
                }
            }

        }

        return centrality;
    }



    public List<Edge<Localidades,Integer>> findMinPath(MapGraph<Localidades, Integer> graph, Localidades hubDest, int[] path){

        List<Edge<Localidades,Integer>> newPath = new java.util.ArrayList<>();

        int prev = path[graph.key(hubDest)];
        if(prev == -1) return null;
        newPath.add(graph.edge(hubDest, graph.vertex(prev)));

        while(prev != -1 ){
            if(path[prev] != -1){
                Edge<Localidades, Integer> edge = graph.edge(graph.vertex(prev), graph.vertex(path[prev]));
                newPath.add(edge);
            }
            prev = path[prev];
        }
        return newPath;
    }



    public void shortestPathDijkstraWithAutonomy(MapGraph<Localidades, Integer> graph, Localidades hOrig, boolean[] visited, int[] path, int[] distances, int autonomy) {

        final int Infinity = Integer.MAX_VALUE;
        int weight,i,j;
        Arrays.fill(distances, Infinity);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        distances[graph.key(hOrig)]=0;

        while(hOrig != null){
            i=graph.key(hOrig);
            visited[i]=true;
            for(Localidades hAdj : graph.adjVertices(hOrig)){
                weight = graph.edge(hOrig, hAdj).getWeight();
                j=graph.key(hAdj);


                if(!visited[j] && weight <= autonomy && distances[j]>distances[i]+weight){
                    distances[j] = distances[i]+weight;
                    path[j] = i;
                }
            }
            hOrig = graph.vertex(findMinVertexIndex(distances, visited));
        }
    }

    public Queue<BFSVert> BFS(MapGraph<Localidades, Integer> graph, Localidades hOrig, int nMax){
        Queue<BFSVert> queue = new LinkedList<>();
        queue.add(new BFSVert(hOrig,0,new ArrayList<>(Arrays.asList(hOrig))));

        BFSVert vert;
        ArrayList<Localidades> circuit;
        while(queue.peek().getDepth() != nMax || queue.isEmpty()){
            vert = queue.poll();
            for(Localidades hAdj : graph.adjVertices(vert.getCircuit().get(vert.getDepth()))){
                if(!vert.getCircuit().contains(hAdj)){
                    circuit = new ArrayList<>(vert.getCircuit());
                    circuit.add(hAdj);
                    queue.add(new BFSVert(hAdj, vert.getDepth()+1, circuit));
                }
            }
        }

        return queue;
    }




}
