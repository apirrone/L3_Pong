package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import network.Client;
import network.CustomProtocol;
import network.Server;
import util.ExceptionPong;


/**
 * Un Pong est un conteneur graphique Java qui etend la classe JPanel 
 * afin d'afficher les elements graphiques.
 */
public class Pong extends JPanel implements KeyListener {

	/**
	 * backgroundColor definit la couleur de fond
	 * scoreColor definit la couleur d'ecriture du score
	 * SIZE_PONG X et Y definissent la longueur et la hauteur du Pong
	 */
	private static final long serialVersionUID = 1L;
	private static final Color backgroundColor = new Color(0x22, 0x40, 0);
	private static final Color scoreColor = new Color(0, 0, 0);
	private static final int SIZE_PONG_X = 800;
	private static final int SIZE_PONG_Y = 600;
	private static final int timeToWaitAtEndOfRound = 2000;

	/**
	 * timeStep defini la duree entre chaque action, duree en ms
	 * decalageMaxOnRacket defini le decalage maximal de la balle en touchant la raquette
	 */
	public static final int timeStep = 10;
	public static final Point POINT_DEFAULT = new Point(0,0);
	public static final float decalageMaxOnRacket = 6;

	/**
	 * buffer represente le buffer du graphique
	 */
	private Image buffer = null;
	private BallType ball;
	private BallType ball2;
	private RacketType racketPlayer;
	private RacketType racketOpponent;
	private Client client;
	private Server server;
	private Score pongScore;
	private int playTime = 0;
	private Bonus bonus;
	private Bonus bonus1;
	private boolean bonusIsCreated = false;
	private long time;
	
	public Graphics graphicContext = null;
	
	/** 
	 * Constructeur lorsque l'on est un serveur
	 */
	public Pong(Server s) {
		this.server = s;
		this.client = null;
		construct(false);
	}

	/** 
	 * Constructeur lorsque l'on est un client
	 */
	public Pong(Client c) {
		this.client = c;
		this.server = null;
		construct(true);
	}

	public void construct(boolean client) {
		this.pongScore = new Score();
		this.time = System.currentTimeMillis();
		this.ball = new Ball(client);
// seconde balle utilisee quand l'un des joueurs atteind 10 points
//		this.ball2 = new Ball(client);
		this.racketPlayer = new Racket(true);
		this.racketOpponent = new Racket(false);
		this.setPreferredSize(new Dimension(SIZE_PONG_X, SIZE_PONG_Y));
		this.addKeyListener(this);
	}

