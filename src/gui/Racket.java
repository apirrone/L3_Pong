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
	
	public void moveBallOnRacket(int size_pong_x, int size_pong_y,RacketType racketPlayer, RacketType racketOpponent, BallType ball){	
				if (itemOnRacketCote(ball, racketPlayer) || itemOnRacketCote(ball, racketOpponent))
					ball.setSpeedBall(-ball.getSpeedBall().x, ball.getSpeedBall().y );
				if (itemOnRacketHaut(ball, racketPlayer) || itemOnRacketHaut(ball, racketOpponent))
					ball.setSpeedBall(ball.getSpeedBall().x, -ball.getSpeedBall().y);			
	}
}


