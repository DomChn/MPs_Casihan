import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
	This is the selection GUI file for MCO2
*/
public class SelectionUI extends JFrame {
	private JPanel characterCreation = new JPanel();
	private JPanel armorSelection = new JPanel();
	private JPanel weaponSelection = new JPanel();
	private JPanel opponentSelection = new JPanel();
	private JPanel environmentSelection = new JPanel();
	private JTextField name = new JTextField();
	private JButton confirmName = new JButton("Enter");
	private JButton lArmor = new JButton("L. Armor");
	private JButton mArmor = new JButton("M. Armor");
	private JButton hArmor = new JButton("H. Armor");
	private JButton nArmor = new JButton("None");
	private JLabel lArmorD = new JLabel("+20 def, -5 spd");
	private JLabel mArmorD = new JLabel("+30 def, -15 spd");
	private JLabel hArmorD = new JLabel("+40 def, -25 spd");
	private JLabel nArmorD = new JLabel("+0 def, -0 spd");
	private JButton daggerB = new JButton("Dagger");
	private JButton swordB = new JButton("Sword");
	private JButton axeB = new JButton("Battle Axe");
	private JButton noWep = new JButton("None");
	private JLabel daggerD = new JLabel("+20 atk, -0 spd");
	private JLabel swordD = new JLabel("+30 atk, -10 spd");
	private JLabel axeD = new JLabel("+40 atk, -20 spd");
	private JLabel noWepD = new JLabel("+0 atk, -0 spd");
	private JLabel daggerS = new JLabel("Every other defend becomes an evade");
	private JLabel swordS = new JLabel("Grants +10 atk for every attack ");
	private JLabel axeS = new JLabel("Grants +5 atk and +5 spd for the next round");
	private JLabel noWepS = new JLabel("Literally nothing");
	private JButton thiefB = new JButton("Thief");
	private JButton vikingB = new JButton("Viking");
	private JButton minotaurB = new JButton("Minotaur");
	private JLabel thiefD = new JLabel("Puny, but relentless");
	private JLabel vikingD = new JLabel("A defensive and strategic warrior...");
	private JLabel minotaurD = new JLabel("A monolith of strength, despite its speed.");
	private JButton arena = new JButton("Arena");
	private JButton swamp = new JButton("Swamp");
	private JButton colosseum = new JButton("Colosseum");
	private JLabel arenaE = new JLabel("No effect");
	private JLabel swampE = new JLabel("-1 hp for the player, +1 atk for the enemy");
	private JLabel colosseumE = new JLabel("-1 def for the enemy, +1 atk for the player");
	private JButton begin = new JButton("Begin");
	private GameController gc;
	
