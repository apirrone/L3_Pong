package tests;

import static org.junit.Assert.*;

import gui.Racket;

import org.junit.Test;

public class RacketTest {

	@Test
	public void testRacket() {
		Racket racket = new Racket(true);
		assertNotNull(racket);
		assertTrue(racket.getHeight() == 76 && racket.getWidth() == 22);
	}
	
	@Test
	public void restartImageRacket() {
		Racket racket = new Racket(true);
		assertNotNull(racket);
		racket.restartImageRacket();
		assertTrue(racket.getHeight() == 76 && racket.getWidth() == 22);
	}
	
	@Test
	public void divideRacket() {
		Racket racket = new Racket(true);
		racket.multiplyRacket();
		racket.divideRacket();
		assertTrue(racket.getHeight() == 76 && racket.getWidth() == 22);
		racket.divideRacket();
		assertTrue(racket.getHeight() == 38 && racket.getWidth() == 22);
		racket.divideRacket();
		assertTrue(racket.getHeight() == 19 && racket.getWidth() == 22);
		racket.divideRacket();
		assertTrue(racket.getHeight() == 19 && racket.getWidth() == 22);
	}
	
	@Test
	public void multiplyRacket() {
		Racket racket = new Racket(true);
		racket.divideRacket();
		racket.divideRacket();
		racket.multiplyRacket();
		assertTrue(racket.getHeight() == 38 && racket.getWidth() == 22);
		racket.multiplyRacket();
		assertTrue(racket.getHeight() == 76 && racket.getWidth() == 22);
		racket.multiplyRacket();
		assertTrue(racket.getHeight() == 152 && racket.getWidth() == 22);
		racket.multiplyRacket();
		assertTrue(racket.getHeight() == 152 && racket.getWidth() == 22);
	}
}
