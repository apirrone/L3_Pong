package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	public Server(int port){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void waitForConnection(){
		try {
			serverSocket.setSoTimeout(30000);
			socket = serverSocket.accept();//Bloquant : Attend une connexion (timeout de 30 secondes)
			System.out.println("Connexion établie");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
