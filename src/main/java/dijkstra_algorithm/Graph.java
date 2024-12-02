package dijkstra_algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<String, GraphNode> nodes;
    private Set<GraphEdge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    public void addNode(String nodeId) {
        if (!nodes.containsKey(nodeId)) {
            GraphNode node = new GraphNode(nodeId);
            nodes.put(nodeId, node);
        }
    }

    public void addEdge(String fromNodeId, String toNodeId, double weight) {
        GraphNode fromNode = nodes.get(fromNodeId);
        GraphNode toNode = nodes.get(toNodeId);

        if (fromNode == null) {
            addNode(fromNodeId);
            fromNode = nodes.get(fromNodeId);
        }
        if (toNode == null) {
            addNode(toNodeId);
            toNode = nodes.get(toNodeId);
        }

        GraphEdge edge = new GraphEdge(fromNode, toNode, weight);
        edges.add(edge);
        fromNode.addEdge(edge);
        toNode.addEdge(edge);
    }

    public Set<GraphNode> getNodes() {
        return new HashSet<>(nodes.values());
    }

    public Set<GraphEdge> getEdges() {
        return new HashSet<>(edges);
    }

    public GraphNode getNode(String nodeId) {
        return nodes.get(nodeId);
    }
}