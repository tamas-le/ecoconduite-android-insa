package Controller;


import model.Speed;
import server.SocketServer;
import GUI.Window;

public class Controller {
	private SocketServer server;
	private Window window;
	private String path;
	private Speed speed;
	
	public Controller(){
		// On lance le server
		this.speed = new Speed(5.5f, 70.0f);
		//this.path = path;
		this.server = new SocketServer(this);
		//this.server.start();
		
		// On crée la fenêtre
		this.window = new Window(this);
		
		this.manageSpeed();
		
		
	}
	
	public void manageSpeed(){
		//attente de modification sur acceleration intencity
		//On modifie la vitesse mais si on a une modification d'acceleration intensity on change de dinamyqieu
		
		float current_speed = 0.0f;
		
		while(true){
			try {

				Thread.sleep(500);
				
				current_speed = 3.6f*(
						(this.speed.getAccelerationIntencity()/100.0f)*
						(this.speed.getMaxAcceletation())*0.5f
						)
						+ this.speed.getSpeed();

				if(current_speed > this.speed.getMaxSpeed()){
					current_speed = this.speed.getMaxSpeed();
				}else if(current_speed < 0){
					current_speed = 0.0f;
				}
				
				this.speed.setSpeed(current_speed);
				this.window.processSpeedModification(current_speed);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
	}
	
	public void processSpeedRequest(String message){
		this.server.performSpeedSending(this.speed.getSpeed());
	}
	
	public void processAccelerationIntensitymodification(float acceleration_intensity){
		try{
			this.speed.setAccelerationIntencity(acceleration_intensity);
		}catch(Exception e){
			System.out.println("Invalid acceleration intensity." + e.getMessage());
		}	
	
	}
	
	public String getPath(){
		return this.path;
	}
}
