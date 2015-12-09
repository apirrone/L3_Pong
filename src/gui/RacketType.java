package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public abstract class RacketType extends PongItem{
	
	public RacketType(Image image){
		super(image);
	}
	abstract void moveBallOnRacket(int size_pong_x, int size_pong_y,RacketType racketPlayer, RacketType racketOpponent, BallType ball);
	abstract void moveRacket(int size_pong_y, BallType ball);
	abstract void setY(int y);

}
