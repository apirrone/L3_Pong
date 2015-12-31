package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

abstract public class PongItem {
	
	protected final Image image;
	protected final ImageIcon icon;
	protected int width;
	protected int height;
	protected Point position;
	
	public PongItem(Image image) {
		this.image = image;
		this.icon = new ImageIcon(this.image);
		this.width = this.icon.getIconWidth();
		this.height = this.icon.getIconHeight();
		this.position = new Point(Pong.POINT_DEFAULT);
	}

	/**
	 * Get / Set accesseurs des attributs
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
	
	public void inverserPosition(int sizePongX) {
		this.setPosition((sizePongX - this.getPosition().x - this.getWidth()), this.getPosition().y);
	}
}
