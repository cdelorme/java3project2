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

		// Check aCommand to determine action
		String command = (String) aCommand.get("COMMAND");

		if (command.equals("NEWGAME")) {

			// Get Game ID
			int aGameID = Integer.parseInt((String) aCommand.get("GAMEID"));

			// Get Opponent Name
			String anOpponent = (String) aCommand.get("OPPONENT");

			// Create new Game Instance
			getGF().newGame(aGameID, anOpponent);

		} else if (command.equals("SOMETHING")) {

			// More DO?

		}

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