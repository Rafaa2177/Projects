package application.utils;

import application.domain.Edge;
import application.domain.Hub;
import application.domain.MapGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.List;

public class Algorithms {

    public void dijkstra(MapGraph<Hub, Integer> graph, Hub hOrig, boolean[] visited, int[] path, int[] distances){
        initialization(visited,path,distances);
        int weight,i,j;
        distances[graph.key(hOrig)] = 0;
        while(hOrig != null){
            i=graph.key(hOrig);
            visited[i]=true;
            for(Hub hAdj : graph.adjVertices(hOrig)){
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

    public MapGraph<Hub, Integer> prim(MapGraph<Hub, Integer> graph, Hub hOrig , boolean[] visited, int[] path, int[] distances){
        initialization(visited,path,distances);
        int weight,i,j;
        distances[graph.key(hOrig)] = 0;
        while(hOrig != null){
            i=graph.key(hOrig);
            visited[i]=true;
            for(Hub hAdj : graph.adjVertices(hOrig)){
                weight = graph.edge(hOrig, hAdj).getWeight();
                j=graph.key(hAdj);

                if(!visited[j] && distances[j]>weight){
                    distances[j] = weight;
                    path[j] = i;
                }
            }
            hOrig = graph.vertex(findMinVertexIndex(distances, visited));
        }

        MapGraph<Hub, Integer> mst = buildMST(graph,path,distances);

        return mst;
    }

    private MapGraph<Hub, Integer> buildMST(MapGraph<Hub, Integer> graph, int[] path, int[] distances) {
        MapGraph<Hub, Integer> mst = new MapGraph<>(false);

        for(Hub hub : graph.vertices()){
            mst.addVertex(hub);
        }

        for(int i=0; i<graph.numVertices(); i++){
            if(path[i]!=-1) mst.addEdge(graph.vertex(i),graph.vertex(path[i]),distances[i]);
        }
        return mst;
    }

    private int findMinVertexIndex(int[] distances, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && (minVertex == -1 || distances[i] < distances[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    public void dijkstraWithAutonomy(MapGraph<Hub, Integer> graph, Hub hOrig, boolean[] visited, int[] path, int[] distances, int autonomy){
        final int Infinity = Integer.MAX_VALUE;
        int weight,i,j;
        Arrays.fill(distances, Infinity);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        distances[graph.key(hOrig)]=0;

        while(hOrig != null){
            i=graph.key(hOrig);
            visited[i]=true;
            for(Hub hAdj : graph.adjVertices(hOrig)){
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

    public List<Edge<Hub,Integer>> findMinPath(MapGraph<Hub, Integer> graph, Hub hubDest, int[] path){

        List<Edge<Hub,Integer>> newPath = new java.util.ArrayList<>();

        int prev = path[graph.key(hubDest)];
        newPath.add(graph.edge(hubDest, graph.vertex(prev)));

        while(prev != -1 ){
            if(path[prev] != -1){
                Edge<Hub, Integer> edge = graph.edge(graph.vertex(prev), graph.vertex(path[prev]));
                newPath.add(edge);
            }
            prev = path[prev];
        }
        return newPath;
    }

    private void initialization(boolean[] visited, int[] path, int[] distances){
        final int Infinity = Integer.MAX_VALUE;
        Arrays.fill(distances, Infinity);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
    }
}
