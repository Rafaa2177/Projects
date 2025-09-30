package application.domain;

import java.util.List;

public class Percurso {

        private Localidades origem;
        private Localidades destino;

        private List<Edge<Localidades,Integer>> caminho;

        private List<Localidades> locaisCarregamento;

        private int distance;

        private int numeroLocaisCarregamento;

        private int autonomy;

        public Percurso(Localidades origem, Localidades destino, List<Edge<Localidades,Integer>> caminho, List<Localidades> locaisCarregamento, int distance, int autonomy) {
                this.origem = origem;
                this.destino = destino;
                this.caminho = caminho;
                this.locaisCarregamento = locaisCarregamento;
                this.distance = distance;
                this.autonomy = autonomy;
                setNumeroLocaisCarregamento();
        }

        public Percurso(Localidades origem, Localidades destino, List<Edge<Localidades,Integer>> caminho, int distance, int autonomy) {
            this.origem = origem;
            this.destino = destino;
            this.caminho = caminho;
            this.distance = distance;
            this.autonomy = autonomy;
        }

        private void setNumeroLocaisCarregamento() {
                this.numeroLocaisCarregamento = this.locaisCarregamento.size();
        }

        public int getNumeroLocaisCarregamento() {
                return this.numeroLocaisCarregamento;
        }

        public Localidades getOrigem() {
                return origem;
        }

        public Localidades getDestino() {
                return destino;
        }

        public List<Edge<Localidades,Integer>> getCaminho() {
                return caminho;
        }

        public List<Localidades> getLocaisCarregamento() {
                return locaisCarregamento;
        }

        public int getDistance() {
                return distance;
        }

        public int getAutonomy() {
                return autonomy;
        }


        @Override
        public String toString() {
            StringBuilder caminhoStr = new StringBuilder();
            int i=0;
            int totalAutonomy = autonomy;
            for (Edge<Localidades, Integer> edge : caminho) {
                caminhoStr.append(edge.getVOrig().getNumId());
                caminhoStr.append(" -> ").append(edge.getVDest().getNumId()).append(" | ").append(edge.getWeight()).append(" metros").append(" | Autonomia restaste: ").append(autonomy-edge.getWeight()).append(" metros");
                autonomy = autonomy - edge.getWeight();
                if(locaisCarregamento != null && locaisCarregamento.size() > 0 && i < locaisCarregamento.size() && edge.getVDest().equals(locaisCarregamento.get(i))){
                    caminhoStr.append("\nCarregou no hub ").append(locaisCarregamento.get(i).getNumId()).append(" com ").append(edge.getWeight()).append(" metros de autonomia\n");
                    i++;
                }

                caminhoStr.append("\n");
            }
            StringBuilder str = new StringBuilder();
            str.append("Detalhes do percurso: \n" );
            str.append("Origem: ").append(origem.getNumId()).append("\n");
            str.append("Destino: ").append(destino.getNumId()).append("\n");
            str.append("Distância total: ").append(distance).append("\n");
            str.append("Autonomia: ").append(totalAutonomy).append("\n");
            str.append("Número de locais de carregamento: ").append(numeroLocaisCarregamento).append("\n");
            str.append("Caminho: \n");
            str.append(caminhoStr);

           return str.toString();
        }
}
