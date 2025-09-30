package application.domain;

import java.util.ArrayList;

public class BFSVert {
    private Localidades hub;
    private int depth;
    private ArrayList<Localidades> circuit;

    public BFSVert(Localidades hub, int depth, ArrayList<Localidades> circuit) {
        this.hub = hub;
        this.depth = depth;
        this.circuit = circuit;
    }

    public Localidades getHub() {
        return hub;
    }

    public void setHub(Localidades hub) {
        this.hub = hub;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public ArrayList<Localidades> getCircuit() {
        return circuit;
    }

    public void setCircuit(ArrayList<Localidades> path) {
        this.circuit = path;
    }

    public void addHub(Localidades hub){
        this.circuit.add(hub);
    }
}
