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

	public void newGame(int aGameID, String anOpponent) {

		/*
		 * Given enough time future revisions would allow
		 * the user to specify the "type" of game and various
		 * other options, which would become parameters.
		 */

		// Create new Memory Instance
		getGames().add(new Memory(aGameID));

		// Create new Tab with appropriate labels
		addTab(anOpponent + " (" + aGameID + ")", (JPanel) getGames().get(getGames().size() - 1));

		// Adjust Visibility
		checkGames();

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