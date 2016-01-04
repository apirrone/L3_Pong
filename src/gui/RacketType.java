package gui;

import java.awt.Image;

public abstract class RacketType extends PongItem {
	
	/**
	 * Definition des constantes de bases
	 */	
	public static final int RACKET_BASE_SPEED = 4;
	public static final int RACKET_PLAYER_BASE_POSITION_X = 28;
	public static final int RACKET_OPPONENT_BASE_POSITION_X = 750;
	public static final int RACKET_BASE_POSITION_Y = 250;
	
	/**
	 * speed defini la vitesse sur "Y" de la raquette, en pixels par timeStep
	 */
	protected int speed;
	private int speedMax;
	
	public RacketType(Image image, boolean player) {
		super(image);
		speed = 0;
		setSpeedMax(RACKET_BASE_SPEED);
		if(player)
			this.position.setLocation(RACKET_PLAYER_BASE_POSITION_X, RACKET_BASE_POSITION_Y);
		else
			this.position.setLocation(RACKET_OPPONENT_BASE_POSITION_X, RACKET_BASE_POSITION_Y);
	}
	
	/**
	 * Remet la raquette au meme etat que lors de sa construction
	 */
	public void restartRacket(boolean player) {
		this.restartImageRacket();
		speed = 0;
		setSpeedMax(RACKET_BASE_SPEED);
		if(player)
			this.position.setLocation(RACKET_PLAYER_BASE_POSITION_X, RACKET_BASE_POSITION_Y);
		else
			this.position.setLocation(RACKET_OPPONENT_BASE_POSITION_X, RACKET_BASE_POSITION_Y);
	}

	/**
	 * Get / Set accesseurs de l'attribut "speed"
	 */
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(int speedMax) {
		this.speedMax = speedMax;
	}
	
	public void setY(int y) {
		this.position.setLocation(this.position.getX(), y);
	}

	abstract public void divideRacket();
	abstract public void multiplyRacket();
	abstract public void restartImageRacket();

	/**
	 * Fonction de deplacement de la raquette
	 */
	public void moveRacket(BallType ball) {
		boolean prob=false;
		// Tant que l'on ne rencontre pas d'obstacle, la raquette realise le mouvement demande
		for(int i=Math.abs(speed); i>0 && prob==false; i--) {
			if (itemOnRacketTop(ball) || itemOnRacketBot(ball) || itemOnRacketCorner(ball))
				prob=true;
			else
				position.y += (speed/Math.abs(speed));
		}
		// Si apres le dernier mouvement on rencontre un obstacle, il y a probleme
		if (itemOnRacketTop(ball) || itemOnRacketBot(ball) || itemOnRacketCorner(ball))
			prob=true;
		/* Si ce probleme a lieu parce que, avant le premier mouvement, la balle touchait la raquette *
		 * par le haut ou le bas et que la raquette allait dans la meme direction que la balle        */
		if (itemOnRacketTop(ball) && getSpeed() > 0 && ball.getSpeed().getX() > 0 || 
				itemOnRacketBot(ball) && getSpeed() < 0 && ball.getSpeed().getX() < 0)
			prob=false;
		if (prob && speed != 0)
			position.y -= (speed/Math.abs(speed));
		if (position.y < 0)
			position.y = 0;
		if (position.y > Pong.SIZE_PONG_Y - height)
			position.y = Pong.SIZE_PONG_Y - height; 
	}

	/**
     * Teste si l'item touche le cote droite ou gauche de la raquette
	 */
	public boolean itemOnRacketCote(PongItem item) {
		return ((((
			item.getPosition().y > getPosition().y &&
			item.getPosition().y < getPosition().y + getHeight()) || (
			item.getPosition().y + item.getHeight() > getPosition().y &&
			item.getPosition().y + item.getHeight() < getPosition().y + getHeight())) && ((
			item.getPosition().x == getPosition().x + getWidth()) || (
			item.getPosition().x + item.getWidth() == getPosition().x))));
	}

	/**
     * Teste si l'item touche le cote haut de la raquette
	 */
	public boolean itemOnRacketTop(PongItem item) {
		return ((((
			item.getPosition().x > getPosition().x &&
			item.getPosition().x < getPosition().x + getWidth()) || (
			item.getPosition().x + item.getWidth() > getPosition().x &&
			item.getPosition().x + item.getWidth() < getPosition().x + getWidth())) && (
			item.getPosition().y + item.getHeight() == getPosition().y )));
	}

	/**
     * Teste si l'item touche le cote bas de la raquette
	 */
	public boolean itemOnRacketBot(PongItem item) {
		return ((((
			item.getPosition().x > getPosition().x &&
			item.getPosition().x < getPosition().x + getWidth()) || (
			item.getPosition().x + item.getWidth() > getPosition().x &&
			item.getPosition().x + item.getWidth() < getPosition().x + getWidth())) && (
			item.getPosition().y == getPosition().y + getHeight())));
	}

	/**
     * Teste si l'item touche l'un des coins de la raquette
	 */
	public boolean itemOnRacketCorner(PongItem item) {
		return ((
			item.getPosition().y == getPosition().y + getHeight() || 
			item.getPosition().y + item.getHeight() == getPosition().y) && (
			item.getPosition().x == getPosition().x + getWidth() || 
			item.getPosition().x + item.getWidth() == getPosition().x));
	}
}