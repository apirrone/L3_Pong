package gui;

import java.awt.Toolkit;

public class Ball extends BallType {
	
	public Ball(boolean serveur) {
		super(Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/ball.png")) ,serveur);
	}
}