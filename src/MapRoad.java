import javafx.util.Pair;

public class MapRoad {
    Pair<Vertex, Vertex> road; // first is the starting point of a road, second is the ending.

    MapRoad(Vertex startVertex, Vertex endVertex) {
        this.road = new Pair<Vertex, Vertex>(startVertex, endVertex);
    }

    boolean checkRoadIntersection(MapRoad road1, MapRoad road2) {
        return doIntersect(road1.getStartVertex(), road1.getEndVertex(), road2.getStartVertex(), road2.getEndVertex());
    }

    int orientation(Vertex p1, Vertex p2, Vertex q) {
        int x = (p2.y - p1.y) * (q.x - p2.x) - (q.y - p2.y) * (p2.x - p1.x);
        if (x == 0)
            return 0;
        return x < 0 ? 1 : 2;
    }

    boolean onSegment(Vertex a, Vertex b, Vertex c) {
        if (c.x >= Math.min(a.x, b.x) && c.x <= Math.max(a.x, b.x) && c.y >= Math.min(a.y, b.y)
                && c.y <= Math.max(a.y, b.y))
            return true;
        return false;
    }

    boolean doIntersect(Vertex p1, Vertex q1, Vertex p2, Vertex q2) {
        int a = orientation(p1, q1, p2);
        int b = orientation(p1, q1, q2);
        int c = orientation(p2, q2, p1);
        int d = orientation(p2, q2, q1);

        if (a == 0 && onSegment(p1, q1, p2))
            return true;
        if (b == 0 && onSegment(p1, q1, q2))
            return true;
        if (c == 0 && onSegment(p2, q2, p1))
            return true;
        if (d == 0 && onSegment(p2, q2, q1))
            return true;
        if (a != b && c != d)
            return true;
        return false;
    }

    public Vertex getStartVertex() {
        return road.getKey();
    }

    public Vertex getEndVertex() {
        return road.getValue();
    }
}
