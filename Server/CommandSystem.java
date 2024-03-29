/**
 *
 * CLI System, used to identify commands and forward to interpreters
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
 *
 */


// Imports
import java.util.ArrayList;
import java.util.Hashtable;


public class CommandSystem implements Commander {


	/* Static */


	/* Properties */

	private ArrayList<Interpreter> i;


	/* Constructors */

	public CommandSystem(UserManager aUM) {

		// Prepare Interpreter Array List
		setInterpreters(new ArrayList<Interpreter>());

		// Create/Load Interpreters!

		// Add Chat Interpreter
		getInterpreters().add(new ChatSystem(aUM));

		// Add Game Interpreter
		getInterpreters().add(new GameSystem(aUM));

	}


	/* Custom Methods */

	public void interpret(Object aCommand, User aUser) {

		// Check Command against Interpreter names, and pass to identified recipient

		// Transform parameter to Hashtable
		Hashtable command = null;
		if (aCommand instanceof Hashtable) command = (Hashtable) aCommand;

		// Make sure it has a value
		if (command != null) {

			// Grab system
			String system = (String) command.get("SYSTEM");

			// For each loop of interpreters to compare systems
			for (Interpreter e : getInterpreters()) {

				// If a match is found, send the command
				if (e.getSystem().equals(system)) e.interpret(command, aUser);

			}

		}

	}


	/* Mutators */

	private void setInterpreters(ArrayList<Interpreter> list) {
		i = list;
	}


	/* Accessors */

	private ArrayList<Interpreter> getInterpreters() {
		return i;
	}


}