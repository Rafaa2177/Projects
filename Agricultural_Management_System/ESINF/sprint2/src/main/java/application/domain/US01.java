package application.domain;

public class US01 {

    private static final US01 instance = new US01();

    final private MapGraph<Hub, Integer> redeDistribuicao;

    private US01() {
        this.redeDistribuicao = new MapGraph<>(false);
    }
    public static US01 getInstance() {

        return instance;
    }

    public boolean addHub(String numId, Double lat, Double lon) {
        Hub vert = new Hub(numId, lat, lon);
        return redeDistribuicao.addVertex(vert);
    }

    public boolean addRoute(Hub orig, Hub dest, Integer distance) {
        return redeDistribuicao.addEdge(orig,dest,distance);
    }

    @Override
    public String toString() {
        return redeDistribuicao.toString();
    }

    public MapGraph<Hub, Integer> getRedeDistribuicao() {
        return redeDistribuicao;
    }
}

