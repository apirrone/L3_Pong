package tests;

import static org.junit.Assert.*;
import gui.PongItem;
import gui.Racket;


import org.junit.Test;

public class PongItemTest {

	@Test
	public void testSwitchPosition() {
		PongItem pongItem = new Racket(true);
		pongItem.switchPosition();
		assertTrue(pongItem.getPosition().x == 750 && pongItem.getPosition().y == 250);
	}
}
