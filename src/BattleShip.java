
public class BattleShip {
	
	private int MAXFIELDSIZE = 12;
	private int MINFIELDSIZE = 5;
	private int fieldSize = 10;
	private boolean winStatus = false;
	
	public BattleShip (){
		fieldSize = CommonFunctions.randomInt(MINFIELDSIZE, MAXFIELDSIZE);
		SeaFieldShips ships = new SeaFieldShips(fieldSize);
		ships.placeShips();
	}

	public void play (){
		
		SeaFieldShips battleShipGame = new SeaFieldShips(10);//debug
		
		
		battleShipGame.placeShips(); //debug
		battleShipGame.placeShip(4);
		
		battleShipGame.printBoard();//debug
		
		//debug
//		SeaFieldShips seaFiledShips = new SeaFieldShips(10);
//		int[] firstCell = {0,0};
//		int fieldSize = 3;
//		int [][] arrayTest = seaFiledShips.createShipCoordinates(firstCell, fieldSize, false);
//		for (int i = 0; i < fieldSize; i++) {
//			for (int a = 0; a < 2; a++) {
//				System.out.println(arrayTest[i][a]);
//			}
//		}
		// end of debug
		
		// start game:	
		// for loop starts with number of given shots:
		
		// ask user for input
		// check FieldCell value and:
		// - if 0 or 3 - setCellState (-1) : "Your shot was in water."
		// - if 2 - setCellState ( 1) :      "You aimed!"
			// check ship array elements 
			// to determine whether the whole ship is shot:
// !!!	    // ??? how to determine which ship is it? or go through all elements ?
				// if ship is shot, check for other ships
				// when all ships are shot: exit the game : "Game over. You Won!"
		// - else if -1 or 1 - "you already shot here"
		
		// end of for loop : "You have used all shots. Game Over!"
		// this.winStatus = true;
	}
	
	// other methods needed:
	// - validate user input
	// - get user input
	// - 2 print field methods : 
	// one for regular shots, 
	// one for displaying all ships
	
	
	// accessor method for winStatus
	public boolean getWinSattus() {
		return this.winStatus;
	}
	
}
