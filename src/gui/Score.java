package gui;

public class Score {
	
	private int scorePlayer;
	private int scoreOpponent;

	public Score(){
		scorePlayer = 0;
		scoreOpponent = 0;
	}

	/**
	 * Get / Set accessors object
	 */
	public int getScorePlayer() {
		return scorePlayer;
	}
	
	public int getScoreOpponent() {
		return scoreOpponent;
	}

	public void incrementScorePlayer() {
		this.scorePlayer++;
	}

	public void incrementScoreOpponent() {
		this.scoreOpponent++;
	}
	
	
}
