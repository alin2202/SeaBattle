public class SeaFieldShips {

	// number of ships to draw
	private int numOfhips;
	// ship sizes : e.g. {4, 3, 2}
	// 3 ships with 4, 3 and 2 cells accordingly
	private int[] sizes;
	private int fieldSize;
	private FieldCell[][] battleField;
	private int[][] shipsArray;

	public SeaFieldShips(int fieldSize) {
		this.fieldSize = fieldSize;
		this.numOfhips = setNumOfShips(fieldSize); 
		this.sizes = setShipSizes(numOfhips);
		this.shipsArray = new int[numOfhips][];		
		this.battleField = new FieldCell[fieldSize][fieldSize];

	}

	// method that will determine how many
	// and what size ships to draw
	private int setNumOfShips(int fieldSize) {
		int numberOfShips = (int) (fieldSize/1.5);
		return numberOfShips; // generated number
	}

	// determine ship sizes
	private int[] setShipSizes(int numOfShips) {
		int[] shipSizes = new int[numOfShips];
		int shipSize = numOfShips;
		for (int i = 0; i < numOfShips; i++) {			
			shipSizes[i] = shipSize;
			shipSize = shipSize - 1;
		}
		return shipSizes;
	}

	// evaluate selected cell
	private boolean checkIfCellIsEmpty(int[] address) { 
		boolean cellIsValid = false;
		try{
			if (address[0] < this.fieldSize || address[1] < this.fieldSize){	
				int cellState = battleField[address[0]][address[1]].getCellState();
				if (cellState == Constants.emptyCell){
					cellIsValid = true;
				}
			}
		}catch(Exception e){
			cellIsValid = false;
		}		
		return cellIsValid;
	}


	private int[][] createShipCoordinates(int shipSize, boolean isVertical) {
		int[][] coordinates = new int[shipSize][2];
		int [] firstCell = new int [2];
		firstCell[0] = CommonFunctions.randomInt(0, this.fieldSize - 1);
		firstCell[1] = CommonFunctions.randomInt(0, this.fieldSize - 1);
		coordinates[0][0] = firstCell[0];
		coordinates[0][1] = firstCell[1];
		if (isVertical == true) {
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
			if (checkIfCellIsEmpty(current)) {
				i++;
				continue;
			} else {
				isShipValid = false;
				break;
			}
		}
		return isShipValid;
	}
	
	public void setReservedCells(int [][] shipAddress, boolean isVertical){
		int numOfCells = shipAddress.length;
		try{
			if (numOfCells==1){
				int addr [] = {shipAddress[0][0],shipAddress[0][1]};
				battleField[addr[0]+1][addr[0]].setCellState(Constants.reservedCell); 
				battleField[addr[0]-1][addr[0]].setCellState(Constants.reservedCell); 
				battleField[addr[0]][addr[0]+1].setCellState(Constants.reservedCell);
				battleField[addr[0]][addr[0]-1].setCellState(Constants.reservedCell);				
				
//				battleField[shipAddress[0][0]][shipAddress[0][1]].setCellState(Constants.reservedCell); // debug
			}else if (isVertical){
				// for vertical:
				// first element:
				int addrFirst [] = {shipAddress[0][0],shipAddress[0][1]};
				battleField[addrFirst[0]][addrFirst[0]-1].setCellState(Constants.reservedCell); 
				battleField[addrFirst[0]+1][addrFirst[0]+1].setCellState(Constants.reservedCell);
				battleField[addrFirst[0]-1][addrFirst[0]-1].setCellState(Constants.reservedCell);
				// last element:
				int addrLast [] = {shipAddress[numOfCells - 1][0],shipAddress[numOfCells - 1][1]};
				battleField[addrLast[0]][addrLast[0]+1].setCellState(Constants.reservedCell); 
				battleField[addrLast[0]+1][addrLast[0]].setCellState(Constants.reservedCell);
				battleField[addrLast[0]-1][addrLast[0]].setCellState(Constants.reservedCell);
				// remaining elements:
				for (int i = 1; i < numOfCells - 1; i++){
					int addr [] = {shipAddress[i][0],shipAddress[i][1]};
					battleField[addr[0]+1][addr[0]].setCellState(Constants.reservedCell);
					battleField[addr[0]-1][addr[0]].setCellState(Constants.reservedCell);
				}
			}else{
				// for horizontal:
				// first element:
				int addrFirst [] = {shipAddress[0][0],shipAddress[0][1]};
				battleField[addrFirst[0]-1][addrFirst[0]].setCellState(Constants.reservedCell); 
				battleField[addrFirst[0]][addrFirst[0]+1].setCellState(Constants.reservedCell);
				battleField[addrFirst[0]][addrFirst[0]-1].setCellState(Constants.reservedCell);
				// last element:
				int addrLast [] = {shipAddress[numOfCells - 1][0],shipAddress[numOfCells - 1][1]};
				battleField[addrLast[0]+1][addrLast[0]].setCellState(Constants.reservedCell); 
				battleField[addrLast[0]][addrLast[0]+1].setCellState(Constants.reservedCell);
				battleField[addrLast[0]][addrLast[0]-1].setCellState(Constants.reservedCell);
				// remaining elements:
				for (int i = 1; i < numOfCells - 1; i++){
					int addr [] = {shipAddress[i][0],shipAddress[i][1]};
					battleField[addr[0]][addr[0]+1].setCellState(Constants.reservedCell);
					battleField[addr[0]][addr[0]-1].setCellState(Constants.reservedCell);
				}
			}
		}catch(Exception e){
			System.out.println("Ooops... something went wrong."); // debug
		}		
	}

	// method for placing a single ship
	private int [][] placeShip(int inputShipSize) {
		boolean isVertical = CommonFunctions.randomBoolean();
		int[][] shipCoordinates;
		boolean isValid;
		while (true){
			// get ship coordinates
			shipCoordinates = createShipCoordinates(inputShipSize, isVertical);
			// check if coordinates cells are empty/valid
			isValid = validateShipCoordinates(shipCoordinates);
			// if valid, exit loop
			if (isValid){
				break;
			// if not, continue searching for new ship coordinates
			}else{
				continue;
			}		
		}	
		// assign cell states to ship cells
		for (int i = 0; i < shipCoordinates.length; i++) {
				battleField[shipCoordinates[i][0]][shipCoordinates[i][1]].setCellState(Constants.reveal);
				// to be completed : place reserved cells
		}
		
//		setReservedCells(shipCoordinates, isVertical);
		
		return shipCoordinates;
	}

	// place ships on the field
	public void placeShips() {
		// initialize field elements to 0s
		for (int i = 0; i < fieldSize; i++) {
			for (int a = 0; a < fieldSize; a++) {
				battleField[i][a] = new FieldCell();
			}
		}
		for (int i = 0; i < this.sizes.length; i++){
			placeShip(this.sizes[i]);
//			shipsArray[i][] = placeShip(this.sizes[i]);
		}
	}

	// printing current board
	public void printBoard() {
		for (int i = 0; i < fieldSize; i++) {
			System.out.printf("\t %-1s", i);
		}
		System.out.println();
		for (int row = 0; row < fieldSize; row++) {
			System.out.print(row);
			for (int col = 0; col < fieldSize; col++) {
				System.out.printf("\t %-1s", battleField[row][col]);
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
