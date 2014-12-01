import java.util.Scanner;

public class MemoryMatch {

	// class variables
	private int MAXBOARDSIZE = 10;
	private int MINBOARDSIZE = 4;
	private boolean winStatus = false;
	private WordCard[][] board = null;
	private int boardSize = 0;
	final String INFILENAME = "data/superheros.txt";
	NameReader name_reader = new NameReader(INFILENAME);
	private String[] wordsToGuess = name_reader.getSuperHeroNames();
	int numberOfMatchedWords = 0;

	// class constructor
	public MemoryMatch() {
		this.winStatus = false;
		int boardSize = numberOfWordsToSet();
		board = new WordCard[boardSize][boardSize];
		assignWordsToBoard(setWordsToGuess(numberOfWordsToSet()));
	}

	// get board size by random generator (board width = board length)
	private int numberOfWordsToSet() {
		boardSize = CommonFunctions.randomInt(MINBOARDSIZE, MAXBOARDSIZE);
		boardSize = boardSize & ~1;
//		boardSize = 4; // for debugging purpose only
		int numOfWordsToBoard = boardSize * boardSize / 2;
		return numOfWordsToBoard;
	}

	// method that randomly selects amount of words needed for the board
	// this method also excludes words duplicates
	private String[] setWordsToGuess(int numOfWordsToSet) {

		int numberOfWordsToSet = numOfWordsToSet;
		String[] selectedWords = new String[numberOfWordsToSet];
		for (int i = 0; i < numberOfWordsToSet; i++) {
			int position = CommonFunctions
					.randomInt(0, wordsToGuess.length - 1);
			for (int a = 0; a <= i; a++) {
				while (selectedWords[a] == wordsToGuess[position]) {
					position = CommonFunctions.randomInt(0,
							wordsToGuess.length - 1);
				}
			}
			selectedWords[i] = wordsToGuess[position];
		}
		return selectedWords;
	}

	// assign selected words to board : assign words to random positions of
	// board 2D array
	private void assignWordsToBoard(String[] selectedWords) {
		for (int i = 0; i < selectedWords.length; i++) {
			int row = CommonFunctions.randomInt(0, boardSize - 1);
			int col = CommonFunctions.randomInt(0, boardSize - 1);
			while (board[row][col] != null) {
				row = CommonFunctions.randomInt(0, boardSize - 1);
				col = CommonFunctions.randomInt(0, boardSize - 1);
			}
			WordCard myCard = new WordCard(selectedWords[i]);
			board[row][col] = myCard;
		}
		// assigning duplicates of words to the rest of board tiles
		for (int i = 0; i < selectedWords.length; i++) {
			int row = CommonFunctions.randomInt(0, boardSize - 1);
			int col = CommonFunctions.randomInt(0, boardSize - 1);
			while (board[row][col] != null) {
				row = CommonFunctions.randomInt(0, boardSize - 1);
				col = CommonFunctions.randomInt(0, boardSize - 1);
			}
			WordCard myCard = new WordCard(selectedWords[i]);
			board[row][col] = myCard;
		}
	}

