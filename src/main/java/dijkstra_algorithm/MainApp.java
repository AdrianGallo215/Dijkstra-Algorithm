package dijkstra_algorithm;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainApp extends JFrame {
    private Graph graph;
    private JTextField startNodeField;
    private JTextField endNodeField;
    private JTextArea outputArea;

    public MainApp() {
        setTitle("Dijkstra's Algorithm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Start Node:"));
        startNodeField = new JTextField();
        inputPanel.add(startNodeField);
        inputPanel.add(new JLabel("End Node:"));
        endNodeField = new JTextField();
        inputPanel.add(endNodeField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateShortestPath());
        buttonPanel.add(calculateButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        graph = new Graph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "B", 5.0);
        graph.addEdge("A", "C", 1.0);
        graph.addEdge("B", "D", 1.0);
        graph.addEdge("C", "B", 2.0);
        graph.addEdge("C", "D", 4.0);
        graph.addEdge("D", "B", 3.0);

        setVisible(true);
    }

    private void calculateShortestPath() {
        String startNodeId = startNodeField.getText();
        String endNodeId = endNodeField.getText();

        GraphNode startNode = graph.getNode(startNodeId);
        GraphNode endNode = graph.getNode(endNodeId);

        if (startNode == null || endNode == null) {
            outputArea.setText("Invalid start or end node.");
            return;
        }

        Map<GraphNode, Double> distances = DijkstraAlgorithm.findShortestPath(graph, startNode, endNode);
        if (distances == null) {
            outputArea.setText("No path found from " + startNodeId + " to " + endNodeId);
        } else {
            outputArea.setText(DijkstraAlgorithm.printShortestPath(graph, startNode, endNode));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}