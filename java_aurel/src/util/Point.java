package util;

public class Point {
	
	private int stamp;
	private float speed;
	
	public Point(int stamp, float speed) {
		super();
		this.stamp = stamp;
		this.speed = speed;
	}
	
	
	public int getStamp() {
		return stamp;
	}



	public void setStamp(int stamp) {
		this.stamp = stamp;
	}



	public float getSpeed() {
		return speed;
	}



	public void setSpeed(float speed) {
		this.speed = speed;
	}



	@Override
	public String toString() {
		return "Stamp : "+stamp+" Speed :"+speed;
	}

	
}
