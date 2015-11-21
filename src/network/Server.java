package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;

public class Server{
	
	private ServerSocket serverSocket;
	private Socket socket;
	private PrintWriter out;
	
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
	
	public void setData(){
		try{
			out = new PrintWriter(socket.getOutputStream());
			out.println("Vous êtes connecté");
			out.flush();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
