package gui;

import java.awt.Image;
import java.awt.Point;

public abstract class BallType extends PongItem {

	/**
	 * Definition des constantes de bases
	 */	
	private static final int BALL_BASE_SPEED_X = 2;
	private static final int BALL_BASE_SPEED_Y = 0;
	private static final int BALL_BASE_LIFTSPEED = 0;
	private static final boolean BALL_BASE_HASLIFT = false;
	private static final int BALL_BASE_POSITION_X = 390;
	private static final int BALL_BASE_POSITION_Y = 290;
	private static final int TO_MAKE_LIFTSPEED_IN_PERCENT = 10;
	private static final int LIFTSPEED_MAX = 90;
	private static final int LIFTSPEED_MIN = -90;

	/**
	 * speed est un Point qui défini la vitesse sur "X" et "Y" de la balle, en pixels par timeStep
	 * hasLift definit si oui ou non il y a un effet sur la balle
	 * liftSpeed est un pourcentage compris dans [LIFTSPEED_MIN , LIFTSPEED_MAX]%
	 * liftSpeed > 0 => rotation de sens horaire
	 * liftSpeed < 0 => rotation de sens trigo
	 */
	protected Point speed;
	protected boolean hasLift;
	protected int liftSpeed ;
	
	public BallType (Image image, boolean serveur) {
		super(image);
		if (serveur) {
			this.position.setLocation(BALL_BASE_POSITION_X, BALL_BASE_POSITION_Y);
			this.speed = new Point(BALL_BASE_SPEED_X, BALL_BASE_SPEED_Y);
		} else {
			this.position.setLocation(BALL_BASE_POSITION_X, BALL_BASE_POSITION_Y);
			this.speed = new Point(-BALL_BASE_SPEED_X, BALL_BASE_SPEED_Y);
		}
		hasLift = BALL_BASE_HASLIFT;
		liftSpeed = BALL_BASE_LIFTSPEED;
	}

	/**
	 * Remet la balle au meme etat que lors de sa construction
	 */
	public void restartBall(boolean serveur) {
		if (serveur) {
			this.position.setLocation(BALL_BASE_POSITION_X, BALL_BASE_POSITION_Y);
			this.speed.setLocation(BALL_BASE_SPEED_X, BALL_BASE_SPEED_Y);
		} else {
			this.position.setLocation(BALL_BASE_POSITION_X, BALL_BASE_POSITION_Y);
			this.speed.setLocation(-BALL_BASE_SPEED_X, BALL_BASE_SPEED_Y);
		}
		hasLift = BALL_BASE_HASLIFT;
		liftSpeed = BALL_BASE_LIFTSPEED;
	}

