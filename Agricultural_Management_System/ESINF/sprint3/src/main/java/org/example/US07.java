package org.example;

import application.Repositories.Repositories;
import application.domain.Edge;
import application.domain.Localidades;
import application.domain.MapGraph;
import application.domain.StructureOfDelivery;
import application.utils.AlgorithmsUtility;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class US07 {


    private static final MapGraph<Localidades, Integer> graph = US01.getInstance().getRedeDistribuicao();



    public List<StructureOfDelivery> maximizarHubs(Localidades localInicial, LocalTime horaInicial, int autonomia, double velocidade, int carregar, int decarregarMercadoria){

        if(autonomia <= 0) throw new IllegalArgumentException("Autonomia inválida");
        if(velocidade <= 0) throw new IllegalArgumentException("Velocidade inválida");
        if(carregar <= 0) throw new IllegalArgumentException("Tempo de carregamento inválido");
        if(decarregarMercadoria <= 0) throw new IllegalArgumentException("Tempo de descarregamento inválido");
        if(localInicial == null) throw new IllegalArgumentException("Local inicial inválido");
        if(horaInicial == null) throw new IllegalArgumentException("Hora inicial inválida");
        if(graph == null) throw new IllegalArgumentException("Grafo inválido");
        if(!graph.vertices().contains(localInicial)) throw new IllegalArgumentException("Local inicial não existe no grafo");


        // Definir estruturas
        Set<Localidades> hubs = obterHubs();
        if(hubs.size() == 0) throw new IllegalArgumentException("Não existem hubs no grafo");

        List<StructureOfDelivery> caminhos = new ArrayList<>();

        // Tentar chegar a todos os hubs
        while(hubs.size() > 0){

            boolean flag = false; // Flag para verificar se chegamos a um hub
            Localidades hubMaisProximo = null;
            Set<Localidades> tempHubs = hubs;

            while( tempHubs.size() > 0 && !flag){

                // Inicializar arrays
                int[] distances = new int[graph.numVertices()];
                boolean[] visited = new boolean[graph.numVertices()];
                int[] path = new int[graph.numVertices()];

                // Calcular caminho mais curto entre o sitio inicial e as restantes localidades/hubs
                new AlgorithmsUtility().shortestPathDijkstraWithAutonomy(graph, localInicial, visited, path, distances, autonomia);

                // Obter hub mais próximo
                hubMaisProximo = obterHubMaisProximo(tempHubs, distances);

                // Obter caminho mais curto entre o hub mais próximo e o hub inicial
                List<Edge<Localidades, Integer>> caminhoPercorrido = new AlgorithmsUtility().findMinPath(graph, hubMaisProximo, path);

                // Verificar se o caminho existe
                if (caminhoPercorrido != null) {

                    int index = graph.key(hubMaisProximo);
                    long tempoDeChegada = (long) (distances[index] / velocidade);

                    Duration duration = Duration.ofSeconds(tempoDeChegada);
                    LocalTime localTime = LocalTime.MIDNIGHT.plus(duration);

                    LocalTime horaNova = horaInicial.plus(tempoDeChegada, ChronoUnit.SECONDS);

                    // Verificar se chegamos dentro do horário de funcionamento
                    if (horaNova.isAfter(hubMaisProximo.getHorario().getOpenTime()) && horaNova.isBefore(hubMaisProximo.getHorario().getCloseTime())) {

                        // Criar estrutura de dados para guardar o caminho percorrido
                        StructureOfDelivery estrutura = new StructureOfDelivery(
                                localInicial,
                                hubMaisProximo,
                                caminhoPercorrido,
                                horaInicial,
                                horaNova,
                                tempoDeCarregamento(caminhoPercorrido, carregar, autonomia),
                                distances[index],
                                localTime
                        );

                        caminhos.add(estrutura);

                        horaInicial = horaNova.plus(decarregarMercadoria, ChronoUnit.SECONDS);
                        flag = true;
                        localInicial= hubMaisProximo;
                    }
                }
                // Remover o hub mais próximo da lista de hubs
                tempHubs.remove(hubMaisProximo);
            }
            hubs.remove(hubMaisProximo);

        }
        if(caminhos.size() == 0) throw new IllegalArgumentException("Não existem caminhos possíveis");
        if(caminhos.size() < obterHubs().size()) {
            System.out.println("Não foi possível chegar a todos os hubs");
        }
        return caminhos;
    }



    public int tempoDeCarregamento(List<Edge<Localidades,Integer>> caminho, int carregar, int autonomia){
        int tempo = 0;
        int tempAutonomia = autonomia;
        for(Edge<Localidades,Integer> edge : caminho){
            // Verificar se é necessário carregar
            if( edge.getWeight() > tempAutonomia){
                tempo += carregar;
                tempAutonomia = autonomia;
            }
            else tempAutonomia -= edge.getWeight();
        }
        return tempo;

    }

    public Set<Localidades> obterHubs(){
        // Obter os hubs do grafo calculados previamente na US02
        Set<Localidades> hubs = new  IdealVertices().sortVertices().stream().limit(5).collect(Collectors.toSet());

        // Verificar se existem hubs no grafo
        if( hubs.size() == 0) throw new IllegalArgumentException("Não existem hubs no grafo");

        return hubs;
    }



    public Localidades obterHubMaisProximo(Set<Localidades> hubs,  int[] distances){
        int minDistance = Integer.MAX_VALUE;
        Localidades hubMaisProximo = null;

        for (Localidades hub : hubs) {
            int index = graph.key(hub);
            // Verificar se o hub é o mais próximo pela distância ao hub inicial
            if(distances[index] <= minDistance){
                minDistance = distances[index];
                hubMaisProximo = hub;
            }
        }
        return hubMaisProximo;
    }

    public void print(Localidades verticeInicial, int autonomy, double velocidade, int carregar, int descarregar, List<StructureOfDelivery> caminhos, LocalTime currentTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("Hub inicial: " + verticeInicial.getNumId());
        System.out.println("Hub final: " + caminhos.get(caminhos.size()-1).getLocalFinal().getNumId());
        System.out.println("Hora inicial: " + currentTime.format(formatter) + "h");
        System.out.println("Autonomia: " + autonomy/1000 + " km");
        System.out.println("Velocidade: " + velocidade * 3.6 + " km/h");
        System.out.println("Tempo de carregamento: " + LocalTime.MIDNIGHT.plus(Duration.ofSeconds(carregar)) + "h");
        System.out.println("Tempo de descarregamento: " + LocalTime.MIDNIGHT.plus(Duration.ofSeconds(descarregar))  + "h");
        System.out.println("\n");

        System.out.println("Percurso efetuado: ");
        int distanciaPercorrida = 0;
        int totalCarregamentos = 0;

        Duration sum = Duration.ZERO;
        Duration tempo = Duration.ZERO;
        for (StructureOfDelivery caminho : caminhos) {
            Collections.reverse(caminho.getCaminhoPercorrido());

            for (Edge<Localidades, Integer> edge : caminho.getCaminhoPercorrido()) {
                tempo = tempo.plus(Duration.ofSeconds((long) (edge.getWeight() / velocidade)));
                LocalTime tempop = LocalTime.MIDNIGHT.plus(tempo);
                distanciaPercorrida += edge.getWeight();
                System.out.println(edge.getVDest() + " -> " + edge.getVOrig() + " : " +distanciaPercorrida/1000 + " km | Tempo de percurso: " +  tempop.format(formatter) + " h.");
            }
            System.out.println("O hub " + caminho.getLocalFinal().getNumId() + " foi visitado às " + caminho.getTempoFinal().format(formatter) + " horas." );
            totalCarregamentos += caminho.getNumeroCarregamentos(carregar);
            Duration duration = Duration.ofHours(caminho.getTempoPercorrido().getHour()).plusMinutes(caminho.getTempoPercorrido().getMinute()).plusSeconds(caminho.getTempoPercorrido().getSecond());

            totalCarregamentos += caminho.getNumeroCarregamentos(carregar);
            sum = sum.plus(duration);
        }

        System.out.println("\nDistância percorrida: " +distanciaPercorrida/1000 + " km.");
        System.out.println("Tempo total de percurso: " + LocalTime.MIDNIGHT.plus(sum).format(formatter) + " horas.");
        System.out.println("Tempo total de carregamento: " + (totalCarregamentos * carregar) / 60 + " minutos.");
        System.out.println("Numero de carregamentos: " + totalCarregamentos  + " vezes.");

    }


}


