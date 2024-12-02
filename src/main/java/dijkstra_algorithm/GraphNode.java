package dijkstra_algorithm;

import java.util.HashSet;
import java.util.Set;

public class GraphNode {
    private String id;
    private Set<GraphEdge> edges;

    public GraphNode(String id) {
        this.id = id;
        this.edges = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public Set<GraphEdge> getEdges() {
        return new HashSet<>(edges);
    }

    public void addEdge(GraphEdge edge) {
        edges.add(edge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GraphNode graphNode = (GraphNode) o;
        return id.equals(graphNode.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}