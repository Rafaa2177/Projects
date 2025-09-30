package Sheets;

import java.util.ArrayList;

public class FatorProducao {
    public String designacao;
    public String fabricante;
    public String formato;
    public String tipo;
    public String aplicacao;
    public String c1;
    public String perc1;
    public String c2;
    public String perc2;
    public String c3;
    public String perc3;
    public String c4;
    public String perc4;
    public String c5;
    public String perc5;
    public String c6;
    public String perc6;
    public String c7;
    public String perc7;
    public String c8;
    public String perc8;
    static ArrayList<FatorProducao> fatorProducao = new ArrayList<FatorProducao>();
    public FatorProducao (String designacao, String fabricante, String formato, String tipo, String aplicacao, String c1, String perc1, String c2, String perc2, String c3, String perc3, String c4, String perc4, String c5, String perc5, String c6, String perc6, String c7, String perc7, String c8, String perc8) {
        this.designacao = designacao;
        this.fabricante = fabricante;
        this.formato = formato;
        this.tipo = tipo;
        this.aplicacao = aplicacao;
        this.c1 = c1;
        this.perc1 = perc1;
        this.c2 = c2;
        this.perc2 = perc2;
        this.c3 = c3;
        this.perc3 = perc3;
        this.c4 = c4;
        this.perc4 = perc4;
        this.c5 = c5;
        this.perc5 = perc5;
        this.c6 = c6;
        this.perc6 = perc6;
        this.c7 = c7;
        this.perc7 = perc7;
        this.c8 = c8;
        this.perc8 = perc8;
    }
    public String getDesignacao(){
        return this.designacao;
    }
    public String getFabricante(){
        return this.fabricante;
    }
    public String getFormato(){
        return this.formato;
    }
    public String getTipo(){
        return this.tipo;
    }
    public String getAplicacao(){
        return this.aplicacao;
    }
    public String getC1(){
        return this.c1;
    }
    public String getPerc1(){
        return this.perc1;
    }
    public String getC2(){
        return this.c2;
    }
    public String getPerc2(){
        return this.perc2;
    }
    public String getC3(){
        return this.c3;
    }
    public String getPerc3(){
        return this.perc3;
    }
    public String getC4(){
        return this.c4;
    }
    public String getPerc4(){
        return this.perc4;
    }
    public String getC5(){
        return this.c5;
    }
    public String getPerc5(){
        return this.perc5;
    }
    public String getC6(){
        return this.c6;
    }
    public String getPerc6(){
        return this.perc6;
    }
    public String getC7(){
        return this.c7;
    }
    public String getPerc7(){
        return this.perc7;
    }
    public String getC8(){
        return this.c8;
    }
    public String getPerc8(){
        return this.perc8;
    }
    @Override
    public String toString(){
        return "FatorProducao{" +
                "designacao='" + designacao + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", formato='" + formato + '\'' +
                ", tipo='" + tipo + '\'' +
                ", aplicacao='" + aplicacao + '\'' +
                ", c1='" + c1 + '\'' +
                ", perc1='" + perc1 + '\'' +
                ", c2='" + c2 + '\'' +
                ", perc2='" + perc2 + '\'' +
                ", c3='" + c3 + '\'' +
                ", perc3='" + perc3 + '\'' +
                ", c4='" + c4 + '\'' +
                ", perc4='" + perc4 + '\'' +
                ", c5='" + c5 + '\'' +
                ", perc5='" + perc5 + '\'' +
                ", c6='" + c6 + '\'' +
                ", perc6='" + perc6 + '\'' +
                ", c7='" + c7 + '\'' +
                ", perc7='" + perc7 + '\'' +
                ", c8='" + c8 + '\'' +
                ", perc8='" + perc8 + '\'' +
                '}';
    }
    public static void addFatorProducao(FatorProducao fator){
        fatorProducao.add(fator);
    }
    public static ArrayList<FatorProducao> getFatorProducao(){
        return fatorProducao;
    }
}
