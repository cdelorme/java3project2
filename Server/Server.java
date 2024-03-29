/**
 *
 * Server Class Listens for Connections, accepts Port Number
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
 *
 */


// Imports
import java.net.*;
import java.io.*;


public class Server implements Runnable {


	/* Static */

	private static final int PORT = 16789;

	public static void main(String[] args) {

		// Set to Default Port
		int port = PORT;

		// Check for Port
		if (args.length > 0) {

			try {

				port = Integer.parseInt(args[0]);

			} catch (NumberFormatException nfe) {

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

			setServerSocket(new ServerSocket(aPort));

			// Spit out local IP
			System.out.println("Server Established on IP " + getServerSocket().getInetAddress().toString());

			// Call init
			init();

		} catch (IOException ioe) {

			// Report failed connection
			System.out.println("Unable to establish connection on port " + aPort + ", system will now exit.");

			// Exit
			System.exit(0);

		}

	}


	/* Custom Methods */

	private void init() {

		// Create User Manager
		um = new UserFactory();

		// Begin Listening for Connections!
		new Thread(this).start();

	}

	public void run() {

		// Output listening port
		System.out.println("Server listening on port #" + getServerSocket().getLocalPort());

		// Listen for Connections indefinitely
		while (!ss.isClosed()) {

			// Attempt to catch new connections & Add users
			try {

				// On accept, pass to addUser method!
				getUserManager().addUser(getServerSocket().accept());

				// System message about each new connection
				System.out.println("New connection established." + getUserManager().getUsers().get(getUserManager().getUsers().size() - 1).getUserName());

			} catch (IOException ioe) {

				// Failed connections do not halt the application
				System.out.println("Failed to accept a connection: " + ioe);

			}

		}

	}


	/* Mutators */

	private void setServerSocket(ServerSocket aServerSocket) {

		ss = aServerSocket;

	}

	public void setUserManager(UserManager aUserManager) {

		um = aUserManager;

	}


	/* Accessors */

	public ServerSocket getServerSocket() {

		return ss;

	}

	public UserManager getUserManager() {

		return um;

	}


}