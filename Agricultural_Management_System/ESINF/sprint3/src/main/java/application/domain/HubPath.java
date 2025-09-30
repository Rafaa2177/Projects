package application.domain;

import java.util.LinkedList;

public class HubPath {
    private Path informação;
    private LinkedList<String> horas;

    public HubPath(Path informação, LinkedList<String> horas) {
        this.informação = informação;
        this.horas = horas;
    }

    public Path getInformação() {
        return informação;
    }

    public void setInformação(Path informação) {
        this.informação = informação;
    }

    public LinkedList<String> getHoras() {
        return horas;
    }

    public void setHoras(LinkedList<String> horas) {
        this.horas = horas;
    }
}


