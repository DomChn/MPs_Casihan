/**
	This is the BattleAxe child class that creates a type of weapon that the player can equip to boost their stats
*/
public class BattleAxe extends Weapon {
	
	private boolean gaveBonus = false;
	private int skillDisplayChoice = 0;
	
	// Constructor
	/**
		This constructor is used to create a BattleAxe object
		@param name is the name to be given to the object
		@param atk is the attack of the weapon that will be added to the player's own attack
		@param sPen is the speed penalty to be applied to the player's own speed
	*/
	public BattleAxe(String name, int atk, int sPen){
		super(name, atk, sPen);
	}
	
	/**
		This method is used to return the corresponding skill notice to be displayed
	*/
	public String getSkillNotice() {
		if(skillDisplayChoice == 1)
			return "\nBattle Axe buff is removed!";
		else if(skillDisplayChoice == 2)
			return "\nBattle Axe buff is active!";
		else
			return "";
	}
	
	/**
		This method is the ability of the BattleAxe
		@param warrior is the player
	*/
	public void ability(Warrior warrior){
		if(gaveBonus == true && warrior.getWCharge() == false){ // removes buff if it is wasted
			warrior.setAtk(warrior.getAtk() - 5);
			warrior.setSpd(warrior.getSpd() - 5);
			gaveBonus = false;
			skillDisplayChoice = 1;
		}
		else if(gaveBonus == false && warrior.getWCharge() == true){ // applies buff if not yet applied 
			warrior.setAtk(warrior.getAtk() + 5);
			warrior.setSpd(warrior.getSpd() + 5);
			gaveBonus = true;
			skillDisplayChoice = 2;
		}
		else {
			skillDisplayChoice = 0;
		}
	}
	
}