package gui;

import util.RandomNumber;
import java.awt.Toolkit;
import java.awt.Point;

public class Bonus extends PongItem {
	
	private Point speed;
	private int min;
	private int max;
	private boolean inUse;
	
	/**
	 * Creation du bonus representee par un cadeau avec min et max l'intervalle des bonus a appliquer, un bool qui determine si
	 * le bonus est en utilisation ou non et sa position sur le pong 
	 */
	public Bonus(int min, int max, BallType ball,RacketType racketPlayer,RacketType racketOpponent, boolean serveur) {
		super(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("ressource/cadeau.png")));
		this.min = min;
		this.max = max;
		this.inUse = true;
		if(serveur) {
			this.position.setLocation(400, 300);
			this.speed = new Point(1, 0);
		} else {
			this.position.setLocation(400, 300);
			this.speed = new Point(-1, 0);
		}
	}
	
	/**
	 * Fonction permettant de determiner le bonus a appliquer sur les raquettes et balle
	 */
	private void getRandomBonus(BallType ball, RacketType racketPlayer, RacketType racketOpponent) {
		int random = RandomNumber.randomValue(min, max);
		switch (random) {
			case 1:
				increaseSpeedBall(ball);
				break;
			case 2:
				decreaseSpeedBall(ball);
				break;
			case 3:
				increaseSpeedRacket(racketPlayer);
				break;
			case 4:
				decreaseSpeedRacket(racketPlayer);
				break;
			default:
				System.out.print("no bonus");
		}
	}
	
	/** 
	 * True si le bonus est toujours sur le pong, 
	 * False si le bonus a traverse le pong et a ete utilise ou detruit
	 */
	public boolean getInUse() {
		return inUse;
	}
	
	public void moveBonus(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
		for(int i=Math.abs(speed.x); i>0; i--) {
			//si le bonus touche l'une des raquettes on applique le bonus
			if(racketPlayer.itemOnRacketCote(this)) {
				getRandomBonus(ball, racketPlayer, racketOpponent);
				deleteBonus();
			}
			if(racketOpponent.itemOnRacketCote(this)) {
				getRandomBonus(ball, racketOpponent, racketPlayer);
				deleteBonus();
			}
			for(int j=(Math.abs(speed.y)/Math.abs(speed.x)); j>0; j--) {
				if(racketPlayer.itemOnRacketHaut(this) || racketPlayer.itemOnRacketCorner(this)) {
					getRandomBonus(ball, racketPlayer, racketOpponent);
					deleteBonus();
				}
				if(racketOpponent.itemOnRacketHaut(this) || racketOpponent.itemOnRacketCorner(this)) {
					getRandomBonus(ball,racketOpponent, racketPlayer);
					deleteBonus();
				}
				this.position.translate(0, speed.y/Math.abs(speed.y));
			}
			this.position.translate(speed.x/Math.abs(speed.x), 0);
			//si le bonus touche le bord de l'ecran a gauche ou a droite il est efface
			if (position.x < 0 || position.x > size_pong_x - width)
				deleteBonus();
		}	
	}
	
	/**
	 * Liste des bonus applicable aux raquettes ou a la balle
	 */
	private void increaseSpeedBall(BallType ball) {
		ball.setSpeed(ball.getSpeed().x* 2, ball.getSpeed().y*2);
		System.out.print("la vitesse de la balle a ete augmente par 2!\n");
	}
	
	private void decreaseSpeedBall(BallType ball) {
		ball.setSpeed(ball.getSpeed().x/2, ball.getSpeed().y/2);
		System.out.print("La vitesse de la balle a ete divise par 2!\n");
	}
	
	private void increaseSpeedRacket(RacketType racket) {
		racket.setSpeed(racket.getSpeed()*2);
		System.out.print("La vitesse de la raquette a ete multiplie par 2!\n");
	}
	
	private void decreaseSpeedRacket(RacketType racket) {
		racket.setSpeed(racket.getSpeed()/2);
		System.out.print("La vitesse de la raquette a ete divise par 2!\n");
	}
	
	private void deleteBonus() {
		 inUse = false;
		 System.out.print("Le bonus a ete utilise ou detruit\n");
	}
}	