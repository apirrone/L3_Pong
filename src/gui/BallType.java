package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public abstract class BallType extends PongItem{

	/**
	 * Speed of ball, in pixels per second
	 */	
	private static final int BALL_SPEED_X = 2;

	/**
	 * Point defining speed of ball, in pixels per timestamp
	 */
	protected Point speed;
	
	public BallType (Image image, boolean serveur) {
		super(image);
		if (serveur){
			this.position = new Point(390, 0);
			this.speed = new Point(BALL_SPEED_X, BALL_SPEED_X);
		}else{
			this.position = new Point(390, 0);
			this.speed = new Point(-BALL_SPEED_X, BALL_SPEED_X);
		}
	}
	
	/**
	 * Get / Set accessors object
	 */
	public Point getSpeed() {
		return speed;
	}
	public void setSpeed(int x, int y){
		speed.x = x;
		speed.y = y;
	}
	public void setSpeedX(int speedX) {
		this.speed.setLocation(speedX, getSpeed().y);
	}

	public void setSpeedY(int speedY) {
		this.speed.setLocation(getSpeed().x, speedY);
	}

	/**
	 * Move ball position
	 */
	public void moveBall(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent, Score score) {
		
		for(int i=Math.abs(speed.x); i>0; i--){
			racketPlayer.moveBallOnRacket(size_pong_x, size_pong_y, racketPlayer, racketOpponent, this);
			/*if (itemOnRacketCote(this, racketPlayer) || itemOnRacketCote(this, racketOpponent))
				speed.x = -speed.x;
			if (itemOnRacketHaut(this, racketPlayer) || itemOnRacketHaut(this, racketOpponent))
				speed.y = -speed.y;
				*/
			
			position.translate(speed.x/Math.abs(speed.x), speed.y/Math.abs(speed.y));
			if (position.x < 0){
				position.x = 0;
				speed.x = -speed.x;
				score.incrementScoreOpponent();
			}
			if (position.y < 0){
				position.y = 0;
				speed.y = -speed.y;
			}
			if (position.x > size_pong_x - width){
				position.x = size_pong_x - width;
				speed.x = -speed.x;
				score.incrementScorePlayer();
			}
			if (position.y > size_pong_y - height){
				position.y = size_pong_y - height;
				speed.y = -speed.y;
			}
		}
	}


}
