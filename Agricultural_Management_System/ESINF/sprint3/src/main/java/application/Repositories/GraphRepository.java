package application.Repositories;

import application.domain.Localidades;
import application.domain.MapGraph;

public class GraphRepository {


        private MapGraph<Localidades, Integer> graph;

        public GraphRepository() {
            graph = new MapGraph<>(true);
        }

        public MapGraph<Localidades, Integer> getGraph() {
            return graph;
        }

        public void setGraph(MapGraph<Localidades, Integer> graph) {
            this.graph = graph;
        }

}
