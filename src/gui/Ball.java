package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Ball extends PongItem{
	/**
	 * Speed of ball, in pixels per timestamp
	 */
	private Point speedBall;
	/**
	 * Constructor of ball
	 */
	public Ball(Image image, int ball_speed) {
		super(image);
		this.speedBall = new Point(ball_speed, ball_speed);
	}

	/**
	 * Get / Set accessors object
	 */
	public Point getSpeedBall() {
		return speedBall;
	}

	public void setSpeed(int speed) {
		this.speedBall.setLocation(new Point(speed, speed));
	}
	
	public void setSpeed(Point speedBall) {
		this.speedBall.setLocation(speedBall);
	}

	/**
	 * Move ball position
	 */
	public void moveBall(int size_pong_x, int size_pong_y, Racket racketPlayer, Racket racketOpponent) {
		
		for(int i=Math.abs(speedBall.x); i>0; i--){
			if (itemOnRacketCote(this, racketPlayer) || itemOnRacketCote(this, racketOpponent))
				speedBall.x = -speedBall.x;
			if (itemOnRacketHaut(this, racketPlayer) || itemOnRacketHaut(this, racketOpponent))
				speedBall.y = -speedBall.y;
			position.translate(speedBall.x/Math.abs(speedBall.x), speedBall.y/Math.abs(speedBall.y));
			if (position.x < 0){
				position.x = 0;
				speedBall.x = -speedBall.x;
			}
			if (position.y < 0){
				position.y = 0;
				speedBall.y = -speedBall.y;
			}
			if (position.x > size_pong_x - width){
				position.x = size_pong_x - width;
				speedBall.x = -speedBall.x;
			}
			if (position.y > size_pong_y - height){
				position.y = size_pong_y - height;
				speedBall.y = -speedBall.y;
			}
		}
	}
		
}

