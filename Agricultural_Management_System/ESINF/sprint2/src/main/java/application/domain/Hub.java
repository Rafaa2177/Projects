package application.domain;

import java.util.Objects;

public class Hub {

    private String numId;
    private Coordenates coordenadas;


    public Hub(String numId, Coordenates coordenadas) {
        this.numId = numId;
        this.coordenadas = coordenadas;
    }

    public Hub(String numId, Double lat, Double lon) {
        this.numId = numId;
        this.coordenadas = new Coordenates(lat, lon);
    }

    public Hub(String numId) {
        this.numId = numId;
    }

    public String getNumId() {
        return numId;
    }
    public Coordenates getCoordenadas() {
        return coordenadas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hub hub = (Hub) o;
        return Objects.equals(numId, hub.numId) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numId);
    }

    @Override
    public String toString() {
        return String.format("Hub Id = '%s', Coordenadas = %s", numId, coordenadas);
    }
}

