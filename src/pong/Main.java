package pong;

import network.Client;
import network.Server;
import util.ExceptionPong;
import gui.Window;
import gui.Pong;

/**
 * Application pour jouer au Pong
 */
public class Main {
	
	public static void main(String[] args) {
		try {
			if(args.length == 0) {
				// Je suis le serveur
				Server server;
				server = new Server(3008);
				server.waitForConnection();
				Pong pong = new Pong(server);
				Window window = new Window(pong);
				window.displayOnscreen();
			} else {
				// Je suis le client
				Client client = new Client();
				String addr = args[0];
				client.connect(addr, 3008);
				Pong pong = new Pong(client);
				Window window = new Window(pong);
				window.displayOnscreen();
			}
		} catch (ExceptionPong e) {
//			e.printStackTrace();
		}
	}
}