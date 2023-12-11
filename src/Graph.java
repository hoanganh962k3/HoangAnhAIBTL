
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Graph {
    private Vector<Vector<Integer>> adjacencyList; // class graph
                                                   // include an
                                                   // adjacency
                                                   // list.
    private Vertex[] coordinatesVertex = new Vertex[Constants.ARRAY_SIZE];; // Ma trận tọa độ.
    private Vector<Vector<Double>> distanceMatrix;

    public Graph() { // constructor creates the adjacency list from its graph.
        adjacencyList = new Vector<>(Constants.ARRAY_SIZE); // array size được lưu ở 1 cái class chỗ
                                                            // ReadVerticesFile.java
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            adjacencyList.add(new Vector<>());
        }
        distanceMatrix = new Vector<>(Constants.ARRAY_SIZE);
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            distanceMatrix.add(new Vector<>(Constants.ARRAY_SIZE));
        }
        String filePathForGraph = "E:\\LearnOOP\\adjacencList.txt"; // Địa chỉ file chứa danh sách kề.
        readAdjacencyListFromFile(filePathForGraph);
        String filePath = "E:\\LearnOOP\\Vertices.txt";
        ReadVerticesFile.ReadVerticesFromFile(filePath, coordinatesVertex); // Read x and y of vertices.
        // UpdatingDistanceMatrix(this);
    }

    public void readAdjacencyListFromFile(String filePath) { // Input adjacency List from a text
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);

                if (tokenizer.hasMoreTokens()) {
                    int vertex = Integer.parseInt(tokenizer.nextToken());
                    while (tokenizer.hasMoreTokens()) {
                        int neighbor = Integer.parseInt(tokenizer.nextToken());
                        CheckEdge(vertex, neighbor);
                        adjacencyList.get(vertex).add(neighbor);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdatingDistanceMatrix(Graph graph) {
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            for (int j = 0; j < Constants.ARRAY_SIZE; j++) {
                graph.distanceMatrix.get(i).set(j, -1.0);
            }
        }
        for (int i = 0; i < graph.adjacencyList.size(); i++) {
            for (int neighbor : graph.adjacencyList.get(i)) {
                Vertex vertex1 = new Vertex(graph.coordinatesVertex[i].x, graph.coordinatesVertex[i].y);
                Vertex vertex2 = new Vertex(graph.coordinatesVertex[neighbor].x, graph.coordinatesVertex[neighbor].y);
                // Now you can use vertex1 and vertex2 as needed
                graph.distanceMatrix.get(i).set(neighbor, Vertex.Distance(vertex1, vertex2));
            }

        }
    }

    private void CheckEdge(int vertex, int neighbor) { // Method check lại xem nhập đúng chưa thôi.
        if (vertex < neighbor)
            return;
        else {
            for (int i = 0; i < adjacencyList.get(neighbor).size(); i++) {
                if (adjacencyList.get(neighbor).get(i) == vertex)
                    return;
                if (i == adjacencyList.get(neighbor).size())
                    System.out.println("error:" + (vertex) + " " + (neighbor));
            }
        }
    }

    public static void main(String[] args) {
        // Read x and y of vertices.
        // Read the file to create adjacencyList
        Graph graph = new Graph(); // Để tạo danh sách cạnh kề.
        for (int i = 0; i < graph.adjacencyList.size(); i++) {
            System.out.print(i + " ");
            for (int neighbor : graph.adjacencyList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            System.out.println(graph.coordinatesVertex[i].x + " " + graph.coordinatesVertex[i].y);
        }
    }
}
