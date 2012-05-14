/**
 *
 * Chat GUI Display System
 *
 * @author Casey DeLorme
 * @version 05-12-2012
 *
 */


// Imports
import java.util.Hashtable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ChatGUI extends JPanel {


	/* Static */


	/* Properties */

	private ClientConnection cc;
	private JTextField chatMessage;
	private JTextArea chatBox;
	private JList userList;
	private DefaultListModel users;
	private JButton challenge;
	private JButton submit;


	/* Constructors */

	public ChatGUI(ClientConnection aCC) {

		// Assign the ClientConnection
		setCC(aCC);

		// Initialize Display
		init();

	}


	/* Custom Methods */

	private void init() {

		// Set Layout
		setLayout(new BorderLayout());

		// Prepare Properties
		chatMessage = new JTextField(16);
		chatBox = new JTextArea(12, 40);
		chatBox.setEditable(false);
		chatBox.setText("Welcome to Java 219 Chat Game System!");
		users = new DefaultListModel();
		userList = new JList(users);
		submit = new JButton(">");
		submit.setEnabled(false);

		// Create Chat System Elements
		JScrollPane chatScroll = new JScrollPane(chatBox);
		JPanel south = new JPanel();
		challenge = new JButton("Challenge");
		challenge.setEnabled(false);
		JPanel east = new JPanel();

		// Append to South
		south.add(chatMessage);
		south.add(submit);

		// Add to East
		east.setLayout(new BorderLayout());
		east.add(userList, BorderLayout.CENTER);
		east.add(challenge, BorderLayout.SOUTH);

		// Add Event Handlers
		chatMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {

				// If Enter Call Send Message
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage();
				}

			}
			public void keyReleased(KeyEvent ke) {

				// If message field has data
				if (chatMessage.getText().length() >= 1) {

					// Enable Send
					submit.setEnabled(true);

				} else {

					// Disable Send
					submit.setEnabled(false);

				}

			}
		});
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				// Call Send Message
				sendMessage();

			}
		});
		userList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {

				if (userList.getSelectedIndex() != -1) {

					// Enable Challenge Button
					challenge.setEnabled(true);

				} else {

					// Disable Challenge Button
					challenge.setEnabled(false);

				}

			}
		});
		challenge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (userList.getSelectedIndex() != -1) {

					// Disable Button
					challenge.setEnabled(false);

					// Create & Prepare Hashtable Command
					Hashtable<String, String> aCommand = new Hashtable<String, String>();
					aCommand.put("SYSTEM", "CHAT");
					aCommand.put("COMMAND", "CHALLENGE");
					aCommand.put("CHALLENGER", getCC().getUserName());
					aCommand.put("RECIPIENT", (String) userList.getSelectedValue());

					// De-select username from list
					userList.clearSelection();

					// Send Command
					getCC().sendCommand(aCommand);

				}

			}
		});

		// Append Elements to Self
		add(chatScroll, BorderLayout.CENTER);
		add(east, BorderLayout.EAST);
		add(south, BorderLayout.SOUTH);

	}

	public void sendMessage() {

		// If Message is not empty
		if (chatMessage.getText().length() >= 1) {

			// Create a Hashtable Command
			Hashtable<String, String> aCommand = new Hashtable<String, String>();

			// Set the Command Information
			aCommand.put("SYSTEM", "CHAT");
			aCommand.put("COMMAND", "MESSAGE");
			aCommand.put("MESSAGE", chatMessage.getText());

			// Send the Command to the Server
			getCC().sendCommand(aCommand);

			// Clear Text Box
			chatMessage.setText("");

		}

	}

	public void addUser(String aUserName) {

		// Add Username to List
		users.addElement(aUserName);

	}

	public void removeUser(String aUserName) {

		// Removing matching username
		users.removeElement(aUserName);

	}

	public void receiveMessage(String aMessage) {

		// Append message to ChatBox
		chatBox.append("\n" + aMessage);

	}

	public void receiveChallenge(String aChallenger) {

		// Asks the user if they accept the Challenge via JOptionPane
		int choice = JOptionPane.showConfirmDialog(
			null,
			aChallenger + " has challenged you to a game of Memory, do you accept?",
			"Challenge",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.INFORMATION_MESSAGE
		);

		// Validate Replay Choice
		if (choice == JOptionPane.YES_OPTION) {

			// Create Hashtable Command to initiate a game
			Hashtable<String, String> aCommand = new Hashtable<String, String>();
			aCommand.put("SYSTEM", "GAME");
			aCommand.put("COMMAND", "NEWGAME");
			aCommand.put("CHALLENGER", aChallenger);
			aCommand.put("RECIPIENT", getCC().getUserName());

			// Send Command
			getCC().sendCommand(aCommand);

		} else {


			// This system has not yet been integrated, it is an extra if I have time
			// It is not a requirement, but the code is here to make implementation easier later.


			// Create Hashtable Command
			Hashtable<String, String> aCommand = new Hashtable<String, String>();
			aCommand.put("SYSTEM", "MESSAGE");
			aCommand.put("COMMAND", "DECLINE");
			aCommand.put("CHALLENGER", aChallenger);
			aCommand.put("RECIPIENT", getCC().getUserName());

			// Send to Server
			getCC().sendCommand(aCommand);

		}

	}

	public void setChatFocus() {
		chatMessage.requestFocusInWindow();
	}


	/* Mutators */

	private void setCC(ClientConnection aCC) {
		cc = aCC;
	}


	/* Accessors */

	private ClientConnection getCC() {
		return cc;
	}


}