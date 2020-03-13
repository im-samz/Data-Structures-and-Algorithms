
public class nk_TicTacToe {
	
	/**
	 * Declare all the instance variables we will use.
	 */
	char[][] gameboard;
	int board_area;
	int board_size;
	int k;
	int max_levels;
	
	/**
	 * Constructor method for the TicTacToe game.
	 * @param board_size
	 * @param inline
	 * @param max_levels
	 */
	public nk_TicTacToe(int board_size, int inline, int max_levels) {
		// Initialize some variables from input.
		this.board_size = board_size;
		this.k = inline;
		this.max_levels = max_levels;
		board_area = board_size*board_size;
		
		// Create a gameboard.
		gameboard = new char[board_size][board_size];
				
		// Set every position of the gameboard to a space.
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				gameboard[i][j] = ' ';
			}
		}
	}
	
	/**
	 * Creates an instance of the dictionary class. 
	 * It will set the default size to 7069.
	 * @return
	 */
	public Dictionary createDictionary() {
		Dictionary dict = new Dictionary(7069);
		return dict;
	}
	
	/**
	 * Checks the dictionary if the gameboard configuration already exists.
	 * @param configurations
	 * @return -1 if it does not exist or the score if it does.
	 */
	public int repeatedConfig(Dictionary configurations) {
		return configurations.get(convert2String(gameboard));
	}
	
	/**
	 * Will insert the configuration and score as a Record class into the dictionary.
	 * @param configurations
	 * @param score
	 */
	public void insertConfig(Dictionary configurations, int score) {
		// Use repeatedConfig() to check if this configuration exists yet.
		if (repeatedConfig(configurations) == -1) {
			Record instance = new Record(convert2String(gameboard), score);
			configurations.insert(instance);
		}
	}
	
	/**
	 * Stores a particular play into the gameboard.
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void storePlay(int row, int col, char symbol) {
		gameboard[row][col] = symbol;
	}
	
	/**
	 * Checks if a particular square in the gameboard is empty.
	 * @param row
	 * @param col
	 * @return true if it is; false otherwise.
	 */
	public boolean squareIsEmpty(int row, int col) {
		if (gameboard[row][col] == ' ')
			return true;
		
		return false;
	}
	
	/**
	 * Confirms if the game is won or not.
	 * @param symbol is the representation of human ('X') or computer ('O').
	 * @return
	 */
	public boolean wins(char symbol) {

		// Check the rows...
		// Start from the top-left corner and move your way to the bottom-right, checking if the space contains the symbol.
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				// If it is possible to attain a row from this position and there is a symbol at this position.
				if (j + k <= board_size && gameboard[i][j] == symbol) {
					int streak = 1;		// Streak checks how many symbols lie consecutively in a row.
					while (streak != 0) {
						if (j+streak<board_size && gameboard[i][j+streak] == symbol) { // Checks if the next consecutive space in the row also contains the symbol.
							streak++;	// then check if the next one does too
						}
						// If the amount of consecutive symbols equals the specified inline rule, then the game is won.
						else if (streak == k)
							return true;
						
						// As soon as the streak breaks, set it to zero.
						// The loop will terminate and the next space is tested.
						else streak = 0;
					}
					
				}
			}
		}
		
		// Check the columns...
		// Same idea as the algorithm for rows.
		for (int j=0; j < board_size; j++) {
			for (int i=0; i < board_size; i++) {
				// However, instead of checking the rows for streaks (j), we will check columns (i). 
				if (i + k <= board_size && gameboard[i][j] == symbol) {
					int streak = 1;
					while (streak != 0) {
						if (i+streak < board_size && gameboard[i+streak][j] == symbol) { // Note that we add streak to i
							streak++;
						}
						else if (streak == k)
							return true;
						else streak = 0;
					}
				}
			}
		}
		
		// Check the Diagonals...
		// This block starts from the top-left and moves toward the bottom-right, checking for the symbol.
		for (int i=0; i<board_size; i++) {
			for (int j=0; j<board_size; j++) {
				if (gameboard[i][j] == symbol) { // If the symbol is found.
					int streak = 1;
					while (streak != 0) {
						// Since we are going diagonal, we must ensure that there is room in both the columns and the rows to attain a winning streak.
						// The next space that is check the closest square to the bottom-right of the square; hence, we add streak to i and j.
						if (i+streak < board_size && j+streak < board_size && gameboard[i+streak][j+streak] == symbol) {
							streak++;
						}	
						else if (streak == k)
							return true;
						else streak = 0;
					}
				}
			}
		}
		
		// Same algorithm as the first diagonal...
		// However, we will start from the bottom-right and move our way up to the top-left.
		for (int i = board_size-1; i>=0; i--) { 	// Hence, the conditions are different.
			for (int j = board_size-1; j>=0; j--) {
				if (gameboard[i][j] == symbol) {
					int streak = 1;
					while (streak != 0) {
						// The direction of this diagonal is the closet square to the top-right.
						// Hence subtract streak from i to move up, and add streak to j to move right.
						if (i-streak >= 0 && j+streak < board_size && gameboard[i-streak][j+streak] == symbol) {
							streak++;
						}
						// Again, if the number of squares that satisfy the diagonal direction is equal to inline, the game is won.
						else if (streak == k)
							return true;
						else streak = 0;
					}
				}
			}
		}
	
		return false;
	}
	
	/**
	 * Checks if the game has resulted in a draw.
	 * @return true if draw; false otherwise.
	 */
	public boolean isDraw() {
		// These will be our flags.
		boolean filled = false;
		boolean noWin = false;
		
		// Checks to see if the gameboard is full; if no spaces exists.
		String finalGame = convert2String(gameboard);
		for (int i=0; i<finalGame.length(); i++) {
			if (finalGame.charAt(i) == ' ')		// If a space exists, then the board is not full.
				filled = false;
		}
		
		// Checks if neither X or O won the game.
		if (!wins('X') && !wins('O'))
			noWin = true;
			System.out.println(noWin);
		
		// If the board is filled and no one won the game, return true.
		if (filled == true && noWin == true)
			return true;
		
		// Otherwise, return false.
		return false;
	}
	
	/**
	 * Assigns a score based on how likely the human or computer will win.
	 * @return a relative score.
	 */
	public int evalBoard() {
		int score;
		
		// If the computer will win, return 3.
		if (wins('O') == true)
			score = 3;
		
		// If the human will win, return 0.
		else if (wins('X') == true)
			score = 0;
		
		// If the game will end in a draw, return 2.
		else if (isDraw() == true)
			score = 2;
		
		// If no decision can be made of the game, return 1.
		else score = 1;
		
		return score;
	}
	
	/**
	 * This will convert the gameboard array into a readable string that is compatible with the dictionary functions.
	 * @param gameboard
	 * @return a string representation of the gameboard.
	 */
	private String convert2String(char[][] gameboard) {
		String represent = "";	// Start with an empty string.
		
		// For every square of the board from top-left to bottom-right, add its symbol to the string.
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				represent += gameboard[i][j];
			}
		}
		return represent;
	}
}
