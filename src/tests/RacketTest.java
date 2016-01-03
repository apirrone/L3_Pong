package tests;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Toolkit;

import gui.Racket;

import org.junit.Test;

public class RacketTest {

	@Test
	public void testRacket() {
		Racket racket = new Racket(true);
		assertNotNull(racket);
		Image imageTest = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("ressource/barrePong.png"));
		assertTrue(imageTest.toString().compareTo(racket.getImage().toString()) == 0);
	}

}
