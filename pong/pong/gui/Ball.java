package pong.gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Ball {

	private static final long serialVersionUID = 1L;

	/**
	 * One Ball to be displayed
	 */
	private final Image image;
	/**
	 * Icon of Ball
	 */
	private final ImageIcon icon;
	/**
	 * Width of the ball in pixels
	 */
	private int width;
	/**
	 * Height of the ball in pixels
	 */
	private int height;
	/**
	 * Speed of ball, in pixels per timestamp
	 */
	private Point speed;
	/**
	 * Position of ball
	 */
	private Point position;

	/**
	 * Constructor of ball
	 */
	public Ball(Image image, int ball_speed) {
		this.image = image;
		this.icon = new ImageIcon(this.image);
		this.width = this.icon.getIconWidth();
		this.height = this.icon.getIconHeight();
		this.speed = new Point(ball_speed, ball_speed);
		this.position = new Point(0, 0);
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

	public Point getSpeed() {
		return speed;
	}

	public Point getPosition() {
		return position;
	}

	public void setSpeed(Point speed) {
		this.speed.setLocation(speed);
	}

	public void setPosition(Point position) {
		this.position.setLocation(position);
	}

	/**
	 * Move ball position
	 */
	public void moveBall(int size_pong_x, int size_pong_y) {
		position.translate(speed.x, speed.y);
		if (position.x < 0)
		{
			position.x = 0;
			speed.x = -speed.x;
		}
		if (position.y < 0)
		{
			position.y = 0;
			speed.y = -speed.y;
		}
		if (position.x > size_pong_x - width)
		{
			position.x = size_pong_x - width;
			speed.x = -speed.x;
		}
		if (position.y > size_pong_y - height)
		{
			position.y = size_pong_y - height;
			speed.y = -speed.y;
		}
	}
		
}

