/**
	This is the Warrior class that serves as the character of the user
*/
public class Warrior {

	private String name;
	private int hp = 100;
	private int atk = 1;
	private int def = 1;
	private int spd = 50;
	private int multiplier = 1;
	private int attackDialog = 0;
	private int dmgDisplay;
	private Weapon weapon;
	private Armor armor;
	private Opponent opponent;
	private boolean wCharge = false;
	private boolean wDefending = false;
	private boolean evadeStatus = false;
	
	// Constructor
	/** Constructor to create a Warrior object
		@param name is the name that the user chose
	*/
	public Warrior(String name) {
		this.name = name;
	}
	
	// Getter methods
	/** This is a getter for the name of the Warrior object
		@return name is the current name of the Warrior object
	*/
	public String getName() {
		return name;
	}
	
	/** This is a getter for the HP of the Warrior object
		@return hp is the current hp of the Warrior object
	*/
	public int getHP() {
		return hp;
	}
	
	/** This is a getter for the attack of the Warrior object
		@return atk is the current atk of the Warrior object
	*/
	public int getAtk() {
		return atk;
	}
	
	/** This is a getter for the defense of the Warrior object
		@return def is the current def of the Warrior object
	*/
	public int getDef() {
		return def;
	}
	
	/** This is a getter for the speed of the Warrior object
		@return spd is the current spd of the Warrior object
	*/
	public int getSpd() {
		return spd;
	}
	
	/** This is a getter for the attack multiplier 
		@return multiplier is the current damage multiplier of the Warrior object
	*/
	public int getMultiplier() {
		return multiplier;
	}
	
	/** This is a getter for the name of current charge state of the Warrior
		@return wCharge is the current charge state 
	*/
	public boolean getWCharge() {
		return wCharge;
	}
	
	/** This is a getter for the state of the Defending action
		@return wDefending is the current Defend state
	*/
	public boolean getWDefending() {
		return wDefending;
	}
	
	/** This is a getter for the evasion status
		@return evadeStatus is the current evade state
	*/
	public boolean getEvadeStatus() {
		return evadeStatus;
	}
	
	/** This is a getter for the attack display of the Warrior object
		@param opponent is the opponent
		@return a message about the outcome of the attack
	*/
	public String getAttackDialog(Opponent opponent) {
		if(this.attackDialog == 1)
			return "\n" +opponent.getName()+ " has been attacked! Took " +this.dmgDisplay+ "!";
		else if (this.attackDialog == 2)
			return "\n" +opponent.getName()+ " has been attacked! Damage is halved! Took " +this.dmgDisplay+ "!";
		else 
			return "\n" +opponent.getName()+ " took no damage!";
			
	}
	
	// Setter methods
	/** This is a setter for the hp value of the warrior
		@param hp is the hp interger to change the hp to
	*/
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	/** This is a setter for the atk value of the warrior
		@param atk is the attack integer to set the atk to
	*/
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	/** This is a setter for the def value of the warrior
		@param def is the defense integer to set the def to
	*/
	public void setDef(int def) {
		this.def = def;
	}
	
	/** This is a setter for the speed value of the warrior
		@param spd is the speed integer to set the spd to
	*/
	public void setSpd(int spd) {
		this.spd = spd;
	}
	
	/** This is a setter for the attack multiplier of the warrior
		@param multiplier is the integer to set the multiplier to
	*/
	public void setMult(int multiplier) {
		this.multiplier = multiplier;
	}
	
	/** This is a setter for the Charge state of the Warrior
		@param wCharge is the boolean to set the charge state to
	*/
	public void setWCharge(boolean wCharge) {
		this.wCharge = wCharge;
	}
	
	/** This is a setter for the Defend state of the Warrior
		@param wDefending is the boolean to set the defend state to
	*/
	public void setWDefending(boolean wDefending) {
		this.wDefending = wDefending;
	}
	
	// Action/Other methods
	
	/** This is a toggle for the evade status of the warrior
	*/
	public void toggleEvStatus() {
		evadeStatus = !evadeStatus;
	}
	
	/** method to equip a weapon and to change the player stats accordingly
		@param weapon is the weapon object that the player has chosen
	*/
	// method used to equip the chosen weapon
	public void equipWeapon(Weapon weapon) {
		this.weapon = weapon;
		atk += weapon.getAtk(); // get the attack bonus of the weapon
        spd -= weapon.getSPen(); // get the weapon speed penalty
	}
	
	/** method to equip an armor and to change the player stats accordingly
		@param armor is the armor object that the player has chosen
	*/
	// method to equip the chosen armor
	public void equipArmor(Armor armor) {
		this.armor = armor;
		this.def += armor.getDefBonus(); // get the defense bonus of the armor
		this.spd -= armor.getSpdPen(); // get the speed penalty of the armor
	}
	
	/** method to attack the enemy and to change their stats accordingly
		@param opponent is the opponent object that serves as the enemy
		@param evSelect is the environment that the player has selected
	*/
	// method used if "Attack" is selected
	public void attackOpp(Opponent opponent, int evSelect) {
		int dmg = (atk * multiplier) - opponent.getDef(); // compute total damage
		int altD = ((atk * multiplier) / 2) - opponent.getDef();
		int evDec = 0;
		
		 /* compute total CHARGE damage (get the attack the moment charge was used when the stage
		 is Colosseum), computed as (atk - 1) to get the attack stat BEFORE the Colosseum addition 
		 takes effect
		 */
		if(evSelect == 3) {
			evDec = 1;
		}
		
		// a specific if statement to properly calculate the damage that Sword + charge will deal
		if(this.weapon.getName() == "Sword" && wCharge == true) {
			dmg = (((atk - 10 - evDec) * multiplier) + 10) - opponent.getDef();
			altD = ((((atk - 10 - evDec) / 2) * multiplier) + 10) - opponent.getDef();	
		}
		
		// an if-else if-else statement to check if any damage is dealt to the enemy
		if(!opponent.getWDefending() && dmg > 0) {
				opponent.setHP(opponent.getHP() - dmg); // decrease player hp
				attackDialog = 1;
				dmgDisplay = dmg;
			}
		else if (altD > 0) {
			opponent.setHP(opponent.getHP() - altD); // apply the defense turn of the player to deal decreased damage
			attackDialog = 2;
			dmgDisplay = altD;
		}
		else {
			attackDialog = 3;
		}
		
		multiplier = 1; // set multiplier back to 1 if it was changed
		this.wCharge = false; // set the boolean for charging to false to change it back if it was used in the current round
		this.wDefending = false;
		opponent.setWDefending(false);
	}
	
	/** 
		method to defend against the enemy attack 
	*/
	// method used if "Defend" is selected 
	public void defOpp() {
		if(!evadeStatus) {
			wDefending = true; // turn the defending flag to true
		}
		
		wCharge = false; // turn off Charge for the turn (Charge can only be active for the next round after it was activated, in short: charge is wasted)
	}
	
	/** method used to charge the next player attack
	*/
	// method used if "Charge" is selected
	public void chargeAtk() {
		multiplier = 3; // set damage multiplier to true
		wCharge = true; // set while charging to true
		this.wDefending = false;
	}
	
	
}