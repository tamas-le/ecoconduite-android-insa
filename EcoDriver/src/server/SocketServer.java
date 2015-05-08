package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Controller.Controller;



public class SocketServer extends Thread{
	
	private Controller controller;
	private int SERVERPORT = 5657;
	private ServerSocket serverSocket;
	private Socket client = null;
	private boolean running = false;
	private PrintWriter mOut;
	

	public SocketServer(Controller controller){		
		this.controller = controller;
	}

	public void messageReceived(String message){
		System.out.println("Msg Recieved");
		this.controller.processSpeedRequest(message);
	}

	public void sendMessage(String message){
		
		try{
			if (mOut != null && !mOut.checkError()){
				System.out.println(message);
				// Here you can connect with database or else you can do what you want with static message
				mOut.println(message);
				mOut.flush();
			}
		}
		catch (Exception e){
			
		}
	}
	
	public void performSpeedSending(float speed){
		String messageText = Float.toString(speed);
		this.sendMessage(messageText);
	}


	@Override
	public void run(){
		
		super.run();
		running = true;
		
		try{
			
			System.out.println("PA: Connecting...");

			// create a server socket. A server socket waits for requests to
			// come in over the network.
			serverSocket = new ServerSocket(SERVERPORT);

			// create client socket... the method accept() listens for a
			// connection to be made to this socket and accepts it.
			try{
				client = serverSocket.accept();
				System.out.println("S: Receiving...");
				// sends the message to the client
				mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
				System.out.println("PA: Sent");
				System.out.println("PA: Connecting Done.");
				// read the message received from client
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				sendMessage("Server connected with Android Client now you can chat with socket server.");
				sendMessage(this.controller.getPath());
				
				// in this while we wait to receive messages from client (it's an infinite loop)
				// this while it's like a listener for messages
				while (running){
					String message = in.readLine();
					if (message != null){
						// call the method messageReceived from ServerBoard class
						this.messageReceived(message);
					}
				}
			}
			catch (Exception e){
				System.out.println("PA: Error: "+e.getMessage());
				e.printStackTrace();
			}
			finally{
				client.close();
				System.out.println("PA: Done.");
			}
		}
		catch (Exception e){
			System.out.println("PA: Error");
			e.printStackTrace();
		}

	}

}
