package gui;

import util.RandomNumber;
import java.awt.Toolkit;
import java.awt.Point;

public class Bonus extends PongItem {
	
	private Point speed;
	private boolean inUse;
	private int applyBonus;
	
	/**
	 * Creation du bonus representee par un cadeau avec min et max l'intervalle des bonus a appliquer, un bool qui determine si
	 * le bonus est en utilisation ou non et sa position sur le pong 
	 */
	public Bonus(int min, int max, boolean serveur) {
		/*min et max sont en parametre si on souhaiterai eventuellement  utiliser uniquement certains bonus, comme par exemple des bonus plus puissant
		sous certaines conditions, applique dans BonusManagement de Pong*/
		super(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("ressource/cadeau.png")));
		this.inUse = true;
		if(serveur) {
			this.position.setLocation(400, 300);
			this.speed = new Point(1, 0);
		} else {
			this.position.setLocation(400, 300);
			this.speed = new Point(-1, 0);
		}
		//determine quel bonus sera applique a ce bonus quand il sera utilisé
		this.applyBonus = RandomNumber.randomValue(min, max);
	}
	
	/**
	 * Fonction permettant de determiner le bonus a appliquer sur les raquettes et balle
	 */
	private void getRandomBonus(BallType ball, RacketType racketPlayer, RacketType racketOpponent) {
		switch (applyBonus) {
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
	 * Acces à la variable quel bonus a appliquer si le bonus est utilise:
	 * Si 1 la vitesse de la balle est augmente
	 * Si 2 la vitesse de la balle est ralentie
	 * Si 3 La vitesse de la raquette touchant le bonus est augmente
	 * Si 4 la vitesse de la raquette touchant le bonus est diminue
	 */
	public int getApplyBonus(){
		return this.applyBonus;
	}
	
	/** 
	 * True si le bonus est toujours sur le pong, 
	 * False si le bonus a traverse le pong et a ete utilise ou detruit
	 */
	public boolean getInUse() {
		return inUse;
	}
	
	/**
	 * Actualisation du Pong en fonction du bonus
	 */
	public void updateScreen(Pong pong){
		pong.graphicContext.drawImage(this.getImage(), this.getPosition().x, this.getPosition().y, this.getWidth(), this.getHeight(), null);
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