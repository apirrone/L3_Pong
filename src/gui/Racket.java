package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Racket extends RacketType{
	/**
	 * Constructor of racket
	 */
	public Racket(Image image, boolean player) {
		super(image, player);
	}
	
	public void moveBallOnRacketCote(int size_pong_x, int size_pong_y,RacketType racketPlayer, RacketType racketOpponent, BallType ball){	
				float decalageMax = 3;
				if (racketPlayer.itemOnRacketCote(ball)) {
					// Valeur calculée par : ((distance entre le milieu de la balle et le milieu de la raquette) / 
					// ((1/2)* (longueur de la raquette + longueur de la balle))) * 
					// décalage Maximal sur Y
					float valueY = ((((float)ball.getPosition().y + (ball.getHeight() / 2)) - (racketPlayer.getPosition().y + (racketPlayer.getHeight() / 2))) / 
							(((racketPlayer.getHeight() + ball.getHeight()) / 2))) * decalageMax;
					ball.setSpeedY(Math.round(valueY));
					ball.setSpeedX(-ball.getSpeed().x);
					
				}
				if (racketOpponent.itemOnRacketCote(ball)) {
					// Valeur calculée par : ((distance entre le milieu de la balle et le milieu de la raquette) / 
					// ((1/2)* (longueur de la raquette + longueur de la balle))) * 
					// décalage Maximal sur Y
					float valueY = ((((float)ball.getPosition().y + (ball.getHeight() / 2)) - (racketOpponent.getPosition().y + (racketOpponent.getHeight() / 2))) / 
							(((racketOpponent.getHeight() + ball.getHeight()) / 2))) * decalageMax;
					ball.setSpeedY(Math.round(valueY));
					ball.setSpeedX(-ball.getSpeed().x);
				}
	}
	
	public void moveBallOnRacketOther(int size_pong_x, int size_pong_y,RacketType racketPlayer, RacketType racketOpponent, BallType ball){	
				if (racketPlayer.itemOnRacketHaut(ball) || racketOpponent.itemOnRacketHaut(ball))
					ball.setSpeedY(-ball.getSpeed().y);	
				if (racketPlayer.itemOnRacketCorner(ball) || racketOpponent.itemOnRacketCorner(ball))
					ball.setSpeed(-ball.getSpeed().x, -ball.getSpeed().y);
	}
}


