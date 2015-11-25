package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void waitForConnection(){
		try {
			serverSocket.setSoTimeout(30000);
			System.out.println("Attente de connexion, 30 secondes avant déconnexion\n");
			socket = serverSocket.accept();//Bloquant : Attend une connexion (timeout de 30 secondes)
			System.out.println("Connexion établie");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setData(CustomProtocol p){
		
		PrintWriter out;
		
		try{
			out = new PrintWriter(socket.getOutputStream());
			out.println(p.toString());
			out.flush();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public CustomProtocol getData(){
		BufferedReader in;
		
		CustomProtocol c = new CustomProtocol("error");
		try{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String messageRecu = in.readLine();
			c = new CustomProtocol(messageRecu);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return c;
	}

}
