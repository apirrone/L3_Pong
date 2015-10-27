package pong.gui;

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
	 * constructor of racket
	 */
	public Racket(Image image) {
		this.image = image;
		this.icon = new ImageIcon(this.image);
		this.width = this.icon.getIconWidth();
		this.height = this.icon.getIconHeight();
		this.speed = 0;
		this.position = new Point(0, 0);
	}

	/**
	 * Get / Set accessors object
	 */
	public Image getImage() {
		return this.image;
	}

	public ImageIcon getIcon() {
		return this.icon;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getSpeed() {
		return this.speed;
	}

	public Point getPosition() {
		return this.position;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Move racket position
	 */
	public void moveRacket(int size_pong_y) {
		this.position.y += this.speed;
		if (this.position.y < 0)
			this.position.y = 0;
		if (this.position.y > size_pong_y - this.height/2)
			this.position.y = size_pong_y - this.height/2;
	}
		
}



