package gui;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class MagneticRacket extends PongItem{
	/**
	 * SpeedPoint used for stopping the ball on the mangnetic racket
	 */
	private static final Point SpeedStop = new Point(0, 0);

	/** Constructor of MagneticRacket
	 * 
	 */
	public MagneticRacket(Image image, boolean player) {
		super(image);
		if(player)
			this.position = new Point(78, 0);
		else
			this.position = new Point(700, 0);
	}
	/**
	 * Get / Set accessors object
	 */
	public void setY(int y){
		this.position = new Point((int)this.position.getX(), y);
	}

	/**
	 * Move racket position
	 */
	public void moveMagneticRacket(int size_pong_y, Ball ball) {
		int posY=position.y;
		boolean prob=false;
		for(int i=Math.abs(speed); i>0; i--){
			if (itemOnMagneticRacketHaut(ball, this))
				prob=true;
			position.y += (speed/Math.abs(speed));
		}
		if (itemOnMagneticRacketHaut(ball, this))
			prob=true;
		if (prob)
			position.y = posY;
		if (position.y < 0)
			position.y = 0;
		if (position.y > size_pong_y - height)
			position.y = size_pong_y - height;
	}
	
	public boolean itemOnMagneticRacketHaut(PongItem item, MagneticRacket racket){
		return (super.itemOnRacketHaut(item, racket));
	}
	public boolean itemOnMagneticRacketCote(PongItem item, MagneticRacket racket) {
		return (super.itemOnRacketCote(item, racket));
	}
	/**
	 * moveBall with MagneticRackets changes, not finished yet
	 */
	public void moveBall(int size_pong_x, int size_pong_y, MagneticRacket racketPlayer,MagneticRacket racketOpponent, PongItem ball){
		//on verifie si le PongItem est bien une balle
		if (ball instanceof Ball){
			Ball ballon;
			ballon = (Ball) ball;
			for(int i=Math.abs(ballon.getSpeedBall().x); i>0; i--){
				if (itemOnMagneticRacketCote(ballon, racketPlayer) || itemOnMagneticRacketHaut(ballon, racketPlayer)){
					ballon.getSpeedBall().x = this.position.x;
					ballon.getSpeedBall().y = this.position.y;
				}
				if (itemOnMagneticRacketCote(ballon, racketOpponent) || itemOnMagneticRacketCote(ballon, racketOpponent)){
					ballon.getSpeedBall().x = this.position.x;
					ballon.getSpeedBall().y = this.position.y;
				}
				//changement du code de moveBall dans pongItem pour uniformalisation du code et ainsi utiliser super() ici
	}
	}
}
}
	


