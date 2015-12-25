package pong;


import java.net.*;
import java.io.*;
import network.Client;
import network.Server;
import util.ExceptionPong;
import gui.Window;
import gui.Pong;

/**
 * Starting point of the Pong application
 */
public class Main  {
	public static void main(String[] args) {
		
		if(args.length == 0){//Serveur
			Server server;
			try {
				server = new Server(2008);
				server.waitForConnection();
				Pong pong = new Pong(server);
				Window window = new Window(pong);
				window.displayOnscreen();
			} catch (ExceptionPong e) {
				e.printStackTrace();
			}
			
		}
		else{//client
			Client client = new Client();
			String addr = args[0];
			client.connect(addr, 2008);
			Pong pong = new Pong(client);
			Window window = new Window(pong);
			window.displayOnscreen();
			
		}
	}
	

}
