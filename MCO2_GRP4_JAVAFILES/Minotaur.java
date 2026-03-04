/**
	This is the Minotaur child class that is one of the optional Opponents
*/
public class Minotaur extends Opponent {
	
	private int turnCounter = 1;
	
	/** Constructor to create a Minotaur object
		@param name is the name of the Opponent 
		@param hp is the base hp of the Opponent 
		@param atk is the attack of the Opponent 
		@param def is the defense of the Opponent 
		@param spd is the speed of the Opponent 
	*/
	public Minotaur(String name, int hp, int atk, int def, int spd) {
		super(name, hp, atk, def, spd);
	}
	
	/** 
		method to change oChoice based on turn number
		@param warrior is the player
	*/
	public void Think(Warrior warrior){
		
		if(turnCounter % 3 <= 1){
			this.setChoice(1); // attack move
		}
		else{
			this.setChoice(3); // charge move
		}
		
		turnCounter++; // add to the turnCounter
	}
}