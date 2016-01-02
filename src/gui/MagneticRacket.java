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
	public void moveBallOnRacketCote(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
				if (racketPlayer.itemOnRacketCote(ball)) {
					ball.setSpeed(racketPlayer.position.x,racketPlayer.speed);
				}
				if (racketOpponent.itemOnRacketCote(ball)) {
					ball.setSpeed(racketOpponent.position.x,racketOpponent.speed);
				}
				//ball.position.translate(ball.getSpeed().x/Math.abs(ball.getSpeed().x), 0);
	}
	
	public void moveBallOnRacketOther(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
		if (racketPlayer.itemOnRacketHaut(ball)) {
			ball.setSpeed(racketPlayer.position.x,racketPlayer.speed);
		}
		 if (racketOpponent.itemOnRacketHaut(ball)) {
			ball.setSpeed(racketPlayer.position.x,racketOpponent.speed);
		}
		if (racketPlayer.itemOnRacketCorner(ball)) {
			ball.setSpeed(racketPlayer.position.x,racketPlayer.speed);
		}
		if (racketOpponent.itemOnRacketCorner(ball)) {
			ball.setSpeed(racketPlayer.position.x,racketOpponent.speed);
		}
		//ball.position.translate(0, ball.getSpeed().y/Math.abs(ball.getSpeed().y));
	}

	@Override
	void moveBallOnRacketCote(RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void moveBallOnRacketOther(RacketType racketPlayer, RacketType racketOpponent, BallType ball) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void divideRacket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void multiplyRacket() {
		// TODO Auto-generated method stub
		
	}
}