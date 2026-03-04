import java.util.*;

/**
	This is the controller file for MCO2
*/
public class GameController {
	private int turnNumber = 1;
	private int orderDecision;
	private int oChoice;
	private int evSelect;
	private int oppSelect;
	private Warrior warrior;
	private Opponent opponent;
	private Weapon weapon;
	private Armor armor;
	private GameUI game;
	private Swamp sw1 = new Swamp();
	private Colosseum c1 = new Colosseum();
	
	/** Constructor to create the Controller for the game
	*/
	public GameController() {

	}
	
	/** A getter for the opponent's choice
		@return oChoice is the chosen move of the opponent;
	*/
	public int getOChoice() {
		return oChoice;
	}
	
	/** A getter for the current turn count
		@return turnNumber is the current turn count of the iteration;
	*/
	public int getTurnNumber() {
		return turnNumber;
	}
	
	/** A getter for the HP of the warrior or the opponent
		@param selection is 1 if the needed data is from the player, and 2 otherwise
		@return either opponent or player HP
	*/
	public int getUnitHP(int selection) {
		if(selection == 1) 
			return warrior.getHP();
		else 
			return opponent.getHP();
	}
	
	/** A getter for the name of the warrior or the opponent
		@param selection is 1 if the needed data is from the player, and 2 otherwise
		@return opponent or player name
	*/
	public String getUnitName(int selection) {
		if(selection == 1) 
			return warrior.getName();
		else 
			return opponent.getName();
	}
	
	/** A getter for the defense of the warrior or the opponent
		@param selection is 1 if the needed data is from the player, and 2 otherwise
		@return opponent or player def
	*/
	public int getUnitDef(int selection) {
		if(selection == 1) 
			return warrior.getDef();
		else 
			return opponent.getDef();
	}
	
	/** A getter for the speed of the warrior or the opponent
		@param selection is 1 if the needed data is from the player, and 2 otherwise
		@return either opponent or player speed
	*/
	public int getUnitSpd(int selection) {
		if(selection == 1) 
			return warrior.getSpd();
		else 
			return opponent.getSpd();
	}
	
	/** A getter for the attack of the warrior or the opponent
		@param selection is 1 if the needed data is from the player, and 2 otherwise
		@return either opponent or player attack
	*/
	public int getUnitAtk(int selection) {
		if(selection == 1) 
			return warrior.getAtk();
		else 
			return opponent.getAtk();
	}
	
	/** A getter for the player's charge status
		@return warrior.getWCharge() is the getter for the player's charge state
	*/
	public boolean getWarriorCharging() {
		return warrior.getWCharge();
	}
	
	/** A getter for the chosen stage
		@return the chosen stage of the player
	*/
	public String getStage() {
		if(evSelect == 2) 
			return "Swamp";
		else if(evSelect == 3)
			return "Colosseum";
		else
			return "Arena";
	}
	
	/** Sets the GUI object 
		@param g is the game UI 
	*/
	public void setGUI(GameUI g) {
		this.game = g;
	}
	
	/** Checks if the player has won
		@return boolean if opponent HP is depleted
		
	*/
	public boolean checkWinCondition() {
		return opponent.getHP() <= 0;
	}
	
	/** Checks if the player has lost
		@return boolean if player HP is depleted
		
	*/
	public boolean checkLoseCondition() {
		return warrior.getHP() <= 0;
	}
	
	/** Checks who is faster between the player and the opponent
		@return 1 or 2, 1 being player first before opponent, and vice versa for 2
	*/
	public int checkSpeed() {
		if(warrior.getSpd() >= opponent.getSpd()) 
			return 1;
		else
			return 0;
	}
	
	/** A method to let the opponent make a move
	*/
	public void enemyMove() {
		opponent.Think(warrior);
		oChoice = opponent.returnChoice();
	}
	
