package dijkstra_algorithm;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    private Graph graph;
    private ShortestPathService shortestPathService;
    private JTextField startNodeField;
    private JTextField endNodeField;
    private JTextArea outputArea;
    private GraphPanel graphPanel;

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

        initializeGraph();

        shortestPathService = new ShortestPathService(graph);

        graphPanel = new GraphPanel(graph);
        graphPanel.setPreferredSize(new Dimension(600, 400));
        getContentPane().add(graphPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void initializeGraph() {
        graph = new Graph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addEdge("A", "B", 3.0);
        graph.addEdge("A", "C", 5.0);
        graph.addEdge("B", "D", 2.0);
        graph.addEdge("C", "D", 1.0);
        graph.addEdge("C", "E", 6.0);
        graph.addEdge("D", "F", 4.0);
        graph.addEdge("E", "F", 2.0);
    }

    private void calculateShortestPath() {
        String startNodeId = startNodeField.getText();
        String endNodeId = endNodeField.getText();

        ShortestPathResult result = shortestPathService.calculateShortestPath(startNodeId, endNodeId);
        outputArea.setText(result.getMessage());

        graphPanel.highlightPath(result.getPathEdges(), graph.getNode(startNodeId), graph.getNode(endNodeId));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}
