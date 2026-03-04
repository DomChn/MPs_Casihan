import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DriverFinal2 {
	
	/**  
		A method that instantiates and shows the GUI for the main game
		@param gc is the Controller for the entire game 
	*/
	public static void initGame(GameController gc) {
		GameUI g = new GameUI(gc); // instantiate the selection GUI
		g.setSize(700, 600);
		g.setVisible(true);
		
		gc.setGUI(g); // set the GUI needed in the game controller
		g.roundDisplay(); // display the round info in the game UI
		
		// for the 'X' button, to close the game
		g.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**  
		A method that instantiates and shows the GUI for the selection part of game
		@param gc is the Controller for the entire game 
	*/
	public static void createSelectionGUI(GameController gc) {
		SelectionUI s = new SelectionUI(gc); // instantiate the selection GUI
		s.setSize(800, 600);
		s.setVisible(true); // show the UI to the user
		
		// for the 'X' button, to close the game
		s.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		GameController gc = new GameController();
		
		createSelectionGUI(gc);
		
	}
}