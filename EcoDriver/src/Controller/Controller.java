package Controller;

import java.net.ServerSocket;
import GUI.Window;

public class Controller {
	private ServerSocket server;
	private Window window;
	private String path;
	
	public Controller(String path){
		this.window = new Window(path);
	}
}
