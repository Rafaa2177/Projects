package Sheets;

import java.util.ArrayList;

public class ReceitasFertirrega {
    public String id;
    public String produto;
    public String fabricante;
    public String quantidade;
    public String unidade;
    static ArrayList<ReceitasFertirrega> receitas = new ArrayList<ReceitasFertirrega>();
    public ReceitasFertirrega(String id, String produto, String fabricante, String quantidade, String unidade){
        this.id = id;
        this.produto = produto;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }
    public String getId(){
        return this.id;
    }
    public String getProduto(){
        return this.produto;
    }
    public String getFabricante(){
        return this.fabricante;
    }
    public String getQuantidade(){
        return this.quantidade;
    }
    public String getUnidade(){
        return this.unidade;
    }
    @Override
    public String toString() {
        return "ReceitasFertirrega{" +
                "id='" + id + '\'' +
                ", produto='" + produto + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", unidade='" + unidade + '\'' +
                '}';
    }
    public static void addReceitasFertirrega(ReceitasFertirrega receitasFertirrega){
        receitas.add(receitasFertirrega);
    }
    public static ArrayList<ReceitasFertirrega> getReceitasFertirrega(){
        return receitas;
    }
}