	/** Constructor to create the selection view as well as to create the content pane
		to display the added components in. Also adds action listeners to the buttons
		@param gc is the Controller for the entire game
	*/
	public SelectionUI(GameController gc) {
		this.gc = gc;
		
		// set all panels to invisible
		weaponSelection.setVisible(false);
		armorSelection.setVisible(false);
		opponentSelection.setVisible(false);
		environmentSelection.setVisible(false);
	
		getContentPane().setLayout(new GridLayout(6, 1, 2, 1)); // add a grid layout to the content pane
		
		// add components to the characterCreation panel, to be used to get the player name
		name.setFont(new Font("Arial", Font.PLAIN, 25));
		characterCreation.setBorder(BorderFactory.createTitledBorder("Who dares enter the mayhem?"));
		characterCreation.setLayout(new GridLayout(2, 1, 1, 1));
		characterCreation.add(name);
		characterCreation.add(confirmName);
		
		/* add an action listener to the confirm button to show the next panel, as well as disable the 
			components in the characterCreation panel
		*/
		confirmName.addActionListener(e -> {
			confirmName.setEnabled(false);
			name.setEditable(false);
			gc.createHero(name.getText());
			name.setText("Chosen name is " +gc.getUnitName(1));
			weaponSelection.setVisible(true);
		});
		
		// add components to the weaponSelection panel, to be used to get the player's chosen weapon
		weaponSelection.setBorder(BorderFactory.createTitledBorder("Brandish those which you fancy..."));
		weaponSelection.setLayout(new GridLayout(3, 4, 1, 1));
		weaponSelection.add(daggerB);
		weaponSelection.add(swordB);
		weaponSelection.add(axeB);
		weaponSelection.add(noWep);
		weaponSelection.add(daggerD);
		weaponSelection.add(swordD);
		weaponSelection.add(axeD);
		weaponSelection.add(noWepD);
		weaponSelection.add(daggerS);
		weaponSelection.add(swordS);
		weaponSelection.add(axeS);
		weaponSelection.add(noWepS);
		
		 /* add an action listener to the weapon button choices to set them as the player's choice, show the next panel, as well as disable the 
			components in the weaponSelection panel
		*/
		daggerB.addActionListener(e -> {
			shiftButtons(1);
			wepShiftDisplays(1);
			gc.wepSelect(1);
		});
		
		swordB.addActionListener(e -> {
			shiftButtons(1);
			wepShiftDisplays(2);
			gc.wepSelect(2);
		});
		
		axeB.addActionListener(e -> {
			shiftButtons(1);
			wepShiftDisplays(3);
			gc.wepSelect(3);
		});
		
		noWep.addActionListener(e -> {
			shiftButtons(1);
			wepShiftDisplays(4);
			gc.wepSelect(4);
		});
		
		// add components to the weaponSelection panel, to be used to get the player's chosen armor
		armorSelection.setBorder(BorderFactory.createTitledBorder("You are not blade-proof..."));
		armorSelection.setLayout(new GridLayout(2, 4, 1, 1));
		armorSelection.add(lArmor);
		armorSelection.add(mArmor);
		armorSelection.add(hArmor);
		armorSelection.add(nArmor);
		armorSelection.add(lArmorD);
		armorSelection.add(mArmorD);
		armorSelection.add(hArmorD);
		armorSelection.add(nArmorD);
		
		/* add an action listener to the armor button choices to set them as the player's choice, show the next panel, as well as disable the 
			components in the armorSelection panel
		*/
		lArmor.addActionListener(e -> {
			shiftButtons(2);
			armShiftDisplays(1);
			gc.armSelect(1);
		});
		
		mArmor.addActionListener(e -> {
			shiftButtons(2);
			armShiftDisplays(2);
			gc.armSelect(2);
		});
		
		hArmor.addActionListener(e -> {
			shiftButtons(2);
			armShiftDisplays(3);
			gc.armSelect(3);
		});
		
		nArmor.addActionListener(e -> {
			shiftButtons(2);
			armShiftDisplays(4);
			gc.armSelect(4);
		});
		
		// add components to the opponentSelection panel, to be used to get the player's chosen opponent
		opponentSelection.setBorder(BorderFactory.createTitledBorder("A challenger approaches!"));
		opponentSelection.setLayout(new GridLayout(2, 3, 1, 1));
		opponentSelection.add(thiefB);
		opponentSelection.add(vikingB);
		opponentSelection.add(minotaurB);
		opponentSelection.add(thiefD);
		opponentSelection.add(vikingD);
		opponentSelection.add(minotaurD);
		
		/* add an action listener to the opponent button choices to set them as the player's choice, show the next panel, as well as disable the 
			components in the opponentSelection panel
		*/
		thiefB.addActionListener(e -> {
			shiftButtons(3);
			oppShiftDisplays(1);
			gc.opSelect(1);
		});
		
		vikingB.addActionListener(e -> {
			shiftButtons(3);
			oppShiftDisplays(2);
			gc.opSelect(2);
		});
		
		minotaurB.addActionListener(e -> {
			shiftButtons(3);
			oppShiftDisplays(3);
			gc.opSelect(3);
		});
		
		// add components to the opponentSelection panel, to be used to get the player's chosen environment
		environmentSelection.setBorder(BorderFactory.createTitledBorder("The setting of your battle. (Effects activate every round)"));
		environmentSelection.setLayout(new GridLayout(2, 3, 1, 1));
		environmentSelection.add(arena);
		environmentSelection.add(swamp);
		environmentSelection.add(colosseum);
		environmentSelection.add(arenaE);
		environmentSelection.add(swampE);
		environmentSelection.add(colosseumE);
		
		/* add an action listener to the opponent button choices to set them as the player's choice, show the final panel, as well as disable the 
			components in the environmentSelection panel
		*/
		arena.addActionListener(e -> {
			shiftButtons(4);
			envShiftDisplays(1);
			gc.envSelect(1);
		});
		
		swamp.addActionListener(e -> {
			shiftButtons(4);
			envShiftDisplays(2);
			gc.envSelect(2);
		});
		
		colosseum.addActionListener(e -> {
			shiftButtons(4);
			envShiftDisplays(3);
			gc.envSelect(3);
		});
		
		// add all the panels to the content pane
		getContentPane().add(characterCreation);
		getContentPane().add(weaponSelection);
		getContentPane().add(armorSelection);
		getContentPane().add(opponentSelection);
		getContentPane().add(environmentSelection);
		getContentPane().add(begin);
		
		/* add an action listener to the begin button to make the entire pane invisible, then call commenceGame() so that
			the controller can tell the driver to start the main game GUI
		*/
		begin.addActionListener(e -> {
			gc.commenceGame();
			this.setVisible(false);
		});
		
	}
	
