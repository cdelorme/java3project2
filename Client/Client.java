/**
 *
 * Client Loads Required Objects to Establish Connection
 *
 * @author Casey DeLorme
 * @version 05-11-2012
 *
 */


// Imports
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Client implements ClientGUIMediator {


	/* Static */

	public static void main(String[] args) {

		// Start Self
		new Client();

	}


	/* Properties */

	private ConnectionGUI cg;
	private ConnectionAdapter ca;
	private ClientConnection cc;
	private SystemGUI sg;


	/* Constructors */

	public Client() {

		// Begin!
		init();

	}


	/* Custom Methods */

	private void init() {

		// Load Connection GUI
		setCG(new ConnectionGUI());

		// Load Connection Adapter
		setCA(new ConnectionAdapter(this));

	}

	public void connectToServer(String aName, String anAddress, int aPort) {

		// Try connection
		setCC(new ClientConnection(aName, anAddress, aPort));

		// Thread the connection attempt
		new Thread(new Runnable() {

			public void run() {

				// Call Connection Attempt
				if (getCC().connectToServer()) {

					// Successful Connection!!!

					// Close ConnectionGUI
					getCA().closeGUI();

					// Null Adapter
					setCA(null);

					// Begin Chat Client
					loadGUI();

				} else {

					// Connection Failed, error messages should have occurred elsewhere
					// Re-enable GUI Button
					getCA().failedLogin();

				}

			}

		}).start();

	}

	private void loadGUI() {

		// Load SystemGUI
		setSG(new SystemGUI(this, getCC()));

	}

	public void reset() {

		// Close Connection
		getCC().close();

		// Unset All Objects
		setCC(null);
		setSG(null);

		// Call init()
		init();

	}


	/* Mutators */

	private void setCA(ConnectionAdapter aCA) {
		ca = aCA;
	}

	private void setCG(ConnectionGUI aCG) {
		cg = aCG;
	}

	private void setCC(ClientConnection aCC) {
		cc = aCC;
	}

	private void setSG(SystemGUI aSG) {
		sg = aSG;
	}


	/* Accessors */

	private ConnectionAdapter getCA() {
		return ca;
	}

	public ConnectionGUI getCG() {
		return cg;
	}

	private ClientConnection getCC() {
		return cc;
	}

	private SystemGUI getSG() {
		return sg;
	}


}