package application.domain;

public class RedeDist {

    private static final RedeDist instance = new RedeDist();

    final private MapGraph<Localidades, Integer> redeDistribuicao;

    private RedeDist() {
        this.redeDistribuicao = new MapGraph<>(false);
    }
    public static RedeDist getInstance() {
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

}

