package tamasle.insa.ecoconduite.route;


/**
 * Created by Aurelien on 05/05/2015.
 */

public class SegmentImproved extends Segment {
	private Integer distance;
	
	public SegmentImproved(Segment s,int d){
		super(s.getMax_speed(),s.getLength());
		this.distance=d;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "SegmentImproved [distance=" + distance 
				+ ", toString()=" + super.toString() +"]";
	}
	
	
	
	

}
