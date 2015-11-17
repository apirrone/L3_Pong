package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class PongItem {
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
	public PongItem(Image image) {
		this.image = image;
		this.icon = new ImageIcon(this.image);
		this.width = this.icon.getIconWidth();
		this.height = this.icon.getIconHeight();
		this.speed = 0;
		this.position = new Point(300, 300);
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
	public void movePongItem() {
		}
}
