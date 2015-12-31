package gui;

import java.awt.Toolkit;

public class Racket extends RacketType{

	public Racket(boolean player) {
		super(Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/barrePong.png")), player);
	}
	
	/**
	 * Reaction de la balle si elle touche le cote de la raquette
	 */
	public void moveBallOnRacketCote(int size_pong_x, int size_pong_y,RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
		if (racketPlayer.itemOnRacketCote(ball)) {
			// Valeur calculee par : ((distance entre le milieu de la balle et le milieu de la raquette) / 
			// ((1/2)* (longueur de la raquette + longueur de la balle))) * 
			// decalage Maximal sur Y
			float valueY = ((((float)ball.getPosition().y + (ball.getHeight() / 2)) - (racketPlayer.getPosition().y + (racketPlayer.getHeight() / 2))) / 
					(((racketPlayer.getHeight() + ball.getHeight()) / 2))) * Pong.decalageMaxOnRacket;
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
		if (racketOpponent.itemOnRacketCote(ball)) {
			// Valeur calculee par : ((distance entre le milieu de la balle et le milieu de la raquette) / 
			// ((1/2)* (longueur de la raquette + longueur de la balle))) * 
			// decalage Maximal sur Y
			float valueY = ((((float)ball.getPosition().y + (ball.getHeight() / 2)) - (racketOpponent.getPosition().y + (racketOpponent.getHeight() / 2))) / 
					(((racketOpponent.getHeight() + ball.getHeight()) / 2))) * Pong.decalageMaxOnRacket;
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
	public void moveBallOnRacketOther(int size_pong_x, int size_pong_y,RacketType racketPlayer, RacketType racketOpponent, BallType ball) {	
		if (racketPlayer.itemOnRacketHaut(ball) || racketOpponent.itemOnRacketHaut(ball))
			ball.setSpeedY(-ball.getSpeed().y);	
		if (racketPlayer.itemOnRacketCorner(ball) || racketOpponent.itemOnRacketCorner(ball))
			ball.setSpeed(-ball.getSpeed().x, -ball.getSpeed().y);
	}
}