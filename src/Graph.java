import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Graph {
    private Vector<Vector<Integer>> adjacencyList; // class graph just include an adjacency list.

    public Graph() { // constructor creates the adjacency list from its graph.
        adjacencyList = new Vector<>(Constants.ARRAY_SIZE); // array size được lưu ở 1 cái class chỗ
                                                            // ReadVerticesFile.java
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            adjacencyList.add(new Vector<>());
        }
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
                        CheckEdge(vertex - 1, neighbor - 1);
                        adjacencyList.get(vertex - 1).add(neighbor - 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CheckEdge(int vertex, int neighbor) { // Method check lại xem nhập đúng chưa thôi.
        if (vertex < neighbor)
            return;
        else {
            for (int i = 0; i < adjacencyList.get(neighbor).size(); i++) {
                if (adjacencyList.get(neighbor).get(i) == vertex)
                    return;
                if (i == adjacencyList.get(neighbor).size() - 1)
                    System.out.println("error:" + (vertex + 1) + " " + (neighbor + 1));
            }
        }
    }

    public static void main(String[] args) {
        // Read x and y of vertices.
        String filePath = "E:\\LearnOOP\\Vertices.txt";
        Vertex[] vertices = new Vertex[Constants.ARRAY_SIZE];
        ReadVerticesFile.ReadVerticesFromFile(filePath, vertices);
        ReadVerticesFile.minimalizeCoordinates(vertices);
        // Read the graph
        String filePathForGraph = "E:\\LearnOOP\\adjacencList.txt";
        Graph graph = new Graph();
        graph.readAdjacencyListFromFile(filePathForGraph);
    }
}
