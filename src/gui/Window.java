package gui;

import javax.swing.JFrame;
import util.ExceptionPong;

/**
 * Window est un Jframe contenant un Pong
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final Pong pong;

	public Window(Pong pong) {
		this.pong = pong;
		this.addKeyListener(pong);
	}

	/**
	 * displayOnScreen est la procedure affichant la fenetre en utilisant les marges definies
	 * Cette procedure sera appelee toutes les "timeStep" ms
	 */
	public void displayOnscreen() throws ExceptionPong {
		add(pong);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		while(true) {
			pong.animate();
			try {
				Thread.sleep(Pong.timeStep);
			} catch (InterruptedException e) {
				throw new ExceptionPong("ERROR : Un thread vient d'arreter Pong.java");
			}
		}
	}
}