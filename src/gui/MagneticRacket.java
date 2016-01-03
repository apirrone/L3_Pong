package gui;

import java.awt.Toolkit;

public class MagneticRacket extends RacketType {
	
	public MagneticRacket(boolean player) {
		super(Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/barrePongAimante.png")), player);
	}
	@Override
	public void divideRacket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void multiplyRacket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restartImageRacket() {
		// TODO Auto-generated method stub
		
	}
}