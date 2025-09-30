import application.Repositories.Repositories;
import application.domain.Hub;
import application.domain.MapGraph;
import application.utils.ImportFiles;
import org.example.IdealVertices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class US02Test {

    static MapGraph<Hub,Integer> graph = Repositories.getInstance().getGraphsRepository().getGraph();
    @BeforeEach
    public void setUp() throws IOException {;
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
        graph = Repositories.getInstance().getGraphsRepository().getGraph();
    }

    @Test
    void testThatIdealVerticesWorks(){
        IdealVertices idealVertices = new IdealVertices();
        idealVertices.printIdealVertices();

       Set<Hub> hubs =  idealVertices.sortVertices();
       Assertions.assertEquals(17, hubs.size());
       Assertions.assertNotNull(hubs);
    }
}