	// Method gets user input, validates it and returns card coordinates
	public int[] getCardPosition(boolean askCardPosition) {
		// creating scanner object and get user input 
		Scanner input = new Scanner(System.in);
		int[] cardPosition = null;
		String inputtedText = input.nextLine();
		// validate user input
		while (true) {
			int firstInt;
			int secondInt;
			try {
				String[] coordinates = inputtedText.split(" ");
				if (coordinates.length != 2) {
					throw new Exception("\nWrong number of arguments.\n"
							+ "Please enter two integers separated by white space:\n");
				}

				if (!CommonFunctions.isInteger(coordinates[0]) || 
						!CommonFunctions.isInteger(coordinates[1])){
					throw new Exception("\nWrong arguments.\n"
							+ "Please enter integers only:\n");
				}
				
				firstInt = Integer.parseInt(coordinates[0]);
				secondInt = Integer.parseInt(coordinates[1]);

				if (firstInt > boardSize || secondInt > boardSize) {
					throw new Exception("\nYour coordinates exceed board size.\n"
							+ "Please enter a valid position:\n");			
				}
				
				if (firstInt == boardSize) {
					throw new Exception("\nYour coordinates exceed board size.\n"
							+ "Please enter a valid position:\n");			
				}
				
				if (secondInt == boardSize) {
					throw new Exception("\nYour coordinates exceed board size.\n"
							+ "Please enter a valid position:\n");			
				}
				
				if (firstInt < 0 || secondInt < 0) {
					throw new Exception("\nCan't enter negative.\n"
							+ "Please enter a valid position:\n");			
				}
				
				if (board[firstInt][secondInt].getMatchMade()) {
					throw new Exception(
							"\nThis card is already opened!\n"
							+ "Please enter different card position:\n");
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				inputtedText = input.nextLine();
				continue;
			}
			// validation passed
			// assign entered card position and exit while loop
			cardPosition = new int[2];
			cardPosition[0] = firstInt;
			cardPosition[1] = secondInt;
			break;
		}
		return cardPosition;
	}

	
	// method takes in cardPosition, prints board and returns string representation of card
	private String printBoardAndReturnWord(int[] cardPosition){
		int[] wordPosition = cardPosition;
		String word = board[wordPosition[0]][wordPosition[1]].showCard();
		printBoard(wordPosition);		
		return word;
	}
	
	
	// defining main game logic
	protected void play() {
		// start game
		System.out.println("Welcome to Memory Match!\n\nBoard:\n");
		printBoard(true);
		System.out.println("\nMemory Match: playing...\n");
		printBoard(false);
		System.out.println("\n");

		// if there are still unmatched words - continue game
		while (this.numberOfMatchedWords < this.boardSize * this.boardSize) {

			// get first input and print first word on board
			System.out.println("\nEnter first row col: \n");
			int[] firstCardPosition = getCardPosition(true);
			String firstWord = printBoardAndReturnWord(firstCardPosition);
			
			// get second input and print second word on board
			System.out.println("\nEnter second row col: \n");
			int[] secondCardPosition = getCardPosition(true);
			String secondWord = printBoardAndReturnWord(secondCardPosition);
			
			// check if selected words match and print board accordingly
			if (firstWord.equals(secondWord)) {
				// print board with matched words
				System.out.println("\nMatch found!\n");
				printBoard();
				// updateNumberOfMatchedWords;
				this.numberOfMatchedWords++;
				this.numberOfMatchedWords++;
			} else {
				// print board without words user was trying to guess
				System.out.println("\nNo Match found.\n");
				board[firstCardPosition[0]][firstCardPosition[1]].setMatchMade(false);
				board[secondCardPosition[0]][secondCardPosition[1]].setMatchMade(false);
				printBoard();
			}

		}
		// end the game if user guessed all words on the board
		System.out.println("\nCongratulations! You've won!\n");
		this.winStatus = true;
	}

	// logic for printing the board //debugging board
	private void printBoard(boolean showCards) {
		for (int i = 0; i < boardSize; i++) {
			System.out.printf("\t %-15s", i);
		}
		System.out.println();
		for (int row = 0; row < boardSize; row++) {
			System.out.print(row);
			for (int col = 0; col < boardSize; col++) {
				board[row][col].setMatchMade(showCards);
				System.out.printf("\t %-15s", board[row][col]);
			}
			System.out.println();
		}
	}

	// printBoard override method - for
	private void printBoard(int[] coordinates) {
		int posX = coordinates[0];
		int posY = coordinates[1];
		board[posX][posY].setMatchMade(true);
		printBoard();
	}

	// overload print board 2
	private void printBoard() {
		for (int i = 0; i < boardSize; i++) {
			System.out.printf("\t %-15s", i);
		}
		System.out.println();
		for (int row = 0; row < boardSize; row++) {
			System.out.print(row);
			for (int col = 0; col < boardSize; col++) {
				System.out.printf("\t %-15s", board[row][col]);
			}
			System.out.println();
		}
	}

	// accessor method for winStatus
	public boolean getWinSattus() {
		return this.winStatus;
	}
}