package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class MagneticRacket extends RacketType{
	/** 
	 * Constructor of MagneticRacket
	 */
	public MagneticRacket(Image image, boolean player) {
		super(image);
		if(player)
			this.position = new Point(78, 0);
		else
			this.position = new Point(700, 0);
	}
	/**
	 * Get / Set accessors object
	 */
	public void setY(int y){
		this.position = new Point((int)this.position.getX(), y);
	}

	/**
	 * Move racket position
	 */
	public void moveRacket(int size_pong_y, BallType ball) {
		int posY=position.y;
		boolean prob=false;
		for(int i=Math.abs(speed); i>0; i--){
			if (itemOnMagneticRacketHaut(ball, this))
				prob=true;
			position.y += (speed/Math.abs(speed));
		}
		if (itemOnMagneticRacketHaut(ball, this))
			prob=true;
		if (prob)
			position.y = posY;
		if (position.y < 0)
			position.y = 0;
		if (position.y > size_pong_y - height)
			position.y = size_pong_y - height;
	}
	
	public boolean itemOnMagneticRacketHaut(BallType ball, RacketType racket){
		return (super.itemOnRacketHaut(ball, racket));
	}
	public boolean itemOnMagneticRacketCote(BallType ball, RacketType racket) {
		return (super.itemOnRacketCote(ball, racket));
	}
	/**
	 * moveBall with MagneticRackets changes, not finished yet
	 */
	public void moveBallOnRacket(int size_pong_x, int size_pong_y, RacketType racketPlayer,RacketType racketOpponent, BallType ball){
				if (itemOnMagneticRacketCote(ball, racketPlayer) || itemOnMagneticRacketCote(ball, racketPlayer)){
					ball.setSpeedBall(0,0);
					ball.setPosition(new Point(position.x, position.y));
				}
				if (itemOnMagneticRacketHaut(ball, racketOpponent) || itemOnMagneticRacketHaut(ball, racketOpponent)){
					ball.setSpeedBall(0,0);
					ball.setPosition(new Point(position.x, position.y));
				}
				else
					ball.position.translate(ball.getSpeedBall().x/Math.abs(ball.getSpeedBall().x), ball.getSpeedBall().y/Math.abs(ball.getSpeedBall().y));
	}	
	}	
	


