/**
	This is the Opponent class that serves as the enemy that the player must defeat
*/
public abstract class Opponent {
	private String name;
	private int atk;
	private int hp;
	private int def;
	private int spd;
	private int dmgDisplay;
	private int multiplier = 1;
	private int choice;
	private int attackDialog = 0;
	private boolean wCharge = false;
	private boolean wDefending = false;
	
	// Constructor
	/** Constructor to create an Opponent object
		@param name is the name of the Opponent 
		@param hp is the base hp of the Opponent 
		@param atk is the attack of the Opponent 
		@param def is the defense of the Opponent 
		@param spd is the speed of the Opponent 
	*/
	public Opponent(String name, int hp, int atk, int def, int spd) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spd = spd;
	}
	
	// Getter methods
	/** This is a getter for the HP of the Opponent object
		@return hp is the current hp of the Opponent object
	*/
	public int getHP() {
		return hp;
	}
	
	/** This is a getter for the name of the Opponent object
		@return name is the current name of the Opponent object
	*/
	public String getName() {
		return name;
	}
	
	/** This is a getter for the attack of the Opponent object
		@return atk is the current attack of the Opponent object
	*/
	public int getAtk() {
		return atk;
	}
	
	/** This is a getter for the defense of the Opponent object
		@return def is the current def of the Opponent object
	*/
	public int getDef() {
		return def;
	}
	
	/** This is a getter for the choice of the Opponent object
		@return choice is the choice of the Opponent object
	*/
	public int returnChoice() {
		return choice;
	}
	
	/** This is a getter for the speed of the Opponent object
		@return spd is the current spd of the Opponent object
	*/
	public int getSpd() {
		return spd;
	}
	
	/** This is a getter for the defend state of the Opponent object
		@return wDefending is the current state of defense
	*/
	public boolean getWDefending() {
		return wDefending;
	}
	
	/** This is a getter for the attack display of the Opponent object
		@param warrior is the player
		@return a message about the outcome of the attack
	*/
	public String getAttackDialog(Warrior warrior) {
		if(this.attackDialog == 1)
			return "\n" +warrior.getName()+ " evaded the attack! Took no damage!";
		else if (this.attackDialog == 2)
			return "\n" +warrior.getName()+ " has been attacked! Took " +this.dmgDisplay+ "!";
		else if (this.attackDialog == 3)
			return "\n" +warrior.getName()+ " has been attacked! Damage is halved! Took " +this.dmgDisplay+ "!";
		else 
			return "\n" +warrior.getName()+ " took no damage!";
			
	}
	
	// Setter methods
	/** This is a setter for the hp value of the opponent
		@param hp is the hp interger to change the hp to
	*/
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	/** This is a setter for the atk value of the opponent
		@param atk is the attack integer to set the atk to
	*/
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	/** This is a setter for the def value of the opponent
		@param def is the defense integer to set the def to
	*/
	public void setDef(int def) {
		this.def = def;
	}
	
	/** This is a setter for the choice of the opponent
		@param choice is the choice of the opponent
	*/
	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	/** This is a setter for the defense state of the opponent
		@param wDefending is the state of the defense to be set to
	*/
	public void setWDefending(boolean wDefending) {
		this.wDefending = wDefending;
	}
	
	/** This is a setter for the charge state of the opponent
		@param wCharge is the state of the charge to be set to
	*/
	public void setWCharging(boolean wCharge) {
		this.wCharge = wCharge;
	}
	
	
	/**
		This method is used so that the opponent can attack the warrior
		@param warrior is the Warrior object, or the player
	*/
	// Method to attack the warrior
	public void attackWarrior(Warrior warrior) {
		int dmg = (atk * multiplier) - warrior.getDef(); // used if the player is not defending
 		int altD = ((atk * multiplier) / 2) - warrior.getDef(); // used when the player is defending
		
		// a nested if-else if statement to check if damage is dealt and how much it is
		if(warrior.getEvadeStatus() == true) {
				attackDialog = 1;
				warrior.toggleEvStatus();
		}
		else {
			if(!warrior.getWDefending() && dmg > 0) {
				warrior.setHP(warrior.getHP() - dmg); // decrease player hp
				attackDialog = 2;
				dmgDisplay = dmg;
			}
			else if (altD > 0) {
				warrior.setHP(warrior.getHP() - altD); // apply the defense turn of the player to deal decreased damage
				attackDialog = 3;
				dmgDisplay = altD;
			}
			else {
				attackDialog = 4;
			}
			
			warrior.setWDefending(false);
		}
		
		multiplier = 1;
		wCharge = false;
		wDefending = false;
	}
	
	/**
		This method is used so that the opponent can defend against the warrior
	*/
	public void defWarrior() {
		wDefending = true; // turn the defending flag to true
		
		wCharge = false; // turn off Charge for the turn (Charge can only be active for the next round after it was activated, in short: charge is wasted)
	}
	
	/**
		This method is used so that the opponent can charge against the warrior
	*/
	public void enemyChargeAtk() {
		multiplier = 3; // set damage multiplier to true
		this.wCharge = true; // set while charging to true
		this.wDefending = false;
	}
	
	/**
		This method is used by opponent to make a turn
		@param warrior is the player
	*/
	public abstract void Think(Warrior warrior);
	//
}