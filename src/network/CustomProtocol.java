package network;

import java.awt.Point;

public class CustomProtocol {
	
	private int yRacket;
	private Point ballPosition;
	private boolean hasLift;
	private int liftSpeed ;
	private int ballSpeedY;
	private long timeToRand;
	
	public CustomProtocol() {
		this.yRacket = 0;
		this.ballPosition = new Point(0,0);
		this.hasLift = false;
		this.liftSpeed = 0;
		this.ballSpeedY = 0;
		this.timeToRand = 0;
	}
	
	/** 
	 * Fonction d'ajout des variables au CustomProtocol
	 */
	public void setCustomProtocol(int yRacket, Point ballPosition, boolean haslift, int liftSpeed, int ballSpeedY, long timeToRand) {
		this.yRacket = yRacket;
		this.ballPosition.setLocation(ballPosition);
		this.hasLift = haslift;
		this.liftSpeed = liftSpeed;
		this.ballSpeedY = ballSpeedY;
		this.timeToRand = timeToRand;
	}
	
	/** 
	 * Fonction de conversion d'une string en CustomProtocol
	 */
	public void setCustomProtocol(String s) {
		if(!s.isEmpty() && !s.equals("error")) {
			// Separe dans un tableau de String les elements de la chaine "s" separes par ";"
			String[] st = s.split(";");
			this.yRacket = Integer.parseInt(st[0]);
			this.ballPosition.setLocation(Integer.parseInt(st[1]), Integer.parseInt(st[2]));
			this.hasLift = Boolean.valueOf(st[3]);
			this.liftSpeed = Integer.parseInt(st[4]);
			this.ballSpeedY = Integer.parseInt(st[5]);
			this.timeToRand = Long.parseLong(st[6]);
		} else {
			this.yRacket = 0;
			this.ballPosition.setLocation(0,0);
			this.hasLift = false;
			this.liftSpeed = 0;
			this.ballSpeedY = 0;
			this.timeToRand = 0;
		}
	}
	
	/** 
	 * Fonction de conversion d'une string en CustomProtocol
	 */
	public void setCustomProtocol(byte[] b) {
		if(b.length != 0 && !b.toString().equals("error")) {
			// Separe dans un tableau de String les elements de la chaine "s" separes par ";"
			String s = b.toString();
			String[] st = s.split(";");
			this.yRacket = Integer.parseInt(st[0]);
			this.ballPosition.setLocation(Integer.parseInt(st[1]), Integer.parseInt(st[2]));
			this.hasLift = Boolean.valueOf(st[3]);
			this.liftSpeed = Integer.parseInt(st[4]);
			this.ballSpeedY = Integer.parseInt(st[5]);
			this.timeToRand = Long.parseLong(st[6]);
		} else {
			this.yRacket = 0;
			this.ballPosition.setLocation(0,0);
			this.hasLift = false;
			this.liftSpeed = 0;
			this.ballSpeedY = 0;
			this.timeToRand = 0;
		}
	}
	
	/** 
	 * Format du protocole : "<yRacket>;<xBall>;<yBall>;<hasLift>;<liftSpeed>;<yBallSpeed>;<timeToRand>"
	 */
	public String toString() {
		String s = "";
		s += Integer.toString(this.yRacket);
		// Caractere de separation : ";"
		s += ";";
		s += Integer.toString((int)this.ballPosition.getX());
		s += ";";
		s += Integer.toString((int)this.ballPosition.getY());
		s += ";";
		s += Boolean.toString(this.hasLift);
		s += ";";
		s += Integer.toString(this.liftSpeed);
		s += ";";
		s += Integer.toString(this.ballSpeedY);
		s += ";";
		s += Long.toString(this.timeToRand);
		return s;
	}

	public byte[] toByteArray(){
		byte[] ret;
		ret = this.toString().getBytes();
		return ret;
	}
	
	/**
	 * Get / Set accesseurs des attributs
	 */
	public int getRacketY() {
		return this.yRacket;
	}
	
	public Point getBallPosition() {
		return this.ballPosition;
	}

	public boolean getHasLift() {
		return hasLift;
	}
	
	public int getLiftSpeed() {
		return liftSpeed;
	}
	
	public int getBallSpeedY() {
		return ballSpeedY;
	}
	
	public long getTime() {
		return timeToRand;
	}
}