package Sheets;

import java.util.ArrayList;

public class SetoresRega {
    public String id;
    public String data_inicio;
    public String data_fim;
    public String quantidade;
    public String unidade;
    public String sistema;
    static ArrayList<SetoresRega> setoresRega = new ArrayList<SetoresRega>();
    public SetoresRega(String id, String data_inicio, String data_fim, String quantidade, String unidade, String sistema){
        this.id = id;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.sistema = sistema;
    }
    public String getId(){
        return this.id;
    }
    public String getData_inicio(){
        return this.data_inicio;
    }
    public String getData_fim(){
        return this.data_fim;
    }
    public String getQuantidade(){
        return this.quantidade;
    }
    public String getUnidade(){
        return this.unidade;
    }
    public String getSistema(){
        return this.sistema;
    }
    @Override
    public String toString() {
        return "SetoresRega{" +
                "id='" + id + '\'' +
                ", data_inicio='" + data_inicio + '\'' +
                ", data_fim='" + data_fim + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", unidade='" + unidade + '\'' +
                ", sistema='" + sistema + '\'' +
                '}';
    }
    public static void addSetorRega(SetoresRega setorRega){
        setoresRega.add(setorRega);
    }
    public static ArrayList<SetoresRega> getSetoresRega(){
        return setoresRega;
    }
}
