/**
 *
 * Connection object could be used to separate the connection from the Client GUI
 *
 * @author Casey DeLorme
 * @version 04-16-2012
 *
 */


// Imports
import java.net.*;
import java.io.*;


public class ClientConnection implements Runnable {


	/* Static */


	/* Properties */

	private Socket s;
	// Input
	// Output


	/* Constructors */

	public ClientConnection(String anAddress, int aPort) {

		// Establish a connection
		try {

			s = new Socket(anAddress, aPort);

		} catch (UnknownHostException uhe) {

			System.out.println("Host not Found, exiting now.");
			System.exit(0);

		} catch (IOException ioe) {

			System.out.println("IO Exception Occured, application will not close.");
			System.exit(0);

		}

		// Request Username until Valid

	}


	/* Custom Methods */

	public void run() {

		// Establish IO

		// Get an untaken Username

		// Loop Listener
		while (s.isConnected()) {
			// Loop until connection has been closed
		}

		// If Socket Closes End Application

	}

	private void setUserName() {

	}


	/* Mutators */


	/* Accessors */


}