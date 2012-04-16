/**
 *
 * CLI System, used to identify commands and forward to interpreters
 *
 * @author Casey DeLorme
 * @version 04-16-2012
 *
 */


// Imports
import java.util.*;


public class ServerCommandSystem implements Commander {


	/* Static */


	/* Properties */

	private ArrayList<Interpreter> i;


	/* Constructors */

	public ServerCommandSystem() {

		// Create & Load Interpreters
		i = new ArrayList<Interpreter>();



	}


	/* Custom Methods */

	public void interpret(String aCommand) {

		// Check aCommand against interpreter names and pass remainder to identified recipient
		// If no recipient found, command is an error, output message IF debug mode is on!

	}


	/* Mutators */


	/* Accessors */


}