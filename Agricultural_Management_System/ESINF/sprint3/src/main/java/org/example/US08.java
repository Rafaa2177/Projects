package org.example;

import application.Repositories.GraphRepository;
import application.Repositories.Repositories;
import application.domain.BFSVert;
import application.domain.Localidades;
import application.domain.MapGraph;
import application.utils.Algorithms;
import application.utils.AlgorithmsUtility;
import org.jgrapht.alg.util.Pair;

import java.util.*;

public class US08 {
    private GraphRepository graphRepository = null;
    private Algorithms algorithms = null;

    public US08(){
        getGraphRepository();
        algorithms = new Algorithms();
    }

    private void getGraphRepository() {
        if (graphRepository == null) {
            Repositories repositories = Repositories.getInstance();
            graphRepository = repositories.getGraphsRepository();
        }
    }

    public void print(Localidades hOrig, int nHub, int a, double v){

        if(hOrig == null || nHub < 1 || a < 1 || v < 1) throw new IllegalArgumentException("Parâmetros inválidos.");


        MapGraph<Localidades, Integer> graph = graphRepository.getGraph();
        Pair<Integer,ArrayList<Localidades>> circuit = getCircuit(hOrig, nHub, a);
        if(circuit == null){
            System.out.println("Não foi possível encontrar um circuito com os parâmetros dados.");
        }
        else{
            Localidades h, nextH;

            for(int i=0;i<circuit.getSecond().size()-1;i++){
                h = circuit.getSecond().get(i);
                nextH = circuit.getSecond().get(i+1);
                System.out.print(h.getNumId()+" ("+h.getNumId().replace("CT","")+" colaboradores)  -- "+graph.edge(h, nextH).getWeight()+" m ->  ");
                if(i==circuit.getSecond().size()-2) System.out.print(nextH.getNumId()+" ("+nextH.getNumId().replace("CT","")+" colaboradores)");
            }
            System.out.println();

            double totalDistance = calculateCircuitDistance(graph, circuit.getSecond())/1000.0;
            System.out.println("Distância total percorrida: "+totalDistance+" km");
            System.out.println("Número de carregamentos: "+circuit.getFirst());
            System.out.printf("Tempo total do circuito: %.2f horas\n",totalDistance/v);
        }
    }

    public Pair<Integer,ArrayList<Localidades>> getCircuit(Localidades hOrig, int nHub, int a){

        if(hOrig == null || nHub < 1 || a < 1) throw new IllegalArgumentException("Parâmetros inválidos.");

        MapGraph<Localidades, Integer> graph = graphRepository.getGraph();
        Queue<BFSVert> possibleCircuitsQueue = new AlgorithmsUtility().BFS(graph, hOrig, nHub);
        BFSVert vert;

        Map<Localidades, Pair<Integer,ArrayList<Localidades>>> circuits = new HashMap<>();
        int nCharges;
        while(!possibleCircuitsQueue.isEmpty()){
            vert = possibleCircuitsQueue.poll();
            if(graph.adjVertices(vert.getCircuit().get(nHub)).contains(hOrig)){
                vert.addHub(hOrig);
                nCharges = calculateCircuitCharges(graph, a, vert.getCircuit());

                if(nCharges != -1) circuits.put(vert.getHub(), new Pair<>(nCharges,vert.getCircuit()));
            }
        }

        Comparator<Localidades> colabComparator = new Comparator<>() {
            @Override
            public int compare(Localidades h1, Localidades h2) {
                int c1 = Integer.parseInt(h1.getNumId().replace("CT", ""));
                int c2 = Integer.parseInt(h2.getNumId().replace("CT", ""));
                return c1-c2;
            }
        };
        Set<Localidades> sortedLocalidadess = new TreeSet<>(colabComparator);
        sortedLocalidadess.addAll(circuits.keySet());

        Comparator<Localidades> circuitComparator = new Comparator<>() {
            @Override
            public int compare(Localidades h1, Localidades h2) {
                int t1 = calculateCircuitDistance(graph,circuits.get(h1).getSecond());
                int t2 = calculateCircuitDistance(graph,circuits.get(h2).getSecond());
                return t1-t2;
            }
        };
        Map<Localidades,Pair<Integer,ArrayList<Localidades>>> sortedCircuits = new TreeMap<>(circuitComparator);
        sortedCircuits.putAll(circuits);

        Pair<Integer,ArrayList<Localidades>> bestCircuit = null;
        int score, bestScore = Integer.MAX_VALUE;

        for(Localidades h : sortedLocalidadess){
            score = findPosition(sortedLocalidadess, h) + findPosition(sortedCircuits.keySet(), h);
            if(score < bestScore){
                bestCircuit = sortedCircuits.get(h);
            }
        }

        return bestCircuit;
    }

    private int calculateCircuitCharges(MapGraph<Localidades,Integer> graph ,int a, ArrayList<Localidades> circuit) {
        int distance, charges = 0, autonomy = a;
        for(int i=0; i<circuit.size()-1; i++){
            distance = graph.edge(circuit.get(i), circuit.get(i+1)).getWeight();
            autonomy -= distance;
            if(distance > a) return -1;
            else if(autonomy < 0){
                charges++;
                autonomy+=a;
            }
        }
        return charges;
    }

    private int calculateCircuitDistance(MapGraph<Localidades,Integer> graph, ArrayList<Localidades> circuit){
        int distance = 0;
        for(int i=0; i<circuit.size()-1; i++){
            distance += graph.edge(circuit.get(i), circuit.get(i+1)).getWeight();
        }
        return distance;
    }

    private int findPosition(Set<Localidades> Localidadess, Localidades h){
        int i=0;
        for(Localidades Localidades : Localidadess){
            if(Localidades.equals(h)) return i;
            i++;
        }
        return -1;
    }
}
