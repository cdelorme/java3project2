/**
 *
 * Connection object could be used to separate the connection from the Client GUI
 *
 * @author Casey DeLorme
 * @version 04-26-2012
 *
 */


// Imports
import java.net.*;
import java.io.*;
import javax.swing.*;


public class ClientConnection implements Runnable {


	/* Static */


	/* Properties */

	private String username;
	private Socket s;
	// Input
	// Output


	/* Constructors */

	public ClientConnection() {

		// Prepare the socket
		setSocket(new Socket());

/*
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
/**/

	}


	/* Custom Methods */

	public boolean connectToServer(String aUsername, String anAddress, int aPort) {

		boolean ret = true;

		try {

			getSocket().connect(new InetSocketAddress(anAddress, aPort), 10000);

			//SocketAddress sockaddr = new InetSocketAddress(host, port);

			//Socket sock = new Socket();
			//sock.connect(sockaddr, 2000);

			// Try setting the connection

			// Establish IO Components


			// Try setting the Username



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

		// Establish IO

		// Get an untaken Username

		// Loop Listener
		while (s.isConnected()) {

			// Loop until connection has been closed
			// This should be accepting input and passing it to the Receiver


		}

		// If Socket Closes End Application?
		// May modify, on disconnect reload the ClientConnectionGUI to access again?


	}


	/* Mutators */

	private void setSocket(Socket aSocket) {
		s = aSocket;
	}

	private void setUsername(String aUsername) {
		username = aUsername;
	}


	/* Accessors */

	private Socket getSocket() {
		return s;
	}

	private String getUsername() {
		return username;
	}


}