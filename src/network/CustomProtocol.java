package network;

import java.awt.Point;

public class CustomProtocol {
	
	private int yRacket;
	private Point ballPosition;
	private boolean hasLift;
	private int liftSpeed ;
	private int ballSpeedY;
	
	
	//Constructeur à partir de variables
	public CustomProtocol(int yRacket, Point ballPosition, boolean haslift, int liftSpeed, int ballSpeedY){
		this.yRacket = yRacket;
		this.ballPosition = new Point(ballPosition);
		this.hasLift = haslift;
		this.liftSpeed = liftSpeed;
		this.ballSpeedY = ballSpeedY;
	}
	
	//Constructeur à partir de string (supposée déja parsée)
	public CustomProtocol(String s){
		
		if(!s.isEmpty()){
			String[] st = s.split(";");//Sépare dans un tableau de String les éléments de la string séparés de ";"
			
			if(st[0].equals("error")){
	
				this.yRacket = 0;
				this.ballPosition = new Point(0,0);
				this.hasLift = false;
				this.liftSpeed = 0;
				this.ballSpeedY = 0;
			}
			else{
				this.yRacket = Integer.parseInt(st[0]);
				this.ballPosition = new Point(Integer.parseInt(st[1]), Integer.parseInt(st[2]));
				this.hasLift = Boolean.valueOf(st[3]);
				this.liftSpeed = Integer.parseInt(st[4]);
				this.ballSpeedY = Integer.parseInt(st[5]);
			}
		}else{

			this.yRacket = 0;
			this.ballPosition = new Point(0,0);
			this.hasLift = false;
			this.liftSpeed = 0;
			this.ballSpeedY = 0;
		}
	}
	
	//Format du protocole : "<yracket>;<xBall>;<yBall>"
	public String toString(){
		
		String s = "";
		s += Integer.toString(this.yRacket);
		
		s += ";"; // Caractere de séparation
		
		s += Integer.toString((int)this.ballPosition.getX());
		
		s += ";"; // Caractere de séparation
		
		s += Integer.toString((int)this.ballPosition.getY());
		
		s += ";"; // Caractere de séparation
		
		s += Boolean.toString(this.hasLift);
		
		s += ";"; // Caractere de séparation
		
		s += Integer.toString(this.liftSpeed);
		
		s += ";"; // Caractere de séparation
		
		s += Integer.toString(this.ballSpeedY);
		
		return s;
	}
	
	public int getRacketY(){
		return this.yRacket;
	}
	
	public Point getBallPosition(){
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
