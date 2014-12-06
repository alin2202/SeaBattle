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
	private boolean checkIfCellIsValid(int[] address) { 	
		boolean isValid = false;
		if (address[0] < this.fieldSize || address[1] < this.fieldSize) {
			int cellState = battleField[address[0]][address[1]].getCellState();
			if (cellState == Constants.emptyCell) {
				isValid = true;
			} else {
				isValid = false;
			}
		} else {
			isValid = false;
		}		
		return isValid;
	}

	// get first array cell
	private int[] getFirstCell() {
		boolean cellIsValid = false;
		int[] cellAddress = new int[2];
		while (!cellIsValid) {
//			cellAddress[0] = CommonFunctions.randomInt(0, this.fieldSize - 1);
//			cellAddress[1] = CommonFunctions.randomInt(0, this.fieldSize - 1);
			cellAddress[0] = 9;
			cellAddress[1] = 9;
			cellIsValid = checkIfCellIsValid(cellAddress);
		}
		return cellAddress;
	}

	public int[][] createShipCoordinates(int[] firstCell, int shipSize,
			boolean isHorizontal) {
		int[][] coordinates = new int[shipSize][2];
		coordinates[0][0] = firstCell[0];
		coordinates[0][1] = firstCell[1];
		if (isHorizontal == true) {
			for (int i = 1; i < shipSize; i++) {
				coordinates[i][0] = firstCell[0] + i;
				coordinates[i][1] = firstCell[1];
			}
		} else {
			for (int i = 1; i < shipSize; i++) {
				coordinates[i][0] = firstCell[0];
				coordinates[i][1] = firstCell[1] + i;
			}
		}
		return coordinates;
	}

	private boolean validateShipCoordinates(int[][] shipCoordinates) {
		boolean isShipValid = true;
		int i = 0;
		while (i < shipCoordinates.length) {
			int[] current = new int[2];
			current[0] = shipCoordinates[i][0];
			current[1] = shipCoordinates[i][1];
			if (checkIfCellIsValid(current)) {
				i++;
				continue;
			} else {
				isShipValid = false;
				break;
			}
		}
		return isShipValid;
	}

	// get random position of the first ship element
	// and check if it fits on the filed and doesn't overlap with existing ships
	public void placeShip(int inputShipSize) {
		// random horizontal or vertical "isHozontal"
//		boolean isVertical = true;
		boolean isVertical = CommonFunctions.randomBoolean();
		int[] firstCellCoordinates;
		int[][] shipCoordinates;
		// while loop starts
		while (true){
			// get first cell ()
			firstCellCoordinates = getFirstCell();
			// createShipCoordinates (firstCell, shipsize)
			shipCoordinates = createShipCoordinates(firstCellCoordinates, inputShipSize, isVertical);
			// validate ship coordinates ( )
			// method changes class variable isShipValid to false if coordinates are invalid
			boolean isShipValid = validateShipCoordinates(shipCoordinates);		
			// isFullShipValid set to false
			// isShip valid true: place ship
			if (isShipValid){
				break;
			}else{
				continue;
			}		
		}	
		// assign cell states to ship cells
		for (int i = 0; i < shipCoordinates.length; i++) {
				battleField[shipCoordinates[i][0]][shipCoordinates[i][1]].setCellState(Constants.reveal);
				// to be completed : place reserved cells
		}
	}

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
			System.out.printf("\t %-3s", i);
		}
		System.out.println();
		for (int row = 0; row < fieldSize; row++) {
			System.out.print(row);
			for (int col = 0; col < fieldSize; col++) {
				System.out.printf("\t %-3s", battleField[row][col]);
			}
			System.out.println();
		}
	}

	// method to be called before printing final (result) board with Everything
	public void reveal() {
		for (int i = 0; i < fieldSize; i++) {
			for (int a = 0; a < fieldSize; a++) {
				if (battleField[i][a].getCellState() == Constants.shipCell)
					battleField[i][a].setCellState(Constants.reveal);
			}
		}

	}

}
