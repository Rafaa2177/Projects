package Sheets;

import java.util.ArrayList;

public class Plantas {
    public String especie;
    public String nome;
    public String variedade;
    public String tipo_plantacao;
    public String fruto;
    static ArrayList<Plantas> plantas = new ArrayList<Plantas>();

    public Plantas(String especie, String nome , String variedade, String tipo_plantacao, String fruto){
        this.especie = especie;
        this.nome = nome;
        this.variedade = variedade;
        this.tipo_plantacao = tipo_plantacao;
        this.fruto = fruto;
    }
    public String getEspecie(){
        return this.especie;
    }
    public String getNome(){
        return this.nome;
    }
    public String getVariedade(){
        return this.variedade;
    }
    public String getTipo_plantacao(){
        return this.tipo_plantacao;
    }
    public String getFruto(){
        return this.fruto;
    }

    @Override
    public String toString() {
        return "Plantas{" +
                "especie='" + especie + '\'' +
                ", nome='" + nome + '\'' +
                ", variedade='" + variedade + '\'' +
                ", tipo_plantacao='" + tipo_plantacao + '\'' +
                ", fruto='" + fruto + '\'' +
                '}';
    }

    public static void addPlanta(Plantas planta){
        plantas.add(planta);
    }
    public static ArrayList<Plantas> getPlantas(){
        return plantas;
    }
}
