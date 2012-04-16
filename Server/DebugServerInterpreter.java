/**
 *
 * Debug Interpreter loaded only during debug testing
 *
 * @author Casey DeLorme
 * @version 04-16-2012
 *
 */


// Imports


public class DebugServerInterpreter implements Interpreter {


	/* Static */


	/* Properties */

	private boolean debugMode;


	/* Constructors */

	public DebugServerInterpreter() {

		// Debug Mode is off by default
		debugMode = false;

	}


	/* Custom Methods */

	public void interpret(String aCommand) {

		// Identify command & Perform operation

	}

	private void printError(String aMessage) {

		// print Error if debug mode is on
		if (debugMode) System.out.println(aMessage);

	}

	private void setDebug(boolean debug) {
		debugMode = debug;
	}

	private void shutDown() {

		// Shut Down Server
		System.exit(0);

	}


	/* Mutators */


	/* Accessors */


}