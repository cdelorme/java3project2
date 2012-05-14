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

		// Obtain Command
		String command = (String) aCommand.get("COMMAND");

		// Check/Identify Command
		if (command.equals("NEWGAME")) {

			// Grab User Names
			String challengerName = (String) aCommand.get("CHALLENGER");
			String recipientName = (String) aCommand.get("RECIPIENT");

			// Identify & Set Users
			User aChallenger = null;
			User aRecipient = null;
			for (User u : getUM().getUsers()) {

				if (u.getUserName().equals(challengerName)) aChallenger = u;
				if (u.getUserName().equals(recipientName)) aRecipient = u;

			}

			// If both a Challenger & Recipient were found
			if (aChallenger != null && aRecipient != null) {

				// Send addGame command to GameFactory
				getGF().addGame(aChallenger, aRecipient);

			}

		} else if (command.equals("SOMETHING")) {


		}

	}


	public User getUserByName(String aUserName) {

		User aUser = null;

		// Cycle Users
		for (User u : getUM().getUsers()) {

			// Set Match if Found
			if (u.getUserName().equals(aUserName)) aUser = u;

		}

		return aUser;

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