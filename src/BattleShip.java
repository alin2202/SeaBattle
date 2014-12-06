
public class BattleShip {
	
	private int MAXFIELDSIZE = 12;
	private int MINFIELDSIZE = 5;
	private int givenShots = 4; // need to change
	private int fieldSize;
	private boolean winStatus = false;
	SeaFieldShips battleShipGame;
	
	public BattleShip (){
		fieldSize = CommonFunctions.randomInt(MINFIELDSIZE, MAXFIELDSIZE);
		battleShipGame = new SeaFieldShips(fieldSize);
		battleShipGame.placeShips();
	}

	public void play (){		
		System.out.println("\nWelcome to Battle Ship Game!\n"
				+ "You have " + givenShots + " to destroy your enemie's ships.\n");
		battleShipGame.printBoard();
		
		// start game:	
		// for loop starts with number of given shots:
		for (int shots = 0; shots <= givenShots; shots++){
			int [] addr = new int [2];
			// ask user for input (get user input method)
//			addr = getUserInput();
			// check FieldCell value
			int cellValue = battleShipGame.battleField[addr[0]][addr[1]].getCellState();
			// - if 0 or 3 - setCellState (-1) : "Your shot was in water."
			if (cellValue==Constants.shipCell){
				System.out.println("You aimed!");
				battleShipGame.battleField[addr[0]][addr[1]].setCellState(Constants.shotAimed);
				// check ship array elements : TO BE COMPLETED
				// to determine whether the whole ship is shot:
				// ??? how to determine which ship is it? or go through all elements ?
				// if ship is shot, check for other ships
				// when all ships are shot: exit the game : "Game over. You Won!"
				// this.winStatus = true;
			}else if (cellValue==Constants.emptyCell || cellValue==Constants.reservedCell){
				System.out.println("Your shot was in water.");
				battleShipGame.battleField[addr[0]][addr[1]].setCellState(Constants.shotMissed);
			// - else if -1 or 1 - "you already shot here"
			}else{
				System.out.println("You already shot here..");				
			}
			battleShipGame.printBoard();
		}
		// exit when user used all given shots
		System.out.println("You have used all shots. Game Over!");
		this.winStatus = false;
	}
	
	// other methods needed:
	// - validate user input
	// - get user input
	
	
	// accessor method for winStatus
	public boolean getWinSattus() {
		return this.winStatus;
	}
	
}
