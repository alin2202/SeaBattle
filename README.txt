----- GUESS OF SINK and MEMORY MATCH ----



---- Guess or Sink ----

This game represents a modification on "Hanging Man" game with Ship image.

Ship starts sinking on incorrect guesses. User have to guess all letters of the word correct.

Note: if word has two or more repeating letters, they both should be opened at a single guess 

(e.g.: word java needs only 3 guesses)



---- Memory Match game ----

Game represents a modification of Memozor game which allows you to train and test your memory by memorizing

certain objects and their position on the board.



---- Battle Ship game ----

Game represent simplified version of Battle Ship game, but with user playing against the computer.

New random size field with random number of ships is generated in every game.

User wins if all computer's ships are destroyed or user has used given amount of guesses



--------------------------------

******* PROJECT CONTENTS *******

--------------------------------



***Game.java***



Java class that prompts user to select one of the games, runs appropriate game 

and prints out user's score when use quits out of the program.



***BattleShip.java***



Represent Battle Ship's game main logic. Consists of constructor, and three methods:

- play (for the play logic)

- getUserInput (for validating and recording user's input)

- accessor method getWinStatus (to return boolean: true if user wins, false if looses)



***SeaFiledShip.java***



Class contains logic for creating, placing and keeping track of ships on the board.

Contains constructor and following methods:



- setNumOfShips

method takes in field size and

returns number of ships to place on the field


- getNumberOfShips
method returns number of ships


- setShipSizes

method takes in number of ships

and returns array of ship sizes


- checkIfCellIsEmpty

method takes in cell coordinates, validates them

and returns boolean true if cell is empty, false if not



- createShipCoordinates

method generates random ship coordinates

takes in number of cells in ship (ship size) and boolean isVertical

returns two dimensional array of ship coordinates



- validateShipCoordinates

method takes in generated ship coordinates, checks if they are all empty

and returns boolean true if coordinates are valid, false if any of cells is not empty



- placeShip

method takes in ship size

and generates single ship on the field (assigns ship values to filed cells),

has no return type



- setReservedCells

method takes in ship address 

and sets value for cells around the ship to reserved



- placeShips

method place ships on the board, has no return type



- getShipsArray

accessor method for two-dimensional array of ships



- getNumberOfShipCells

method takes in array of ships 

and returns number of ship cells on the field



- revealSunkShips

method reveals sunk ships, has no return type



- checkIfAllShipsSunk

method returns boolean true if all ships sunk, 

false if there are still alive ships



- printBoard

method prints board with current elements states, has no return type



- reveal

method reveals all ships at the end of the game, has no return type





***FiledCell.java***



Class represents FiledCell objects. Consists of constructor and 3 methods:

- getCellState

- setCellState

- toString (string representation of FieldCell objects)



***Constants.java***



Class contains constant variables for Field cell states





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