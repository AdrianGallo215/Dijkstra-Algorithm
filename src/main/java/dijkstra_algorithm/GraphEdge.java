package dijkstra_algorithm;

public class GraphEdge {
    private GraphNode fromNode;
    private GraphNode toNode;
    private double weight;
    private double cost;
    private boolean enabled;

    public GraphEdge(GraphNode fromNode, GraphNode toNode, double weight, double cost) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.weight = weight;
        this.cost = cost;
        this.enabled = true;
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

    public double getCost() {
        return cost;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
}
