
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadVerticesFile {

    public static void ReadVerticesFromFile(String filePath, Vertex[] vertices) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int vertexCount = 0;

            // Read each line from the file until the end of the file is reached
            while ((line = reader.readLine()) != null) {
                // Split the line into two numbers
                String[] numbers = line.split("\\s+");

                // Check if there are exactly two numbers on the line
                if (numbers.length == 2) {
                    try {
                        int number1 = Integer.parseInt(numbers[0]);
                        int number2 = Integer.parseInt(numbers[1]);
                        vertices[vertexCount++] = new Vertex(number1, number2);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid integer format on line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        minimalizeCoordinates(vertices);
    }

    public static void minimalizeCoordinates(Vertex[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i].x = vertices[i].x - 480000;
            vertices[i].y = vertices[i].y - 2320000;
        }
    }

}

class Constants {
    public static final int ARRAY_SIZE = 82;
}