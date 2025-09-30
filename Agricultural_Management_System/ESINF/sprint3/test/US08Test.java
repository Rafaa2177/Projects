import application.domain.Localidades;
import application.domain.MapGraph;
import application.utils.ImportFiles;
import org.example.IdealVertices;
import org.example.US08;
import org.example.US09;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class US08Test {

    private US08 us08;
    public static MapGraph<Localidades,Integer> graph = IdealVertices.getMapGraph();


    @BeforeEach
    void setUp() throws IOException {
        ImportFiles.importLocal("src/main/resources/locais_small.csv");
        ImportFiles.importDist("src/main/resources/distancias_small.csv");
        graph = IdealVertices.getMapGraph();
        us08 = new US08();

        // You may need to set up your graphRepository and other dependencies here
    }

    @Test
    void testPrint() {

        // Write your test cases for the print method
        US08 us08 = new US08();
        int nHub = 5;
        int a = 300000;
        int v = 90;
        Localidades origem = graph.vertices().get(0);
        us08.print(origem, nHub, a, v);
    }

    @Test
    void createPrint(){

        // Assert Doesn't Work
        int nHub = 5;
        int a = 300000;
        int v = 90;
        Localidades origem = graph.vertices().get(0);

        assertThrows(IllegalArgumentException.class, () -> new US08().print(origem, 0,a,v));
        assertThrows(IllegalArgumentException.class, () -> new US08().print(origem, nHub,0,v));
        assertThrows(IllegalArgumentException.class, () -> new US08().print(origem, nHub,a,0));
        assertThrows(IllegalArgumentException.class, () -> new US08().print(null, nHub,a,v));

    }

    @Test
    void testGetCircuit() {
        // Assert Works
        Localidades origem = graph.vertices().get(0);
        int nHub = 5;
        int a = 300000;
        assertNotNull(us08.getCircuit(origem, nHub, a));

        // Assert Doesn't Work
        assertThrows(IllegalArgumentException.class, () -> new US08().getCircuit(null, nHub,a));

    }

}
