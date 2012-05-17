/**
 *
 * Adapter for ConnectionGUI and Client
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
 *
 */


// Imports
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ConnectionAdapter {


	/* Static */


	/* Properties */

	private Client client;
	private ConnectionGUI cg;


	/* Constructors */

	public ConnectionAdapter(Client aClient) {

		// Set Client
		setClient(aClient);

		// Request ConnectionGUI
		setCG(getClient().getCG());

		// Send Self to CCG
		getCG().setCA(this);

	}


	/* Custom Methods */

	public void connectToServer(String aName, String anAddress, String aPort) {

		// Ask the client to attempt connection
		getClient().connectToServer(aName, anAddress, Integer.parseInt(aPort));

	}

	public void closeGUI() {

		// Hide CCG
		getCG().setVisible(false);

		// Null reference to GUI
		setCG(null);

	}

	public void failedLogin() {

		// Re-enable connection button
		getCG().failedLogin();

	}


	/* Mutators */

	private void setClient(Client aClient) {
		client = aClient;
	}

	private void setCG(ConnectionGUI aCG) {
		cg = aCG;
	}


	/* Accessors */

	private Client getClient() {
		return client;
	}

	private ConnectionGUI getCG() {
		return cg;
	}


}