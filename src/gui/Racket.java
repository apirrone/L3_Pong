package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Racket extends PongItem{
	
	/**
	 * Constructor of racket
	 */
	public Racket(Image image, boolean player) {
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
	public void moveRacket(int size_pong_y, Ball ball) {
		int posY=position.y;
		boolean prob=false;
		for(int i=Math.abs(speed); i>0; i--){
			if (itemOnRacketHaut(ball, this))
				prob=true;
			position.y += (speed/Math.abs(speed));
		}
		if (itemOnRacketHaut(ball, this))
			prob=true;
		if (prob)
			position.y = posY;
		if (position.y < 0)
			position.y = 0;
		if (position.y > size_pong_y - height)
			position.y = size_pong_y - height;
	}
}


