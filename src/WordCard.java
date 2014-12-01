
public class WordCard {
	
	// class variables
	private String word = null;
	private boolean matchMade;

	// class constructor
	public WordCard(String word){
		this.word = word;
		this.matchMade = false;
	}
	
	// Return value of the word the card is holding
	protected String showCard(){
		return word;
	}
	
	// accessor method that returns MatchMade value
	public boolean getMatchMade(){	
		return this.matchMade;
	}
	
	public void setMatchMade(boolean matchMade){
		this.matchMade = matchMade;
	}

	// return string representation for the card
	public String toString(){
		if (matchMade){
			return showCard();
		}else{
			return "?";
		}
	}
}
