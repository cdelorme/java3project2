/**
 *
 * Client Loads Required Objects to Establish Connection
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


public class Client {


	/* Static */

	public static void main(String[] args) {

		// Start Self
		new Client();

	}


	/* Properties */

	private ClientConnectionGUI ccg;
	private ClientConnectionMediator ccm;
	private ClientConnection cc;


	/* Constructors */

	public Client() {

		// Begin!
		init();

	}


	/* Custom Methods */

	private void init() {

		// Load Connection GUI
		setCCG(new ClientConnectionGUI());

		// Load Connection Mediator
		setCCM(new ClientConnectionMediator(this));

	}

	public boolean connectToServer(String aUsername, String anAddress, int aPort) {

		boolean ret = true;

		// Try connection & Return results to CCM
		setCC(new ClientConnection());

		if (getCC().connectToServer(aUsername, anAddress, aPort)) {

			// Begin threaded listener on Socket
			new Thread(getCC()).start();

		} else {

			// Failed
			setCC(null);
			ret = false;

		}

		return ret;

	}

	public void startChatClient() {

		// Load the Chat Client



	}


	/* Mutators */

	private void setCCM(ClientConnectionMediator aCCM) {
		ccm = aCCM;
	}

	private void setCCG(ClientConnectionGUI aCCG) {
		ccg = aCCG;
	}

	private void setCC(ClientConnection aCC) {
		cc = aCC;
	}


	/* Accessors */

	private ClientConnectionMediator getCCM() {
		return ccm;
	}

	public ClientConnectionGUI getCCG() {
		return ccg;
	}

	private ClientConnection getCC() {
		return cc;
	}


}