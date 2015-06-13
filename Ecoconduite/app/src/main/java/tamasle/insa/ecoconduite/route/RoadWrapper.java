package tamasle.insa.ecoconduite.route;

import java.util.Arrays;

/**
 * Created by Aurelien on 05/05/2015.
 */
public class RoadWrapper {
    private Road [] roads;

    public Road[] getRoads() {
        return roads;
    }

    public void setRoads(Road[] roads) {
        this.roads = roads;
    }

    @Override
    public String toString() {
        return "RoadWrapper{" +
                "roads=" + Arrays.toString(roads) +
                '}';
    }
}
