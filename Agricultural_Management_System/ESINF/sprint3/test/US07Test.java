import application.domain.Localidades;
import application.domain.MapGraph;
import application.domain.StructureOfDelivery;
import application.utils.ImportFiles;
import org.example.IdealVertices;
import org.example.US07;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class US07Test {

    public static MapGraph<Localidades,Integer> graph = IdealVertices.getMapGraph();

    @BeforeAll
    public static void setUp() throws IOException {
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
        graph = IdealVertices.getMapGraph();
    }

    @Test
    public void assertUsFunciona()  {
        Localidades verticeInicial = graph.vertices().get(0);

        int autonomy = 100000;
        int descarregar = 15*60;
        int carregar = 30*60;
        int velocidade = (int) ( 100 / 3.6);
        LocalTime currentTime = LocalTime.now();

        US07 us07 = new US07();
        List<StructureOfDelivery> caminhos = us07.maximizarHubs(verticeInicial,currentTime, autonomy, velocidade, carregar, descarregar);
        assertNotNull(caminhos);
    }

    @Test
    public void criarPercursos() throws IOException {

        // Testar caso em que a autonomia é inferior a 1
        int autonomy = 0;
        Localidades origem = new Localidades("H100", null);
        LocalTime currentTime = LocalTime.now();

        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
        MapGraph<Localidades,Integer> graph = IdealVertices.getMapGraph();
        Localidades finalOrigem1 = origem;
        int finalAutonomy1 = autonomy;
        assertThrows(IllegalArgumentException.class, () ->  new US07().maximizarHubs(finalOrigem1, currentTime, finalAutonomy1, 10, 10, 10));


        // Testar caso em que a origem não existe no grafo
        autonomy = 300000;
        origem = new Localidades("H100", null);

        Localidades finalOrigem = origem;
        int finalAutonomy = autonomy;
        assertThrows(IllegalArgumentException.class,  () -> new US07().maximizarHubs(finalOrigem, currentTime, finalAutonomy, 10, 10, 10));

        // Testar caso em que a origem não existe no grafo
        autonomy = 300000;
        origem = null;
        Localidades finalOrigem2 = origem;
        int finalAutonomy2 = autonomy;
        assertThrows(IllegalArgumentException.class, () ->  new US07().maximizarHubs(finalOrigem2, currentTime, finalAutonomy2, 10, 10, 10));

        // Testar caso em que a origem  existe no grafo
        autonomy = 300000;
        origem = graph.vertices().get(0);
        assertNotNull(new US07().maximizarHubs(origem, currentTime, autonomy, 10, 10, 10));
    }

    @Test
    public void obterHubs(){

        // Testar caso em que o grafo contém hubs
       assertNotNull(new US07().obterHubs());
    }

    @Test
    public void obterHubMaisProximo(){

        // Testar caso em que o grafo contém hubs
        int nums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Integer.MAX_VALUE, 12, 13, 14, 15, 16, 17, 18};
        assertNotNull(new US07().obterHubMaisProximo(new US07().obterHubs(), nums ));
        assertEquals( new Localidades("CT16"), new US07().obterHubMaisProximo(new US07().obterHubs(), nums ));
    }
}
