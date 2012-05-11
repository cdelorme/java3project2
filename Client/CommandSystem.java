/**
 *
 * Client CLI System
 *
 * @author Casey DeLorme
 * @version 05-05-2012
 *
 */


// Imports
import java.util.*;


public class CommandSystem implements Commander {


	/* Static */


	/* Properties */

	private ArrayList<Interpreter> i;


	/* Constructors */

	public CommandSystem() {

		// Prepare ArrayList
		i = new ArrayList<Interpreter>();

	}


	/* Custom Methods */

	public void interpret(Object aCommand) {

		// Cast Object to Hashtable
		Hashtable command = null;
		if (aCommand instanceof Hashtable) command = (Hashtable) aCommand;

		// Make sure it has a value
		if (command != null) {

			// Identify System
			String system = (String) command.get("SYSTEM");

			// For each loop of interpreters to compare systems
			for (Interpreter e : getInterpreters()) {

				// If a match is found, send the command
				if (e.getSystem().equals(system)) e.interpret(command);

			}

		}

	}

	public void removeInterpreter(String systemName) {

		// Remove Matching System Name from ArrayList
		for (Interpreter e : getInterpreters()) {

			// If System Name Matches Remove
			if (e.getSystem().equals(systemName)) getInterpreters().remove(e);

		}

	}

	public void addInterpreter(Interpreter anInterpreter) {

		// Add Interpreter to ArrayList
		getInterpreters().add(anInterpreter);

	}



	/* Mutators */

	private void getInterpreters(ArrayList<Interpreter> anI) {
		i = anI;
	}


	/* Accessors */

	private ArrayList<Interpreter> getInterpreters() {
		return i;
	}


}