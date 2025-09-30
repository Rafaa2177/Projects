package application.utils;

import application.domain.Localidades;
import application.domain.MapGraph;

import java.util.*;

public class Algorithms {

    public void dijkstra(MapGraph<Localidades, Integer> graph, Localidades hOrig, boolean[] visited, int[] path, int[] distances){
        initialization(visited,path,distances);
        int weight,i,j;
        distances[graph.key(hOrig)] = 0;
        while(hOrig != null){
            i=graph.key(hOrig);
            visited[i]=true;
            for(Localidades hAdj : graph.adjVertices(hOrig)){
                weight = graph.edge(hOrig, hAdj).getWeight();
                j=graph.key(hAdj);

                if(!visited[j] && distances[j]>distances[i]+weight){
                    distances[j] = distances[i]+weight;
                    path[j] = i;
                }
            }
            hOrig = graph.vertex(findMinVertexIndex(distances, visited));
        }
    }


    static int findMinVertexIndex(int[] distances, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && (minVertex == -1 || distances[i] < distances[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    public MapGraph<Localidades, Integer> prim(MapGraph<Localidades, Integer> graph, Localidades hOrig , boolean[] visited, int[] path, int[] distances){
        initialization(visited,path,distances);
        int weight,i,j;
        distances[graph.key(hOrig)] = 0;
        while(hOrig != null){
            i=graph.key(hOrig);
            visited[i]=true;
            for(Localidades hAdj : graph.adjVertices(hOrig)){
                weight = graph.edge(hOrig, hAdj).getWeight();
                j=graph.key(hAdj);

                if(!visited[j] && distances[j]>weight){
                    distances[j] = weight;
                    path[j] = i;
                }
            }
            hOrig = graph.vertex(findMinVertexIndex(distances, visited));
        }

        MapGraph<Localidades, Integer> mst = buildMST(graph,path,distances);

        return mst;
    }
    private MapGraph<Localidades, Integer> buildMST(MapGraph<Localidades, Integer> graph, int[] path, int[] distances) {
        MapGraph<Localidades, Integer> mst = new MapGraph<>(false);

        for(Localidades hub : graph.vertices()){
            mst.addVertex(hub);
        }

        for(int i=0; i<graph.numVertices(); i++){
            if(path[i]!=-1) mst.addEdge(graph.vertex(i),graph.vertex(path[i]),distances[i]);
        }
        return mst;
    }


    private void initialization(boolean[] visited, int[] path, int[] distances){
        final int Infinity = Integer.MAX_VALUE;
        Arrays.fill(distances, Infinity);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
    }

}
