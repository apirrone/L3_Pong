package tests;

import static org.junit.Assert.*;
import gui.Ball;
import gui.BallType;
import gui.Bonus;
import gui.Racket;
import gui.RacketType;

import org.junit.Test;

public class BonusTest {

	@Test
	public void testBonus() {
		Bonus bonus = new Bonus();
		assertNotNull(bonus);
		assertFalse(bonus.getInUse());
		assertTrue(bonus.getSpeed().x == 0 && bonus.getSpeed().y == 0);
		assertTrue(bonus.getPosition().x == 386 && bonus.getPosition().y == 0);
	}

	@Test
	public void testSetBonus() {
		Bonus bonus = new Bonus();
		bonus.setBonus(true, 0, 0);
		assertTrue(bonus.getInUse());
		assertTrue(bonus.getPosition().x == 386 && bonus.getPosition().y == 0);
		assertTrue(bonus.getSpeed().x == -1 && bonus.getSpeed().y == 0);
	}

	@Test
	public void testGetInUse() {
		Bonus bonus = new Bonus();
		assertFalse(bonus.getInUse());
	}

	@Test
	public void testMoveBonus() {
		Bonus bonus = new Bonus();
		RacketType racket = new Racket(true);
		RacketType racket2 = new Racket(false);
		BallType ball = new Ball(true);
		bonus.moveBonus(racket, racket2, ball);
		assertTrue(bonus.getPosition().x == 386);
		bonus.setBonus(true, 0, 0);
		bonus.moveBonus(racket, racket, ball);
		assertTrue(bonus.getPosition().x == 385);
	}

	@Test
	public void testDeleteBonus() {
		Bonus bonus = new Bonus();
		assertFalse(bonus.getInUse());
	}

}
