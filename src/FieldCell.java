public class FieldCell {
	
	// class variables
	private int cellState;

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
		if (cellState == Constants.emptyCell){
			return "~";
		}else if (cellState == Constants.shotMissed){
			return "o";
		}else if (cellState == Constants.shotAimed){
			return "*";
		}else if (cellState == Constants.reservedCell){
			return "~";
		}else if (cellState == Constants.reveal){
			return "[]";
		}else if (cellState == Constants.shipCell){
			return "~";
		}else if (cellState == Constants.sunkShip){
			return "X";
		}else{
		return "?";
		}
	}
}
