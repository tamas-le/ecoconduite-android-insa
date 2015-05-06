package model;

import java.io.IOException;

public class Speed {
	private int speed; /* vitesse en km/h */
	private int acceleration_intencity; /* intencité de l'accélération de -100% (freinage) à +100% (accélération) */
	
	public Speed(){
		this.speed = 0;
		this.acceleration_intencity = 0;
	}
	
	
	
	/* GETTERS AND SETTERS */
	
	public int getAccelerationIntencity(int acceleration_intencity){
		return this.acceleration_intencity;
	}
	
	public void setAccelerationIntencity(int acceleration_intencity) throws IOException{
		if(-100 > acceleration_intencity || 100 < acceleration_intencity){
			throw new IOException("Invalid acceleration intencity !");
		}
		this.acceleration_intencity = acceleration_intencity;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	
}
