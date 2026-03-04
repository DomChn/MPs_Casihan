/** 
	This class is used to affect the player and enemy stats every round	
*/
public class Colosseum {
	
	int playerAtkBonus = 1;
	int oppDefPen = 1;
	
	// Constructor
	/** 
		method used to instantiate a Colosseum object
	*/
	public Colosseum() {
	}
	
	//Getter methods
	/**
		This method is used to return the attack bonus to be given to the player after every round
		@return playerAtkBonus is the attack bonus (1) to be granted to the player
	*/
	public int getPlayerAtkBonus() {
		return playerAtkBonus	;
	}
	
	/**
		This method is used to return the defense penalty to be given to the enemy after every round
		@return oppDefPen is the defense penalty (1) to be applied to the enemy
	*/
	public int getOppDefPen() {
		return oppDefPen;
	}
	
	/**
		This method is used to apply the appropriate buffs and debuffs to the player or the enemy after every round
		@param warrior is the warrior object to be given the attack bonus to
		@param opponent is the enemy object to be dealt with the defense penalty
	*/
	// method used to apply the turn effect of the Swamp
	public void turnEffect(Warrior warrior, Opponent opponent) {
		warrior.setAtk(warrior.getAtk() + playerAtkBonus); // gives player +1 attack
		if(opponent.getDef() > 0)
			opponent.setDef(opponent.getDef() - oppDefPen); // decreases enemy defense by 1
	}
	
}