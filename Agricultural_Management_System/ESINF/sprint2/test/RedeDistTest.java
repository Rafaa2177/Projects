import application.domain.Coordenates;
import application.domain.Hub;
import application.domain.US01;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RedeDistTest {

    private US01 redeDist;

    @BeforeEach
    public void setUp() {
        //Inicializar uma nova instancia da classe  RedeDist before each teste

        if (US01.getInstance()!= null){
            redeDist = US01.getInstance();
        }
    }

    @Test
    public void testAddHub() {
        //Adicionar hubs aos grafos
        boolean hub1Added = redeDist.addHub("H12", 40.7128, -74.0060);
        boolean hub2Added = redeDist.addHub("H22", 34.0522, -118.2437);
        boolean hub3Added = redeDist.addHub("H32", 41.8781, -87.6298);

        //Verificar se os hubs foram adicionados corretamente
        Assertions.assertNotNull(redeDist);
        Assertions.assertTrue(hub1Added);
        Assertions.assertTrue(hub2Added);
        Assertions.assertTrue(hub3Added);

        //Verificar se o grafo contém os grafos adicionados
        Assertions.assertTrue(redeDist.getRedeDistribuicao().vertices().contains(new Hub("H12", new Coordenates(40.7128, -74.0060))));
        Assertions.assertTrue(redeDist.getRedeDistribuicao().vertices().contains(new Hub("H22", new Coordenates(34.0522, -118.2437))));
        Assertions.assertTrue(redeDist.getRedeDistribuicao().vertices().contains(new Hub("H32", new Coordenates(41.8781, -87.6298))));

    }

    @Test
    public void testAddRoute() {
        //Adicionar hubs ao grafo
        redeDist.addHub("H40", 40.7128, -74.0060);
        redeDist.addHub("H89", 34.0522, -118.2437);

        //Adicionar a route entre os hubs(vértices)
        boolean routeAdded = redeDist.addRoute(
                new Hub("H40", new Coordenates(40.7128, -74.0060)),
                new Hub("H89", new Coordenates(34.0522, -118.2437)),
                100 // Distance of the route
        );

        //Verificar se foi adicionada
        Assertions.assertTrue(routeAdded);

        //Verificar se o grafo contém a route
        Assertions.assertNotNull(redeDist.getRedeDistribuicao().edge(
                new Hub("H1", new Coordenates(40.7128, -74.0060)),
                new Hub("H2", new Coordenates(34.0522, -118.2437))
        ));

    }

    @Test
    public void testToString() {
        redeDist.addHub("H1", 40.7128, -74.0060);
        redeDist.addHub("H2", 34.0522, -118.2437);

        redeDist.addRoute(
                new Hub("H1", new Coordenates(40.7128, -74.0060)),
                new Hub("H2", new Coordenates(34.0522, -118.2437)),
                100 // Distance of the route
        );

            //Verificar o toString do grafo
        Assertions.assertNotNull(redeDist.toString());
    }

    @Test
    public void testAddDuplicateHub() {
        Assertions.assertTrue(redeDist.addHub("H1", 40.7128, -74.0060));
        Assertions.assertFalse(redeDist.addHub("H1", 35.6895, 139.6917));  //Adicionar hub com o mesmo id
    }

    @Test
    public void testAddDuplicateRoute() {
        redeDist.addHub("H35", 40.7128, -74.0060);
        redeDist.addHub("H25", 34.0522, -118.2437);

        Assertions.assertTrue(redeDist.addRoute(new Hub("H35"), new Hub("H25"), 100));
        Assertions.assertFalse(redeDist.addRoute(new Hub("H35"), new Hub("H25"), 150));  //Adiconar uma route duplicada

    }

}

