package application.domain;

import java.util.Objects;

public class Localidades {

    private String numId;
    private Coordenates coordenadas;

    private boolean isHub = true;

    private Horario horario;


    public Localidades(String numId, Coordenates coordenadas, boolean isHub, Horario horario) {
        this.numId = numId;
        this.coordenadas = coordenadas;
        this.isHub = isHub;
        this.horario = horario;
    }
    public Localidades(String numId, Double lat, Double lon, boolean isHub) {
        this.numId = numId;
        this.coordenadas = new Coordenates(lat, lon);
        this.isHub =false;
    }

    public Localidades(String numId, Coordenates coordenadas) {
        this.numId = numId;
        this.coordenadas = coordenadas;
    }

    public Localidades(String numId, Double lat, Double lon) {
        this.numId = numId;
        this.coordenadas = new Coordenates(lat, lon);
    }



    public Localidades(String numId) {

        this.numId = numId;
    }

    public String getNumId() {
        return numId;
    }
    public Coordenates getCoordenadas() {
        return coordenadas;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public void setCoordenadas(Coordenates coordenadas) {
        this.coordenadas = coordenadas;
    }

    public boolean isHub() {
        return isHub;
    }

    public void isHub(boolean isHub) {
        this.isHub = isHub;
    }

    public void setHub(boolean hub) {
        isHub = hub;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Localidades other = (Localidades) obj;
        return Objects.equals(getNumId(), other.getNumId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumId());
    }

    @Override
    public String toString() {
        //return String.format("Hub Id = '%s', Coordenadas = %s", numId, coordenadas);
        return numId ;
    }
}

