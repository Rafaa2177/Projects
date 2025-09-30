import application.Repositories.Repositories;
import application.domain.Hub;
import application.domain.MapGraph;
import application.domain.Percurso;
import application.utils.ImportFiles;
import org.example.US03;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class US03Test {

    static MapGraph<Hub,Integer> graph = Repositories.getInstance().getGraphsRepository().getGraph();
    @BeforeEach
    public void setUp() throws IOException {;
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
        graph = Repositories.getInstance().getGraphsRepository().getGraph();
    }

    @Test
    void findMaxMinimumPath() {
        // Testar caso em que não existe nenhum caminho possível com a autonomia dada.
        int autonomy = 0;
        Percurso p = new US03().findMaxMinimumPath(autonomy);
        Assertions.assertNull(p);

        System.out.println("------------------");


        // Testar caso em que existe um caminho possível com a autonomia dada.
        autonomy = 150000;
        p = new US03().findMaxMinimumPath(autonomy);
        Assertions.assertNotNull(p);

        System.out.println("------------------");

        // Testar caso em que existe um caminho diferente com a autonomia maior que a anterior.
        autonomy = 300000;
        p = new US03().findMaxMinimumPath(autonomy);
        Assertions.assertNotNull(p);

    }

    @Test
    void assertNotNullGraph() {
        Assertions.assertNotNull(graph);
    }

}