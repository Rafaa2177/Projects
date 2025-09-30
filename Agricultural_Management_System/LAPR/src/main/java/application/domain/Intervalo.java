package application.domain;

import java.util.Date;

public class Intervalo {
    private Date data_inicio;
    private Date data_final;

    public Intervalo(Date data_inicio, Date data_final){
        this.data_inicio = data_inicio;
        this.data_final = data_final;
    }

    public Date getData_final() {
        return data_final;
    }

    public Date getData_inicio() {
        return data_inicio;
    }
}
