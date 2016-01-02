package gui;

import util.RandomNumber;
import java.awt.Toolkit;
import java.awt.Point;

public class Bonus extends PongItem {

	private static final int BONUS_BASE_POSITION_X = 386;
	private static final int BONUS_BASE_SPEED_X = 1;

	public static final int BONUS_MAX_POSITION_Y  = 570;
	public static final int NUMBER_OF_BONUS = 6;
	
	private Point speed;
	private boolean inUse;
//	private int applyBonus;
	
	/**
	 * 
	 */
	public Bonus() {
		super(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("ressource/cadeau.png")));
		this.inUse = false;
		this.speed = new Point(0, 0);
		this.position.setLocation(BONUS_BASE_POSITION_X, 0);
	}
	
	public void setBonus (boolean serveur, long positionY /* , int numBonus */) {
		inUse = true;
		position.setLocation(BONUS_BASE_POSITION_X, (int)positionY);
		if (serveur)
			speed.setLocation(-BONUS_BASE_SPEED_X, 0);
		else 
			speed.setLocation(BONUS_BASE_SPEED_X, 0);
//		this.applyBonus = (int) numBonus;
		
		/*
		if(serveur) {
			this.position.setLocation(BONUS_BASE_POSITION_X, 300);
			this.speed = new Point(BONUS_BASE_SPEED_X, 0);
		} else {
			this.position.setLocation(BONUS_BASE_POSITION_X, 300);
			this.speed = new Point(-BONUS_BASE_SPEED_X, 0);
		}
		//determine quel bonus sera applique a ce bonus quand il sera utilis�
		this.applyBonus = RandomNumber.randomValue(min, max);
		*/
	}
	
	/**
	 * Fonction permettant de determiner le bonus a appliquer sur les raquettes et balle
	 */
	private void getRandomBonus(BallType ball, RacketType racketPlayer, RacketType racketOpponent) {
		int min = 0;
		int max = NUMBER_OF_BONUS;
		int bonusId = min + (int)(Math.random() * ((max - min) + 1));
		switch (bonusId) {
			case 0:
				increaseSpeedBall(ball);
				break;
			case 1:
				decreaseSpeedBall(ball);
				break;
			case 2:
				increaseSpeedRacket(racketPlayer);
				break;
			case 3:
				decreaseSpeedRacket(racketPlayer);
				break;
			case 4:
				increaseSizeRacket(racketPlayer);
				break;
			case 5:
				decreaseSizeRacket(racketPlayer);
				break;
			default:
				System.out.println("no bonus");
		}
	}
	
	/**
	 * Acces � la variable quel bonus a appliquer si le bonus est utilise:
	 * Si 0 la vitesse de la balle est augmente
	 * Si 1 la vitesse de la balle est ralentie
	 * Si 2 La vitesse de la raquette touchant le bonus est augmentée
	 * Si 3 la vitesse de la raquette touchant le bonus est diminuée
	 * Si 4 la taille de la raquette touchant le bonus est augmentée
	 * Si 5 la taille de la raquette touchant le bonus est diminuée
	 */
//	public int getApplyBonus(){
//		return this.applyBonus;
//	}
	
	/** 
	 * True si le bonus est toujours sur le pong, 
	 * False si le bonus a traverse le pong et a ete utilise ou detruit
	 */
	public boolean getInUse() {
		return inUse;
	}
	
	public void moveBonus(RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
		for (int i=Math.abs(speed.x); i>0; i--) {
			// Si le bonus touche l'une des raquettes on applique le bonus
			if (racketPlayer.itemOnRacketCote(this) || racketPlayer.itemOnRacketCorner(this)) {
				getRandomBonus(ball, racketPlayer, racketOpponent);
				deleteBonus();
			}
			if (racketOpponent.itemOnRacketCote(this) || racketOpponent.itemOnRacketCorner(this)) {
				getRandomBonus(ball, racketOpponent, racketPlayer);
				deleteBonus();
			}
			for (int j=(Math.abs(speed.y)/Math.abs(speed.x)); j>0; j--) {
				if (racketPlayer.itemOnRacketHaut(this) || racketPlayer.itemOnRacketCorner(this)) {
					getRandomBonus(ball, racketPlayer, racketOpponent);
					deleteBonus();
				}
				if (racketOpponent.itemOnRacketHaut(this) || racketOpponent.itemOnRacketCorner(this)) {
					getRandomBonus(ball,racketOpponent, racketPlayer);
					deleteBonus();
				}
				this.position.translate(0, speed.y/Math.abs(speed.y));
			}
			this.position.translate(speed.x/Math.abs(speed.x), 0);
			// Si le bonus touche le bord de l'ecran a gauche ou a droite il est efface
			if (position.x < (RacketType.RACKET_PLAYER_BASE_POSITION_X + racketPlayer.getWidth()) || position.x > (RacketType.RACKET_OPPONENT_BASE_POSITION_X - width))
				deleteBonus();
		}	
	}
	
	/**
	 * Liste des bonus applicable aux raquettes ou a la balle
	 */
	private void increaseSpeedBall(BallType ball) {
		ball.setSpeed(ball.getSpeed().x* 2, ball.getSpeed().y*2);
		System.out.println("la vitesse de la balle a ete augmente par 2!");
	}
	
	private void decreaseSpeedBall(BallType ball) {
		if (Math.abs(ball.getSpeed().x) != 1) {
			ball.setSpeed(ball.getSpeed().x/2, ball.getSpeed().y/2);
			System.out.println("La vitesse de la balle a ete divise par 2!");
		} else {
			System.out.println("Reduction de la vitesse de la balle impossible. La balle est deja trop lente");
		}
	}
	
	private void increaseSpeedRacket(RacketType racket) {
		racket.setSpeedMax(racket.getSpeedMax()*2);
		System.out.println("La vitesse de la raquette a ete multiplie par 2!");
	}
	
	private void decreaseSpeedRacket(RacketType racket) {
		if (Math.abs(racket.getSpeedMax()) != 1) {
			racket.setSpeedMax(racket.getSpeedMax()/2);
			System.out.println("La vitesse de la raquette a ete divise par 2!");
		} else
			System.out.println("Reduction de la vitesse de la raquette impossible. La raquette est deja trop lente");
	}
	
	private void increaseSizeRacket(RacketType racket) {
		racket.multiplyRacket();
	}
	
	private void decreaseSizeRacket(RacketType racket) {
		racket.divideRacket();
	}
	
	private void deleteBonus() {
		 inUse = false;
		 System.out.println("Le bonus a ete utilise ou detruit");
	}
}	