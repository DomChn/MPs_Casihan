/**
	This is the Sword child class that creates a type of weapon that the player can equip to boost their stats
*/
public class Sword extends Weapon {
	
	// Constructor
	/**
		This constructor is used to create a Sword object
		@param name is the name to be given to the object
		@param atk is the attack of the weapon that will be added to the player's own attack
		@param sPen is the speed penalty to be applied to the player's own speed
	*/
	public Sword(String name, int atk, int sPen){
		super(name, atk, sPen);
	}
	
	/**
		This method is used to return the corresponding skill notice to be displayed
	*/
	public String getSkillNotice() {
		return "\nSword ability is active! Damage dealt +10!";
	}

	/**
		This method is the ability of the Sword
		@param warrior is the player 
	*/
	public void ability(Warrior warrior){
		warrior.setAtk(warrior.getAtk() + 10);
	}
	
}