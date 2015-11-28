package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

abstract public class PongItem {
	private static final long serialVersionUID = 1L;

	/**
	 * One Racket to be displayed
	 */
	protected final Image image;
	/**
	 * Icon of Racket
	 */
	protected final ImageIcon icon;
	/**
	 * Width of the racket in pixels
	 */
	protected int width;
	/**
	 * Height of the racket in pixels
	 */
	protected int height;
	/**
	 * Speed of racket, in pixels per timestamp
	 */
	protected int speed;
	/**
	 * Position of racket
	 */
	protected Point position;

	/**
	 * Constructor of racket
	 */
	public PongItem(Image image) {
		this.image = image;
		this.icon = new ImageIcon(this.image);
		this.width = this.icon.getIconWidth();
		this.height = this.icon.getIconHeight();
		this.speed = 0;
		this.position = new Point(150, 0);
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
     * Test if the ball is on the racket
	 */
	public static boolean itemOnRacketCote(PongItem item, Racket racket) {
		return ((((
			// Si l'item touche sur les cotés de la racket
			item.getPosition().y >= racket.getPosition().y &&
			item.getPosition().y <= racket.getPosition().y + racket.getHeight()) || (
			item.getPosition().y + item.getHeight() >= racket.getPosition().y &&
			item.getPosition().y + item.getHeight() <= racket.getPosition().y + racket.getHeight())) && ((
			item.getPosition().x == racket.getPosition().x + racket.getWidth()) || (
			item.getPosition().x + item.getWidth() == racket.getPosition().x))));
	}
	
	public static boolean itemOnRacketHaut(PongItem item, Racket racket) {
		return ((((
			// Si l'item touche le dessous de la racket
			item.getPosition().x >= racket.getPosition().x &&
			item.getPosition().x <= racket.getPosition().x + racket.getWidth()) || (
			item.getPosition().x + item.getWidth() >= racket.getPosition().x &&
			item.getPosition().x + item.getWidth() <= racket.getPosition().x + racket.getWidth())) && ((
			item.getPosition().y + item.getHeight() == racket.getPosition().y ) || (
			item.getPosition().y == racket.getPosition().y + racket.getHeight()))));
	}
	
	public void inverserPosition(int sizePongX){
		this.setPosition(new Point((sizePongX - this.getPosition().x), this.getPosition().y));
	}

	/**
	 * Move racket position
	 */
	public void movePongItem() {
		
		}
}
