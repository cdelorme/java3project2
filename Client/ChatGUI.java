/**
 *
 * Chat GUI Display System
 *
 * @author Casey DeLorme
 * @version 05-11-2012
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
	private ClientGUIMediator cgm;
	private JTextField chatMessage;
	private JTextArea chatBox;
	private JList userList;
	private JButton challenge;


	/* Constructors */

	public ChatGUI(ClientGUIMediator aCGM, ClientConnection aCC) {

		// Set ClientGUIMediator
		setCGM(aCGM);

		// Assign the ClientConnection
		setCC(aCC);

		// Initialize Display
		init();

	}


	/* Custom Methods */

	private void init() {

		// Create Elements required for Chat System

		/*
		 * Five Elements:
		 * Scrollable Text Area
		 * Input JTextField
		 * Submit Button
		 * User JList
		 * Challenge JButton
		 *
		 * Implementation of functionality should be fairly easy
		 *
		 * Still missing the JList for Users
		 * and the Challenge JButton
		 *
		 * Going to put them on the left hand side, so EAST?
		 * Gotta make a JPanel and add them to it
		 *
		 *
		 */

		// Set Layout
		setLayout(new BorderLayout());

		// Prepare Properties
		chatMessage = new JTextField(16);
		chatBox = new JTextArea(12, 40);
		chatBox.setEditable(false);
		userList = new JList();

		// Create Chat System Elements
		JScrollPane chatScroll = new JScrollPane(chatBox);
		JButton submit = new JButton(">");
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

				// Check userList for selected
				// Disable Enabled
				// Deselect User
				// Create Hashtable Command
				// sendCommand

			}
		});

		// Append Elements to Self
		add(chatScroll, BorderLayout.CENTER);
		add(east, BorderLayout.EAST);
		add(south, BorderLayout.SOUTH);

	}

	public void sendMessage() {

		// If Message is not empty
		if (chatMessage.getText().length() > 0) {

			// Create a Hashtable Command
			Hashtable<String, String> aCommand = new Hashtable<String, String>();

			// Set the Command Information
			aCommand.put("SYSTEM", "CHAT");
			aCommand.put("COMMAND", "SEND");
			aCommand.put("MESSAGE", chatMessage.getText());

			// Send the Command to the Server
			getCC().sendCommand(aCommand);

		}

	}


	/* Mutators */

	private void setCC(ClientConnection aCC) {
		cc = aCC;
	}

	private void setCGM(ClientGUIMediator aCGM) {
		cgm = aCGM;
	}


	/* Accessors */

	private ClientConnection getCC() {
		return cc;
	}

	private ClientGUIMediator getCGM() {
		return cgm;
	}


}