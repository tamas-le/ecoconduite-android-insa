package tamasle.insa.ecoconduite.route;

/**
 * Created by Aurelien on 05/05/2015.
 */
public class Segment {
    private Integer max_speed;
    private Integer length;

    public Segment(Integer max_speed, Integer length) {
        this.max_speed = max_speed;
        this.length = length;
    }


    public Integer getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(Integer max_speed) {
        this.max_speed = max_speed;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "max_speed=" + max_speed +
                ", length=" + length +
                '}';
    }
}
