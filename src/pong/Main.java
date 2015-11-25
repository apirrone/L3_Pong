package pong;


import java.net.*;
import java.io.*;
import network.Client;
import network.Server;
import gui.Window;
import gui.Pong;

/**
 * Starting point of the Pong application
 */
public class Main  {
	public static void main(String[] args) {
		
		Pong pong = new Pong();
		if(args.length == 0){//Serveur
			Server server = new Server(2009);
			server.waitForConnection();
			server.setData();
			
		}
		else{//client
			Client client = new Client();
			String addr = args[0];
			client.connect(addr, 2009);
			client.getData();
			
		}
		Window window = new Window(pong);
		window.displayOnscreen();
	}
}
