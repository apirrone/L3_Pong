package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	private Socket socket;
	
	public Client(){
		
		
	}
	
	public void connect(String address, int port){
		try {
			socket = new Socket(InetAddress.getByName(address), port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
