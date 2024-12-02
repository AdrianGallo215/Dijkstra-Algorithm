package dijkstra_algorithm;

import java.util.HashMap;
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
            return ("No camino encontrado de  " + startNode + " a " + endNode);
        }

        System.out.println("Camino más corto de  " + startNode + " a " + endNode + ":");
        Double totalDistance = 0.0;
        GraphNode currentNode = endNode;
        while (!currentNode.equals(startNode)) {
            Set<GraphEdge> edges = currentNode.getEdges();
            for (GraphEdge edge : edges) {
                GraphNode neighbor = edge.getOtherNode(currentNode);
                if (distances.get(neighbor) + edge.getWeight() == distances.get(currentNode)) {
                    System.out
                            .println("  " + neighbor + " -> " + currentNode + " (distancia: " + edge.getWeight() + ")");
                    totalDistance += edge.getWeight();
                    currentNode = neighbor;
                    break;
                }
            }
        }
        return ("Distancia total: " + totalDistance);
    }
}