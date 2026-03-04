/**
	This is the Dagger child class that creates a type of weapon that the player can equip to boost their stats
*/
public class Dagger extends Weapon {
	
	private int defendNum = 0;
	private boolean skillActive = false;
	
	// Constructor
	/**
		This constructor is used to create a Dagger object
		@param name is the name to be given to the object
		@param atk is the attack of the weapon that will be added to the player's own attack
		@param sPen is the speed penalty to be applied to the player's own speed
	*/
	public Dagger(String name, int atk, int sPen) {
		super(name, atk, sPen);
	}
	
	/**
		This method is used to return the number of times the holder has defended
		@return the number of times the holder has defended
	*/
	public int getDefendNum(){
		return defendNum;
	}
	
	/**
		This method is used to return the corresponding skill notice to be displayed
	*/
	public String getSkillNotice() {
		if(skillActive)
			return "\nDagger's ability is active! This 'Defend' will become an 'Evade'!";
		else
			return "";
	}
	
	/**
		This method used to tally the amount of times the weapon is about to use a charge attack
	*/
	public void addDefended(){
		defendNum++;
	}
	
	/**
		This method is the ability of the Dagger
		@param name is the name of the Warrior
	*/
	public void ability(Warrior warrior){
		if(defendNum % 2 == 0) {
			skillActive = true;
			warrior.toggleEvStatus(); // makes it so every other defend toggles the evade status of the player
		}
		else {
			skillActive = false; 
		}
		
	}
	
}