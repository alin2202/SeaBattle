public class SeaFieldShips {

	// number of ships to draw
	private int numOfhips;
	// ship sizes : e.g. {4, 3, 2}
	// 3 ships with 4, 3 and 2 cells accordingly
	private int[] sizes;
	private int fieldSize;
	private FieldCell[][] battleField;
	private int[] shipsArray;

	public SeaFieldShips(int fieldSize) {
		this.numOfhips = setNumOfShips(fieldSize); // need to change to be
													// dependent on field size
		this.sizes = setShipSizes(numOfhips);
		this.fieldSize = fieldSize;
		this.battleField = new FieldCell[fieldSize][fieldSize];
		this.shipsArray = new int[numOfhips];
	}

	// method that will determine how many
	// and what size ships to draw
	private int setNumOfShips(int fieldSize) {
		int numberOfShips = 3; // NEED TO CHANGE AFTER
		// set number of ships based on field size
		// ex. field size = 10, num of ships = 5
		// need to figure out algorithm for determining this number
		return numberOfShips; // generated number
	}

	// determine ship sizes
	private int[] setShipSizes(int numOfShips) {
		int[] shipSizes = new int[numOfShips];
		// generate ship sizes integers
		// and assign to array positions
		for (int i = 0; i < numOfShips; i++) {
			shipSizes[i] = numOfShips;
			numOfShips = numOfShips - 1;
		}
		return shipSizes;
	}

	// evaluate selected cell
		private boolean checkIfCellIsValid (int [] address){ // change name - validation
			boolean isValid = false;
			int cellState = battleField[address[0]][address[1]].getCellState();
			if (cellState!= 0 || cellState!= 3 || 
					address[0] <= this.fieldSize || address[1] <= this.fieldSize){
				isValid = false;
			}else{
				isValid = true;
			}
			return isValid;
		}
		
		// get first array cell
		private int [] getFirstCell(){
			boolean cellIsValid = false;
			int [] cellAddress = new int [2];
			while (!cellIsValid){
				cellAddress[0] = CommonFunctions.randomInt(0, this.fieldSize);
				cellAddress[1] = CommonFunctions.randomInt(0, this.fieldSize);
				cellIsValid = checkIfCellIsValid(cellAddress);
			}
			return cellAddress;
		}
		
		// get random position of the first ship element 
		// and check if it fits on the filed and doesn't overlap with existing ships
		private void placeShip (int inputShipSize){
			int shipSize = inputShipSize;	
			FieldCell[] ship = new FieldCell [shipSize];

			int x = getFirstCell()[0];
			int y = getFirstCell()[1];
			battleField[x][y].setCellState(2); // need to change
			
			for (int i = 0; i < shipSize - 1; i++){
				
			}
			
			// 1. get random field coordinates 
			// 1a. get random boolean horizontal or vertical
			// 2. check if there is no ship in that cell and space around 
			//    (ship and additional cells around ships)
			// 3. check if ship will fit in the filed space
			// determine position of all elements of the ship
			// check if coordinates of elements don't overlap with ship and around
			// ---> if overlap: then start over
			// ---> else: assign 2s (for ship elements) and add elements to the array "ship[]"
			//               and 3s (for spaces around)
			// add new created array to array of arrays "ship[numOfShips][]"
		}

	// to be completed
	// place ships on the field
	public void placeShips() {
		// initialize field elements to 0s
		for (int i = 0; i < fieldSize; i++) {
			for (int a = 0; a < fieldSize; a++) {
				battleField[i][a] = new FieldCell();
			}
		}
		// Take a ship size from shipSizes array (for loop starts)
		// for each ship call "placeShip()" element
	}

	// printing current board
	public void printBoard() {
		for (int i = 0; i < fieldSize; i++) {
			System.out.printf("\t %-15s", i);
		}
		System.out.println();
		for (int row = 0; row < fieldSize; row++) {
			System.out.print(row);
			for (int col = 0; col < fieldSize; col++) {
				System.out.printf("\t %-15s", battleField[row][col]);
			}
			System.out.println();
		}
	}

	// method to be called before printing final (result) board with Everything
	public void reveal() {
		for (int i = 0; i < fieldSize; i++) {
			for (int a = 0; a < fieldSize; a++) {
				if (battleField[i][a].getCellState() == 2)
				battleField[i][a].setCellState(4);
			}
		}

	}

}
