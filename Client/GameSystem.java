/**
 *
 * Client Game Command Interpreter
 *
 * @author Casey DeLorme
 * @version 05-05-2012
 *
 */


// Imports
import java.util.Hashtable;


public class GameSystem implements Interpreter {


	/* Static */

	private static String SYSTEM = "GAME";


	/* Properties */

	private GameFactory gf;


	/* Constructors */

	public GameSystem(GameFactory aGF) {

		// Set Game Factory
		setGF(aGF);

	}


	/* Custom Methods */

	public void interpret(Hashtable aCommand) {

		// Do!

	}


	/* Mutators */

	private void setGF(GameFactory aGF) {
		gf = aGF;
	}


	/* Accessors */

	public String getSystem() {
		return SYSTEM;
	}

	private GameFactory getGF() {
		return gf;
	}


}