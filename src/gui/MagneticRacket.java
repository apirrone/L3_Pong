package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class MagneticRacket extends RacketType{
	/** 
	 * Constructor of MagneticRacket
	 */
	public MagneticRacket(Image image, boolean player) {
		super(image, player);
	}
	
	/**
	 * moveBall with MagneticRackets changes, not finished yet
	 */
	public void moveBallOnRacket(int size_pong_x, int size_pong_y, RacketType racketPlayer,RacketType racketOpponent, BallType ball){
				if (itemOnRacketCote(ball, racketPlayer) || itemOnRacketCote(ball, racketPlayer)){
					ball.setSpeedBall(0,0);
					ball.setPosition(position.x, position.y);
				}
				if (itemOnRacketHaut(ball, racketOpponent) || itemOnRacketHaut(ball, racketOpponent)){
					ball.setSpeedBall(0,0);
					ball.setPosition(position.x, position.y);
				}
				else
					ball.position.translate(ball.getSpeedBall().x/Math.abs(ball.getSpeedBall().x), ball.getSpeedBall().y/Math.abs(ball.getSpeedBall().y));
	}	
}	
	


