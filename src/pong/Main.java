package pong;


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
		try {
			if(args.length == 0){//Serveur
				Server server;
				server = new Server(3008);
				server.waitForConnection();
				Pong pong = new Pong(server);
				Window window = new Window(pong);
				window.displayOnscreen();
			}
			else{//client
				Client client = new Client();
				String addr = args[0];
				client.connect(addr, 3008);
				Pong pong = new Pong(client);
				Window window = new Window(pong);
				window.displayOnscreen();
			}
		} catch (ExceptionPong e) {
			e.printStackTrace();
		}
	}
}
