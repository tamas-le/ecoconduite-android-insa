package model;

import java.io.IOException;

public class Speed {
	private volatile float speed; /* vitesse en km/h */
	private volatile float acceleration_intencity; /* intencité de l'accélération de -100% (freinage) à +100% (accélération) */
	private float max_accelaration;
	private float max_speed;
	
	public Speed(int max_acceleration, int max_speed){
		this.max_speed = max_speed;
		this.speed = 0;
		this.acceleration_intencity = 0;
		this.max_accelaration = max_acceleration;
	}
	
	
	
	/* GETTERS AND SETTERS */
	
	public float getMaxSpeed(){
		return this.max_speed;
	}
	
	public float getMaxAcceletation(){
		return this.max_accelaration;
	}
	
	public float getAccelerationIntencity(){
		return this.acceleration_intencity;
	}
	
	public void setAccelerationIntencity(float acceleration_intencity) throws IOException{
		if(-100 > acceleration_intencity || 100 < acceleration_intencity){
			throw new IOException("Invalid acceleration intencity !");
		}
		this.acceleration_intencity = acceleration_intencity;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getSpeed(){
		return this.speed;
	}
	
	
}
