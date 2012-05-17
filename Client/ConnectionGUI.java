/**
 *
 * Client Connection Utility
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
import javax.swing.event.*;


public class ConnectionGUI extends JFrame {


	/* Static */


	/* Properties */

	private ConnectionAdapter ca;
	private JTextField userName;
	private JTextField serverIP;
	private JTextField portNumber;
	private JButton login;


	/* Constructors */

	public ConnectionGUI() {

		// Initialize GUI
		init();

	}


	/* Custom Methods */

	public void init() {

		// Prepare Window
		setTitle("Connect to Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4,2));

		// Grid stores Labels and Text Inputs for
		// Username, Server IP, and Port Number
		userName = new JTextField(15);
		serverIP = new JTextField(15);
		portNumber = new JTextField(15);
		login = new JButton("Connect");
		JButton exit = new JButton("Exit");

		// Disable Connect by Default
		login.setEnabled(false);

		// Add Mnemonics
		login.setMnemonic('C');
		exit.setMnemonic('X');

		// Set port number to default port
		portNumber.setText("16789");

		add(new JLabel("Username: "));
		add(userName);
		add(new JLabel("Server IP: "));
		add(serverIP);
		add(new JLabel("Port Number: "));
		add(portNumber);
		add(login);
		add(exit);

		// Set Event Handlers
		login.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Call Connection Method
					tryConnection();

				}
			}
		);
		userName.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {

				// If Enter Attempt Connection
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					tryConnection();
				}

			}
			public void keyReleased(KeyEvent ke) {

				// Login Button Adjustment
				checkLogin();

			}
		});
		serverIP.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {

				// If Enter Attempt Connection
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					tryConnection();
				}

			}
			public void keyReleased(KeyEvent ke) {

				// Login Button Adjustment
				checkLogin();

			}
		});
		portNumber.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {

				// If Enter Attempt Connection
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					tryConnection();
				}

			}
			public void keyReleased(KeyEvent ke) {

				// Login Button Adjustment
				checkLogin();

			}
		});
		exit.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Exit
					System.exit(0);

				}
			}
		);

		// Size, Position & Display
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocation((dim.width - (getWidth())) / 2, (dim.height - (getHeight())) / 2);
		setVisible(true);

	}

	private void checkLogin() {

		// If all three inputs are populated
		if (userName.getText().length() >= 1 && serverIP.getText().length() >= 1 && portNumber.getText().length() >= 1) {

			// Enable login
			login.setEnabled(true);

		} else {

			// Disable Login
			login.setEnabled(false);

		}

	}

	private void tryConnection() {

		// Confirm a username entered
		if (!userName.getText().equals("")) {

			// Disable Button
			login.setEnabled(false);

			// Ask to Try Connection
			getCA().connectToServer(userName.getText(), serverIP.getText(), portNumber.getText());

		}

	}

	public void failedLogin() {

		// Re-Enable login button
		login.setEnabled(true);

	}


	/* Mutators */

	public void setCA(ConnectionAdapter aCA) {
		ca = aCA;
	}


	/* Accessors */

	private ConnectionAdapter getCA() {
		return ca;
	}


}