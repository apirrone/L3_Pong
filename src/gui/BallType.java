package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public abstract class BallType extends PongItem{
	
	public BallType (Image image){
		super(image);
	}
	/**
	 * Get / Set accessors object
	 */
	abstract Point getSpeedBall();
	abstract void setSpeedBall(int x, int y);
	abstract void setSpeed(Point speedBall);	
	abstract void moveBall(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent);	
	abstract int getScorePlayer();	
	abstract int getScoreOpponent();


}
