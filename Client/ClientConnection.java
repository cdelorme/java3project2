/**
 *
 * Connection object could be used to separate the connection from the Client GUI
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
import java.util.*;
import javax.swing.*;


public class ClientConnection implements Runnable {


	/* Static */


	/* Properties */

	private Commander cs;
	private ClientMediator cm;
	private Socket s;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String userName;
	private String address;
	private int port;


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

				// Awesome, if everything worked thus far, we can try setting the username!
				Hashtable<String, String> tmp = new Hashtable<String, String>();
				tmp.put("SYSTEM", "CHAT");
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

				// On Success Thread Listener
				if (ret) new Thread(this).start();

			} catch (IOException iioe) {

				// IO Streams failed to open, have to close connection, and return failed
				JOptionPane.showMessageDialog(null, "Failed to open IO to the socket, will now close this connection.", "IO Exception", JOptionPane.ERROR_MESSAGE);
				ret = false;

				try {

					getSocket().close();

				} catch (IOException cioe) {}// Probably need to use System.exit(0) if close fails

			}

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

			// Ignore Traffic until Commander is setup
			if (getC() != null) {

				// Attempt to receive commands from Server
				try {

					// Parse Object to Commander for Interpretation
					getC().interpret(in.readObject());

				} catch (ClassNotFoundException cnfe) {

					// Data sent was invalid
					// No specific handler available, but not a fatal error either

				} catch (IOException ioe) {

					// If the connection was not already closed
					if (!getSocket().isClosed()) {

						// Inform user receive failed
						JOptionPane.showMessageDialog(null, "Unable to receive message from server.", "Read Failed", JOptionPane.ERROR_MESSAGE);

						// IO Failed, Close Socket
						close();

					}

				}

			}

		}

		/*
		 * I had many ideas for the best approach to handle this
		 * I initially thought to pass it off to the GUI classes but
		 * realized this was a mistake, and re-integrated the closure process
		 * by adding the ClientMediator here
		 */
		if (getCM() != null) getCM().reset();
		setSocket(null);

	}

	public void sendCommand(Object aCommand) {

		// Attempt to send command using out
		try {

			// Send!
			out.writeObject(aCommand);
			out.flush();

		} catch (IOException ioe) {

			// IO Failed, Inform user & Close Socket
			JOptionPane.showMessageDialog(null, "Unable to send message to server.", "Write Failed", JOptionPane.ERROR_MESSAGE);
			close();

		}

	}

	public void close() {

			// Attempt to Close Socket
			try {

				getSocket().close();

			} catch (IOException ioe) {}

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

	public void setC(Commander aC) {
		cs = aC;
	}

	public void setCM(ClientMediator aCM) {
		cm = aCM;
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

	public String getUserName() {
		return userName;
	}

	private Commander getC() {
		return cs;
	}

	private ClientMediator getCM() {
		return cm;
	}


}