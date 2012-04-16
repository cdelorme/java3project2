/**
 *
 * Client for Game
 *
 * @author Casey DeLorme
 * @version 04-16-2012
 *
 */


// Imports
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Client extends JFrame {


	/* Static */

	public static final String ADDRESS = "10.0.1.20";
	public static final int PORT = 123;
	public static boolean debug = false;

	public static void main(String[] args) {

		// Accept Address
		String address = ADDRESS;

		if (args.length > 0) address = args[0];

		// Accept Port
		int port = PORT;

		if (args.length > 1) {

			try {

				port = Integer.parseInt(args[1]);

			} catch (NumberFormatException nfe) {

				// Exit with Error Message - CONVERT TO JOptionPane!
				System.out.println("Invalid Port Provided, system will now exit.");
				System.exit(0);

			}

		}

		// Set Debug on if Passed for Debug Menu
		if ((args.length > 2) && (args[2].equals("debug"))) debug = true;

		// Start Self
		new Client(address, port);

	}


	/* Properties */

	private ClientConnection c;


	/* Constructors */

	public Client(String anAddress, int aPort) {

		// Load Connection
		c = new ClientConnection(anAddress, aPort);

		// Begin Connection Processing
		new Thread(c).start();

		// Initialize Display
		init();

	}


	/* Custom Methods */

	private void init() {

		// Establish a basic GUI for the Chat Client



	}


	/* Mutators */


	/* Accessors */


}