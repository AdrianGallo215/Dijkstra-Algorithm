package dijkstra_algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraAlgorithm {

    public static Map<GraphNode, Double> findShortestPath(Graph graph, GraphNode startNode, GraphNode endNode) {
        Map<GraphNode, Double> distances = new HashMap<>();
        for (GraphNode node : graph.getNodes()) {
            distances.put(node, Double.MAX_VALUE);
        }
        distances.put(startNode, 0.0);

        PriorityQueue<GraphNode> queue = new PriorityQueue<>(
                (a, b) -> Double.compare(distances.get(a), distances.get(b)));
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.poll();

            if (currentNode.equals(endNode)) {
                return distances;
            }

            for (GraphEdge edge : currentNode.getEdges()) {
                GraphNode neighbor = edge.getOtherNode(currentNode);
                double newDistance = distances.get(currentNode) + edge.getWeight();
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    queue.offer(neighbor);
                }
            }
        }

        return null;
    }

    public static String printShortestPath(Graph graph, GraphNode startNode, GraphNode endNode) {
    Map<GraphNode, Double> distances = findShortestPath(graph, startNode, endNode);
    if (distances == null) {
        return "No se encontró un camino de " + startNode + " a " + endNode;
    }

    StringBuilder path = new StringBuilder();
    path.append("Camino más corto de ").append(startNode).append(" a ").append(endNode).append(":\n");

    Double totalDistance = 0.0;
    GraphNode currentNode = endNode;

    // Usamos una pila para invertir el camino
    LinkedList<String> steps = new LinkedList<>();

    while (!currentNode.equals(startNode)) {
        boolean found = false;
        for (GraphEdge edge : currentNode.getEdges()) {
            GraphNode neighbor = edge.getOtherNode(currentNode);
            if (distances.get(neighbor) + edge.getWeight() == distances.get(currentNode)) {
                steps.addFirst(neighbor + " -> " + currentNode + " (distancia: " + edge.getWeight() + ")");
                totalDistance += edge.getWeight();
                currentNode = neighbor;
                found = true;
                break;
            }
        }
        if (!found) {
            return "Error al reconstruir el camino. No se encontró un camino válido.";
        }
    }

    // Agregar los pasos al StringBuilder
    for (String step : steps) {
        path.append("  ").append(step).append("\n");
    }

    path.append("Distancia total: ").append(totalDistance);
    return path.toString();
}
}
