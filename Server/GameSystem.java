/**
 *
 * Game System Interprets Game Commands!
 *
 * @author Casey DeLorme
 * @version 05-12-2012
 *
 */


// Imports
import java.util.Hashtable;


public class GameSystem implements Interpreter {


	/* Static */

	private static String SYSTEM = "GAME";


	/* Properties */

	private UserManager um;
	private GameFactory gf;


	/* Constructors */

	public GameSystem(UserManager aUM) {

		// Set User Manager
		setUM(aUM);

		// Create GameFactory
		setGF(new GameFactory());

	}


	/* Custom Methods */

	public void interpret(Hashtable aCommand, User aUser) {

		// Interpret!

	}


	/* Mutators */

	private void setUM(UserManager aUM) {
		um = aUM;
	}

	private void setGF(GameFactory aGF) {
		gf = aGF;
	}


	/* Accessors */

	public UserManager getUM() {
		return um;
	}

	public String getSystem() {
		return SYSTEM;
	}

	private GameFactory getGF() {
		return gf;
	}


}