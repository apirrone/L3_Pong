package gui;

import java.awt.Toolkit;

public class MagneticRacket extends RacketType {
	
	public MagneticRacket(boolean player) {
		super(Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/barrePongAimante.png")), player);
	}
	
	/**
	 * "moveBall" change avec une "magneticRacket", A terminer
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
			ball.setSpeedX(0);
		}
	}
	
	public void moveBallOnRacketOther(RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
		if (racketPlayer.itemOnRacketHaut(ball) || racketOpponent.itemOnRacketHaut(ball))
			ball.setSpeed(0, -ball.getSpeed().y);	
		if (racketPlayer.itemOnRacketCorner(ball) || racketOpponent.itemOnRacketCorner(ball))
			ball.setSpeed(0, -ball.getSpeed().y);
	}

	@Override
	public void divideRacket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void multiplyRacket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restartImageRacket() {
		// TODO Auto-generated method stub
		
	}
}