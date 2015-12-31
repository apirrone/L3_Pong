package gui;

public class Score {
	
	private int scorePlayer;
	private int scoreOpponent;
	private boolean finRound;

	public Score() {
		scorePlayer = 0;
		scoreOpponent = 0;
		setFinRound(false);
	}

	/**
	 * Get / Set accesseurs des attributs
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

	public boolean getFinRound() {
		return finRound;
	}

	public void setFinRound(boolean finRound) {
		this.finRound = finRound;
	}
}