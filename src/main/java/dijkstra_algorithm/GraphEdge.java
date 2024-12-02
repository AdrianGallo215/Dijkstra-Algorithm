package dijkstra_algorithm;

public class GraphEdge {
    private GraphNode fromNode;
    private GraphNode toNode;
    private double weight;

    public GraphEdge(GraphNode fromNode, GraphNode toNode, double weight) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.weight = weight;
    }

    public GraphNode getFromNode() {
        return fromNode;
    }

    public GraphNode getToNode() {
        return toNode;
    }

    public double getWeight() {
        return weight;
    }

    public GraphNode getOtherNode(GraphNode node) {
        if (node.equals(fromNode)) {
            return toNode;
        } else if (node.equals(toNode)) {
            return fromNode;
        } else {
            throw new IllegalArgumentException("El nodo dado no es parte de esta arista.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GraphEdge graphEdge = (GraphEdge) o;
        return Double.compare(graphEdge.weight, weight) == 0 &&
                fromNode.equals(graphEdge.fromNode) &&
                toNode.equals(graphEdge.toNode);
    }

    @Override
    public int hashCode() {
        int result = fromNode.hashCode();
        result = 31 * result + toNode.hashCode();
        long temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}