package tests;

import static org.junit.Assert.*;
import gui.Score;

import org.junit.Test;

public class ScoreTest {

	@Test
	public void testScore() {
		Score score = new Score();
		assertNotNull(score);
		assertTrue(score.getScorePlayer() == 0 && score.getScoreOpponent() == 0);
		assertFalse(score.getFinRound());
	}

	@Test
	public void testGetScorePlayer() {
		Score score = new Score();
		score.incrementScorePlayer();
		assertTrue(score.getScorePlayer() == 1);
	}

	@Test
	public void testGetScoreOpponent() {
		Score score = new Score();
		score.incrementScoreOpponent();
		assertTrue(score.getScoreOpponent() == 1);
	}

	@Test
	public void testIncrementScorePlayer() {
		Score score = new Score();
		score.incrementScorePlayer();
		assertTrue(score.getScorePlayer() == 1);
	}

	@Test
	public void testIncrementScoreOpponent() {
		Score score = new Score();
		score.incrementScoreOpponent();
		assertTrue(score.getScoreOpponent() == 1);
	}

	@Test
	public void testGetFinRound() {
		Score score = new Score();
		assertFalse(score.getFinRound());
		score.setFinRound(true);
		assertTrue(score.getFinRound());
	}

	@Test
	public void testSetFinRound() {
		Score score = new Score();
		assertFalse(score.getFinRound());
		score.setFinRound(true);
		assertTrue(score.getFinRound());
	}
}
