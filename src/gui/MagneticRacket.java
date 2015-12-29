package gui;


import java.awt.Toolkit;

public class MagneticRacket extends RacketType{
	/** 
	 * Constructor of MagneticRacket
	 */
	public MagneticRacket(boolean player) {
		super(Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/barrePongAimante.png")), player);
	}
	
	/**
	 * moveBall with MagneticRackets changes, not finished yet
	 */
	public void moveBallOnRacketCote(int size_pong_x, int size_pong_y, RacketType racketPlayer,RacketType racketOpponent, BallType ball){
				if (racketPlayer.itemOnRacketCote(ball)){
					ball.setSpeed(1,1);
					ball.setPosition(position.x, position.y);
				}
				
				if (racketOpponent.itemOnRacketCote(ball)) {
					ball.setSpeed(1,1);
					ball.setPosition(racketOpponent.getPosition().x, racketOpponent.getPosition().y);
				}
				//ball.position.translate(ball.getSpeed().x/Math.abs(ball.getSpeed().x), 0);
	}
	public void moveBallOnRacketOther(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent,
			BallType ball) {
		if (racketPlayer.itemOnRacketHaut(ball)){
			ball.setSpeed(1,1);
			ball.setPosition(position.x, position.y);
		}
		 if (racketOpponent.itemOnRacketHaut(ball)){
			ball.setSpeed(1,1);
			ball.setPosition(racketOpponent.getPosition().x, racketOpponent.getPosition().y);
		}
		if (racketPlayer.itemOnRacketCorner(ball)){
			ball.setSpeed(1,1);
			ball.setPosition(position.x, position.y);
		}
		if (racketOpponent.itemOnRacketCorner(ball)){
			ball.setSpeed(1,1);
			ball.setPosition(racketOpponent.getPosition().x, racketOpponent.getPosition().y);
	}
		//ball.position.translate(0, ball.getSpeed().y/Math.abs(ball.getSpeed().y));
	}
}	
	


