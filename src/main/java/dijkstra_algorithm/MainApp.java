package dijkstra_algorithm;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // Crear un grafo representando una red de transporte
        Graph graph = new Graph();

        // Añadir nodos (ciudades)
        graph.addNode("Lima");
        graph.addNode("Arequipa");
        graph.addNode("Cusco");
        graph.addNode("Tacna");
        graph.addNode("Puno");
        graph.addNode("Trujillo");

        // Añadir bordes (carreteras con distancias en km)
        graph.addEdge("Lima", "Arequipa", 14.0);
        graph.addEdge("Lima", "Cusco", 18.0);
        graph.addEdge("Arequipa", "Cusco", 9.0);
        graph.addEdge("Arequipa", "Tacna", 12.0);
        graph.addEdge("Cusco", "Tacna", 16.0);
        graph.addEdge("Cusco", "Puno", 5.0);
        graph.addEdge("Tacna", "Puno", 8.0);
        graph.addEdge("Lima", "Trujillo", 7.0);
        graph.addEdge("Trujillo", "Arequipa", 10.0);
        graph.addEdge("Puno", "Trujillo", 25.0);

        // Solicitar al usuario el nodo de inicio y fin
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la ciudad de inicio:");
        String startCity = scanner.nextLine();
        System.out.println("Ingrese la ciudad de destino:");
        String endCity = scanner.nextLine();

        // Obtener los nodos correspondientes
        GraphNode startNode = graph.getNode(startCity);
        GraphNode endNode = graph.getNode(endCity);

        // Validar la existencia de los nodos
        if (startNode == null || endNode == null) {
            System.out.println("Una o ambas ciudades no son válidas. Verifique los nombres ingresados.");
            return;
        }

        // Calcular y mostrar el camino más corto
        System.out.println("\nCalculando el camino más corto...");
        String result = DijkstraAlgorithm.printShortestPath(graph, startNode, endNode);
        System.out.println(result);
    }
}
