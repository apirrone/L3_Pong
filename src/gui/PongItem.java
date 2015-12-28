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

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position.setLocation(position);
	}

	public void setPosition(int x, int y) {
		this.position.setLocation(x, y);
	}
	
	public void inverserPosition(int sizePongX){
		this.setPosition((sizePongX - this.getPosition().x - this.getWidth()), this.getPosition().y);
	}
}