	/** A method to apply the chosen environment's effect 
	*/
	public void applyEnvEffect() {
		if(evSelect == 2) {
			sw1.turnEffect(warrior, opponent);
			game.displayText("\n\n" +warrior.getName()+ " took 1 dmg from the swamp!");
			game.displayText("\nThe swamp grants +1 atk to " +opponent.getName()+ "\n");
			
		} else if(evSelect == 3) {
			c1.turnEffect(warrior, opponent);
			game.displayText("\n\nThe colosseum grants +1 atk to " +warrior.getName());
			game.displayText("\nThe colosseum takes away 1 def from " +opponent.getName()+ "\n");
		}
	}
	
	/** The method that handles the player and opponent interactions, as well as the events that happen
		every turn
		@param mChoice is the player's chosen move
	*/
	public void turnHandler(int mChoice) {
		enemyMove(); // call the method to let the opponent decide on a move
		
		// checks evade status so the player cannot stack evasion/'pre-evade'
		if(warrior.getEvadeStatus()) 
			warrior.toggleEvStatus(); 
		
		// if opponent chooses to block
		if(oChoice == 2) {
			opponent.defWarrior();
			game.displayText("\n" +opponent.getName()+ " is defending! Their next damage taken is halved!");
		}
		
		// happens first disregarding speed if player chooses to defend
		if(mChoice == 2) {
			if(weapon.getName() == "Dagger") {
				((Dagger)weapon).addDefended();
				weapon.ability(warrior); // activate dagger ability if the conditions are met
				game.displayText(weapon.getSkillNotice());
			}
			warrior.defOpp(); // defends normally if evasion condition not met, or if using another weapon
			
			if(!warrior.getEvadeStatus()) 
				game.displayText("\nYou are defending! Next damage taken is halved!\n");
				
			// opponent moves second
			if(oChoice == 1) {  
				opponent.attackWarrior(warrior); // executes if opponent chooses to attack
				game.displayText(opponent.getAttackDialog(warrior));
			} else if(oChoice == 3) {
				game.displayText("\n" +opponent.getName()+ " is charging! Their next attack is tripled.");
				opponent.enemyChargeAtk(); // excutes if opponent chooses to charge
			}
			
		} else if(checkSpeed() == 1) { // happens if player is faster than the opponent
			// executes player's move first
			if(mChoice == 1) { 
				if(weapon.getName() == "Sword") { // if player has a sword, apply sword attack buff
					game.displayText(weapon.getSkillNotice());
					((Sword)weapon).ability(warrior);
					warrior.attackOpp(opponent, evSelect);
					game.displayText(warrior.getAttackDialog(opponent));
					warrior.setAtk(warrior.getAtk() - 10);
				} else { // else, attack normally
					warrior.attackOpp(opponent, evSelect); 
					game.displayText(warrior.getAttackDialog(opponent));
				}
			} else { // executes if player picked charge
				warrior.chargeAtk(); 
				game.displayText("\n" +warrior.getName()+ " is charging! Your next attack is tripled.");
				if(weapon.getName() == "Battle Axe") {
					((BattleAxe)weapon).ability(warrior); // apply the battle axe's buff if its equipped
					game.displayText(weapon.getSkillNotice());
				}
			}
			
			// opponent moves second
			if(oChoice == 1) {  
				opponent.attackWarrior(warrior);
				game.displayText(opponent.getAttackDialog(warrior));
			} else if(oChoice == 3) {
				game.displayText("\n" +opponent.getName()+ " is charging! Their next attack is tripled.");
				opponent.enemyChargeAtk();
			}
			
		} else { // happens if opponent is faster than the player
			// opponent moves first
			if(oChoice == 1) { 
				opponent.attackWarrior(warrior);
				game.displayText(opponent.getAttackDialog(warrior));
			} else if(oChoice == 3) {
				opponent.enemyChargeAtk();
				game.displayText("\n" +opponent.getName()+ " is charging! Their next attack is tripled.");
			}
			
			// execute the player's move second
			if(mChoice == 1) {
				if(weapon.getName() == "Sword") {
					game.displayText(weapon.getSkillNotice());
					((Sword)weapon).ability(warrior);
					warrior.attackOpp(opponent, evSelect);
					game.displayText(warrior.getAttackDialog(opponent));
					warrior.setAtk(warrior.getAtk() - 10);
				} else {
					warrior.attackOpp(opponent, evSelect); // if player input is 1, player attacks
					game.displayText(warrior.getAttackDialog(opponent));
				}
			} else {
				warrior.chargeAtk();
				game.displayText("\n" +warrior.getName()+ " is charging! Your next attack is tripled.");
				if(weapon.getName() == "Battle Axe") {
					((BattleAxe)weapon).ability(warrior);
					game.displayText(weapon.getSkillNotice());
				}
			} 
		} // end of the player and opponent move execution
		
		applyEnvEffect(); // apply environment effect at the end of the turn
		
		// call the battle axe weapon skill to remove the buff if conditions are met
		if(weapon.getName() == "Battle Axe") {
			((BattleAxe)weapon).ability(warrior);
			game.displayText(weapon.getSkillNotice());
		} 
		
		turnNumber++; // add the turn count
		
	}
	
