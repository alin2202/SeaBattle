----- GUESS OF SINK and MEMORY MATCH ----

---- Guess or Sink ----
This game represents a modification on "Hanging Man" game with Ship image.
Ship starts sinking on incorrect guesses. User have to guess all letters of the word correct.
Note: if word has two or more repeating letters, they both should be opened at a single guess 
(e.g.: word java needs only 3 guesses)

---- Memory Match game ----
Game represents a modification of Memozor game which allows you to train and test your memory by memorizing
certain objects and their position on the board.

--------------------------------
******* PROJECT CONTENTS *******
--------------------------------

***Game.java***

Java class that prompts user to select one of the games, runs appropriate game 
and prints out user's score when use quits out of the program.


***GuessOrSink.java***

File represents logic of the Guess Or Sink Game and consists of class constructor and few different methods:

- "correctGuessesNeededCount" : 
calculates the amount of guesses (letters) user needs to guess the word, returns integer

- "printUpdatedWord" : 
prints updated word with guessed letters, replace empty letters by "_" char, no return type

- "checkIfGuessIsCorrect" : 
check if user entered a correct guess (letter) by comparing user's input with word array, returns boolean

- "play" :
implements Guess or Sink main game logic

- "getWinStatus" :
getter method that returns a result of "play" winStatus, returns boolean


***Ship.java***

Class implements a logic for drawing a ship, consists of class constructor and 3 methods:

- "addHits" :
method that keeps track of incorrect guesses during Guess or Sink Game, no return type

- "getIsAlive" :
method that determines if ship is still alive or it's already under water, returns boolean

- "draw" :
contains logic for drawing the ship based on user guesses


***WordCard***
Class that represents WordCards, consists of constructor and a few other methods:

- "showCard" :
returns a word card is holding
in: - ; return: String word

- "setMatchMade" :
method that allows to set the value of matchMade variable
in: boolean matchMade; return: no return type

- "getMatchMade" :
in: - : return: boolean matchMade

- "toString" :
converts WordCard to string, returns word if match was made, and "?" in matchMade is false
in: - ; return: String cardToPrint


***MemoryMatch***
Class that defines Memory Match game logic, consists of constructor and few methods:

- "numberOfWordsToSet" :
method generates random board size (integer) between 4 and 10 that is divisible by 2 (e.g. 4, 6, 8, 10)
and returns number of words needed for the game
in: - ; return: int numOfWordsToBoard

- "setWordsToGues" :
selects random words from wordsToGues array (excluding duplicates) and returns selectedWords String array
in: - ; return: String[] selectedWords

- "assignWordsToBoard" :
assigns array values to random positions of 2D array called "board"
in: String[] selectedWords; return: -

- "printBoard" - 3 overloading methods:
prints appropriate version of the board based on user's guesses
in: (boolean) or (int[]) or (); return: no return type

- "updateNumberOfMatchedWords" :
updates number of guessed words
number helps to determine if user guessed all words or not
in: - ; return: no return type

- "getWinStatus" :
accessor method for class variable "WinStatus"
in: - ; return: boolean winStatus;

- "printBoardAndReturnWord" :
method prints board with opened card 
and returns string representation of the card
in: int[] wordPosition; return: String word

- "play" :
implements Memory Match game logic, no input, no return type

- "getCardPosition" :
takes in boolean that determines whether to ask user for input
gets an input from user and returns coordinates
in: boolean; return: int[] cardPosition


***Common Functions***
Class includes methods that may be shared by all classes:
- "randomInt" :
method returns random generated integer based on min and max parameters


RUN.txt  	 - file that contains program output
.gitignore	 - list of project files that should be ignored during code submission process
.project	 - Eclipse project structure description 