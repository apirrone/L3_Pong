package gui;

import java.awt.Image;
import java.awt.Point;

public abstract class BallType extends PongItem{

	/**
	 * Speed of ball, in pixels per second
	 */	
	private static final int BALL_SPEED_X = 2;

	/**
	 * Point defining speed of ball, in pixels per timestamp
	 * hasLift definit si oui ou non il y a un effet sur la balle
	 * liftSpeed est un pourcentage compris dans [-100% , 100%]
	 * liftSpeed>0 => lift sens horaire
	 * liftSpeed<0 => lift sens trigo
	 */
	protected Point speed;
	protected boolean hasLift;
	protected int liftSpeed ;
	
	public BallType (Image image, boolean serveur) {
		super(image);
		if (serveur){
			this.position.setLocation(390, 290);
			this.speed = new Point(BALL_SPEED_X, 0);
		}else{
			this.position.setLocation(390, 290);
			this.speed = new Point(-BALL_SPEED_X, 0);
		}
		hasLift = false;
		liftSpeed = 0;
	}

	/**
	 * Restart ball in early definition
	 */
	public void restartBall(boolean serveur) {
		if (serveur){
			this.position.setLocation(390, 290);
			this.speed = new Point(BALL_SPEED_X, 0);
		}else{
			this.position.setLocation(390, 290);
			this.speed = new Point(-BALL_SPEED_X, 0);
		}
		hasLift = false;
		liftSpeed = 0;
	}

	/**
	 * Get / Set accessors object
	 */
	public Point getSpeed() {
		return speed;
	}
	public void setSpeed(int x, int y){
		speed.x = x;
		speed.y = y;
	}
	public void setSpeedX(int speedX) {
		this.speed.setLocation(speedX, getSpeed().y);
	}

	public void setSpeedY(int speedY) {
		this.speed.setLocation(getSpeed().x, speedY);
	}
	
	public boolean getHasLift() {
		return hasLift;
	}

	public void setHasLift(boolean hasLift) {
		this.hasLift = hasLift;
	}
	
	public int getLiftSpeed() {
		return liftSpeed;
	}

	public void setLiftSpeed(int liftSpeed) {
		this.liftSpeed = liftSpeed;
	}

	/**
	 * Move ball position
	 */
	public void moveBall(int size_pong_x, int size_pong_y, RacketType racketPlayer, RacketType racketOpponent, Score score) {
		// On crée une copie de speed.y pour garder, plus tard, le reste de la division par speed.x
		int copyOfY=Math.abs(speed.y);
		// Pour chaque speed.x on va se déplacer de speed.y/speed.x
		for(int i=Math.abs(speed.x); i>0; i--){
			// Si la balle est sur la raquette on change la direction de la balle
			racketPlayer.moveBallOnRacketCote(size_pong_x, size_pong_y, racketPlayer, racketOpponent, this);
			// Si la raquette est en mouvement, on donne un effet a la balle
			if(testIfMoveRacketCote(racketPlayer)){
				doLift(racketPlayer, true);
			}
			if(testIfMoveRacketCote(racketOpponent)){
				doLift(racketOpponent, false);
			}
			// Si la direction de la balle n'est pas horizontal
			if (speed.y != 0) {
				// Pour chaque mouvement vertical
				for(int j=(Math.abs(speed.y)/Math.abs(speed.x)); j>0; j--){
					// On test si on ne touche pas la raquette
					racketPlayer.moveBallOnRacketOther(size_pong_x, size_pong_y, racketPlayer, racketOpponent, this);
					// On réalise le mouvement vertical d'un pixel
					position.translate(0, speed.y/Math.abs(speed.y));
					// Si la balle tape le haut du pong
					if (position.y < 0){
						position.y = 0;
						// On inverse son mouvement ET on y applique l'effet de la balle SI elle en a un
						if(getHasLift()){
							setHasLift(false);
							speed.y = -(speed.y + ((liftSpeed * speed.y) /100));
						}
						else
							speed.y = -speed.y;
					}
					// Si la balle tape le bas du pong
					if (position.y > size_pong_y - height){
						position.y = size_pong_y - height;
						// On inverse son mouvement ET on y applique l'effet de la balle SI elle en a un
						if(this.hasLift){
							this.hasLift = false;
							speed.y = -(speed.y + (((-liftSpeed) * speed.y) /100));
						}
						else
							speed.y = -speed.y;
					}
					// On decremente notre copie de speed.y pour connaitre le nombre de mouvement vertical restant
					copyOfY--;
					// Ce test permet d'ajouter le reste de la division de speed.y par speed.x
					// Ainsi on pourra realiser ces derniers mouvements sur la dernière iteration de boucle de speed.x
					if (copyOfY<Math.abs(speed.x) && j==1)
						j+=copyOfY;
				}
			}
			// On réalise le mouvement horizontal d'un pixel
			position.translate(speed.x/Math.abs(speed.x), 0);
			// Si la balle touche notre cote du pong, on arrete la balle ET l'adversaire gagne un point
			// C'est la fin d'un round
			if (position.x < 0){
				position.x = 0;
				i = 0;
				setSpeed(0, 0);
				score.incrementScoreOpponent();
				score.setFinRound(true);
			}
			// Si la balle touche le cote du pong adverse, on arrete la balle ET nous gagnons un point
			// C'est la fin d'un round
			if (position.x > size_pong_x - width){
				position.x = size_pong_x - width;
				i = 0;
				setSpeed(0, 0);
				score.incrementScorePlayer();
				score.setFinRound(true);
			}
		}
	}
	
	/**
	 * Test si la raquette touche la balle quand elle est en mouvement
	 */
	public boolean testIfMoveRacketCote (RacketType racket){
		return (racket.itemOnRacketCote(this) && racket.getSpeed() != 0);
	}

	/**
	 * Applique un effet lift sur la balle selon la vitesse de la raquette
	 * Cet effet est traduit en pourcentage, donc la vitesse de la raquette doit etre comprise dans [-10 ; 10]
	 */
	public void doLift (RacketType racket, boolean player){
		this.hasLift = true;
		if(racket.getSpeed()<0){
			if(player)
				liftSpeed = racket.getSpeed()*10;
			else
				liftSpeed = -racket.getSpeed()*10;
		}else{
			if(player)
				liftSpeed = -racket.getSpeed()*10;
			else
				liftSpeed = racket.getSpeed()*10;
		}
	}
}
