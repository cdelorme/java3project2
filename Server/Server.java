/**
 *
 * Server Class Listens for Connections, accepts Port Number
 *
 * @author Casey DeLorme
 * @version 04-16-2012
 *
 */


// Imports
import java.net.*;
import java.io.*;


public class Server implements Runnable {


	/* Static */

	private static final int PORT = 123;

	public static void main(String[] args) {

		// Set to Default Port
		int port = PORT;

		// Check for Port
		if (args.length > 0) {

			try {

				port = Integer.parseInt(args[0]);

			} catch (NumberFormatException nfe) {

				// Close on invalid port
				// invalid syntax to launch would be an uncommon mistake
				// It means either the user accidentally added extra characters OR
				// They meant to specify the port and had a typo
				// We want to make sure they don't end up with a process they need to kill
				System.out.println("Invalid port number provided, system will now shut down");
				System.exit(0);

			}

		}

		// Create Self to start Server
		new Server(port);

	}


	/* Properties */

	private ServerSocket ss;
	private UserManager um;


	/* Constructors */

	public Server(int aPort) {

		// Create Connection
		try {

			ss = new ServerSocket(aPort);

		} catch (IOException ioe) {

			// Report Error & Exit System (no ServerSocket == no Connections)
			System.out.println("Unable to establish connection on port " + aPort + ", system will not exit.");
			System.exit(0);

		}

		// Call init
		init();

	}


	/* Custom Methods */

	private void init() {

		// Create User Manager
		um = new UserFactory();

		// Begin Listening for Connections!
		new Thread(this).start();

	}

	public void run() {

		// Listen for Connections indefinitely
		while (!ss.isClosed()) {

			// Attempt to catch new connections & Add users
			try {

				// On accept, pass to addUser method!
				um.addUser(ss.accept());

			} catch (IOException ioe) {

				// Failed connections do not halt the application
				System.out.println("Failed to accept a connection: " + ioe);

			}

		}

	}


	/* Mutators */


	/* Accessors */


}