	/**  A method that handles the creation of the warrior object
		@param name is the inputted name of the user
	*/
	public void createHero(String name) {
		Warrior w1 = new Warrior(name);
		
		this.warrior = w1;
	}
	
	/**  A method that handles the weapon select function of the game
		@param wChoice is the chosen weapon of the user
	*/
	public void wepSelect(int wChoice) {
		
		// check the user input and instantiate the corresponding objects, as well as equip the chosen weapon afterwards
		if(wChoice == 1) {
			Weapon wep1 = new Dagger("Dagger", 20, 0); // instantiate Dagger as a Weapon object 
			this.weapon = wep1; 
			warrior.equipWeapon(weapon); 
		}
		else if(wChoice == 2) {
			Weapon wep1 = new Sword("Sword", 30, 10); // instantiate Sword as a Weapon object 
			this.weapon = wep1;
			warrior.equipWeapon(weapon);
		}
		else if(wChoice == 3) {
			Weapon wep1 = new BattleAxe("Battle Axe", 40, 20); // instantiate BattleAxe as a Weapon object 
			this.weapon = wep1;
			warrior.equipWeapon(weapon);
		}
		else {
			Weapon wep1 = new NoWeapon("Bare Hands", 0, 0);
			this.weapon = wep1;
			warrior.equipWeapon(weapon);
		}
		
	}
	
	
	/**  A method that handles the armor select function of the game
		@param aChoice is the chosen armor of the user
	*/
	public void armSelect(int aChoice) {
		
		// check the user input and instantiate the corresponding objects, as well as equip the chosen armor afterwards
		if(aChoice == 1) {
			Armor arm1 = new Armor("Light Armor", 20, 5); 
			this.armor = arm1;
			warrior.equipArmor(armor);
		}
		else if(aChoice == 2) {
			Armor arm1 = new Armor("Medium Armor", 30, 15); 
			this.armor = arm1;
			warrior.equipArmor(armor);
		}
		else if(aChoice == 3) {
			Armor arm1 = new Armor("Heavy Armor", 40, 20); 
			this.armor = arm1;
			warrior.equipArmor(armor);
		}
		else {
			Armor arm1 = new Armor("Bare Body", 0, 0);
			this.armor = arm1;
			warrior.equipArmor(armor);
		}
	}
	
	
	/**  A method that handles the opponent select function of the game
		@param eChoice is the chosen opponent of the user
	*/
	public void opSelect(int eChoice) {
		
		// check the user input and instantiate the corresponding objects
		if(eChoice == 1) {
			Opponent op1 = new Thief("Robin", 150, 20, 20, 40); // instantiate Thief as an opponent object 
			this.opponent = op1;
		}
		else if(eChoice == 2) {
			Opponent op1 = new Viking("Varjager", 250, 30, 30, 30); // instantiate Viking as an opponent object 
			this.opponent = op1;
		}
		else {
			Opponent op1 = new Minotaur("Cow man", 350, 40, 40, 20); // instantiate Minotaur as an opponent object 
			this.opponent = op1;
		}
	}
	
	/** A method that handles the environment select function of the game
		@param evChoice - the chosen environment of the user
	*/
	public void envSelect(int evChoice) {
		this.evSelect = evChoice;
	}
	
	/** A method that calls the initGame() function in main that starts the main game 
	*/
	public void commenceGame() {
		DriverFinal2.initGame(this);
	}
}