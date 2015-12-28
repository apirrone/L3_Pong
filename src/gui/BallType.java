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
	protected boolean hasLift;
	protected int liftSpeed ;
	
	public BallType (Image image, boolean serveur) {
		super(image);
		if (serveur){
			this.position.setLocation(390, 290);
			this.speed = new Point(BALL_SPEED_X, 0);
		}else{
			this.position.setLocation(390, 290);
			this.speed = new Point(-BALL_SPEED_X, 0);
		}
		hasLift = false;
		liftSpeed = 0;
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
		if(racketPlayer.itemOnRacketCote(this) && racketPlayer.getSpeed() != 0){
			//applyLift
			this.hasLift = true;
			liftSpeed = racketPlayer.getSpeed()+10;
		}
		if(racketOpponent.itemOnRacketCote(this) && racketOpponent.getSpeed() != 0){
			//applyLift
			this.hasLift = true;
			liftSpeed = racketOpponent.getSpeed()+10;
		}
		
		for(int i=Math.abs(speed.x); i>0; i--){
			racketPlayer.moveBallOnRacketCote(size_pong_x, size_pong_y, racketPlayer, racketOpponent, this);
			if (speed.y != 0) {
				for(int j=Math.abs(speed.y); j>0; j--){
					racketPlayer.moveBallOnRacketOther(size_pong_x, size_pong_y, racketPlayer, racketOpponent, this);
					position.translate(0, speed.y/Math.abs(speed.y));
					
					
					if (position.y < 0){
						if(this.hasLift){
							this.hasLift = false;
							position.y = 0;
							speed.y = -speed.y + liftSpeed;
							
						}
						else{
							position.y = 0;
							if(speed.y<=0)
								speed.y = BALL_SPEED_X;
							else{
								speed.y = - BALL_SPEED_X;
							}
						}
					}
					if (position.y > size_pong_y - height){
						if(this.hasLift){
							this.hasLift = false;
							position.y = size_pong_y - height;
							speed.y = -speed.y + liftSpeed;
							
						}
						else{
							position.y = size_pong_y - height;	
							if(speed.y<=0)
								speed.y = BALL_SPEED_X;
							else{
								speed.y = - BALL_SPEED_X;
							}
							
						}
					}
				}
			}
			position.translate(speed.x/Math.abs(speed.x), 0);
			if (position.x < 0){
				position.x = 0;
				speed.x = -speed.x;
				score.incrementScoreOpponent();
			}
			if (position.x > size_pong_x - width){
				position.x = size_pong_x - width;
				speed.x = -speed.x;
				score.incrementScorePlayer();
			}
		}
	}


}
