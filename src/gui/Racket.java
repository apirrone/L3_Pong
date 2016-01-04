package gui;

import java.awt.Image;
import java.awt.Toolkit;

public class Racket extends RacketType{

	/**
	 * Les quatres niveaux de PONG_BARRE représentent les quatres tailles de raquettes possibles
	 */
	private static final Image PONG_BARRE_NIV_P1 = Toolkit.getDefaultToolkit().createImage(
			ClassLoader.getSystemResource("ressource/barrePongMultNivP1.png"));
	private static final Image PONG_BARRE_NIV_0 = Toolkit.getDefaultToolkit().createImage(
			ClassLoader.getSystemResource("ressource/barrePong.png"));
	private static final Image PONG_BARRE_NIV_M1 = Toolkit.getDefaultToolkit().createImage(
			ClassLoader.getSystemResource("ressource/barrePongDivNivM1.png"));
	private static final Image PONG_BARRE_NIV_M2 = Toolkit.getDefaultToolkit().createImage(
			ClassLoader.getSystemResource("ressource/barrePongDivNivM2.png"));
	
	public Racket(boolean player) {
		super(PONG_BARRE_NIV_0, player);
	}

	public void restartImageRacket() {
		setImage(PONG_BARRE_NIV_0);
	}

	/**
	 * Diminue la taille de la raquette
	 */
	public void divideRacket() {
		if (getImage().equals(PONG_BARRE_NIV_P1)) {
			setImage(PONG_BARRE_NIV_0);
			System.out.println("La taille de la raquette a ete divise par 2!");
		}
		else {
			if (getImage().equals(PONG_BARRE_NIV_0)) {
				setImage(PONG_BARRE_NIV_M1); 
				System.out.println("La taille de la raquette a ete divise par 2!");
				}
			else {
				if (getImage().equals(PONG_BARRE_NIV_M1)) {
					setImage(PONG_BARRE_NIV_M2);
					System.out.println("La taille de la raquette a ete divise par 2!");
				}
				else
					System.out.println("Diminution de la taille de la raquette impossible. La raquette est deja trop petite");
			}
		}
	}

	/**
	 * Augmente la taille de la raquette
	 */
	public void multiplyRacket() {
		if (getImage().equals(PONG_BARRE_NIV_M2)) {
			setImage(PONG_BARRE_NIV_M1);
			System.out.println("La taille de la raquette a ete augmente par 2!");
		}
		else {
			if (getImage().equals(PONG_BARRE_NIV_M1)) {
				setImage(PONG_BARRE_NIV_0);
				System.out.println("La taille de la raquette a ete augmente par 2!");
			}
			else {
				if (getImage().equals(PONG_BARRE_NIV_0)) {
					setImage(PONG_BARRE_NIV_P1);
					System.out.println("La taille de la raquette a ete augmente par 2!");
				}
				else
					System.out.println("Augmentation de la taille de la raquette impossible. La raquette est deja trop grande");
			}
		}
	}
}