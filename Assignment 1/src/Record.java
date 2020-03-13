
public class Record {
	/*
	 * Declare the instance variables: game configuration and its respective score.
	 */
	private String config;
	private int score;
	
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}
	
	public String getConfig() {
		return config;
	}
	
	public int getScore() {
		return score;
	}
}
