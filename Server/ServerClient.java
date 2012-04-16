/**
 *
 * Server Client object, aka User
 *
 * @author
 * @version
 *
 */


// Imports
import java.io.*;
import java.net.*;


public class ServerClient implements User, Runnable {


	/* Static */


	/* Properties */

	private Socket s;
	private String userName;
	private Commander c;
	// Input
	// Output
	// Commander Reference
	// Other Reference Variables?


	/* Constructors */

	public ServerClient(Socket aSocket, Commander aCommander) {

		// Set Provided Variables
		s = aSocket;
		c = aCommander;

	}


	/* Custom Methods */

	public void run() {

		// Establish Input
		// Establish Output

		// If Fail error message and destroy self

		// Loop for Commands
		// Send Commands to Commander


		// On socket closed (null) free up variables & remove self from user list
		close();

	}

	private void close() {
		// Undo Variables to free up memory
		s = null;
		userName = null;
		// Remove self from arraylist and auto-resize
	}

	public void sendMessage(String aMessage) {
		// Use Output to send client a response
	}


	/* Mutators */


	/* Accessors */


}