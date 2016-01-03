package tests;

import static org.junit.Assert.*;
import gui.RacketType;
import gui.Racket;
import gui.BallType;
import gui.Ball;

import org.junit.Test;

public class RacketTypeTest {

	@Test
	public void testRacketType() {
		RacketType racket = new Racket(true);
		assertNotNull(racket);
		assertTrue(racket.getSpeed() == 0);
		assertTrue(racket.getSpeedMax() == 4);
		assertTrue(racket.getPosition().x == 28 && racket.getPosition().y == 250);
	}

	@Test
	public void testRestartRacket() {
		RacketType racket = new Racket(true);
		racket.restartRacket(true);
		assertNotNull(racket);
		assertTrue(racket.getSpeed() == 0);
		assertTrue(racket.getSpeedMax() == 4);
		assertTrue(racket.getPosition().x == 28 && racket.getPosition().y == 250);
	}

	@Test
	public void testGetSpeed() {
		RacketType racket = new Racket(true);
		racket.setSpeed(2);
		assertTrue(racket.getSpeed() == 2);
	}

	@Test
	public void testSetSpeed() {
		RacketType racket = new Racket(true);
		racket.setSpeed(5);
		assertTrue(racket.getSpeed() == 5);
	}

	@Test
	public void testGetSpeedMax() {
		RacketType racket = new Racket(true);
		racket.setSpeedMax(6);
		assertTrue(racket.getSpeedMax() == 6);
	}

	@Test
	public void testSetSpeedMax() {
		RacketType racket = new Racket(true);
		racket.setSpeedMax(7);
		assertTrue(racket.getSpeedMax() == 7);
	}

	@Test
	public void testMoveRacket() {
		RacketType racket = new Racket(true);
		BallType ball = new Ball(true);
		ball.setPosition(30 , 230);
		racket.setSpeed(2);
		racket.moveRacket(ball);
		assertTrue(racket.getPosition().y == 249);
	}

	@Test
	public void testSetY() {
		RacketType racket = new Racket(true);
		racket.setY(5);
		assertTrue(racket.getPosition().y == 5);
	}

	@Test
	public void testItemOnRacketCote() {
		RacketType racket = new Racket(true);
		BallType ball = new Ball(true);
		ball.setPosition(50 , 300);
		assertTrue(racket.itemOnRacketCote(ball));
	}

	@Test
	public void testItemOnRacketHaut() {
		RacketType racket = new Racket(true);
		BallType ball = new Ball(true);
		ball.setPosition(30 , 230);
		assertTrue(racket.itemOnRacketHaut(ball));
	}

	@Test
	public void testItemOnRacketCorner() {
		RacketType racket = new Racket(true);
		BallType ball = new Ball(true);
		ball.setPosition(50 , 326);
		assertTrue(racket.itemOnRacketCorner(ball));
	}

}
