/**
 *
 * Chat Server CLI System
 *
 * @author Casey DeLorme
 * @version 05-14-2012
 * @course 219-03
 * @group Group 4
 *
 */


// Imports
import java.net.Socket;
import java.util.Hashtable;
import java.util.ArrayList;


public class ChatSystem implements Interpreter {


	/* Static */

	private static String SYSTEM = "CHAT";


	/* Properties */

	private UserManager um;


	/* Constructors */

	public ChatSystem(UserManager aUM) {

		// Set User Manager
		setUM(aUM);

	}

	/* Custom Methods */

	public void interpret(Hashtable aCommand, User aUser) {

		// Obtain Command
		String command = (String) aCommand.get("COMMAND");

		// Check/Identify Command
		if (command.equals("SETNAME")) {

			// Get Name
			String aUserName = (String) aCommand.get("USERNAME");

			// Attempt to add the User
			addUser(aUserName, aUser);

		} else if (command.equals("REMOVE")) {

			// Remove user from arraylist
			removeUser(aUser);

		} else if (command.equals("MESSAGE")) {

			// Get Message
			String aMessage = (String) aCommand.get("MESSAGE");

			// Distribute message to all users
			sendMessage(aUser.getUserName() + ": " + aMessage);

		} else if (command.equals("CHALLENGE")) {

			// Get Challenger Username
			String aChallenger = (String) aCommand.get("CHALLENGER");
			String aRecipient = (String) aCommand.get("RECIPIENT");

			// Issue challenge to another user on the network
			sendChallenge(aChallenger, aRecipient);

		}

	}

	private void addUser(String aUserName, User aUser) {

		// Get Users
		ArrayList<User> list = getUM().getUsers();

		// For Loop to compare names
		for (User u : list) {

			if (u.getUserName().equals(aUserName)) {

				// Print Server Error
				System.out.println("Encountered duplicate User Name, discarding duplicate connection: " + aUserName);

				// Close Socket
				getUM().getUsers().get(getUM().getUsers().indexOf(aUser)).close();

				// Match Found, close connection in rejection
				removeUser(aUser);

				// Set aUserName to null
				aUserName = null;

			}

		}

		// if aUserName != null then success
		if (aUserName != null) {

			// Set Username
			aUser.setUserName(aUserName);

			// Prepare Hashtable for use
			Hashtable<String, String> aCommand = new Hashtable<String, String>();

			// Send New User to all Users & send all Users Names to New User
			for (User u : getUM().getUsers()) {

				// Only send usernames to eachother if not the same user
				if (aUser != u) {

					// Prep Command to Send User this Users name
					aCommand.put("SYSTEM", "CHAT");
					aCommand.put("COMMAND", "ADDUSER");
					aCommand.put("USERNAME", u.getUserName());

					// Send Command
					aUser.sendCommand(aCommand);

					// Null & rebuild Hashtable
					aCommand = null;
					aCommand = new Hashtable<String, String>();

					// Prep command for this user
					aCommand.put("SYSTEM", "CHAT");
					aCommand.put("COMMAND", "ADDUSER");
					aCommand.put("USERNAME", aUserName);

					// Send to this User
					u.sendCommand(aCommand);

				}

			}

		}

	}

	private void removeUser(User aUser) {

		// Create a Command to send removeUser to all connected clients
		Hashtable<String, String> aCommand = new Hashtable<String, String>();
		aCommand.put("SYSTEM", "CHAT");
		aCommand.put("COMMAND", "REMOVEUSER");
		aCommand.put("USERNAME", aUser.getUserName());

		// For loop to send command to all users
		for (User u : getUM().getUsers()) {

			// If user is not itself
			if (aUser != u) {

				// Send Remove User Command
				u.sendCommand(aCommand);

			}

		}

		// Remove User Object from List
		getUM().getUsers().remove(aUser);

	}

	private void sendMessage(String aMessage) {

		// Create Command to Send Message
		Hashtable<String, String> aCommand = new Hashtable<String, String>();
		aCommand.put("SYSTEM", "CHAT");
		aCommand.put("COMMAND", "MESSAGE");
		aCommand.put("MESSAGE", aMessage);

		// For loop to send command to all users
		for (User u : getUM().getUsers()) {
			u.sendCommand(aCommand);
		}

	}

	private void sendChallenge(String aChallenger, String aRecipient) {

		// Create Hashtable Command for Challenge
		Hashtable<String, String> aCommand = new Hashtable<String, String>();
		aCommand.put("SYSTEM", "CHAT");
		aCommand.put("COMMAND", "CHALLENGE");
		aCommand.put("CHALLENGER", aChallenger);

		// Identify Recipient
		for (User u : getUM().getUsers()) {

			// If Match Found
			if (u.getUserName().equals(aRecipient)) {

				// Issue Challenge
				u.sendCommand(aCommand);

			}

		}

	}

	private void sendPrivateMessage(String aMessage, String aUserName) {

		// Probably not enough time to implement this...

	}


	/* Mutators */

	private void setUM(UserManager aUM) {
		um = aUM;
	}


	/* Accessors */

	public String getSystem() {
		return this.SYSTEM;
	}

	public UserManager getUM() {
		return um;
	}


}