	/**
     * Realise le mouvement des objets ET
     * Met a jour la fenetre graphique
	 */
	public void animate() throws ExceptionPong {
		ball.moveBall(SIZE_PONG_X, SIZE_PONG_Y, racketPlayer, racketOpponent, pongScore);
		playTime++;
		// Si le round est en cours
		if(!pongScore.getFinRound()) {
			/* Actualisation de la position de la raquette */
			racketPlayer.moveRacket(SIZE_PONG_Y, ball);
			/* Actualisation de la position de la raquette */
			racketOpponent.moveRacket(SIZE_PONG_Y, ball);
			/* Et actualisation du Pong */
			updateScreen();
		} else {
		// Sinon on reinitialise le round
			pongScore.setFinRound(false);
			boolean client = (this.server == null);
			ball.restartBall(client);
			racketPlayer.restartRacket(true);
			racketOpponent.restartRacket(false);
			/* Et actualisation du Pong */
			updateScreen();
			// On attend deux sec avant le debut du prochain round
			try {
				Thread.sleep(timeToWaitAtEndOfRound);
			} catch (InterruptedException e) {
				throw new ExceptionPong("ERROR : Un thread vient d'arreter Pong.java");
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				racketPlayer.setSpeed(racketPlayer.getSpeed()-RacketType.RACKET_BASE_SPEED);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				racketPlayer.setSpeed(RacketType.RACKET_BASE_SPEED);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
				racketPlayer.releaseTheBall(ball, this);
				break;
			default:
				System.out.println("Tu viens d'appuyer sur la touche "+e+" qui n'a pas d'utilite");
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
				System.out.println("Tu viens de relacher la touche "+e+" qui n'a pas d'utilite");
		}
	}
	
	public void keyTyped(KeyEvent e) { }

	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	/**
	 * Dessine chaque PongItem sur leurs nouvelles positions
	 */
	public void updateScreen() throws ExceptionPong {
		sendReceiveData();
		if (buffer == null) {
			// Dans un premier temps nous allons initialiser la fenetre
			buffer = createImage(SIZE_PONG_X, SIZE_PONG_Y);
			if (buffer == null)
				throw new ExceptionPong("ERROR : N'a pas pu instancier le graphique.");
			else
				graphicContext = buffer.getGraphics();
		}
		// Dessiner le fond de couleur du Pong 
		graphicContext.setColor(backgroundColor);
		graphicContext.fillRect(0, 0, SIZE_PONG_X, SIZE_PONG_Y);

		// Dessiner les objets 
		graphicContext.drawImage(ball.getImage(), ball.getPosition().x, ball.getPosition().y, ball.getWidth(), ball.getHeight(), null);
		graphicContext.drawImage(racketPlayer.getImage(), racketPlayer.getPosition().x, racketPlayer.getPosition().y, racketPlayer.getWidth(), racketPlayer.getHeight(), null);
		graphicContext.drawImage(racketOpponent.getImage(), racketOpponent.getPosition().x, racketOpponent.getPosition().y, racketOpponent.getWidth(), racketOpponent.getHeight(), null);
		graphicContext.setColor(scoreColor);
		graphicContext.drawString("score Player:"+Integer.toString(pongScore.getScorePlayer()), 400, 50);
		graphicContext.drawString("score Opponent:"+Integer.toString(pongScore.getScoreOpponent()), 400, 70);
		//Permet le lancement des bonus a un moment precis
		if(playTime == 300 || playTime == 301) {
			bonusManagement();
		}
		this.repaint();
	}
	
	public void sendReceiveData() throws ExceptionPong {
		boolean cheater = false;
		if(this.client == null) {
			// Je suis serveur
			server.setData((int)racketPlayer.getPosition().getY(), ball.getPosition(), ball.getHasLift(), ball.getLiftSpeed(), ball.getSpeed().y);
			CustomProtocol p = server.getData();
			racketOpponent.setY(p.getRacketY());
//			System.out.println("ball pos X = "+(SIZE_PONG_X - ball.getPosition().x - ball.getWidth())+" Y = "+ball.getPosition().y+" lift? "+ball.getHasLift()+" Opp = "+p.getHasLift()+" P = "+ball.getLiftSpeed()+" O = "+p.getLiftSpeed()+" speed X = "+ball.getSpeed().x+" Y = "+ball.getSpeed().y);
			// Si la balle est de l'autre cote du Pong, on prend les donnees de l'adversaire
			if (ball.getPosition().getX() > ((SIZE_PONG_X - ball.getWidth())/2) ) {
				if ((System.currentTimeMillis() - time) >= timeStep) {
					// On test les donnees de l'adversaire pour savoir s'il a put tricher ou non
					if ((SIZE_PONG_X - ball.getPosition().getX() - ball.getWidth())!= p.getBallPosition().getX() ||
								ball.getPosition().getY() != p.getBallPosition().getY() ||
								ball.getHasLift() != p.getHasLift() ||
								ball.getLiftSpeed() != p.getLiftSpeed() ||
								ball.getSpeed().getY() != p.getBallSpeedY()) {
						cheater = true;
//						System.out.println("ball pos X = "+(SIZE_PONG_X - ball.getPosition().x - ball.getWidth())+" Y = "+ball.getPosition().y+" lift? "+ball.getHasLift()+" Opp = "+p.getHasLift()+" P = "+ball.getLiftSpeed()+" O = "+p.getLiftSpeed()+" speed X = "+ball.getSpeed().x+" Y = "+ball.getSpeed().y);
//						System.out.println("ball pos X = "+ball.getPosition().getX()+" OppX = "+(RacketType.RACKET_OPPONENT_BASE_POSITION_X - ball.getWidth()));
					}
					time = System.currentTimeMillis();
				}
				ball.setPosition(p.getBallPosition());
				ball.inverserPosition(SIZE_PONG_X);
//				System.out.println("ball pos X = "+ball.getPosition().getX()+" OppX = "+(RacketType.RACKET_OPPONENT_BASE_POSITION_X - ball.getWidth()));
				/* Pour des raisons d'implementations, si la balle est proche de la racket de l'adversaire, (proche d'environ 2 mouvements de la balle sur X)
				 * on va prendre quelques donnees de la balle sans les testees et en annulant le test de triche*/
				if (ball.getPosition().getX() < RacketType.RACKET_OPPONENT_BASE_POSITION_X - ball.getWidth() || 
						ball.getPosition().getX() > RacketType.RACKET_OPPONENT_BASE_POSITION_X - ball.getWidth() - (2 * Math.abs(ball.getSpeed().getX()))) {
					ball.setHasLift(p.getHasLift());
					ball.setLiftSpeed(p.getLiftSpeed());
					ball.setSpeedY(p.getBallSpeedY());
					cheater = false;
				}
				if (cheater)
//					throw new ExceptionPong("ATTENTION : Il y a un tricheur dans la partie.\nLa partie est arretee");
					System.out.println("CHEATER !!!!!"); 
			}
		} else {
			// Je suis client
			client.setData((int)racketPlayer.getPosition().getY(), ball.getPosition(), ball.getHasLift(), ball.getLiftSpeed(), ball.getSpeed().y);
			CustomProtocol p = client.getData();
			racketOpponent.setY(p.getRacketY());
//			System.out.println("ball pos X = "+ball.getPosition().x+" Y = "+ball.getPosition().y+" lift? "+ball.getHasLift()+" Opp = "+p.getHasLift()+" P = "+ball.getLiftSpeed()+" O = "+p.getLiftSpeed()+" speed X = "+ball.getSpeed().x+" Y = "+ball.getSpeed().y); 
			// Si la balle est de l'autre cote du Pong, on prend les donnees de l'adversaire
			if (ball.getPosition().getX() > ((SIZE_PONG_X - ball.getWidth())/2) ) {
				if ((System.currentTimeMillis() - time) >= timeStep) {
					// On test les donnees de l'adversaire pour savoir s'il a put tricher ou non
					if ((SIZE_PONG_X - ball.getPosition().getX()- ball.getWidth())!= p.getBallPosition().getX() ||
								ball.getPosition().getY() != p.getBallPosition().getY() ||
										ball.getHasLift() != p.getHasLift() ||
										ball.getLiftSpeed() != p.getLiftSpeed() ||
										ball.getSpeed().getY() != p.getBallSpeedY()) {
						cheater = true;
//						System.out.println("ball pos X = "+ball.getPosition().x+" Y = "+ball.getPosition().y+" lift? "+ball.getHasLift()+" Opp = "+p.getHasLift()+" P = "+ball.getLiftSpeed()+" O = "+p.getLiftSpeed()+" speed X = "+ball.getSpeed().x+" Y = "+ball.getSpeed().y); 
//						System.out.println("ball pos X = "+ball.getPosition().getX()+" OppX = "+(RacketType.RACKET_OPPONENT_BASE_POSITION_X - ball.getWidth()));
					}
					time = System.currentTimeMillis();
				}
				ball.setPosition(p.getBallPosition());
				ball.inverserPosition(SIZE_PONG_X);
//				System.out.println("ball pos X = "+ball.getPosition().getX()+" OppX = "+(RacketType.RACKET_OPPONENT_BASE_POSITION_X - ball.getWidth()));
				/* Pour des raisons d'implementations, si la balle est proche de la racket de l'adversaire, (proche d'environ 2 mouvements de la balle sur X) 
				 * on va prendre quelques donnees de la balle sans les testees et en annulant le test de triche*/
				if (ball.getPosition().getX() < RacketType.RACKET_OPPONENT_BASE_POSITION_X - ball.getWidth() || 
						ball.getPosition().getX() > RacketType.RACKET_OPPONENT_BASE_POSITION_X - ball.getWidth() - (2 * Math.abs(ball.getSpeed().getX()))) {
					ball.setHasLift(p.getHasLift());
					ball.setLiftSpeed(p.getLiftSpeed());
					ball.setSpeedY(p.getBallSpeedY());
					cheater = false;
				}
				if (cheater)
//					throw new ExceptionPong("ATTENTION : Il y a un tricheur dans la partie.\nLa partie est arretee");
					System.out.println("CHEATER !!!!!"); 
			}
		}
	}
	
	/**
	 * Gestion des bonus
	 */
	private void bonusManagement() {
		if (!bonusIsCreated) {
			this.bonus = new Bonus(1,4,ball,racketPlayer,racketOpponent,true);
			this.bonus1 = new Bonus(1,4,ball,racketPlayer,racketOpponent,false);
			System.out.print("Arrivee des bonus\n");
			bonusIsCreated = true;
			bonus.updateScreen(this);
			bonus1.updateScreen(this);
		}
		if (bonus.getInUse() == true) {
			bonus.moveBonus(SIZE_PONG_X, SIZE_PONG_Y ,racketPlayer, racketOpponent, ball);
			bonus.updateScreen(this);
		}
		if (bonus1.getInUse() == true) {
			bonus1.moveBonus(SIZE_PONG_X, SIZE_PONG_Y ,racketPlayer, racketOpponent, ball);
			bonus1.updateScreen(this);
		}
		if (bonus.getInUse() == false && bonus1.getInUse() == false) {
			playTime = 302;
			bonusIsCreated = false;
			System.out.print("Fin des bonus\n");
		} else
			playTime = 300;
	}
}