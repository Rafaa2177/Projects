package org.example;

import application.domain.Localidades;
import application.domain.MapGraph;

public class US01 {

    private static final US01 instance = new US01();

    final private MapGraph<Localidades, Integer> redeDistribuicao;

    private US01() {
        this.redeDistribuicao = new MapGraph<>(false);
    }
    public static US01 getInstance() {

        return instance;
    }

    public boolean addHub(String numId, Double lat, Double lon) {
        Localidades vert = new Localidades(numId, lat, lon);
        return redeDistribuicao.addVertex(vert);
    }

    public boolean addRoute(Localidades orig, Localidades dest, Integer distance) {
        return redeDistribuicao.addEdge(orig,dest,distance);
    }

    @Override
    public String toString() {
        return redeDistribuicao.toString();
    }

    public MapGraph<Localidades, Integer> getRedeDistribuicao() {
        return redeDistribuicao;
    }

    public Localidades getHubById(String hubId) {
        // Itera sobre todos os vértices (Hubs) no grafo
        for (Localidades localidades : redeDistribuicao.vertices()) {
            // Compara o ID do Hub atual com o ID desejado
            if (localidades.getNumId().equals(hubId)) {
                // Retorna o Hub se encontrar correspondência
                return localidades;
            }
        }
        // Retorna null se nenhum Hub correspondente for encontrado
        return null;
    }
}

