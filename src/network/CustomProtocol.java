package network;

import java.awt.Point;

public class CustomProtocol {
	
	private int yRacket;
	private Point ballPosition;
	private boolean hasLift;
	private int liftSpeed ;
	private int ballSpeedY;
	
	public CustomProtocol() {
		this.yRacket = 0;
		this.ballPosition = new Point(0,0);
		this.hasLift = false;
		this.liftSpeed = 0;
		this.ballSpeedY = 0;
	}
	
	public void setCustomProtocol(int yRacket, Point ballPosition, boolean haslift, int liftSpeed, int ballSpeedY) {
		this.yRacket = yRacket;
		this.ballPosition = new Point(ballPosition);
		this.hasLift = haslift;
		this.liftSpeed = liftSpeed;
		this.ballSpeedY = ballSpeedY;
	}
	
	/** 
	 * Fonction d'ajout des variables au CustomProtocol
	 */
	public void setCustomProtocol(String s) {
		if(!s.isEmpty() && !s.equals("error")) {
			// Separe dans un tableau de String les elements de la chaine "s" separes par ";"
			String[] st = s.split(";");
			this.yRacket = Integer.parseInt(st[0]);
			this.ballPosition = new Point(Integer.parseInt(st[1]), Integer.parseInt(st[2]));
			this.hasLift = Boolean.valueOf(st[3]);
			this.liftSpeed = Integer.parseInt(st[4]);
			this.ballSpeedY = Integer.parseInt(st[5]);
		} else {
			this.yRacket = 0;
			this.ballPosition = new Point(0,0);
			this.hasLift = false;
			this.liftSpeed = 0;
			this.ballSpeedY = 0;
		}
	}
	
	/** 
	 * Format du protocole : "<yRacket>;<xBall>;<yBall>;<hasLift>;<liftSpeed>;<yBallSpeed>"
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
		return s;
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
}