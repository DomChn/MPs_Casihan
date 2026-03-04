	import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
	This is the main game GUI file for MCO2
*/
public class GameUI extends JFrame {
	private JPanel displayPanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private JPanel enemyInfo = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel battlePanel = new JPanel();
	private JLabel playerHp = new JLabel("Player HP: ");
	private JLabel enemyHp = new JLabel("Enemy HP: ");
	private JLabel stage = new JLabel("Stage: ");
	private JLabel name = new JLabel("Name: ");
	private JLabel attackDisp = new JLabel("Attack: ");
	private JLabel defense = new JLabel("Def: ");
	private JLabel speed = new JLabel("Speed: ");
	private JLabel eName = new JLabel("Name: ");
	private JLabel eAttackDisp = new JLabel("Attack: ");
	private JLabel eDefense = new JLabel("Def: ");
	private JLabel eSpeed = new JLabel("Speed: ");
	private JTextArea display = new JTextArea(20, 40);
	private JButton attack = new JButton("Attack");
	private JButton defend = new JButton("Defend");
	private JButton charge = new JButton("Charge");
	private GameController gc;

	/** Constructor to create the main game view as well as to create the content pane
		to display the added components in. Also adds action listeners to the buttons
		@param gc is the Controller for the entire game
	*/
	public GameUI(GameController gc) {
		this.gc = gc;
		
		playerHp.setText("Player HP: " +gc.getUnitHP(1));
		enemyHp.setText("Enemy HP: " +gc.getUnitHP(2));
		stage.setText("Stage: " +gc.getStage());
		
		getContentPane().setLayout(new BorderLayout(2, 2)); // set a border layout for the content pane
		
		/* 
			Create a Grid layout for the infoPanel, then add components to the panel. To be used to display player, 
			opponent HP and the stages
		*/
		infoPanel.setBorder(BorderFactory.createTitledBorder("Health and Stage Info"));
		infoPanel.setLayout(new GridLayout(1, 3, 1, 1));
		infoPanel.add(playerHp);
		infoPanel.add(stage);
		infoPanel.add(enemyHp);
		
		// add components to the displayPanel, to be used to display the action history
		display.setText("Turn begins here!\n");
		display.setLineWrap(true);
		display.setEditable(false);
		displayPanel.setBorder(BorderFactory.createTitledBorder("Action History"));
		displayPanel.setLayout(new FlowLayout());
		displayPanel.add(new JScrollPane(display));
		
		// add components to enemyInfo, to be used to display the enemy stats
		eName.setText("Name: " +gc.getUnitName(2));
		eAttackDisp.setText("Attack: " +gc.getUnitAtk(2));
		eDefense.setText("Defense: " +gc.getUnitDef(2));
		eSpeed.setText("Speed: " +gc.getUnitSpd(2));
		enemyInfo.setBorder(BorderFactory.createTitledBorder("Enemy Info"));
		enemyInfo.setLayout(new GridLayout(4, 1, 1, 1));
		enemyInfo.add(eName);
		enemyInfo.add(eAttackDisp);
		enemyInfo.add(eDefense);
		enemyInfo.add(eSpeed);
		
		// add components to battlePanel, to be used to display the player stats
		name.setText("Name: " +gc.getUnitName(1));
		attackDisp.setText("Attack: " +gc.getUnitAtk(1));
		defense.setText("Defense: " +gc.getUnitDef(1));
		speed.setText("Speed: " +gc.getUnitSpd(1));
		battlePanel.setBorder(BorderFactory.createTitledBorder("Player Info"));
		battlePanel.setLayout(new GridLayout(4, 1, 1, 1));
		battlePanel.add(name);
		battlePanel.add(attackDisp);
		battlePanel.add(defense);
		battlePanel.add(speed);
		
		/* add an action listener to respond to button clicks, check win and lose condition each time
			to end the program if either of the conditions is met. display the next round if no conditions
			are met
		*/
		attack.addActionListener(e -> {
			gc.turnHandler(1); // call the turnHandler in the controller with the choice being "Attack"
			updateInfo();
			if(gc.checkWinCondition()) 
				displayWin();
			else if(gc.checkLoseCondition()) 
				displayLoss();
			else 
				roundDisplay();
		});
		
		defend.addActionListener(e -> {
			gc.turnHandler(2); // call the turnHandler in the controller with the choice being "Defend"
			updateInfo();
			if(gc.checkLoseCondition()) 
				displayLoss();
			else 
				roundDisplay();
		});
		
		charge.addActionListener(e -> {
			gc.turnHandler(3); // call the turnHandler in the controller with the choice being "Charge"
			updateInfo();
			if(gc.checkLoseCondition())  
				displayLoss();
			else 
				roundDisplay();
		});
		
		
		// add the buttons to the buttonPanel to be used as player moves
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Pick your move"));
		buttonPanel.setLayout(new GridLayout(1, 3, 1, 1));
		buttonPanel.add(attack);
		buttonPanel.add(defend);
		buttonPanel.add(charge);
		
		// add the various panels to the content pane
		getContentPane().add(infoPanel, "North");
		getContentPane().add(displayPanel, "Center");
		getContentPane().add(battlePanel, "East");
		getContentPane().add(enemyInfo, "West");
		getContentPane().add(buttonPanel, "South");
		
	}
	
	/** A method used to display the next round in the game
	*/
	public void roundDisplay() {
		display.append("\n\nRound " +gc.getTurnNumber()+ ": Lock-in Phase");
		display.append("\nAttack charged?: " +gc.getWarriorCharging());
		display.append("\nSelect your action\n");
	}
	
	/** A method used to display information in the GUI, called by the Controller
		@param textToAdd is the text to be appended to the text area
	*/
	public void displayText(String textToAdd) {
		display.append(textToAdd);
	}
	
	/** A method used to display the Win message, disable buttons afterwards
	*/
	public void displayWin() {
		display.append("\n\n" +gc.getUnitName(2)+ " has been defeated! " +gc.getUnitName(1)+ " has won the bout!");
		attack.setEnabled(false);
		defend.setEnabled(false);
		charge.setEnabled(false);
	}
	
	/** A method used to display the Loss message, disable buttons afterwards
	*/
	public void displayLoss() {
		display.append("\n\n" +gc.getUnitName(1)+ " has been defeated! You lack the training...");
		attack.setEnabled(false);
		defend.setEnabled(false);
		charge.setEnabled(false);
	}
	
	/** A method used to update the info in case there were changes
	*/
	public void updateInfo() {
		if(gc.getUnitHP(1) <= 0) { 
			playerHp.setText("Player HP: 0");
			enemyHp.setText("Enemy HP: " +gc.getUnitHP(2));
		} else if(gc.getUnitHP(2) <= 0) {
			playerHp.setText("Player HP: " +gc.getUnitHP(1));
			enemyHp.setText("Enemy HP: 0");
		} else {
			playerHp.setText("Player HP: " +gc.getUnitHP(1));
			enemyHp.setText("Enemy HP: " +gc.getUnitHP(2));
		}
		attackDisp.setText("Attack: " +gc.getUnitAtk(1));
		defense.setText("Defense: " +gc.getUnitDef(1));
		speed.setText("Speed: " +gc.getUnitSpd(1));
		
		eAttackDisp.setText("Enemy Attack: " +gc.getUnitAtk(2));
		eDefense.setText("Enemy Defense: " +gc.getUnitDef(2));
		eSpeed.setText("Enemy Speed: " +gc.getUnitSpd(2));
		
	}
	
}
