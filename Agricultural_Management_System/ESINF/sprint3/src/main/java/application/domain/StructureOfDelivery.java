package application.domain;

import org.example.US07;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StructureOfDelivery {

        private Localidades localInicial;
        private Localidades localFinal;
        private List<Edge<Localidades, Integer>> caminhoPercorrido = new ArrayList<>();
        private LocalTime tempoInicial;
        private LocalTime tempoFinal;
        private double tempoTotalCarregamento;
        private LocalTime tempoPercorrido;
        private int distanciaPercorrida;


    public StructureOfDelivery(
            Localidades localInicial,
            Localidades localFinal,
            List<Edge<Localidades, Integer>> caminhoPercorrido,
            LocalTime tempoInicial,
            LocalTime tempoTotal,
            double tempoTotalCarregamento,
            int distanciaPercorrida,
            LocalTime tempoPercorrido
    ) {
        this.localInicial = localInicial;
        this.localFinal = localFinal;
        this.caminhoPercorrido = caminhoPercorrido;
        this.tempoInicial = tempoInicial;
        this.tempoFinal = tempoTotal;
        this.tempoTotalCarregamento = tempoTotalCarregamento;
        this.distanciaPercorrida = distanciaPercorrida;
        this.tempoPercorrido = tempoPercorrido;
    }

    public Localidades getLocalInicial() {
        return localInicial;
    }

    public Localidades getLocalFinal() {
        return localFinal;
    }

    public List<Edge<Localidades, Integer>> getCaminhoPercorrido() {
        return caminhoPercorrido;
    }

    public LocalTime getTempoInicial() {
        return tempoInicial;
    }

    public LocalTime getTempoFinal() {
        return tempoFinal;
    }

    public double getTempoTotalCarregamento() {
        return tempoTotalCarregamento;
    }

    public int getNumeroCarregamentos(int carregar) {
        return (int) (tempoTotalCarregamento / carregar);
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public LocalTime getTempoPercorrido() {
        return tempoPercorrido;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        return "\nlocalInicial = " + localInicial +
                ", \nhub = " + localFinal +
                ", \ncaminhoPercorrido= " + caminhoPercorrido +
                ", \ntempoInicial= " + tempoInicial.format(formatter)+ "h" +
                ", \ntempoFinal= " + tempoFinal.format(formatter) + "h"+
                ", \ntempoTotalCarregamento= " + tempoTotalCarregamento
                + ", \ndistanciaPercorrida= " + distanciaPercorrida + "m,"
                + "\ntempoPercorrido= " + tempoPercorrido.format(formatter) + "h\n";
    }
}
