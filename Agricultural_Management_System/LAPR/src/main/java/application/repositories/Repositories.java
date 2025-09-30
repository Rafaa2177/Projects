package application.repositories;

public class Repositories {
    public Repositories(){

    }
    private static final Repositories instance = new Repositories();

    public static Repositories getInstance(){
        return instance;
    }

    IrrigationProgRepo irrigationProgRepo = new IrrigationProgRepo();

    public IrrigationProgRepo getIrrigationProgRepo() {
        return irrigationProgRepo;
    }
}
