package application.Repositories;

import application.domain.Hub;
import application.domain.MapGraph;

public class GraphRepository {


        private MapGraph<Hub, Integer> graph;

        public GraphRepository() {
            graph = new MapGraph<>(true);
        }

        public MapGraph<Hub, Integer> getGraph() {
            return graph;
        }

        public void setGraph(MapGraph<Hub, Integer> graph) {
            this.graph = graph;
        }

}
