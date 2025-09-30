package Sheets;

import java.util.ArrayList;

public class CicloRega {
    public String idSetor;
    public String idParcela;
    public String parcela;
    public String cultura;
    public String dataInicial;
    public String dataFinal;
    static ArrayList<CicloRega> cicloRega = new ArrayList<CicloRega>();
    public CicloRega(String idSetor, String idParcela, String parcela, String cultura, String dataInicial, String dataFinal){
        this.idSetor = idSetor;
        this.idParcela = idParcela;
        this.parcela = parcela;
        this.cultura = cultura;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }
    public String getIdSetor(){
        return this.idSetor;
    }
    public String getIdParcela(){
        return this.idParcela;
    }
    public String getParcela(){
        return this.parcela;
    }
    public String getCultura(){
        return this.cultura;
    }
    public String getDataInicial(){
        return this.dataInicial;
    }
    public String getDataFinal(){
        return this.dataFinal;
    }
    @Override
    public String toString(){
        return "CicloRega{" +
                "idSetor=" + idSetor +
                ", idParcela='" + idParcela + '\'' +
                ", parcela='" + parcela + '\'' +
                ", cultura='" + cultura + '\'' +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                '}';
    }
    public static void addCicloRega(CicloRega ciclo){
        cicloRega.add(ciclo);
    }
    public static ArrayList<CicloRega> getCicloRega(){
        return cicloRega;
    }

}
