
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Graph {
    Vector<Vector<Integer>> adjacencyList; // class graph
                                           // include an
                                           // adjacency
                                           // list.
    Vertex[] coordinatesVertex = new Vertex[Constants.ARRAY_SIZE];; // Ma trận tọa độ.
    Vector<Vector<Double>> distanceMatrix;

    public Graph() { // constructor creates the adjacency list from its graph.
        adjacencyList = new Vector<>(Constants.ARRAY_SIZE); // array size được lưu ở 1 cái class chỗ
                                                            // ReadVerticesFile.java
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            adjacencyList.add(new Vector<>());
        }
        String filePathForAdjacencyList = "E:\\LearnOOP\\adjacencList.txt"; // Địa chỉ file chứa danh sách kề.
        readAdjacencyListFromFile(filePathForAdjacencyList);
        String filePathfromCoordinatesVertex = "E:\\LearnOOP\\Vertices.txt";
        coordinatesVertex = ReadVerticesFile.readVerticesFromFile(filePathfromCoordinatesVertex); // Read x and y of
                                                                                                  // vertices.
        UpdatingDistanceMatrix();
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

    public void UpdatingDistanceMatrix() {
        distanceMatrix = new Vector<>(Constants.ARRAY_SIZE);

        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            Vector<Double> row = new Vector<>(Constants.ARRAY_SIZE);

            for (int j = 0; j < Constants.ARRAY_SIZE; j++) {
                if (i == j) {
                    row.add(0.0); // Set the diagonal elements to 0.
                } else {
                    row.add(-1.0); // Set other elements to -1.
                }
            }

            distanceMatrix.add(row);
        }

        for (int i = 0; i < adjacencyList.size(); i++) {
            for (int neighbor : adjacencyList.get(i)) {
                Vertex vertex1 = new Vertex(coordinatesVertex[i].x, coordinatesVertex[i].y);
                Vertex vertex2 = new Vertex(coordinatesVertex[neighbor].x, coordinatesVertex[neighbor].y);

                // Set distanceMatrix[i][neighbor] to 0 if i equals neighbor
                // Otherwise, set the distance using Vertex.Distance method
                if (i == neighbor) {
                    distanceMatrix.get(i).set(neighbor, 0.0);
                } else {
                    distanceMatrix.get(i).set(neighbor, Vertex.Distance(vertex1, vertex2));
                }
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
                if (i == adjacencyList.get(neighbor).size() - 1)
                    System.out.println("ERROR:" + (vertex) + " " + (neighbor));
            }
        }
    }

    public void printAdjacencyList() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.print("Vertex " + i + " is connected to: ");
            for (int neighbor : adjacencyList.get(i)) {
                CheckEdge(i, neighbor);
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void printCoordinatesVertex() {
        System.out.println("Coordinates of Vertices:");
        for (int i = 0; i < coordinatesVertex.length; i++) {
            if (coordinatesVertex[i] != null) {
                System.out
                        .println("Vertex " + i + ": x = " + coordinatesVertex[i].x + ", y = " + coordinatesVertex[i].y);
            }
        }
    }

    public void printDistanceMatrix() {
        System.out.println("Distance Matrix:");
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            for (int j = 0; j < Constants.ARRAY_SIZE; j++) {
                double distance = distanceMatrix.get(i).get(j);
                if (distance != -1.0) {
                    System.out.println("Distance from Vertex " + i + " to Vertex " + j + ": " + distance);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Read x and y of vertices.
        // Read the file to create adjacencyList
        Graph graph = new Graph(); // Để tạo danh sách cạnh kề.
        graph.printAdjacencyList();
        graph.printCoordinatesVertex();
        graph.printDistanceMatrix();
    }
}

class Constants {
    public static final int ARRAY_SIZE = 301;
}