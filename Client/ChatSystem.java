/**
 *
 * Chat Client System
 *
 * @author Casey DeLorme
 * @version 05-11-2012
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

		if (aCommand.get("COMMAND").equals("")) {
			// Do Something!
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