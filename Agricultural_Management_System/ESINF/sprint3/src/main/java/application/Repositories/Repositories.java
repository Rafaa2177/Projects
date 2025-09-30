package application.Repositories;

public class Repositories {

    public Repositories() {
    }
    public static Repositories getInstance() {
        return instance;
    }
    private static final Repositories instance = new Repositories();
    GraphRepository graphsRepository = new GraphRepository();

    public GraphRepository getGraphsRepository() {return graphsRepository;}
}
