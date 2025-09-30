package Sheets;

import java.util.ArrayList;
public class Operacoes {
    public String setor;
    public String id;
    public String parcela;
    public String operacao;
    public String modo;
    public String cultura;
    public String data;
    public String qtt;
    public String unidade;
    public String fatorProducao;
    public String area;
    public String unidadeArea;
    public String horario;
    public String compasso;
    public String distancia;
    public String fruto;
    public String receita;
    static ArrayList<Operacoes> operacoes = new ArrayList<Operacoes>();
    public Operacoes(String setor, String id, String parcela, String operacao, String modo, String cultura, String data, String qtt, String unidade, String fatorProducao, String area, String unidadeArea, String horario, String compasso, String distancia, String fruto, String receita){
        this.setor = setor;
        this.id = id;
        this.parcela = parcela;
        this.operacao = operacao;
        this.modo = modo;
        this.cultura = cultura;
        this.data = data;
        this.qtt = qtt;
        this.unidade = unidade;
        this.fatorProducao = fatorProducao;
        this.area = area;
        this.unidadeArea = unidadeArea;
        this.horario = horario;
        this.compasso = compasso;
        this.distancia = distancia;
        this.fruto = fruto;
        this.receita = receita;
    }
    public String getSetor(){
        return this.setor;
    }
    public String getId(){
        return this.id;
    }
    public String getParcela(){
        return this.parcela;
    }
    public String getOperacao(){
        return this.operacao;
    }
    public String getModo(){
        return this.modo;
    }
    public String getCultura(){
        return this.cultura;
    }
    public String getData(){
        return this.data;
    }
    public String getQtt(){
        return this.qtt;
    }
    public String getUnidade(){
        return this.unidade;
    }
    public String getFatorProducao(){
        return this.fatorProducao;
    }
    public String getArea(){
        return this.area;
    }
    public String getUnidadeArea(){
        return this.unidadeArea;
    }
    public String getHorario(){
        return this.horario;
    }
    public String getCompasso(){
        return this.compasso;
    }
    public String getDistancia(){
        return this.distancia;
    }
    public String getFruto(){
        return this.fruto;
    }
    public String getReceita(){
        return this.receita;
    }


    public void getFatorProducao(String fatorProducao){
        this.fatorProducao = fatorProducao;
    }
    @Override
    public String toString(){
        return "Operacoes{" +
                "setor='" + setor + '\'' +
                ", id='" + id + '\'' +
                ", parcela='" + parcela + '\'' +
                ", operacao='" + operacao + '\'' +
                ", modo='" + modo + '\'' +
                ", cultura='" + cultura + '\'' +
                ", data='" + data + '\'' +
                ", qtt='" + qtt + '\'' +
                ", unidade='" + unidade + '\'' +
                ", fatorProducao='" + fatorProducao + '\'' +
                ", area='" + area + '\'' +
                ", unidadeArea='" + unidadeArea + '\'' +
                ", horario='" + horario + '\'' +
                ", compasso='" + compasso + '\'' +
                ", distancia='" + distancia + '\'' +
                ", fruto='" + fruto + '\'' +
                ", receita='" + receita + '\'' +
                '}';
    }
    public static void addOperacao(Operacoes operacao){
        operacoes.add(operacao);
    }
    public static ArrayList<Operacoes> getOperacoes(){
        return operacoes;
    }
    public void setQtt() {
        this.qtt = "null";
    }
}
