
import java.util.ArrayList;

public class Distance {
    private ArrayList<ArrayList<Double>> distanceMatrix;

    public Distance() {
        distanceMatrix = new ArrayList<>(Constants.ARRAY_SIZE);
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            distanceMatrix.add(new ArrayList<>());
        }
        for (int i = 0; i < Constants.ARRAY_SIZE; i++) {
            for (int j = 0; j < Constants.ARRAY_SIZE; j++) {
                distanceMatrix.get(i).add(-1.0);
            }
        }

    }
}
