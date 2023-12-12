
public class Vertex {
    int x;
    int y;

    Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double Distance(Vertex a, Vertex b) {
        double distance = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        distance = (double) Math.ceil(distance * 100) / 100; // Làm tròn lấy 2 chữ số phần
        // thập phân.
        return distance;
    }
}
