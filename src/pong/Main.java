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
		
		Pong pong = new Pong(args);
		Window window = new Window(pong);
		window.displayOnscreen();
	}
}
