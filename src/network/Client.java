package network;

import gui.Racket;
import gui.Ball;

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
	public String getData(Racket r, Ball b){
		String data= "";
		//ajout en string de position de racket
		data +=Integer.toString(r.getWidth()) + Integer.toString(r.getHeight()) + Integer.toString(r.getSpeed()) + 
				(r.getPosition().x) + (r.getPosition().y);
		
		//ajout en string de position de ball
		data+= Integer.toString(b.getWidth()) + Integer.toString(b.getHeight()) + Integer.toString(b.getSpeed().x) +  
				Integer.toString(b.getSpeed().y) + (b.getPosition().x) + (b.getPosition().y);
		return data;
	}
	
}
