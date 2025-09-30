import application.Repositories.Repositories;
import application.domain.Localidades;
import application.domain.MapGraph;
import application.utils.ImportFiles;
import org.example.IdealVertices;
import org.example.US06;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class US06Test {

    @Test
    public void assertUsFunciona() throws IOException {
        // Testar que a classe US06 funciona
        int autonomy = 300000;

        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");

        MapGraph<Localidades,Integer> graph = IdealVertices.getMapGraph();
        Localidades origem = graph.vertices().get(0);

        assertNotNull(new US06().obterTodosPercursosPossiveis(origem, autonomy, graph));
    }

    @Test
    public void criarPercursos() throws IOException {
        // Testar caso em que não exista um grafo válido
        int autonomy = 300000;
        Localidades origem = new Localidades("H1", null);
        assertThrows(IllegalArgumentException.class, (Executable) new US06().obterTodosPercursosPossiveis(origem, autonomy,null));

        // Testar caso em que a autonomia é inferior a 1
        autonomy = 0;
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
        MapGraph<Localidades,Integer> graph = IdealVertices.getMapGraph();
        assertThrows(IllegalArgumentException.class, (Executable) new US06().obterTodosPercursosPossiveis(origem, autonomy,graph));

        // Testar caso em que a origem não existe no grafo
        autonomy = 300000;
        origem = new Localidades("H100", null);
        assertThrows(IllegalArgumentException.class, (Executable) new US06().obterTodosPercursosPossiveis(origem, autonomy,graph));

        // Testar caso em que a origem não existe no grafo
        autonomy = 300000;
        origem = null;
        assertThrows(IllegalArgumentException.class, (Executable) new US06().obterTodosPercursosPossiveis(origem, autonomy,graph));

        // Testar caso em que a origem não existe no grafo
        autonomy = 300000;
        origem = graph.vertices().get(0);
        assertNotNull(new US06().obterTodosPercursosPossiveis(origem, autonomy,graph));
    }


}
