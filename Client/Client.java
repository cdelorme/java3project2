/**
 *
 * Client Loads Required Objects to Establish Connection
 *
 * @author Casey DeLorme
 * @version 05-05-2012
 *
 */


// Imports
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Client {


	/* Static */

	public static void main(String[] args) {

		// Start Self
		new Client();

	}


	/* Properties */

	private ConnectionGUI cg;
	private ConnectionMediator cm;
	private ClientConnection cc;


	/* Constructors */

	public Client() {

		// Begin!
		init();

	}


	/* Custom Methods */

	private void init() {

		// Load Connection GUI
		setCG(new ConnectionGUI());

		// Load Connection Mediator
		setCM(new ConnectionMediator(this));

	}

	public void connectToServer(String aName, String anAddress, int aPort) {

		// Try connection & Return results to CCM
		setCC(new ClientConnection(aName, anAddress, aPort));

		// Thread the connection attempt
		new Thread(new Runnable() {

			public void run() {

				// Call Connection Attempt
				if (getCC().connectToServer()) {

					// Successful Connection!!!

					// Close ConnectionGUI
					getCM().closeGUI();

					// Null Mediator
					setCM(null);

					// Begin Chat Client
					startChatClient();

				} else {

					// Connection Failed, errors Should have occurred elsewhere
					// Re-enable GUI Button
					getCM().failedLogin();

				}

			}

		}).start();

	}

	private void startChatClient() {

		// Load the Chat Client
		// Verified it reaches this
		System.out.println("Starting Chat Client");

	}


	/* Mutators */

	private void setCM(ConnectionMediator aCM) {
		cm = aCM;
	}

	private void setCG(ConnectionGUI aCG) {
		cg = aCG;
	}

	private void setCC(ClientConnection aCC) {
		cc = aCC;
	}


	/* Accessors */

	private ConnectionMediator getCM() {
		return cm;
	}

	public ConnectionGUI getCG() {
		return cg;
	}

	private ClientConnection getCC() {
		return cc;
	}


}