
public class Vertex {
    int x;
    int y;

    Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double Distance(Vertex a, Vertex b) {
        double distance = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        return distance;
    }
}
