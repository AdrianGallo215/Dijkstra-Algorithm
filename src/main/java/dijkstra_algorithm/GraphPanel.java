package dijkstra_algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphPanel extends JPanel {
    private Graph graph;
    private Map<GraphNode, Point> nodePositions;
    private List<GraphEdge> highlightedEdges;
    private GraphNode startNode;
    private GraphNode endNode;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        this.nodePositions = new HashMap<>();
        generateNodePositions();
    }

    private void generateNodePositions() {
        int width = 600;
        int height = 400;
        int radius = Math.min(width, height) / 3;
        int centerX = width / 2;
        int centerY = height / 2;
        int nodeCount = graph.getNodes().size();
        int angleIncrement = 360 / nodeCount;

        int angle = 0;
        for (GraphNode node : graph.getNodes()) {
            int x = (int) (centerX + radius * Math.cos(Math.toRadians(angle)));
            int y = (int) (centerY + radius * Math.sin(Math.toRadians(angle)));
            nodePositions.put(node, new Point(x, y));
            angle += angleIncrement;
        }
    }

    public void highlightPath(List<GraphEdge> edges, GraphNode start, GraphNode end) {
        this.highlightedEdges = edges;
        this.startNode = start;
        this.endNode = end;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.GRAY);
        for (GraphEdge edge : graph.getEdges()) {
            Point from = nodePositions.get(edge.getFromNode());
            Point to = nodePositions.get(edge.getToNode());
            g2d.drawLine(from.x, from.y, to.x, to.y);

            String weightLabel = String.format("%.1f", edge.getWeight());
            int weightX = (from.x + to.x) / 2;
            int weightY = (from.y + to.y) / 2;
            g2d.setColor(Color.BLACK);
            g2d.drawString(weightLabel, weightX, weightY);
            g2d.setColor(Color.GRAY);
        }

        if (highlightedEdges != null) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3));
            for (GraphEdge edge : highlightedEdges) {
                Point from = nodePositions.get(edge.getFromNode());
                Point to = nodePositions.get(edge.getToNode());
                g2d.drawLine(from.x, from.y, to.x, to.y);
            }
            g2d.setStroke(new BasicStroke(1));
        }

        for (Map.Entry<GraphNode, Point> entry : nodePositions.entrySet()) {
            Point pos = entry.getValue();
            GraphNode node = entry.getKey();

            Color nodeColor;
            if (node.equals(startNode) || node.equals(endNode)) {
                nodeColor = new Color(8, 31, 92);
            } else {
                nodeColor = new Color(112, 150, 209);
            }
            g2d.setColor(nodeColor);

            int size = 30;
            int x = pos.x - size / 2;
            int y = pos.y - size / 2;
            g2d.fill(new Ellipse2D.Double(x, y, size, size));

            g2d.setColor(Color.WHITE);
            String label = node.getId();
            g2d.drawString(label, pos.x - g2d.getFontMetrics().stringWidth(label) / 2, pos.y + 5);
        }
    }
}
