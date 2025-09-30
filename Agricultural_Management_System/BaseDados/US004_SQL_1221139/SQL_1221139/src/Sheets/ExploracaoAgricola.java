package Sheets;

import java.util.ArrayList;

public class ExploracaoAgricola {
        public String id;
        public String tipo;
        public String designacao;
        public String area;
        public String unidade;

        static ArrayList<ExploracaoAgricola> exploracaoAgricola = new ArrayList<ExploracaoAgricola>();
        public ExploracaoAgricola (String id, String tipo, String designacao, String area, String unidade) {
            this.id = id;
            this.tipo = tipo;
            this.designacao = designacao;
            this.area = area;
            this.unidade = unidade;
        }
        public String getId(){
            return this.id;
        }
        public String getTipo(){return this.tipo;}
        public String getDesignacao(){
            return this.designacao;
        }
        public String getArea(){
            return this.area;
        }
        public String getUnidade(){
            return this.unidade;
        }
        @Override
        public String toString(){
            return "ExploracaoAgricola{" +
                    "id=" + id +
                    ", tipo='" + tipo + '\'' +
                    ", designacao='" + designacao + '\'' +
                    ", area=" + area +
                    ", unidade='" + unidade + '\'' +
                    '}';
        }
        public static void addExploracaoAgricola(ExploracaoAgricola exploracao){
            exploracaoAgricola.add(exploracao);
        }
        public static ArrayList<ExploracaoAgricola> getExploracaoAgricola(){
            return exploracaoAgricola;
        }
}
