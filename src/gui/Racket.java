package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Racket {

	private static final long serialVersionUID = 1L;

	/**
	 * One Racket to be displayed
	 */
	private final Image image;
	/**
	 * Icon of Racket
	 */
	private final ImageIcon icon;
	/**
	 * Width of the racket in pixels
	 */
	private int width;
	/**
	 * Height of the racket in pixels
	 */
	private int height;
	/**
	 * Speed of racket, in pixels per timestamp
	 */
	private int speed;
	/**
	 * Position of racket
	 */
	private Point position;

	/**
	 * Constructor of racket
	 */
	public Racket(Image image, boolean player) {
		this.image = image;
		this.icon = new ImageIcon(this.image);
		this.width = this.icon.getIconWidth();
		this.height = this.icon.getIconHeight();
		this.speed = 0;
		if(player)
			this.position = new Point(100, 0);
		else
			this.position = new Point(700, 0);
	}

	/**
	 * Get / Set accessors object
	 */
	public Image getImage() {
		return image;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpeed() {
		return speed;
	}

	public Point getPosition() {
		return position;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setPosition(Point position) {
		this.position.setLocation(position);
	}

	public void setY(int y){
		this.position = new Point((int)this.position.getX(), y);
	}
	
	/**
     * Test if the ball is on the racket
	 */
	public static boolean ballOnRacketCote(Ball ball, Racket racket) {
		return ((((
			// Si l'item touche sur les cotés de la racket
			ball.getPosition().y >= racket.getPosition().y &&
			ball.getPosition().y <= racket.getPosition().y + racket.getHeight()) || (
			ball.getPosition().y + ball.getHeight() >= racket.getPosition().y &&
			ball.getPosition().y + ball.getHeight() <= racket.getPosition().y + racket.getHeight())) && ((
			ball.getPosition().x == racket.getPosition().x + racket.getWidth()) || (
			ball.getPosition().x + ball.getWidth() == racket.getPosition().x))));
	}
	
	public static boolean ballOnRacketHaut(Ball ball, Racket racket) {
		return ((((
			// Si l'item touche le dessous de la racket
			ball.getPosition().x >= racket.getPosition().x &&
			ball.getPosition().x <= racket.getPosition().x + racket.getWidth()) || (
			ball.getPosition().x + ball.getWidth() >= racket.getPosition().x &&
			ball.getPosition().x + ball.getWidth() <= racket.getPosition().x + racket.getWidth())) && ((
			ball.getPosition().y + ball.getHeight() == racket.getPosition().y ) || (
			ball.getPosition().y == racket.getPosition().y + racket.getHeight()))));
	}


	/**
	 * Move racket position
	 */
	public void moveRacket(int size_pong_y, Ball ball) {
		int posY=position.y;
		boolean prob=false;
		for(int i=Math.abs(speed); i>0; i--){
			if (ballOnRacketHaut(ball, this))
				prob=true;
			position.y += (speed/Math.abs(speed));
		}
		if (ballOnRacketHaut(ball, this))
			prob=true;
		if (prob)
			position.y = posY;
		if (position.y < 0)
			position.y = 0;
		if (position.y > size_pong_y - height)
			position.y = size_pong_y - height;
	}
		
}



