public class FieldCell {
	
	// class variables
	private int cellState;
	
	// cell states constants
	private int emptyCell = 0;
	private int shipCell = 2;
	private int shotMissed = -1;
	private int shotAimed = 1;
	private int reservedCell = 3;
	private int reveal = 4;
	private int sunkShip = 5;

	// class constructor
	public FieldCell(){
		this.cellState = 0;
	}
	
	// accessor method that returns MatchMade value
	public int getCellState(){	
		return this.cellState;
	}
	
	public void setCellState(int cellState){
		this.cellState = cellState;
	}

	// return string representation for the card // We need var with ~ for shipCell
	public String toString(){
		if (cellState == emptyCell){
			return "~";
		}else if (cellState == shotMissed){
			return "o";
		}else if (cellState == shotAimed){
			return "*";
		}else if (cellState == reservedCell){
			return "~";
		}else if (cellState == reveal){
			return "[]";
		}else if (cellState == shipCell){
			return "~";
		}else if (cellState == sunkShip){
			return "X";
		}else{
		return "?";
		}
	}
}
