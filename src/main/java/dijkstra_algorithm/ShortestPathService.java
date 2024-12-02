package dijkstra_algorithm;

import java.util.*;

public class ShortestPathService {
    private Graph graph;

    public ShortestPathService(Graph graph) {
        this.graph = graph;
    }

    public ShortestPathResult calculateShortestPath(String startNodeId, String endNodeId, String criteria) {
        GraphNode startNode = graph.getNode(startNodeId);
        GraphNode endNode = graph.getNode(endNodeId);

        if (startNode == null || endNode == null) {
            return new ShortestPathResult("Una o ambas ciudades no son válidas.", Collections.emptyList(),
                    Collections.emptyMap());
        }

        Map<GraphNode, Double> distances = DijkstraAlgorithm.findShortestPath(graph, startNode, endNode, criteria);
        if (distances == null || distances.get(endNode) == Double.MAX_VALUE) {
            return new ShortestPathResult("No hay camino disponible.", Collections.emptyList(), Collections.emptyMap());
        }

        List<GraphEdge> pathEdges = reconstructPath(distances, startNode, endNode, criteria);

        return new ShortestPathResult("Camino calculado con éxito.", pathEdges, distances);
    }

    private List<GraphEdge> reconstructPath(Map<GraphNode, Double> distances, GraphNode startNode, GraphNode endNode,
            String criteria) {
        List<GraphEdge> pathEdges = new ArrayList<>();
        GraphNode currentNode = endNode;

        while (!currentNode.equals(startNode)) {
            boolean found = false;
            for (GraphEdge edge : currentNode.getEdges()) {
                if (!edge.isEnabled())
                    continue;

                GraphNode neighbor = edge.getOtherNode(currentNode);
                double value = criteria.equalsIgnoreCase("distancia") ? edge.getWeight() : edge.getCost();
                if (distances.get(neighbor) + value == distances.get(currentNode)) {
                    pathEdges.add(edge);
                    currentNode = neighbor;
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalStateException("Error al reconstruir el camino. No se encontró un camino válido.");
            }
        }

        return pathEdges;
    }
}
