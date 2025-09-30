package application.domain;

import java.util.List;

public class Percurso {

        private Hub origem;
        private Hub destino;

        private List<Edge<Hub,Integer>> caminho;

        private List<Hub> locaisCarregamento;

        private int distance;

        private int numeroLocaisCarregamento;

        private int autonomy;

        public Percurso(Hub origem, Hub destino, List<Edge<Hub,Integer>> caminho, List<Hub> locaisCarregamento, int distance, int autonomy) {
                this.origem = origem;
                this.destino = destino;
                this.caminho = caminho;
                this.locaisCarregamento = locaisCarregamento;
                this.distance = distance;
                this.autonomy = autonomy;
                setNumeroLocaisCarregamento();
        }

        public Percurso(Hub origem, Hub destino, List<Edge<Hub,Integer>> caminho, int distance, int autonomy) {
            this.origem = origem;
            this.destino = destino;
            this.caminho = caminho;
            this.distance = distance;
            this.autonomy = autonomy;
            setNumeroLocaisCarregamento();
        }

        private void setNumeroLocaisCarregamento() {
                this.numeroLocaisCarregamento = this.locaisCarregamento.size();
        }

        public int getNumeroLocaisCarregamento() {
                return this.numeroLocaisCarregamento;
        }

        public Hub getOrigem() {
                return origem;
        }

        public Hub getDestino() {
                return destino;
        }

        public List<Edge<Hub,Integer>> getCaminho() {
                return caminho;
        }

        public List<Hub> getLocaisCarregamento() {
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
            for (Edge<Hub, Integer> edge : caminho) {
                caminhoStr.append(edge.getVOrig().getNumId());
                caminhoStr.append(" -> ").append(edge.getVDest().getNumId()).append(" | ").append(edge.getWeight()).append(" metros").append(" | Autonomia restaste: ").append(autonomy-edge.getWeight()).append(" metros");
                autonomy = autonomy - edge.getWeight();
                if(i < locaisCarregamento.size() && edge.getVDest().equals(locaisCarregamento.get(i))){
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
            str.append("Autonomia: ").append(autonomy).append("\n");
            str.append("Número de locais de carregamento: ").append(numeroLocaisCarregamento).append("\n");
            str.append("Caminho: \n");
            str.append(caminhoStr);

           return str.toString();
        }
}
