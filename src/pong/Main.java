package pong;


import java.net.InetAddress;
import java.net.UnknownHostException;

import network.Client;
import network.Server;
import gui.Window;
import gui.Pong;

/**
 * Starting point of the Pong application
 */
public class Main  {
	public static void main(String[] args) {
		
		if(args.length == 0){//Serveur
			Server server = new Server(2009);
			server.waitForConnection();
		}
		else{//client
			Client client = new Client();
			String addr = args[0];
			client.connect(addr, 2009);
		}
		
		Pong pong = new Pong();
		Window window = new Window(pong);
		window.displayOnscreen();
	}
}
