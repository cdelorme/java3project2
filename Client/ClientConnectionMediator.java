/**
 *
 * Mediator for ConnectionGUI and Client
 *
 * @author Casey DeLorme
 * @version 04-26-2012
 *
 */


// Imports
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ClientConnectionMediator {


	/* Static */


	/* Properties */

	private Client client;
	private ClientConnectionGUI ccg;


	/* Constructors */

	public ClientConnectionMediator(Client aClient) {

		// Set Client
		setClient(aClient);

		// Request ClientConnectionGUI
		setCCG(getClient().getCCG());

		// Send Self to CCG
		getCCG().setCCM(this);

	}


	/* Custom Methods */

	public void connectToServer(String aUsername, String anAddress, String aPort) {

		// Ask the client to create the connection object
		if (getClient().connectToServer(aUsername, anAddress, Integer.parseInt(aPort))) {

			// Success - dispose of CCG
			getCCG().dispose();// Will this crash because the method hasn't finished yet?
			// Perhaps we hide it here, then start the chat client and have the chat client request to dispose

			// Tell the Client to begin loading the ChatServer
			getClient().startChatClient();

		}

	}



	/* Mutators */

	private void setClient(Client aClient) {
		client = aClient;
	}

	private void setCCG(ClientConnectionGUI aCCG) {
		ccg = aCCG;
	}


	/* Accessors */

	private Client getClient() {
		return client;
	}

	private ClientConnectionGUI getCCG() {
		return ccg;
	}


}