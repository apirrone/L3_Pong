package network;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import util.ExceptionPong;
import java.io.PrintWriter;

public class Server {

	private static final int TIME_MAX_TO_WAIT_CONNECTION = 30000;
	
	private ServerSocket serverSocket;
	private Socket socket;
	private CustomProtocol protocol;
	private BufferedReader bufferIn;
	private PrintWriter writerOut;
	
	public Server(int port) throws ExceptionPong {
		protocol = new CustomProtocol();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new ExceptionPong("ERROR : La socket n'a pas pu s'ouvrir, cause : "+e.getMessage());
		}
	}
	
	/**
	 * Le serveur va attendre une connexion pendant "TIME_MAX_TO_WAIT_CONNECTION" secondes
	 */
	public void waitForConnection() throws ExceptionPong {
		try {
			serverSocket.setSoTimeout(TIME_MAX_TO_WAIT_CONNECTION);
			System.out.println("Attente de connexion, "+TIME_MAX_TO_WAIT_CONNECTION+" secondes avant deconnexion");
			socket = serverSocket.accept();
			System.out.println("Connexion Etablie");
		} catch (IOException e) {
			throw new ExceptionPong("ERROR : Aucun client ne s'est connecte.");
		}
		try {
			bufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException e) {
			throw new ExceptionPong("ERROR : Echec de lecture de l'input stream.");
		}
		try {
			writerOut = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			throw new ExceptionPong("ERROR : Socket non connectee OU erreur dans la creation de l'output stream.");
		}
	}

	public void setData(int yRacket, Point ballPosition, boolean haslift, int liftSpeed, int ballSpeedY, long timeToRand) {
		protocol.setCustomProtocol(yRacket, ballPosition, haslift, liftSpeed, ballSpeedY, timeToRand);
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