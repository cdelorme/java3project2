/**
 *
 * Connection object could be used to separate the connection from the Client GUI
 *
 * @author Casey DeLorme
 * @version 05-03-2012
 *
 */


// Imports
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class ClientConnection implements Runnable {


	/* Static */


	/* Properties */

	private Socket s;
	private String userName;
	private String address;
	private int port;
	private ObjectInputStream in;
	private ObjectOutputStream out;


	/* Constructors */

	public ClientConnection(String aName, String anAddress, int aPort) {

		// Prepare the socket
		setSocket(new Socket());

		// Set Username, Address & Port
		setUserName(aName);
		setAddress(anAddress);
		setPort(aPort);

	}


	/* Custom Methods */

	//public boolean connectToServer(String anAddress, int aPort) {
	public boolean connectToServer() {

		boolean ret = true;

		// Attempt Connection
		try {

			// Attempt Connection with a 10 second time-out
			getSocket().connect(new InetSocketAddress(getAddress(), getPort()), 10000);

			// Prepare IO
			try {

				// Establish IO
				out = new ObjectOutputStream(getSocket().getOutputStream());
				in = new ObjectInputStream(getSocket().getInputStream());

			} catch (IOException iioe) {

				// IO Streams failed to open, have to close connection, and return failed
				JOptionPane.showMessageDialog(null, "Failed to open IO to the socket, will now close this connection.", "IO Exception", JOptionPane.ERROR_MESSAGE);
				ret = false;

				try {

					getSocket().close();

				} catch (IOException cioe) {}// Probably need to use System.exit(0) if close fails

			}

			// Awesome, if everything worked thus far, we can try setting the username!
			Hashtable<String, String> tmp = new Hashtable<String, String>();
			tmp.put("SYSTEM", "USER");
			tmp.put("COMMAND", "SETNAME");
			tmp.put("USERNAME", getUserName());

			// Attempt to send this command
			try {

				// Send!
				out.writeObject(tmp);
				out.flush();

			} catch (IOException ioe) {

				// Failed to send message, non-fatal but maybe important
				JOptionPane.showMessageDialog(null, "Unable to send message to server.", "Write Failed", JOptionPane.ERROR_MESSAGE);
				ret = false;

			}

			// On Success callback to Client
			new Thread(this).start();

		} catch (UnknownHostException uhe) {

			JOptionPane.showMessageDialog(null, "Host not Found.", "Connection Failed", JOptionPane.ERROR_MESSAGE);
			ret = false;

		} catch (IOException ioe) {

			JOptionPane.showMessageDialog(null, "IO Exception Occurred.", "Connection Failed", JOptionPane.ERROR_MESSAGE);
			ret = false;

		}

		return ret;

	}

	public void run() {

		// Loop Listener
		while (!getSocket().isClosed()) {

			// Listen for Command


			// Loop until connection has been closed
			// This should be accepting input and passing it to the Receiver
			// Try/Catch closes socket if error is fatal


		}

		// Approach #1 was to create another mediator, which "might" have cleaned things up a bit
		// However, this approach had its own flaws
		// Since the other components of the system have to react to a lost connection
		// We shift responsibility to those classes
		// If a connection is dropped, they will encounter an error, and tell the Client to reset


	}

	public void sendCommand(Object aCommand) {

		// Attempt to send command using out
		try {

			// Send!
			out.writeObject(aCommand);
			out.flush();

		} catch (IOException ioe) {

			// Failed to send message, non-fatal but maybe important
			JOptionPane.showMessageDialog(null, "Unable to send message to server.", "Write Failed", JOptionPane.ERROR_MESSAGE);

		}

	}


	/* Mutators */

	private void setSocket(Socket aSocket) {
		s = aSocket;
	}

	private void setAddress(String anAddress) {
		address = anAddress;
	}

	private void setPort(int aPort) {
		port = aPort;
	}

	private void setUserName(String aName) {
		userName = aName;
	}


	/* Accessors */

	private Socket getSocket() {
		return s;
	}

	private String getAddress() {
		return address;
	}

	private int getPort() {
		return port;
	}

	private String getUserName() {
		return userName;
	}


}