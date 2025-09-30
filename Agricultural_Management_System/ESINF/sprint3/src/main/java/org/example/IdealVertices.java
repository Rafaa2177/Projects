package org.example;

import application.Repositories.GraphRepository;
import application.Repositories.Repositories;
import application.domain.Horario;
import application.domain.Localidades;
import application.domain.MapGraph;
import application.utils.Algorithms;
import application.utils.AlgorithmsUtility;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexScoringAlgorithm;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.graph.SimpleGraph;

import java.time.LocalTime;
import java.util.*;

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
        Set<Localidades> sortedHubs = sortVertices();
      for(Localidades hub: sortedHubs){
           System.out.println(hub);
        }
    }

    public Set<Localidades> sortVertices() {
        MapGraph<Localidades, Integer> graph = graphRepository.getGraph();
        Comparator<Localidades> comparator = new Comparator<>() {
           @Override
           public int compare(Localidades h1, Localidades h2) {
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

//                    /*int numVertices = graph.numVertices();
//                    boolean[] visited = new boolean[numVertices];
//                    int[] path = new int[numVertices];
//                    int[] distances = new int[numVertices];
//                    List<Hub> minPath;
//                    MapGraph<Hub, Integer> mst = algorithms.prim(graph,h1,visited,path,distances);
//                    for(int i=0; i<graph.numVertices(); i++){
//                        if(mst.vertex(i) != h1) {
//                            minPath = algorithms.findMinPathVertices(mst, mst.vertex(i), path);
//                            if (minPath.contains(h1)) c1++;
//                            if (minPath.contains(h2)) c2++;
//                        }
//                    }
//
//                    mst = algorithms.prim(graph,h2,visited,path,distances);
//                    for(int i=0; i<graph.numVertices(); i++){
//                        if(mst.vertex(i) != h2) {
//                            minPath = algorithms.findMinPathVertices(mst, mst.vertex(i), path);
//                            if (minPath.contains(h1)) c1++;
//                            if (minPath.contains(h2)) c2++;
//                        }
//                    }*/
//
//
                   Graph<Localidades, Double> g = new SimpleGraph<>(Double.class);
                    for(Localidades hOrig: graph.vertices()){
                        g.addVertex(hOrig);
                    }
                   for (Localidades hOrig : graph.vertices()) {
                        for (Localidades hAdj : graph.adjVertices(hOrig)) {
                            g.addEdge(hOrig, hAdj, (double)graph.edge(hOrig, hAdj).getWeight());
                       }
                    }
                    VertexScoringAlgorithm<Localidades, Double> bc = new BetweennessCentrality<>(g);

                   c1 = bc.getVertexScore(h1);
                    c2 = bc.getVertexScore(h2);
                    c = 1;
                    if(c1-c2 < 0) c = -1;
                }

                return c;
           }
       };
        Set<Localidades> sortedHubs = new TreeSet<>(comparator);

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

    private static void escolherHorario(MapGraph<Localidades, Integer> graph) {
        for (Localidades localidades : graph.vertices()) {
            String numId = localidades.getNumId();

            // Extrair o número da localidade (ignorando o prefixo "CT")
            int numeroLocalidade = Integer.parseInt(numId.substring(2));

            // Lógica para definir horários apenas se for um hub

                LocalTime horarioAbertura;
                LocalTime horarioFecho;

                // Aplicar lógica para definir os horários com base no número da localidade
                if (numeroLocalidade >= 1 && numeroLocalidade <= 105) {
                    horarioAbertura = LocalTime.of(1, 0);
                    horarioFecho = LocalTime.of(23, 59);
                } else if (numeroLocalidade >= 106 && numeroLocalidade <= 215) {
                    horarioAbertura = LocalTime.of(2, 0);
                    horarioFecho = LocalTime.of(23, 59);
                } else if (numeroLocalidade >= 216 && numeroLocalidade <= 324) {
                    horarioAbertura = LocalTime.of(1, 0);
                    horarioFecho = LocalTime.of(23, 59);
                } else {
                    // Adicione lógica adicional conforme necessário para outros casos
                    horarioAbertura = LocalTime.of(0, 0);
                    horarioFecho = LocalTime.of(0, 0);
                }

                // Criar Horario com os parâmetros fornecidos
                Horario horario= new Horario(horarioAbertura, horarioFecho);
                localidades.setHorario(horario);
            }
        }


    private static final MapGraph<Localidades, Integer> graph = US01.getInstance().getRedeDistribuicao();
    private static int n;


    public static Map<Localidades, Integer> calculateInfluence(MapGraph<Localidades, Integer> graph) {
        Map<Localidades, Integer> influence = new HashMap<>();
        for (Localidades vertex : graph.vertices()) {
            influence.put(vertex, graph.outDegree(vertex));
        }

        return influence;
    }

    public static Map<Localidades, Integer> calculateProximity(MapGraph<Localidades, Integer> graph) {
        Map<Localidades, Integer> proximity = new HashMap<>();

        for (Localidades vertex : graph.vertices()) {
            Integer proximityValue = calculateVertexProximity(graph, vertex);
            proximity.put(vertex, proximityValue);
        }

        return proximity;
    }

    private static Integer calculateVertexProximity(MapGraph<Localidades, Integer> graph, Localidades vertex) {
        ArrayList<Integer> dists = new ArrayList<>();

        int proximitySum = 0;
        for (Integer dist : dists) {
            if (dist != null) {
                proximitySum += dist;
            }
        }

        return proximitySum;
    }

    public static Map<Localidades, Integer> calculateCentrality(MapGraph<Localidades, Integer> graph) {
        Map<Localidades, Integer> centrality = AlgorithmsUtility.betweennessCentrality(graph);

        return centrality;
    }

    public Map<Localidades, Integer> getTopNHubsSeparate(Map<Localidades, Integer> map, Integer n, boolean isProximity) {
        Map<Localidades, Integer> topNHubsMap = new LinkedHashMap<>();

        // Sort the map entries based on values
        List<Map.Entry<Localidades, Integer>> sortedEntries = new ArrayList<>(map.entrySet());

        if (isProximity) {
            sortedEntries.sort(Map.Entry.comparingByValue());
        } else {
            sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        }

        int count = 0;
        for (Map.Entry<Localidades, Integer> entry : sortedEntries) {
            if (count < n) {
                topNHubsMap.put(entry.getKey(), entry.getValue());
                count++;
            } else {
                break;
            }
        }

        return topNHubsMap;
    }

    public static Map<Localidades, List<Integer>> getTopNMap(Map<Localidades, List<Integer>> map, Integer n) {
        List<Map.Entry<Localidades, List<Integer>>> entries = new ArrayList<>(map.entrySet());
        entries.sort((entry1, entry2) -> {
            List<Integer> values1 = entry1.getValue();
            List<Integer> values2 = entry2.getValue();

            int compareCentrality = Integer.compare(values2.get(0), values1.get(0));
            if (compareCentrality != 0) {
                return compareCentrality;
            }

            int compareInfluence = Integer.compare(values2.get(1), values1.get(1));
            if (compareInfluence != 0) {
                return compareInfluence;
            }

            return Integer.compare(values1.get(2), values2.get(2));
        });

        Map<Localidades, List<Integer>> sortedFinalMap = new LinkedHashMap<>();
        for (Map.Entry<Localidades, List<Integer>> entry : entries) {
            sortedFinalMap.put(entry.getKey(), entry.getValue());
        }

        return getTopNHubs(sortedFinalMap, n);
    }

    public static Map<Localidades, List<Integer>> getTopNHubs(Map<Localidades, List<Integer>> map, Integer n) {
        Map<Localidades, List<Integer>> topNHubsMap = new LinkedHashMap<>();

        int count = 0;
        for (Map.Entry<Localidades, List<Integer>> entry : map.entrySet()) {
            if (count < n) {
                topNHubsMap.put(entry.getKey(), entry.getValue());
                count++;
            } else {
                break;
            }
        }

        return topNHubsMap;
    }

    public static void setHubs(Map<Localidades, List<Integer>> topHubs, int n) {
        US01 rede = US01.getInstance();
        MapGraph<Localidades, Integer> graph = rede.getRedeDistribuicao();

        getTopNHubs(topHubs, n);
        for (Localidades local: graph.vertices()) {
            if (topHubs.containsKey(local)){
                graph.vertex(p -> p.equals(local)).setHub(true);
            }
        }
    }

    public static MapGraph<Localidades, Integer> getMapGraph() {
        Map<Localidades, Integer> influence = calculateInfluence(graph);
        Map<Localidades, Integer> proximity = calculateProximity(graph);
        Map<Localidades, Integer> centrality = calculateCentrality(graph);

        Map<Localidades, List<Integer>> combinedMap = new HashMap<>();
        for (Localidades localidades : graph.vertices()) {
            List<Integer> values = new ArrayList<>();
            values.add(centrality.get(localidades));
            values.add(influence.get(localidades));
            values.add(proximity.get(localidades));
            combinedMap.put(localidades, values);
        }

        Map<Localidades, List<Integer>> topNMap =getTopNMap(combinedMap, n);
        setHubs(topNMap, n);
        escolherHorario(graph);

        return graph;
    }

    public static Map<Localidades, Integer> getMapHubs(){
        getMapGraph();
        int i = 0;
        Map<Localidades, Integer> hubs = new HashMap<>();

        for (Localidades localidades : graph.vertices()) {
            if (localidades.isHub()){
                hubs.put(localidades, i);
                i++;
            }
        }
        return hubs;
    }

    void displayTopNCombined() {
        Map<Localidades, Integer> influence = IdealVertices.calculateInfluence(graph);
        Map<Localidades, Integer> proximity = IdealVertices.calculateProximity(graph);
        Map<Localidades, Integer> centrality = IdealVertices.calculateCentrality(graph);

        Map<Localidades, List<Integer>> combinedMap = new HashMap<>();
        for (Localidades localidades : graph.vertices()) {
            List<Integer> values = new ArrayList<>();
            values.add(centrality.get(localidades));
            values.add(influence.get(localidades));
            values.add(proximity.get(localidades));
            combinedMap.put(localidades, values);
        }

        int n = 5;

        Map<Localidades, List<Integer>> result = IdealVertices.getTopNMap(combinedMap, n);

        System.out.println("Top " + n + " Combined Hubs:");
        for (Map.Entry<Localidades, List<Integer>> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
    }
}
