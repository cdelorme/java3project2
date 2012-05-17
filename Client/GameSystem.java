/**
 *
 * Client Game Command Interpreter
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
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

		} else if (command.equals("SHOW")) {

			// Extract Game ID
			int gameID = Integer.parseInt((String) aCommand.get("GAMEID"));

			// Find Matching Game
			for (Game g : getGF().getGames()) {

				if (g.getGameID() == gameID) {

					// Extract TileName & Coordinates
					String anImage = (String) aCommand.get("TILENAME");
					int theX = Integer.parseInt((String) aCommand.get("X"));
					int theY = Integer.parseInt((String) aCommand.get("Y"));

					// Call Game Method
					g.showTile(anImage, theX, theY);

				}

			}

		} else if (command.equals("UPDATE")) {

			// Get GameID
			int gameID = Integer.parseInt((String) aCommand.get("GAMEID"));

			Game tmp = null;

			// Find Matching Game
			for (Game g : getGF().getGames()) {

				// Grab Matching Instance
				if (g.getGameID() == gameID) tmp = g;

			}

			// After the for loop to avoid ConcurrentModificationError
			if (tmp != null) {

				// Remove Tiles
				int theX = Integer.parseInt((String) aCommand.get("X1"));
				int theY = Integer.parseInt((String) aCommand.get("Y1"));
				tmp.removeTile(theX, theY);
				theX = Integer.parseInt((String) aCommand.get("X2"));
				theY = Integer.parseInt((String) aCommand.get("Y2"));
				tmp.removeTile(theX, theY);

				// Update Score
				String aPlayer = (String) aCommand.get("PLAYER");
				tmp.updateScore(aPlayer);

			}

		} else if (command.equals("KILL")) {

			// Request to end game prematurely
			// Either user disconnected OR a menu selection (if implemented)
			int gameID = Integer.parseInt((String) aCommand.get("GAMEID"));

			// Check for matching game
			// To do this, a reverse loop is required
			for (int x = (getGF().getGames().size() - 1); x >= 0 ; x--) {

				if (getGF().getGames().get(x).getGameID() == gameID) {

					// Kill Command
					getGF().killGame(getGF().getGames().get(x));

				}

			}

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