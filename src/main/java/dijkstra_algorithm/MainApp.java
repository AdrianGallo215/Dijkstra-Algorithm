package dijkstra_algorithm;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class MainApp extends JFrame {
    private Graph graph;
    private ShortestPathService shortestPathService;
    private JTextField startCityField;
    private JTextField endCityField;
    private JTextArea outputArea;
    private GraphPanel graphPanel;

    public MainApp() {
        setTitle("Camino Más Corto entre Ciudades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Ciudad de inicio:"));
        startCityField = new JTextField();
        inputPanel.add(startCityField);
        inputPanel.add(new JLabel("Ciudad de destino:"));
        endCityField = new JTextField();
        inputPanel.add(endCityField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calcular");
        calculateButton.addActionListener(e -> calculateShortestPath());
        buttonPanel.add(calculateButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        initializeGraph();

        shortestPathService = new ShortestPathService(graph);

        graphPanel = new GraphPanel(graph);
        graphPanel.setPreferredSize(new Dimension(600, 400));
        mainPanel.add(graphPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void initializeGraph() {
        graph = new Graph();

        graph.addNode("Lima");
        graph.addNode("Arequipa");
        graph.addNode("Cusco");
        graph.addNode("Tacna");
        graph.addNode("Puno");
        graph.addNode("Trujillo");
        graph.addNode("Cajamarca");

        graph.addEdge("Lima", "Arequipa", 14.0);
        graph.addEdge("Lima", "Cusco", 18.0);
        graph.addEdge("Arequipa", "Cusco", 9.0);
        graph.addEdge("Arequipa", "Tacna", 12.0);
        graph.addEdge("Cusco", "Tacna", 16.0);
        graph.addEdge("Cusco", "Cajamarca", 6.0);
        graph.addEdge("Cusco", "Puno", 5.0);
        graph.addEdge("Tacna", "Puno", 8.0);
        graph.addEdge("Lima", "Trujillo", 7.0);
        graph.addEdge("Trujillo", "Arequipa", 10.0);
        graph.addEdge("Trujillo", "Cajamarca", 4.0);
        graph.addEdge("Puno", "Trujillo", 25.0);
        graph.addEdge("Cajamarca", "Lima", 8.0);
    }

    private void calculateShortestPath() {
        String startCity = startCityField.getText();
        String endCity = endCityField.getText();

        GraphNode startNode = graph.getNode(startCity);
        GraphNode endNode = graph.getNode(endCity);

        if (startNode == null || endNode == null) {
            outputArea.setText("Una o ambas ciudades no son válidas. Verifique los nombres ingresados.");
            return;
        }

        ShortestPathResult result = shortestPathService.calculateShortestPath(startCity, endCity);

        outputArea.setText(result.getMessage());

        graphPanel.highlightPath(result.getPathEdges(), startNode, endNode);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}
