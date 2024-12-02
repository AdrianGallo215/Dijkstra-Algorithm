package dijkstra_algorithm;

import java.util.*;

public class ShortestPathService {
    private Graph graph;

    public ShortestPathService(Graph graph) {
        this.graph = graph;
    }

    public ShortestPathResult calculateShortestPath(String startNodeId, String endNodeId) {
        GraphNode startNode = graph.getNode(startNodeId);
        GraphNode endNode = graph.getNode(endNodeId);

        if (startNode == null || endNode == null) {
            return new ShortestPathResult("Nodo inicial o final inválido.", Collections.emptyList(),
                    Collections.emptyMap());
        }

        Map<GraphNode, Double> distances = DijkstraAlgorithm.findShortestPath(graph, startNode, endNode);
        if (distances == null) {
            return new ShortestPathResult("No se encontró un camino desde " + startNodeId + " hasta " + endNodeId, null,
                    Collections.emptyMap());
        }

        List<GraphEdge> pathEdges = reconstructPath(distances, startNode, endNode);
        return new ShortestPathResult(DijkstraAlgorithm.printShortestPath(graph, startNode, endNode), pathEdges,
                distances);
    }

    private List<GraphEdge> reconstructPath(Map<GraphNode, Double> distances, GraphNode startNode, GraphNode endNode) {
        List<GraphEdge> pathEdges = new ArrayList<>();
        GraphNode currentNode = endNode;

        while (!currentNode.equals(startNode)) {
            for (GraphEdge edge : currentNode.getEdges()) {
                GraphNode neighbor = edge.getOtherNode(currentNode);
                if (distances.get(neighbor) + edge.getWeight() == distances.get(currentNode)) {
                    pathEdges.add(edge);
                    currentNode = neighbor;
                    break;
                }
            }
        }
        return pathEdges;
    }
}
