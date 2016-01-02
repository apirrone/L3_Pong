package network;

import util.ExceptionPong;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {
	
	private Socket socket;
	private CustomProtocol protocol;
	private BufferedReader bufferIn;
	private PrintWriter writerOut;
	
	public Client() {
		protocol = new CustomProtocol();
	}
	
	public void connect(String address, int port) throws ExceptionPong {
		try {
			socket = new Socket(InetAddress.getByName(address), port);
		} catch (IOException e) {
			throw new ExceptionPong("ERROR : Problemes de connection au serveur OU serveur inexistant.");
		}
		try {
			bufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			throw new ExceptionPong("ERROR : Echec de lecture de l'input stream.");
		}
		try{
			writerOut = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			throw new ExceptionPong("ERROR : Socket non connectee OU erreur dans la creation de l'output stream.");
		}
	}
	
	public void setData(int yRacket, Point ballPosition, boolean haslift, int liftSpeed, int ballSpeedY) {
		protocol.setCustomProtocol(yRacket, ballPosition, haslift, liftSpeed, ballSpeedY, 0);
		writerOut.println(protocol.toString());
		writerOut.flush();
	}
	
	public CustomProtocol getData() throws ExceptionPong {
		try {
			String messageRecu = bufferIn.readLine();
			protocol.setCustomProtocol(messageRecu);
		} catch (IOException e) {
			protocol.setCustomProtocol("error");
			throw new ExceptionPong("ERROR : Problemes I/O avec bufferIn.");
		}
		return protocol;
	}
}