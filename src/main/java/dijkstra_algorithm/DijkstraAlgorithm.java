package dijkstra_algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    /**
     * Encuentra el camino más corto en un grafo desde el nodo inicial al nodo final
     * utilizando Dijkstra. Permite optimizar por distancia o costo.
     *
     * @param graph     El grafo que contiene los nodos y las aristas.
     * @param startNode El nodo de inicio.
     * @param endNode   El nodo final.
     * @param criteria  El criterio de optimización: "distancia" o "costo".
     * @return Un mapa con las distancias o costos mínimos a cada nodo desde el nodo
     *         inicial.
     */
    public static Map<GraphNode, Double> findShortestPath(Graph graph, GraphNode startNode, GraphNode endNode,
            String criteria) {
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
                break; // Hemos llegado al nodo final, no es necesario continuar
            }

            for (GraphEdge edge : currentNode.getEdges()) {
                if (!edge.isEnabled())
                    continue; // Ignorar aristas deshabilitadas

                GraphNode neighbor = edge.getOtherNode(currentNode);
                double value = criteria.equalsIgnoreCase("distancia") ? edge.getWeight() : edge.getCost();
                double newDistance = distances.get(currentNode) + value;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    queue.offer(neighbor);
                }
            }
        }

        return distances;
    }

    /**
     * Imprime el camino más corto desde un nodo inicial a un nodo final en un
     * grafo,
     * mostrando los pasos y la distancia total.
     *
     * @param graph     El grafo que contiene los nodos y las aristas.
     * @param startNode El nodo de inicio.
     * @param endNode   El nodo final.
     * @param criteria  El criterio de optimización: "distancia" o "costo".
     * @return Una representación en texto del camino más corto.
     */
    public static String printShortestPath(Graph graph, GraphNode startNode, GraphNode endNode, String criteria) {
        Map<GraphNode, Double> distances = findShortestPath(graph, startNode, endNode, criteria);
        if (distances == null || distances.get(endNode) == Double.MAX_VALUE) {
            return "No se encontró un camino de " + startNode + " a " + endNode;
        }

        StringBuilder path = new StringBuilder();
        path.append("Camino más corto de ").append(startNode).append(" a ").append(endNode).append(" (criterio: ")
                .append(criteria).append("):\n");

        Double totalValue = 0.0;
        GraphNode currentNode = endNode;

        // Usamos una pila para invertir el camino
        LinkedList<String> steps = new LinkedList<>();

        while (!currentNode.equals(startNode)) {
            boolean found = false;
            for (GraphEdge edge : currentNode.getEdges()) {
                if (!edge.isEnabled())
                    continue; // Ignorar aristas deshabilitadas

                GraphNode neighbor = edge.getOtherNode(currentNode);
                double value = criteria.equalsIgnoreCase("distancia") ? edge.getWeight() : edge.getCost();
                if (distances.get(neighbor) + value == distances.get(currentNode)) {
                    steps.addFirst(neighbor + " -> " + currentNode + " (" + criteria + ": " + value + ")");
                    totalValue += value;
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

        path.append("Valor total (").append(criteria).append("): ").append(totalValue);
        return path.toString();
    }
}
