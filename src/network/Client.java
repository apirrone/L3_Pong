package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	private Socket socket;
	
	public Client(){
		
		
	}
	
	public void connect(InetAddress address, int port){
		try {
			socket = new Socket(address, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
