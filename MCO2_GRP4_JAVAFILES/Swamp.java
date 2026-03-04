/** 
	This class is used to affect the player and enemy stats every round, namely player HP and enemy attack	
*/
public class Swamp {
	
	int playerDmg = 1;
	int oppAtkBonus = 1;
	
	// Constructor
	/** 
		method used to instantiate a Swamp object
	*/
	public Swamp() {
	}
	
	// Getter methods
	/**
		This method is used to return the HP penalty to be given to the player after every round
		@return playerDmg is the HP penalty (1) to be dealt to the player
	*/
	public int getPlayerDmg() {
		return playerDmg;
	}
	
	/**
		This method is used to return the Attack bonus to be given to the enemy after every round
		@return oppAtkBonusis the attack bonus (1) to be granted to the enemy
	*/
	public int getOppAtkBonus() {
		return oppAtkBonus;
	}
	
	/**
		This method is used to apply the appropriate buffs and debuffs to the player or the enemy after every round
		@param warrior is the warrior object to be dealt with the HP penalty
		@param opponent is the enemy object to be granted with the attack increase

	*/
	// method used to apply the turn effect of the Swamp
	public void turnEffect(Warrior warrior, Opponent opponent) {
		warrior.setHP(warrior.getHP() - playerDmg); // player gets damaged
		opponent.setAtk(opponent.getAtk() + oppAtkBonus); // enemy gets +1 attack
	}
	
}