package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import network.Client;
import network.Server;

/**
 * An Pong is a Java graphical container that extends the JPanel class in
 * order to display graphical elements.
 */
public class Pong extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Constant (c.f. final) common to all Pong instances (c.f. static)
	 * defining the background color of the Pong
	 */
	private static final Color backgroundColor = new Color(0xFF, 0x40, 0);

	/**
	 * Width of pong area
	 */
	private static final int SIZE_PONG_X = 800;
	/**
	 * Height of pong area
	 */
	private static final int SIZE_PONG_Y = 600;
	/**
	 * Time step of the simulation (in ms)
	 */
	public static final int timestep = 10;
	/**
	 * Speed of ball (in pixels per second)
	 */
	public static final int BALL_SPEED = 2;
	/**
	 * Speed of racket (in pixels per second)
	 */
	public static final int RACKET_SPEED = 4;

	/**
	 * Pixel data buffer for the Pong rendering
	 */
	private Image buffer = null;
	/**
	 * Graphic component context derived from buffer Image
	 */
	private Graphics graphicContext = null;

	/**
	 * One Ball to be displayed
	 */
	private Ball ball;

	private Racket racketPlayer;
	private Racket racketOpponent;

	public Pong(String[] args) {
		this.ball = new Ball(Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/ball.png")),
				BALL_SPEED);
		this.racketPlayer = new Racket(Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/racket.png")), true);
		
		this.racketOpponent = new Racket(Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/racket.png")), false);
		
		this.setPreferredSize(new Dimension(SIZE_PONG_X, SIZE_PONG_Y));
		this.addKeyListener(this);
		
		if(args.length == 0){//Serveur
			Server server = new Server(2009);
			server.waitForConnection();
		}
		else{//client
			Client client = new Client();
			String addr = args[0];
			client.connect(addr, 2009);
		}
	}

	/**
         * Proceeds to the movement of the ball and updates the screen
	 */
	public void animate() {
		/* Update ball position */
		ball.moveBall(SIZE_PONG_X, SIZE_PONG_Y, ballOnRacket(ball, racketPlayer));

		/* Update racket position */
		racketPlayer.moveRacket(SIZE_PONG_Y);
		/* Update racket position */
		racketOpponent.moveRacket(SIZE_PONG_Y);

		/* And update output */
		updateScreen();
	}

	/**
         * Test if the ball is on the racket
	 */
	public boolean ballOnRacket(Ball ball, Racket racket) {
		return ((ball.getPosition().x == racket.getPosition().x + racket.getWidth() &&
			ball.getPosition().y > racket.getPosition().y &&
			ball.getPosition().y < racket.getPosition().y + racket.getHeight())||(
			ball.getPosition().x == racket.getPosition().x + racket.getWidth() &&
			ball.getPosition().y + ball.getHeight() > racket.getPosition().y &&
			ball.getPosition().y + ball.getHeight() < racket.getPosition().y + racket.getHeight()));
	}


	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				racketPlayer.setSpeed(racketPlayer.getSpeed()-RACKET_SPEED);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				racketPlayer.setSpeed(RACKET_SPEED);
				break;
			default:
				System.out.println("got press "+e);
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				racketPlayer.setSpeed(0);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				racketPlayer.setSpeed(0);
				break;
			default:
				System.out.println("got release "+e);
		}
	}
	public void keyTyped(KeyEvent e) { }

	/*
	 * (non-Javadoc) This method is called by the AWT Engine to paint what
	 * appears in the screen. The AWT engine calls the paint method every time
	 * the operative system reports that the canvas has to be painted. When the
	 * window is created for the first time paint is called. The paint method is
	 * also called if we minimize and after we maximize the window and if we
	 * change the size of the window with the mouse.
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	/**
	 * Draw each Pong item based on new positions
	 */
	public void updateScreen() {
		if (buffer == null) {
			/* First time we get called with all windows initialized */
			buffer = createImage(SIZE_PONG_X, SIZE_PONG_Y);
			if (buffer == null)
				throw new RuntimeException("Could not instanciate graphics");
			else
				graphicContext = buffer.getGraphics();
		}
		/* Fill the area with blue */
		graphicContext.setColor(backgroundColor);
		graphicContext.fillRect(0, 0, SIZE_PONG_X, SIZE_PONG_Y);

		/* Draw items */
		graphicContext.drawImage(ball.getImage(), ball.getPosition().x, ball.getPosition().y, ball.getWidth(), ball.getHeight(), null);
		graphicContext.drawImage(racketPlayer.getImage(), racketPlayer.getPosition().x, racketPlayer.getPosition().y, racketPlayer.getWidth(), racketPlayer.getHeight(), null);
		graphicContext.drawImage(racketOpponent.getImage(), racketOpponent.getPosition().x, racketOpponent.getPosition().y, racketOpponent.getWidth(), racketOpponent.getHeight(), null);

		this.repaint();
	}
}
