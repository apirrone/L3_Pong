package gui;

import util.RandomNumber;
import java.awt.Toolkit;
import java.awt.Point;



public class Bonus extends PongItem{
	
	private Point speed;
	private int min;
	private int max;
	private boolean inUse;
	
	public Bonus(int min, int max, BallType ball,RacketType racketPlayer,RacketType racketOpponent, boolean serveur){
		super(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("ressource/cheque-cadeau.png")));
		this.min = min;
		this.max = max;
		this.inUse = true;
		if (serveur){
			this.position.setLocation(400, RandomNumber.randomValue(50, 500));
			this.speed = new Point(1, 0);
		}else{
			this.position.setLocation(400, RandomNumber.randomValue(50, 500));
			this.speed = new Point(-1, 0);
		}
	}
	private void getRandomBonus(int min, int max, BallType ball, RacketType racketPlayer, RacketType racketOpponent){
		int random = RandomNumber.randomValue(min, max);
		switch (random){
		case 1:
			increaseSpeedBall(ball);
			break;
		case 2:
			decreaseSpeedBall(ball);
			break;
		case 3:
			increaseSpeedRacket(racketPlayer);
			break;
		default:
			System.out.print("no bonus");
	}
	}
	public boolean getInUse(){
		return inUse;
	}
	public void moveBonus(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent, BallType ball){
		for(int i=Math.abs(speed.x); i>0; i--){
			for(int j=(Math.abs(speed.y)/Math.abs(speed.x)); j>0; j--){
				if(racketPlayer.itemOnRacketCote(this) || racketPlayer.itemOnRacketHaut(this) || racketPlayer.itemOnRacketCorner(this)){
					getRandomBonus(min, max, ball, racketPlayer, racketOpponent);
					deleteBonus();
					System.out.print("bonus utilisé\n");
				}
				if(racketOpponent.itemOnRacketCote(this) || racketOpponent.itemOnRacketHaut(this) || racketOpponent.itemOnRacketCorner(this)){
					getRandomBonus(min, max, ball,racketOpponent, racketPlayer);
					deleteBonus();
					System.out.print("bonus utilisé\n");
				}
				this.position.translate(0, speed.y/Math.abs(speed.y));
			}
			this.position.translate(speed.x/Math.abs(speed.x), 0);
			if (position.x < 0 || position.x > size_pong_x - width)
				deleteBonus();
		}	
	}
	
	public void updateScreenBonus(Pong pong){
		pong.graphicContext.drawImage(this.getImage(), this.getPosition().x, this.getPosition().y, this.getWidth(), this.getHeight(), null);
	}
		
	public void increaseSpeedBall(BallType ball){
		ball.setSpeed(ball.getSpeed().x* 2,ball.getSpeed().y*2);
		System.out.print("la vitesse de la balle a été augmenté par 2!\n");
			}
	public void decreaseSpeedBall(BallType ball){
		ball.setSpeed(ball.getSpeed().x/2,ball.getSpeed().y/2);
		System.out.print("La vitesse de la balle a été divisé par 2!\n");
			}
	public void increaseSpeedRacket(RacketType racket){
		racket.setSpeed(racket.getSpeed()*2);
		System.out.print("La vitesse de la raquette a été multiplié par 2!\n");
	}
	private void deleteBonus(){
		 inUse = false;
		 System.out.print("Le bonus a été utilisé ou détruit\n");
	}
	}	
