package tests;

import static org.junit.Assert.*;
import gui.BallType;
import gui.Ball;
import gui.RacketType;
import gui.Racket;
import gui.Score;

import org.junit.Test;

public class BallTypeTest {

	@Test
	public void testBallType() {
		BallType ball = new Ball(true);
		assertNotNull(ball);
		assertTrue(ball.getSpeed().x ==2 && ball.getSpeed().y ==0);
		assertFalse(ball.getHasLift());
		assertTrue(ball.getLiftSpeed() == 0);
	}

	@Test
	public void testRestartBall() {
		BallType ball = new Ball(true);
		ball.restartBall(true);
		assertNotNull(ball);
		assertTrue(ball.getSpeed().x ==2 && ball.getSpeed().y ==0);
		assertFalse(ball.getHasLift());
		assertTrue(ball.getLiftSpeed() == 0);
	}

	@Test
	public void testGetSpeed() {
		BallType ball = new Ball(true);
		ball.setSpeed(1, 2);
		assertTrue(ball.getSpeed().x == 1 && ball.getSpeed().y ==2);
	}

	@Test
	public void testSetSpeed() {
		BallType ball = new Ball(true);
		ball.setSpeed(1, 2);
		assertTrue(ball.getSpeed().x == 1 && ball.getSpeed().y ==2);
	}

	@Test
	public void testSetSpeedX() {
		BallType ball = new Ball(true);
		ball.setSpeedX(5);
		assertTrue(ball.getSpeed().x == 5);
	}

	@Test
	public void testSetSpeedY() {
		BallType ball = new Ball(true);
		ball.setSpeedY(5);
		assertTrue(ball.getSpeed().y == 5);
	}

	@Test
	public void testGetHasLift() {
		BallType ball = new Ball(true);
		ball.setHasLift(true);
		assertTrue(ball.getHasLift());
	}

	@Test
	public void testSetHasLift() {
		BallType ball = new Ball(true);
		ball.setHasLift(true);
		assertTrue(ball.getHasLift());
	}

	@Test
	public void testGetLiftSpeed() {
		BallType ball = new Ball(true);
		ball.setLiftSpeed(5);
		assertTrue(ball.getLiftSpeed() == 5);
	}

	@Test
	public void testSetLiftSpeed() {
		BallType ball = new Ball(true);
		ball.setLiftSpeed(3);
		assertTrue(ball.getLiftSpeed() == 3);
	}

	@Test
	public void testMoveBall() {
		BallType ball = new Ball(true);
		RacketType racket = new Racket(true);
		RacketType racket2 = new Racket(false);
		Score score = new Score();
		ball.moveBall(racket, racket2, score);
		assertTrue(ball.getPosition().x ==392 && ball.getPosition().y == 290);
	}

	@Test
	public void testOneMoveBallOnY() {
		BallType ball = new Ball(true);
		RacketType racket = new Racket(true);
		RacketType racket2 = new Racket(false);
		ball.getSpeed().y = 1;
		ball.oneMoveBallOnY(racket, racket2);
	}

	@Test
	public void testMoveBallOnRacketCote() {
		BallType ball = new Ball(true);
		RacketType racket = new Racket(true);
		RacketType racket2 = new Racket(false);
		ball.setPosition(50 , 300);
		assertTrue("true", racket.itemOnRacketCote(ball));
		ball.moveBallOnRacketCote(racket, racket2);
		assertTrue(ball.getSpeed().x == -2 && ball.getSpeed().y == 3);
	}

	@Test
	public void testMoveBallOnRacketTopOrBot() {
		RacketType racket = new Racket(true);
		RacketType racket2 = new Racket(true);
		BallType ball = new Ball(true);
		ball.setPosition(30 , 230);
		ball.getSpeed().y = 2;
		ball.moveBallOnRacketTopOrBot(racket, racket2);
		assertTrue(ball.getSpeed().y == -2);
	}

	@Test
	public void testChangeDirectionBall() {
		BallType ball = new Ball(true);
		RacketType racket = new Racket(true);
		RacketType racket2 = new Racket(false);
		ball.setPosition(50 , 300);
		assertTrue("true", racket.itemOnRacketCote(ball));
		ball.moveBallOnRacketCote(racket, racket2);
		assertTrue(ball.getSpeed().x == -2 && ball.getSpeed().y == 3);
	}

	@Test
	public void testTestIfMoveRacketCote() {
		BallType ball = new Ball(true);
		RacketType racket = new Racket(true);
		ball.setPosition(50 , 300);
		assertFalse(ball.testIfMoveRacketCote(racket));
	}

	@Test
	public void testDoLift() {
		BallType ball = new Ball(true);
		RacketType racket = new Racket(true);
		racket.setSpeed(2);
		ball.doLift(racket, true);
		assertTrue(ball.getHasLift());
		assertTrue(ball.getLiftSpeed() == -20);
	}
}
