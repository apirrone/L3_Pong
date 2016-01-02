package gui;

import java.awt.Image;
import java.awt.Toolkit;

import util.ExceptionPong;

public class Racket extends RacketType{

	private static final Image PONG_BARRE_NIV_P1 = Toolkit.getDefaultToolkit().createImage(
			ClassLoader.getSystemResource("ressource/barrePongMultNivP1.png"));
	private static final Image PONG_BARRE_NIV_0 = Toolkit.getDefaultToolkit().createImage(
			ClassLoader.getSystemResource("ressource/barrePong.png"));
	private static final Image PONG_BARRE_NIV_M1 = Toolkit.getDefaultToolkit().createImage(
			ClassLoader.getSystemResource("ressource/barrePongDivNivM1.png"));
	private static final Image PONG_BARRE_NIV_M2 = Toolkit.getDefaultToolkit().createImage(
			ClassLoader.getSystemResource("ressource/barrePongDivNivM2.png"));
	
	public Racket(boolean player) {
		super(PONG_BARRE_NIV_0, player);
	}

	public void restartImageRacket() {
		setImage(PONG_BARRE_NIV_0);
	}

	public void divideRacket() {
		if (getImage().equals(PONG_BARRE_NIV_P1)) {
			setImage(PONG_BARRE_NIV_0);
			System.out.println("La taille de la raquette a ete divise par 2!");
		}
		else {
			if (getImage().equals(PONG_BARRE_NIV_0)) {
				setImage(PONG_BARRE_NIV_M1); 
				System.out.println("La taille de la raquette a ete divise par 2!");
				}
			else {
				if (getImage().equals(PONG_BARRE_NIV_M1)) {
					setImage(PONG_BARRE_NIV_M2);
					System.out.println("La taille de la raquette a ete divise par 2!");
				}
				else
					System.out.println("Diminution de la taille de la raquette impossible. La raquette est deja trop petite");
			}
		}
	}
	
	public void multiplyRacket() {
		if (getImage().equals(PONG_BARRE_NIV_M2)) {
			setImage(PONG_BARRE_NIV_M1);
			System.out.println("La taille de la raquette a ete augmente par 2!");
		}
		else {
			if (getImage().equals(PONG_BARRE_NIV_M1)) {
				setImage(PONG_BARRE_NIV_0);
				System.out.println("La taille de la raquette a ete augmente par 2!");
			}
			else {
				if (getImage().equals(PONG_BARRE_NIV_0)) {
					setImage(PONG_BARRE_NIV_P1);
					System.out.println("La taille de la raquette a ete augmente par 2!");
				}
				else
					System.out.println("Augmentation de la taille de la raquette impossible. La raquette est deja trop grande");
			}
		}
	}
	
	/**
	 * Reaction de la balle si elle touche le cote de la raquette
	 */
	public void moveBallOnRacketCote(RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
		if (racketPlayer.itemOnRacketCote(ball) || racketOpponent.itemOnRacketCote(ball)) {
			// Valeur calculee par : ((distance entre le milieu de la balle et le milieu de la raquette) / 
			// ((1/2)* (longueur de la raquette + longueur de la balle))) * 
			// decalage Maximal sur Y
			float valueY = ((((float)ball.getPosition().y + (ball.getHeight() / 2)) - (racketPlayer.getPosition().y + (racketPlayer.getHeight() / 2))) / 
					(((racketPlayer.getHeight() + ball.getHeight()) / 2))) * Pong.DECALAGE_MAX_ON_RACKET;
			// Si la balle a un effet
			if(ball.getHasLift()){
				// On ajoute l'effet a la direction de la balle, cet effet est defini comme un pourcentage
				if(valueY > 0)
					valueY = (float)valueY + ((valueY * ball.getLiftSpeed())/100);
				else{
					if(valueY < 0)
						valueY = (float)valueY + ((valueY * (-ball.getLiftSpeed()))/100);
					else
						valueY = 0;
				}
				// On enleve l'effet sur la balle
				ball.setHasLift(false);
			}
			ball.setSpeedY(Math.round(valueY));
			ball.setSpeedX(-ball.getSpeed().x);
		}
	}

	/**
	 * Reaction de la balle si elle touche le coin ou le haut de la raquette
	 */
	public void moveBallOnRacketOther(RacketType racketPlayer, RacketType racketOpponent, BallType ball) {	
		if (racketPlayer.itemOnRacketHaut(ball) || racketOpponent.itemOnRacketHaut(ball))
			ball.setSpeedY(-ball.getSpeed().y);	
		if (racketPlayer.itemOnRacketCorner(ball) || racketOpponent.itemOnRacketCorner(ball))
			ball.setSpeed(-ball.getSpeed().x, -ball.getSpeed().y);
	}
}