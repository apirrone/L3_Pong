package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

abstract public class PongItem {
	
	protected Image image;
	protected ImageIcon icon;
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

	public void setImage(Image image) {
		this.image = image;
		setIcon(image);
	}

	public ImageIcon getIcon() {
		return icon;
	}
	
	public void setIcon(Image image) {
		this.icon.setImage(image);
		this.width = this.icon.getIconWidth();
		this.height = this.icon.getIconHeight();
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
	
	public void inverserPosition() {
		this.setPosition((Pong.SIZE_PONG_X - this.getPosition().x - this.getWidth()), this.getPosition().y);
	}
}
