/**
 *
 *
 */


// Imports
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameFactory extends JTabbedPane {


	/* Static */


	/* Properties */

	private ClientConnection cc;
	private SystemMediator sm;
	private ArrayList<Game> games;


	/* Constructors */

	public GameFactory(ClientConnection aCC, SystemMediator aSM) {

		// Prepare Games Arraylist
 		setGames(new ArrayList<Game>());

		// Set ClientConnection
		setCC(aCC);

		// Set SystemMediator
		setSM(aSM);

		// Initialize
		init();

	}


	/* Custom Methods */

	private void init() {

		// Set a preferred size
		//setPreferredSize(new Dimension(480, 680));
/*
		// Test Creation of a new Game
		getGames().add(new Memory(1));

		// Append that game to a new tab!
		addTab("User (1)", (JPanel) getGames().get(0));
/* Test Successful */

		// Can Set Visibility depending on number of games
		// In this case, the number of tabs, a function can be added and called here
		// as well as whenever a game is "completed" hence ended to update display!

		// For testing purposes I want to load One copy of Memory to check display

		// Test - Worked
		//setVisible(false);

		// Adjust Visibility
		checkGames();

	}

	private void checkGames() {

		// If Games Exist
		if (getGames().size() > 0) {

			// Display True
			setVisible(true);

		} else {

			// Display False
			setVisible(false);

		}

		// Resize SystemGUI
		getSM().resizeMe();

	}


	/* Mutators */

	private void setCC(ClientConnection aCC) {
		cc = aCC;
	}

	private void setGames(ArrayList<Game> theGames) {
		games = theGames;
	}

	private void setSM(SystemMediator aSM) {
		sm = aSM;
	}


	/* Accessors */

	private ClientConnection getCC() {
		return cc;
	}

	private ArrayList<Game> getGames() {
		return games;
	}

	private SystemMediator getSM() {
		return sm;
	}


}