package dijkstra_algorithm;

import java.util.List;
import java.util.Map;

public class ShortestPathResult {
    private String message;
    private List<GraphEdge> pathEdges;
    private Map<GraphNode, Double> distances;

    public ShortestPathResult(String message, List<GraphEdge> pathEdges, Map<GraphNode, Double> distances) {
        this.message = message;
        this.pathEdges = pathEdges;
        this.distances = distances;
    }

    public String getMessage() {
        return message;
    }

    public List<GraphEdge> getPathEdges() {
        return pathEdges;
    }

    public Map<GraphNode, Double> getDistances() {
        return distances;
    }
}
