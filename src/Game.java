import java.util.Scanner;

public class Game {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		int score = 0;
		int numberOfGames = 0;

		// print welcome message check if user wants to play
		System.out.println("Welcome to the Game!");
		while (true) {
			System.out.println("\nWhat would you like to do? "
					+ "\n 1. Play Guess or Sink " + "\n 2. Play Memory Match"
					+ "\n q. Quit " + "\nEnter your selection");

			String userInput = keyboard.next();
			char choice = userInput.charAt(0);

			// play the game if user entered 1
			if (choice == '1') {
				GuessOrSink shipGame = new GuessOrSink();
				shipGame.play();
				numberOfGames++;
				if (shipGame.getWinStatus())
					score++;
				// exit if user types in q
			} else if (choice == '2') {
				MemoryMatch memoryGame = new MemoryMatch();
				memoryGame.play();
				numberOfGames++;
				if (memoryGame.getWinSattus())
					score++;
			} else {
				if (choice == 'q') {
					break;
				}
				// ask user for an input again if input is different than 1 or q
				System.out.printf("\"%s\" is not a valid input. \n",
						new Object[] { userInput });
			}
		}
		// print out user's score when user quits the game
		System.out.println("Thanks for playing!\nYour score is "
				+ score + " out of " + numberOfGames + "." + "\nGoodBye!");

		keyboard.close();
	}
}