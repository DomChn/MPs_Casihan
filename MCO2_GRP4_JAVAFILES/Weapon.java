/**
	This is the Weapon class that creates a weapon that the player can equip to boost their stats
*/
public abstract class Weapon {
	protected String name;
	protected int atk;
	protected int sPen;
	
	// Constructor
	/**
		This constructor is used to create a Weapon object
		@param name is the name to be given to the object
		@param atk is the attack of the weapon that will be added to the player's own attack
		@param sPen is the speed penalty to be applied to the player's own speed
	*/
	public Weapon(String name, int atk, int sPen) {
		this.name = name;
		this.atk = atk;
		this.sPen = sPen;
	}
	
	// Getter methods
	/**
		This method is used to return the name of the Weapon
		@return name is the name of the weapon
	*/
	public String getName() {
		return name;
	}
	
	/**
		This method is used to return the attack bonus of the Weapon
		@return attack is the attack bonus of the weapon
	*/
	public int getAtk() {
		return atk;
	}
	
	/**
		This method is used to return the speed penalty of the Weapon
		@return sPen is the speed penalty applied by the weapon
	*/
	public int getSPen() {
		return sPen;
	}
	
	/**
		This method is used to return the corresponding skill notice to be displayed
		@return the display text for the skill
	*/
	public abstract String getSkillNotice();
	
	/**
		This method is used to trigger the ability that the weapon has
		@param warrior is the player
	*/
	public abstract void ability(Warrior warrior);
}