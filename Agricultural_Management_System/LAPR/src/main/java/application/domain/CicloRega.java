package application.domain;

import java.util.Date;

public class CicloRega {
    private int cicloId;

    private int setorId;
    private Intervalo intervalo;

    public CicloRega(int cicloId, int setorId, Date data_inicio, Date data_fim){
        this.cicloId = cicloId;
        this.setorId = setorId;
        this.intervalo = new Intervalo(data_inicio,data_fim);
    }

    public int getSetorId() {
        return setorId;
    }

    public int getCicloId() {
        return cicloId;
    }

    public Intervalo getIntervalo() {
        return intervalo;
    }
}
