/**
	This is the NoWeapon child class that creates a type of weapon that the player can equip to boost their stats
*/
public class NoWeapon extends Weapon {
	
	// Constructor
	/**
		This constructor is used to create an object of NoWeapon
		@param name is the name to be given to the object
		@param atk is the attack of the weapon that will be added to the player's own attack
		@param sPen is the speed penalty to be applied to the player's own speed
	*/
	public NoWeapon(String name, int atk, int sPen) {
		super(name, atk, sPen);
	}
	
	/**
		This method is used to return the corresponding skill notice to be displayed.
		Doesn't do anything for this class
	*/
	public String getSkillNotice() {
		return "Easter Egg ?";
	
	}
	
	/**
		This method is the ability of the Dagger
		@param name is the name of the Warrior
	*/
	public void ability(Warrior warrior){
		
	}
	
}