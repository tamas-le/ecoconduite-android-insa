package Controller;

import java.net.ServerSocket;

import model.Speed;
import server.SocketServer;
import GUI.Window;

public class Controller {
	private SocketServer server;
	private Window window;
	private String path;
	private Speed speed;
	
	public Controller(String path){
		// On lance le server
		this.speed = new Speed();
		this.path = path;
		this.server = new SocketServer(this);
		this.server.start();
		
		// On crée la fenêtre
		this.window = new Window(path);
	}
	
	public void processSpeedRequest(String message){
		this.server.performSpeedSending(this.speed.getSpeed());
	}
	
	public String getPath(){
		return this.path;
	}
}
