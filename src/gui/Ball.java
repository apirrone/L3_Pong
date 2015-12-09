package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Ball extends BallType{
	/**
	 * Score of the game
	 */
	private int scorePlayer;
	private int scoreOpponent;
	/**
	 * Speed of ball, in pixels per second
	 */	
	private static final int BALL_SPEED = 2;
	/**
	 * Point defining speed of ball, in pixels per timestamp
	 */
	protected Point speedBall;
	/**
	 * Constructor of ball
	 */
	public Ball(Image image, boolean serveur) {
		super(image);
		this.scorePlayer = 0;
		this.scoreOpponent = 0;
		if (serveur){
			this.position = new Point(150, 0);
			this.speedBall = new Point(BALL_SPEED, BALL_SPEED);
		}else{
			this.position = new Point(630, 0);
			this.speedBall = new Point(-BALL_SPEED, BALL_SPEED);
		}
	}
	/**
	 * Move ball position
	 */
	public void moveBall(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent) {
		
		for(int i=Math.abs(speedBall.x); i>0; i--){
			racketPlayer.moveBallOnRacket(size_pong_x, size_pong_y, racketPlayer, racketOpponent, this);
			/**if (itemOnRacketCote(this, racketPlayer) || itemOnRacketCote(this, racketOpponent))
				speedBall.x = -speedBall.x;
			if (itemOnRacketHaut(this, racketPlayer) || itemOnRacketHaut(this, racketOpponent))
				speedBall.y = -speedBall.y;
				*/
			
			position.translate(speedBall.x/Math.abs(speedBall.x), speedBall.y/Math.abs(speedBall.y));
			if (position.x < 0){
				position.x = 0;
				speedBall.x = -speedBall.x;
				scoreUpdateO();
			}
			if (position.y < 0){
				position.y = 0;
				speedBall.y = -speedBall.y;
			}
			if (position.x > size_pong_x - width){
				position.x = size_pong_x - width;
				speedBall.x = -speedBall.x;
				scoreUpdateP();
			}
			if (position.y > size_pong_y - height){
				position.y = size_pong_y - height;
				speedBall.y = -speedBall.y;
			}
		}
	}
	/**
	 * Get / Set accessors object
	 */
	public Point getSpeedBall() {
		return speedBall;
	}
	public void setSpeedBall(int x, int y){
		speedBall.x = x;
		speedBall.y = y;
	}
	public void setSpeed(int speed) {
		this.speedBall.setLocation(new Point(speed, speed));
	}
	
	public void setSpeed(Point speedBall) {
		this.speedBall.setLocation(speedBall);
	}
	
	
	public int getScorePlayer(){
		return scorePlayer;
	}
	
	public int getScoreOpponent(){
		return scoreOpponent;
	}
	
	private void scoreUpdateP(){
		scorePlayer++;
	}
	private void scoreUpdateO(){
		scoreOpponent++;
	}
	
}

