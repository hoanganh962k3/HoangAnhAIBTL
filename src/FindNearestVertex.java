
public class FindNearestVertex {
    public static int findNearVertex(Graph graph, Vertex start_point) {

        int potential_vertex = 0;
        Vertex Coordinates_potential = graph.coordinatesVertex[potential_vertex];
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            if (Vertex.Distance(start_point, Coordinates_potential) > Vertex.Distance(start_point,
                    graph.coordinatesVertex[i])) {
                potential_vertex = i;
                Coordinates_potential = graph.coordinatesVertex[potential_vertex];
            }
        }
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            for (int j = 0; j < graph.adjacencyList.get(i).size(); j++) {
                MapRoad road1 = new MapRoad(start_point, Coordinates_potential);
                MapRoad road2 = new MapRoad(graph.coordinatesVertex[i], graph.coordinatesVertex[j]);
                boolean isIntersection = road1.checkRoadIntersection(road1, road2);
                if (isIntersection == true) {
                    potential_vertex = i; // Chỗ này có thể return i or j đang suy nghĩ.
                    break;
                }
            }
        }
        return potential_vertex;
    }
}
