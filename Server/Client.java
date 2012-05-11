/**
 *
 * Server Client object, aka User
 *
 * @author Casey DeLorme
 * @version 05-11-2012
 *
 */


// Imports
import java.util.Hashtable;
import java.net.Socket;
import java.io.*;


public class Client implements User, Runnable {


	/* Static */


	/* Properties */

	private Commander c;
	private Socket s;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String userName;


	/* Constructors */

	public Client(Socket aSocket, Commander aCommander) {

		// Set Socket
		setSocket(aSocket);

		// Set Commander
		setCommander(aCommander);

		// Default Username to ""
		setUserName("");

	}


	/* Custom Methods */

	public void run() {

		try {

			// Establish IO
			out = new ObjectOutputStream(getSocket().getOutputStream());
			in = new ObjectInputStream(getSocket().getInputStream());

		} catch (IOException ioe) {

			// IO Failed to establish, close socket
			System.out.println("Failed to establish IO for Connection: " + getSocket().toString());
			close();

		}

		// Read while socket exists and is open
		while ((getSocket() != null) && !getSocket().isClosed()) {

			// Try Catch for IO flags alive to false
			try {

				// Read Object as Command & Forward to Commander
				getCommander().interpret(in.readObject(), this);

			} catch (ClassNotFoundException cnfe) {

				// Report Invalid Data
				System.out.println("Invalid data provided, will continue listening.");

				// System remains open, read is not failing, socket is still alive

			} catch (IOException ioe) {

				// Report Failed IO
				//System.out.println("Read failed, closing Client Connection: " + getSocket().toString());
				System.out.println("Read failed, closing Client Connection: " + getUserName());

				// Close Socket
				close();

			}

		}

		// Use commander to tell USER system to REMOVE self
		Hashtable<String, String> tmp = new Hashtable<String, String>();
		tmp.put("SYSTEM", "CHAT");
		tmp.put("COMMAND", "REMOVE");
		getCommander().interpret(tmp, this);

		// Null Socket
		setSocket(null);

		// Null Username
		setUserName(null);

	}

	public void close() {

		// Attempt to close socket
		try {
			getSocket().close();
		} catch (IOException ioe) {}

	}

	public void sendCommand(Object aCommand) {

		// Attempt sending a message
		try {

			// Use Output to send client a response
			out.writeObject(aCommand);
			out.flush();

		} catch (IOException ioe) {

			// report failed message send operation
			System.out.println("Failed to send command to: " + getUserName());

			// Close Connection
			close();

		}

	}


	/* Mutators */

	public void setUserName(String aName) {
		userName = aName;
	}

	private void setSocket(Socket aSocket) {
		s = aSocket;
	}

	private void setCommander(Commander aCommander) {
		c = aCommander;
	}


	/* Accessors */

	public String getUserName() {
		return userName;
	}

	private Commander getCommander() {
		return c;
	}

	private Socket getSocket() {
		return s;
	}


}