	/** Method to disable the buttons in the panels that are already 'visited' by the player.
		Makes the next unvisited panel visible.
		@param scenario is the indicator on which buttons to disable
	*/
	public void shiftButtons(int scenario) {
		if(scenario == 1) {
			daggerB.setEnabled(false);
			swordB.setEnabled(false);
			axeB.setEnabled(false);
			noWep.setEnabled(false);
			armorSelection.setVisible(true);
		} else if(scenario == 2) {
			lArmor.setEnabled(false);
			mArmor.setEnabled(false);
			hArmor.setEnabled(false);
			nArmor.setEnabled(false);
			opponentSelection.setVisible(true);
		} else if(scenario == 3) {
			thiefB.setEnabled(false);
			vikingB.setEnabled(false);
			minotaurB.setEnabled(false);
			environmentSelection.setVisible(true);
		} else {
			arena.setEnabled(false);
			swamp.setEnabled(false);
			colosseum.setEnabled(false);
			begin.setVisible(true);
		}
	}
	
	 /** Method to hide the labels in the weapon selection panel. Keeps the labels for the weapon that the player has selected.
		@param selection is the indicator on which labels to hide
	*/
	public void wepShiftDisplays(int selection) {
		if(selection == 1) {
			swordD.setVisible(false);
			swordS.setVisible(false);
			axeD.setVisible(false);
			axeS.setVisible(false);
			noWepD.setVisible(false);
			noWepS.setVisible(false);
		} else if(selection == 2) {
			daggerD.setVisible(false);
			daggerS.setVisible(false);
			axeD.setVisible(false);
			axeS.setVisible(false);
			noWepD.setVisible(false);
			noWepS.setVisible(false);
		} else if(selection == 3) {
			daggerD.setVisible(false);
			daggerS.setVisible(false);
			swordD.setVisible(false);
			swordS.setVisible(false);
			noWepD.setVisible(false);
			noWepS.setVisible(false);
		} else {
			daggerD.setVisible(false);
			daggerS.setVisible(false);
			swordD.setVisible(false);
			swordS.setVisible(false);
			axeD.setVisible(false);
			axeS.setVisible(false);
		}
	}
	 /** Method to hide the labels in the armor selection panel. Keeps the labels for the armor that the player has selected.
		@param selection is the indicator on which labels to hide
	*/
	public void armShiftDisplays(int selection) {
		if(selection == 1) {
			mArmorD.setVisible(false);
			hArmorD.setVisible(false);
			nArmorD.setVisible(false);
		} else if(selection == 2) {
			lArmorD.setVisible(false);
			hArmorD.setVisible(false);
			nArmorD.setVisible(false);
		} else if(selection == 3) {
			lArmorD.setVisible(false);
			mArmorD.setVisible(false);
			nArmorD.setVisible(false);
		} else {
			lArmorD.setVisible(false);
			mArmorD.setVisible(false);
			hArmorD.setVisible(false);
		}
	}
	
	/** Method to hide the labels in the opponent selection panel. Keeps the labels for the opponent that the player has selected.
		@param selection is the indicator on which labels to hide
	*/
	public void oppShiftDisplays(int selection) {
		if(selection == 1) {
			vikingD.setVisible(false);
			minotaurD.setVisible(false);
		} else if(selection == 2) {
			thiefD.setVisible(false);
			minotaurD.setVisible(false);
		} else {
			thiefD.setVisible(false);
			vikingD.setVisible(false);
		}
	}
	
	/** Method to hide the labels in the environment selection panel. Keeps the labels for the environment that the player has selected.
		@param selection is the indicator on which labels to hide
	*/
	public void envShiftDisplays(int selection) {
		if(selection == 1) {
			swampE.setVisible(false);
			colosseumE.setVisible(false);
		} else if(selection == 2) {
			arenaE.setVisible(false);
			colosseumE.setVisible(false);
		} else {
			arenaE.setVisible(false);
			swampE.setVisible(false);
		}
	}
}