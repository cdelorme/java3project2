/**
 *
 * Client Connection Utility
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


public class ClientConnectionGUI extends JFrame {


	/* Static */


	/* Properties */

	private JTextField userName;
	private JTextField serverIP;
	private JTextField portNumber;
	private ClientConnectionMediator ccm;


	/* Constructors */

	public ClientConnectionGUI() {

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
		JButton login = new JButton("Connect");
		JButton exit = new JButton("Exit");

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

		// Set Handler
		exit.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Exit
					System.exit(0);

				}
			}
		);

		login.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Tell CCM to Attempt a connection and pass it the Username, ServerIP and Port Number
					getCCM().connectToServer(userName.getText(), serverIP.getText(), portNumber.getText());

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


	/* Mutators */

	public void setCCM(ClientConnectionMediator aCCM) {
		ccm = aCCM;
	}


	/* Accessors */

	private ClientConnectionMediator getCCM() {
		return ccm;
	}


}