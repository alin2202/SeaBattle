import java.util.Scanner;

public class BattleShip {

	private int MAXFIELDSIZE = 3; //
	private int MINFIELDSIZE = 2;  //
	private int givenShots;
	private int fieldSize;
	private FieldCell[][] arrayOfShips;
	private boolean winStatus = false;
	SeaFieldShips battleShipGame;

	public BattleShip() {
		this.fieldSize = CommonFunctions.randomInt(MINFIELDSIZE, MAXFIELDSIZE);
		this.battleShipGame = new SeaFieldShips(fieldSize);
		this.battleShipGame.placeShips();
		this.arrayOfShips = battleShipGame.getShipsArray();
		this.givenShots = (battleShipGame
				.getNumberOfShipCells(arrayOfShips) + 3);
	}

	public void play() {
		System.out.println("\nWelcome to Battle Ship Game!\n" + "You have "
				+ this.givenShots + " attempts"
				+ " to destroy your enemie's ships.\n");
		this.battleShipGame.printBoard();
		// start game:
		// for loop starts with number of given shots:
		int remainingShots = this.givenShots;
		for (int shots = 0; shots < this.givenShots; shots++) {
			int[] addr = new int[2];
			// ask user for input (get user input method)
			System.out.println("\n\nPlease enter your shot coordinates:\n");
			remainingShots = remainingShots - 1;
			addr = getUserInput();
			// check FieldCell value
			int cellValue = this.battleShipGame.battleField[addr[0]][addr[1]].getCellState();
			// - if 0 or 3 - setCellState (-1) : "Your shot was in water."
			if (cellValue == Constants.shipCell) {
				System.out.println("\nYou aimed!");
				this.battleShipGame.battleField[addr[0]][addr[1]].setCellState(Constants.shotAimed);
				this.battleShipGame.revealSunkShips();
			} else if (cellValue == Constants.emptyCell
					|| cellValue == Constants.reservedCell) {
				System.out.println("\nYour shot was in water.");
				this.battleShipGame.battleField[addr[0]][addr[1]]
						.setCellState(Constants.shotMissed);
				// - else if -1 or 1 - "you already shot here"
			} else {
				System.out.println("\nYou already shot here...");
			}
//			if (battleShipGame.checkIfAllShipsSunk()){
//				System.out.println("\nCongratulations! You won the game.");
//				this.battleShipGame.printBoard();
//				this.winStatus = true;
//				break;
//			}
			System.out.println("shots left: " + remainingShots + "\n");
			this.battleShipGame.printBoard();
		}
		// exit when user used all given shots
		System.out.println("\nYou have used all shots. Game Over!\n");
		battleShipGame.reveal();
		this.winStatus = false;
	}

	// accessor method for winStatus
	public boolean getWinSattus() {
		return this.winStatus;
	}
	
	// Method gets user input, validates it and returns array integers with
	// coordinates
	private int[] getUserInput() {
		// creating scanner object and get user input
		Scanner input = new Scanner(System.in);
		int[] userCoodrinates = null;
		String inputtedText = input.nextLine();
		// validate user input
		while (true) {
			int firstInt;
			int secondInt;
			try {
				String[] coordinates = inputtedText.split(" ");
				if (coordinates.length != 2) {
					throw new Exception(
							"\nWrong number of arguments.\n"
									+ "Please enter two integers separated by white space:\n");
				}

				if (!CommonFunctions.isInteger(coordinates[0])
						|| !CommonFunctions.isInteger(coordinates[1])) {
					throw new Exception("\nWrong arguments.\n"
							+ "Please enter integers only:\n");
				}

				firstInt = Integer.parseInt(coordinates[0]);
				secondInt = Integer.parseInt(coordinates[1]);

				if (firstInt >= this.fieldSize || secondInt >= this.fieldSize) {
					throw new Exception(
							"\nYour coordinates exceed board size.\n"
									+ "Please enter a valid position:\n");
				}

				if (firstInt < 0 || secondInt < 0) {
					throw new Exception("\nCan't enter negative.\n"
							+ "Please enter a valid position:\n");
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				inputtedText = input.nextLine();
				continue;
			}
			// validation passed
			// assign entered card position and exit while loop
			userCoodrinates = new int[2];
			userCoodrinates[0] = firstInt;
			userCoodrinates[1] = secondInt;
			break;
		}
		return userCoodrinates;
	}

}
