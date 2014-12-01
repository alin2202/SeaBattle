public class Ship {
	// class variables
	final String[] water = { "~^^^^ ~~~~^^~~~~^^~~^^~~~~~ ^^ ~~~~~ ^^",
				"   ~~^^     ~^^~     ~^~ ~^ ~^   ~~",
				"       ~^~~        ~~~^^~  ~~^~" };
	private int numOfHits;
	private int maxHitAllowed;
	private boolean isAlive = true;
	String[] shipImageFull = null;

	// defining class constructor 
	public Ship(int shipHeight) {
		this.maxHitAllowed = shipHeight;
		this.isAlive = true;
		this.shipImageFull = new String[maxHitAllowed];
	}
	
	// defining method for counting incorrect guesses
	protected void addHits() {
		this.numOfHits += 1;
	}

	// defining method to check if ship is still alive
	public boolean getIsAlive() {
		if ((this.maxHitAllowed - this.numOfHits) == 0) {
			this.isAlive = false;
		}
		return this.isAlive;
	}

	// draw the ship
	protected void draw() {
		// define mast and body of the ship
		shipImageFull[0] = "               |    |   |";
		shipImageFull[(maxHitAllowed - 2)] = "        \\---__|____/|___|___-\\\\---";
		shipImageFull[(maxHitAllowed - 1)] = "         \\  oo oo -AL- oo oo  /";
		
		// create additional sails if needed to adjust to the word length
		for (int i = 1; i < maxHitAllowed - 2; i++) {
			shipImageFull[i] = "            )____)____)____)";
		}

		// draw the ship one line shorter when user makes incorrect guess
		for (int i = 0; i < maxHitAllowed - this.numOfHits; i++) {
			System.out.println(shipImageFull[i]);
		}
		
		// draw wahter
		for (int i = 0; i < this.water.length; i++)
			System.out.println(this.water[i]);
	}
}