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
	 * Constructor of racket
	 */
	public Racket(Image image) {
		this.image = image;
		this.icon = new ImageIcon(this.image);
		this.width = this.icon.getIconWidth();
		this.height = this.icon.getIconHeight();
		this.speed = 0;
		this.position = new Point(99, 0);
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

	/**
	 * Move racket position
	 */
	public void moveRacket(int size_pong_y) {
		position.y += speed;
		if (position.y < 0)
			position.y = 0;
		if (position.y > size_pong_y - height/2)
			position.y = size_pong_y - height/2;
		//System.out.println("\nposition X : "+position.x+"\nposition Y : "+position.y+"\nlargeur : "+width+"\nhauteur : "+height+"\n");
	}
		
}



