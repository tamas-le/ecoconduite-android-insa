package tamasle.insa.ecoconduite.route;

import java.util.Arrays;

/**
 * Created by Aurélien on 28/04/2015.
 */
public class Road {
    private String name;
    private String length_unit;
    private String max_speed_unit;
    private Segment [] segments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLenght_unit() {
        return length_unit;
    }

    public void setLenght_unit(String lenght_unit) {
        this.length_unit = lenght_unit;
    }

    public String getMax_speed_unit() {
        return max_speed_unit;
    }

    public void setMax_speed_unit(String max_speed_unit) {
        this.max_speed_unit = max_speed_unit;
    }

    public Segment[] getSegments() {
        return segments;
    }

    public void setSegments(Segment[] segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", lenght_unit='" + length_unit + '\'' +
                ", max_speed_unit='" + max_speed_unit + '\'' +
                ", segments=" + Arrays.toString(segments) +
                '}';
    }
}
