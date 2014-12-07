import java.util.Scanner;

public class SeaFieldShips {

	// number of ships to draw
	private int numOfhips;
	// ship sizes : e.g. {4, 3, 2}
	// 3 ships with 4, 3 and 2 cells accordingly
	private int[] sizes;
	private int fieldSize;
	public FieldCell[][] battleField;
	private FieldCell[][] shipsArray;

	public SeaFieldShips(int fieldSize) {
		this.fieldSize = fieldSize;
		this.numOfhips = setNumOfShips(fieldSize); 
		this.sizes = setShipSizes(numOfhips);
		this.shipsArray = new FieldCell[numOfhips][];		
		this.battleField = new FieldCell[fieldSize][fieldSize];

	}
	public SeaFieldShips() {
		

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
	
	public void setReservedCells(int [][] shipAddress){
		int numOfCells = shipAddress.length;
		try{
				for (int i = 0; i < numOfCells; i++){
					int x = shipAddress[i][0];
					int y = shipAddress[i][1];
					int [][] cellsAround = {{x+1, y},{x-1, y},{x, y+1},{x, y-1}};
					for (int el = 0; el < cellsAround.length; el++){
						if (checkIfCellIsEmpty(cellsAround[el])){
						int ax = cellsAround[el][0];
						int ay = cellsAround[el][1];						
						int cellState = battleField[ax][ay].getCellState();
						if (cellState!= Constants.shipCell){
							battleField[ax][ay].setCellState(Constants.reservedCell);
//              			cellState = battleField[ax][ay].getCellState(); //debug statetment
						}
						}else{
							continue;
						}
					}
				}
		}catch(Exception e){
//			System.out.println("Ooops... something went wrong."); // debug
		}		
	}

	// method for placing a single ship
	private FieldCell [] placeShip(int inputShipSize) {
		boolean isVertical = CommonFunctions.randomBoolean();
		FieldCell[] shipObjects = new FieldCell[inputShipSize];
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
			shipObjects[i] = battleField[shipCoordinates[i][0]][shipCoordinates[i][1]];
			shipObjects[i].setCellState(Constants.shipCell);
		}
		// reserve cells around the ship
		setReservedCells(shipCoordinates);
		return shipObjects;
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
//			placeShip(this.sizes[i]);
			shipsArray[i] = placeShip(this.sizes[i]);
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
	
	
	
	
	// Method gets user input, validates it and returns array integers with coordinates
		public int[] getUserInput() {
			// creating scanner object and get user input 
			Scanner input = new Scanner(System.in);
			int[] userCoodrinates = null;
			String inputtedText = input.nextLine();
			BattleShip battleShip = new BattleShip();
			int fieldSize = battleShip.getFieldSize() + 1;
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

					if (firstInt > fieldSize || secondInt > fieldSize) {
						throw new Exception("\nYour coordinates exceed board size.\n"
								+ "Please enter a valid position:\n");			
					}
					
					if (firstInt == fieldSize) {
						throw new Exception("\nYour coordinates exceed board size.\n"
								+ "Please enter a valid position:\n");			
					}
					
					if (secondInt == fieldSize) {
						throw new Exception("\nYour coordinates exceed board size.\n"
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
