/**
	This is the Thief child class that is one of the optional Opponents
*/
public class Thief extends Opponent {
	
	private int choice = 0;
	
	/** Constructor to create a Thief object
		@param name is the name of the Opponent 
		@param hp is the base hp of the Opponent 
		@param atk is the attack of the Opponent 
		@param def is the defense of the Opponent 
		@param spd is the speed of the Opponent 
	*/
	public Thief(String name, int hp, int atk, int def, int spd) {
		super(name, hp, atk, def, spd); // calls the super class from the parent Opponent class
	}
	
	/** This is a getter for the oChoice of the Thief object
		@return oChoice is the current choice of the Thief object
	*/
	public void Think(Warrior warrior){
		this.setChoice(1); // set to only attack
	}
}