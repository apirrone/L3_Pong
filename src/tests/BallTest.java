package tests;

import static org.junit.Assert.*;
import gui.Ball;

import org.junit.Test;

public class BallTest {

	@Test
	public void testBall() {
		Ball ball = new Ball(true);
		assertNotNull(ball);
		assertTrue(ball.getHeight() == 20 && ball.getWidth() == 20);
	}
}