	/**
	 * Get / Set accesseurs des attributs
	 */
	public Point getSpeed() {
		return speed;
	}
	public void setSpeed(int x, int y) {
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
	 * Fonction de déplacement de la balle
	 */
	public void moveBall(RacketType racketPlayer, RacketType racketOpponent, Score score) {
		// On cree une copie de speed.y pour connaitre le nombre de mouvement Y restant
		int mvtYToDo = Math.abs(speed.y);
		// On calcul combien de mouvement sur Y il faut faire, pour chaque mouvement de X
		int mvtYtoX = (Math.abs(speed.y)/Math.abs(speed.x));
		// On calcul combien de mouvement sur X il faut faire, pour chaque mouvement de Y
		int mvtXtoY = 0;
		if (speed.y != 0)
			mvtXtoY = (Math.abs(speed.x)/Math.abs(speed.y));
		// On note combien de mouvement sur X ont ete realise
		int mvtXMade = 0;
		// Pour chaque speed.x on va se déplacer de speed.y/speed.x
		for(int i=Math.abs(speed.x); i>0; i--) {
			// Si la balle est sur la raquette on change la direction de la balle
			moveBallOnRacketCote(racketPlayer, racketOpponent);
			// Si la raquette est en mouvement, on donne un effet a la balle
			if(testIfMoveRacketCote(racketPlayer)) {
				doLift(racketPlayer, true);
			}
			if(testIfMoveRacketCote(racketOpponent)) {
				doLift(racketOpponent, false);
			}
			// Si la direction de la balle n'est pas horizontal
			if (speed.y != 0) {
				// Pour chaque mouvement vertical
				// Si n'y a pas de mouvement Y par X a faire
				if (mvtYtoX == 0) {
					// Si le nombre de mouvement X par Y a ete fait ET s'il reste des mouvements Y a faire
					if (mvtXtoY == mvtXMade && mvtYToDo != 0) {
						// On realise le mouvement d'un pixel sur Y
						oneMoveBallOnY(racketPlayer, racketOpponent);
						// On remet a 0 le nombre de mouvement X realise
						mvtXMade=0;
						// On decremente le nombre de mouvement vertical restant
						mvtYToDo --;
					}
				} else {
					for(int j=mvtYtoX; j>0; j--) {
						// On realise le mouvement d'un pixel sur Y
						oneMoveBallOnY(racketPlayer, racketOpponent);
						// On decremente le nombre de mouvement vertical restant
						mvtYToDo --;
						// Ce test permet d'ajouter le reste de la division de speed.y par speed.x
						// Ainsi on pourra realiser ces derniers mouvements sur la dernière iteration de boucle de speed.x
						if (mvtYToDo<Math.abs(speed.x) && j==1 && Math.abs(speed.x)!=Math.abs(speed.y))
							j += mvtYToDo;
					}
				}
			}
			// On realise le mouvement horizontal d'un pixel
			position.translate(speed.x/Math.abs(speed.x), 0); 
			mvtXMade ++;
			// Si la balle touche notre cote du pong, on arrete la balle ET l'adversaire gagne un point
			// C'est la fin d'un round
			if (position.x < 0) {
				position.x = 0;
				i = 0;
				setSpeed(0, 0);
				score.incrementScoreOpponent();
				score.setFinRound(true);
			}
			// Si la balle touche le cote du pong adverse, on arrete la balle ET nous gagnons un point
			// C'est la fin d'un round
			if (position.x > Pong.SIZE_PONG_X - width) {
				position.x = Pong.SIZE_PONG_X - width;
				i = 0;
				setSpeed(0, 0);
				score.incrementScorePlayer();
				score.setFinRound(true);
			}
		}
	}
	
	/**
	 * Realisation d'un mouvement vertical (sur Y) de la balle d'un pixel 
	 */
	public void oneMoveBallOnY (RacketType racketPlayer, RacketType racketOpponent) {
		// On test si on ne touche pas la raquette
		moveBallOnRacketTopOrBot(racketPlayer, racketOpponent);
		// On realise le mouvement vertical d'un pixel
		position.translate(0, speed.y/Math.abs(speed.y));
		// Si la balle tape le haut du pong
		if (position.y < 0) {
			position.y = 0;
			// On inverse son mouvement ET on y applique l'effet de la balle SI elle en a un
			if(getHasLift()) {
				setHasLift(false);
				speed.y = -(speed.y + ((liftSpeed * speed.y) /100));
			}
			else
				speed.y = -speed.y;
		}
		// Si la balle tape le bas du pong
		if (position.y > Pong.SIZE_PONG_Y - height) {
			position.y = Pong.SIZE_PONG_Y - height;
			// On inverse son mouvement ET on y applique l'effet de la balle SI elle en a un
			if(this.hasLift) {
				this.hasLift = false;
				speed.y = -(speed.y + (((-liftSpeed) * speed.y) /100));
			}
			else
				speed.y = -speed.y;
		}
	}
	
    /**
     * Reaction de la balle si elle touche le cote de la raquette
     */
    public void moveBallOnRacketCote(RacketType racketPlayer, RacketType racketOpponent) {
    	if (racketPlayer.itemOnRacketCote(this)) 
    		changeDirectionBall(racketPlayer);
    	if (racketOpponent.itemOnRacketCote(this)) 
    		changeDirectionBall(racketOpponent);
    }

	/**
	 * Reaction de la balle si elle touche le haut ou le bas de la raquette
	 */
	public void moveBallOnRacketTopOrBot(RacketType racketPlayer, RacketType racketOpponent) {	
		if (racketPlayer.itemOnRacketTop(this) || racketOpponent.itemOnRacketTop(this) ||
				racketPlayer.itemOnRacketBot(this) || racketOpponent.itemOnRacketBot(this))
			setSpeedY(- getSpeed().y);	
	}
    
    public void changeDirectionBall (RacketType racket) {
    	// Valeur calculee par : ((distance entre le milieu de la balle et le milieu de la raquette) / 
    	// ((1/2)* (longueur de la raquette + longueur de la balle))) * 
    	// decalage Maximal sur Y
    	float valueY = ((((float) getPosition().y + (getHeight() / 2)) - (racket.getPosition().y + (racket.getHeight() / 2))) / 
    			(((racket.getHeight() + getHeight()) / 2))) * Pong.DECALAGE_MAX_ON_RACKET;
    	// Si la balle a un effet
    	if(getHasLift()){
    		// On ajoute l'effet a la direction de la balle, cet effet est defini comme un pourcentage
    		if(valueY > 0)
    			valueY = (float)valueY + ((valueY * getLiftSpeed())/100);
    		else{
    			if(valueY < 0)
    				valueY = (float)valueY + ((valueY * (- getLiftSpeed()))/100);
    			else
    				valueY = 0;
    		}
    		// On enleve l'effet sur la balle
    		setHasLift(false);
    	}
    	setSpeedY(Math.round(valueY));
    	setSpeedX(- getSpeed().x);
    }
	
	/**
	 * Test si la raquette touche la balle quand elle est en mouvement
	 */
	public boolean testIfMoveRacketCote (RacketType racket) {
		return (racket.itemOnRacketCote(this) && racket.getSpeed() != 0);
	}

	/**
	 * Applique un effet lift sur la balle selon la vitesse de la raquette
	 * Cet effet est traduit en pourcentage compris entre [-LIFTSPEED_MIN ; LIFTSPEED_MAX]
	 */
	public void doLift (RacketType racket, boolean player) {
		this.hasLift = true;
		if (racket.getSpeed()<0) {
			if (player)
				liftSpeed = racket.getSpeed()*TO_MAKE_LIFTSPEED_IN_PERCENT;
			else
				liftSpeed = -racket.getSpeed()*TO_MAKE_LIFTSPEED_IN_PERCENT;
		} else {
			if (player)
				liftSpeed = -racket.getSpeed()*TO_MAKE_LIFTSPEED_IN_PERCENT;
			else
				liftSpeed = racket.getSpeed()*TO_MAKE_LIFTSPEED_IN_PERCENT;
		}
		if (liftSpeed > LIFTSPEED_MAX)
			liftSpeed = LIFTSPEED_MAX;
		if (liftSpeed < LIFTSPEED_MIN)
			liftSpeed = LIFTSPEED_MIN;
	}
}