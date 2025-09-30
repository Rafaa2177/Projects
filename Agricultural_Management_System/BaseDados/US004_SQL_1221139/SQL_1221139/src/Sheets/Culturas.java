package Sheets;

import java.util.ArrayList;

public class Culturas {
    public String id;
    public String parcela;
    public String cultura;
    public String tipo;
    public String dataInicial;
    public String dataFinal;
    public String qtt;
    public String unidade;
    static ArrayList<Culturas> culturas = new ArrayList<Culturas>();
    public Culturas(String id, String parcela, String cultura, String tipo, String dataInicial, String dataFinal, String qtt, String unidade) {
        this.id = id;
        this.parcela = parcela;
        this.cultura = cultura;
        this.tipo = tipo;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.qtt = qtt;
        this.unidade = unidade;
    }
    public String getId(){
        return this.id;
    }
    public String getParcela(){
        return this.parcela;
    }
    public String getCultura(){
        return this.cultura;
    }
    public String getTipo(){
        return this.tipo;
    }
    public String getDataInicial(){
        return this.dataInicial;
    }
    public String getDataFinal(){
        return this.dataFinal;
    }
    public String getQtt(){
        return this.qtt;
    }
    public String getUnidade(){
        return this.unidade;
    }
    @Override
    public String toString(){
        return "Culturas{" +
                "id=" + id +
                ", parcela='" + parcela + '\'' +
                ", cultura='" + cultura + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", qtt=" + qtt +
                ", unidade='" + unidade + '\'' +
                '}';
    }
    public static void addCulturas(Culturas cultura){
        culturas.add(cultura);
    }
    public static ArrayList<Culturas> getCulturas(){
        return culturas;
    }
    public void setQtt() {
        this.qtt = "null";
    }
}
