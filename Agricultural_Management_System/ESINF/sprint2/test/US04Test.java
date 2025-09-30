import application.Repositories.Repositories;
import application.domain.Hub;
import application.domain.MapGraph;
import application.utils.ImportFiles;
import org.example.US004_Prim;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class US04Test {
    static MapGraph<Hub,Integer> graph = Repositories.getInstance().getGraphsRepository().getGraph();
    @BeforeEach
    public void setUp() throws IOException {;
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
        graph = Repositories.getInstance().getGraphsRepository().getGraph();
    }

    @Test
    void testThatPrimWorks(){

        Hub verticeInicial = graph.vertices().get(0);
        int distanciaTotal = new US004_Prim().US004_Prim(verticeInicial);

        Assertions.assertNotEquals(0, distanciaTotal);
        Assertions.assertEquals(1185232, distanciaTotal);
    }
}
