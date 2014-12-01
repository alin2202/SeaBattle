import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessOrSink {
	// class variables
	private String[] wordsToGuess = { "bob", "balinese", "bengal", "himalayan", "bobtail","siamese" };
//	private String[] wordsToGuess = { "bob"};
	private String word;
	private char[] wordArray;
	private char[] lettersGuessed;
	private boolean winStatus;
	private Ship ship;
	private int numOfHitsAllowed;
	private int guessNumber;
	private int numberOfCorrectGuesses;
	private int correctGuessesNeeded;

	// creating a constructor and defining variables needed
	public GuessOrSink() {
		int randomPosition = CommonFunctions.randomInt(0, this.wordsToGuess.length);
//		Random randomGenerator = new Random();
//		int randomPosition = randomGenerator.nextInt(this.wordsToGuess.length);
		this.word = this.wordsToGuess[randomPosition];
		this.wordArray = this.word.toCharArray();
		this.lettersGuessed = new char[this.wordArray.length];
		this.correctGuessesNeeded = correctGuessesNeededCount();
		this.numOfHitsAllowed = (this.correctGuessesNeeded + 2);
		this.ship = new Ship(this.numOfHitsAllowed);
	}

	public boolean getWinStatus() {
		return this.winStatus;
	}

	public void play() {
		Scanner keyboard = new Scanner(System.in);
		// print welcome message
		System.out.println("Welcome to Guess or Sink!\n");
		this.ship.draw();
		System.out
				.println("\nTo save this ship from sinking, your job is to guess one of cat breeds.\n");
		System.out.println("\nCat breed you have to guess is "
				+ this.word.length() + " letter word" + "\nYou have "
				+ this.numOfHitsAllowed + " guesses.");

		// defining game logic
		while (this.guessNumber < this.numOfHitsAllowed) {
			// increment guess number and get user input
			this.guessNumber += 1;
			System.out.println("\n\nEnter your guess # " + this.guessNumber + ":\n");
			// checking if user enters only 1 letter
			String userInput = keyboard.next().toLowerCase();
			while (userInput.length() != 1){
				System.out.println(userInput + " is not a valid entry. Please enter one character at a time: ");
				userInput = keyboard.next().toLowerCase();
			}
			// transfer user input to char if it's only one letter
			char guess = userInput.charAt(0);
			// check if user guessed the letter
			if (checkIfGuessIsCorrect(guess)) {
				// if guess is correct 
				this.numberOfCorrectGuesses += 1;
				System.out.println("\nCorrect!\n");
				System.out.println("Updated word:");
				printUpdatedWord();
			} else {
				// if guess is incorrect
				this.ship.addHits();
				System.out.println("\nIncorrect guess!\n");
				this.ship.draw();
			}
			// terminating the game if user guesses the word, but haven't used all available guesses yet
			if ((this.numberOfCorrectGuesses >= this.correctGuessesNeeded)
					&& (Arrays.equals(this.wordArray, this.lettersGuessed))) {
				System.out
						.println("\nCongratulations! You guessed the word!\n");
				this.ship.draw();
				this.winStatus = true;
				break;
			}

		}
		// terminate the game if ship is already down under water or winStatus is false
		if (!this.ship.getIsAlive() || !this.winStatus) {
			System.out
					.println("\n----- GAME OVER! -----\nYou have used all your guesses.");
			System.out.println("\nThe word was: " + this.word + "\n");
		}
	}
	
	// check if entered letter is a correct guess
	private boolean checkIfGuessIsCorrect(char input) {
		boolean isGuessCorrect = false;
		for (int i = 0; i < this.wordArray.length; i++) {
			if (input == this.wordArray[i]) {
				this.lettersGuessed[i] = input;
				isGuessCorrect = true;
			}
		}
		return isGuessCorrect;
	}

	// print updated word with guessed letters 
	// and "_" for the ones that are not guessed yet
	private void printUpdatedWord() {
		for (char c : this.lettersGuessed) {
			if (String.valueOf(c).trim().isEmpty())
				System.out.print("_ ");
			else {
				System.out.print(c + " ");
			}
		}
		System.out.println();
	}

	// calculate amount correct guesses needed to guess the whole word
	// method excludes repeating characters, so if the word is "bob" you only need 2 guesses
	private int correctGuessesNeededCount() {
		String distinctChars = "";
		for (char c : this.wordArray) {
			if (!distinctChars.contains(String.valueOf(c))) {
				distinctChars = distinctChars + String.valueOf(c);
			}
		}
		return distinctChars.length();
	}
}