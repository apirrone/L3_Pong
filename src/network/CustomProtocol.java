package network;

import java.awt.Point;

public class CustomProtocol {
	
	private int yRacket;
	private Point ballPosition;
	
	
	//Constructeur à partir de variables
	public CustomProtocol(int yRacket, Point ballPosition){
		this.yRacket = yRacket;
		this.ballPosition = ballPosition;
	}
	
	//Constructeur à partir de string (supposée déja parsée)
	public CustomProtocol(String s){
		
		if(!s.isEmpty()){
			String[] st = s.split(";");//Sépare dans un tableau de String les éléments de la string séparés de ";"
			
			if(st[0].equals("error")){
	
				this.yRacket = 0;
				this.ballPosition = new Point(0,0);
			}
			else{
				this.yRacket = Integer.parseInt(st[0]);
				this.ballPosition = new Point(Integer.parseInt(st[1]), Integer.parseInt(st[2]));
			}
		}else{

			this.yRacket = 0;
			this.ballPosition = new Point(0,0);
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
		
		return s;
	}
	
	public int getRacketY(){
		return this.yRacket;
	}
	
	public Point getBallPosition(){
		return this.ballPosition;
	}
	
}
