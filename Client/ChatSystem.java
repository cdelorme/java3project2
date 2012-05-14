/**
 *
 * Chat Client System
 *
 * @author Casey DeLorme
 * @version 05-12-2012
 *
 */


// Imports
import java.util.Hashtable;


public class ChatSystem implements Interpreter {


	/* Static */

	private static String SYSTEM = "CHAT";


	/* Properties */

	private ChatGUI cg;


	/* Constructors */

	public ChatSystem(ChatGUI aCG) {

		// Assign ChatGUI
		setCG(aCG);

	}


	/* Custom Methods */

	public void interpret(Hashtable aCommand) {

		// Check aCommand to determine action
		String command = (String) aCommand.get("COMMAND");

		if (command.equals("ADDUSER")) {

			// Get Username
			String aUserName = (String) aCommand.get("USERNAME");

			// Add User
			getCG().addUser(aUserName);

		} else if (command.equals("REMOVEUSER")) {

			// Get Username
			String aUserName = (String) aCommand.get("USERNAME");

			// Remove User
			getCG().removeUser(aUserName);

		} else if (command.equals("MESSAGE")) {

			// Get Message
			String aMessage = (String) aCommand.get("MESSAGE");

			// Append that Message
			getCG().receiveMessage(aMessage);

		} else if (command.equals("CHALLENGE")) {

			// Get Challenger
			String aChallenger = (String) aCommand.get("CHALLENGER");

			// Submit Challenge
			getCG().receiveChallenge(aChallenger);

		}

	}


	/* Mutators */

	private void setCG(ChatGUI aCG) {
		cg = aCG;
	}


	/* Accessors */

	public String getSystem() {
		return SYSTEM;
	}

	private ChatGUI getCG() {
		return cg;
	}


}