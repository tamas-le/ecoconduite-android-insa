package model;

import java.io.IOException;

public class Speed {
	private volatile float speed; /* vitesse en km/h */
	private volatile float acceleration_intencity; /* intencité de l'accélération de -100% (freinage) à +100% (accélération) */
	private float max_accelaration;
	private float max_speed;
	
	public Speed(float max_acceleration, float max_speed){
		this.max_speed = max_speed;
		this.speed = 0.0f;
		this.acceleration_intencity = 0.0f;
		this.max_accelaration = max_acceleration;
	}
	
	
	public float getSpeedloss(float start_speed, float time) throws Exception{
		
		float speed_loss = 0.0f;
		
		/*
		 * 
		 * Pour une vitesse dans [19.38,10.8]

			profil = [-0,000101873073634283	0,158091494458169	-98,1295067226028	
			30453,7914389142	-4725333,29976574	293265928,960533]

			t est dans [306.6,316.1]

			Pour une vitesse dans [10.8,0]

			profil =[-0,000335451321012932	0,134571979214965	-21,5589007787433	1724,07388051862	-68825,5779506290	1097273,85599249]

			t est dans [72.5,83.7]


		 */
		
		if(start_speed < 10.8f*3.6f && start_speed > 0.0f){
			
		}else if(start_speed >= 10.8f*3.6f && start_speed <= 19.38f*3.6f){
			
		}else{
			throw new Exception("Forces de frottement non connu pour cette vitesse.");
		}
		
		return speed_loss;
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
