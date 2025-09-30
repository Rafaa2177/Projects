import application.Repositories.Repositories;
import application.domain.Coordenates;
import application.domain.Edge;
import application.domain.Localidades;
import application.domain.MapGraph;
import application.utils.ImportFiles;
import org.example.IdealVertices;
import org.example.US09;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class US09Test {

    public final static  MapGraph<Localidades,Integer> graph = IdealVertices.getMapGraph();

    @BeforeAll
    public static void setUp() throws IOException {
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
    }

    @Test
    public void assertUsFunciona() throws IOException {
        // Testar que a classe US09 funciona
        int numClusters = 2;

        assertNotNull(new US09().obterClusters(2, graph));
        assertEquals(numClusters, new US09().obterClusters(2, graph).size());
        System.out.println("------------------");
    }

    @Test
    public void criarClusters() {
        // Testar caso em que não exista um grafo válido
        int numClusters = 2;
        assertThrows(NullPointerException.class, () -> new US09().obterClusters(2, null));

        // Testar caso em que o número de clusters é inferior a 1
        numClusters = 0;
        MapGraph<Localidades, Integer> finalGraph = new IdealVertices().getMapGraph();
        assertThrows(IllegalArgumentException.class, () -> new US09().obterClusters(0, finalGraph));

        // Testar caso em que o número de clusters é superior ao número de localidades
        numClusters = 100;
        assertThrows(IllegalArgumentException.class, () -> new US09().obterClusters(100, finalGraph));

        // Testar caso em que o número de clusters é superior ao número de hubs
        numClusters = 100;
        assertThrows(IllegalArgumentException.class, () -> new US09().obterClusters(100, finalGraph));

        // Testar caso em que o número de clusters é igual ao número de hubs
        numClusters = 3;
        assertNotNull(new US09().obterClusters(3, finalGraph));
        assertEquals(numClusters, new US09().obterClusters(3, finalGraph).size());
        System.out.println("------------------");
    }

    @Test
    public void removerMaiorEdge(){
        // Testar caso em que o grafo não contém a edge
        MapGraph<Localidades, Integer> graph = new IdealVertices().getMapGraph();
        Edge<Localidades, Integer> edge = new Edge<>(new Localidades("H1", new Coordenates(40.7128, -74.0060)), new Localidades("H2", new Coordenates(34.0522, -118.2437)), 100);
        assertThrows(IllegalArgumentException.class, () -> new US09().removerMaiorEdge(edge, graph));

        // Testar caso em que a edge é removida
        Edge<Localidades, Integer> newEdge = graph.edges().stream().findFirst().get();
        new US09().removerMaiorEdge(newEdge, graph);
        assertNull(graph.edge(newEdge.getVOrig(), newEdge.getVDest()));
        assertNull(graph.edge(newEdge.getVDest(), newEdge.getVOrig()));

    }


    @Test
    public void existeCaminhoEntreHubs(){
        // Testar caso em que não existam caminhos entre os hubs
        assertFalse(new US09().existeCaminhoEntreHubs(graph.vertices().get(0), graph.vertices().get(1)));

    }

    @Test
    public void obterHubs(){

        // Testar caso em que o grafo contém hubs
        MapGraph<Localidades, Integer> graph2 = new IdealVertices().getMapGraph();
        assertNotNull(new US09().obterHubs(2, graph2));
    }


}
